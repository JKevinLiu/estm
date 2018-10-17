package com.yucheng.estm.utils;

import com.deepoove.poi.XWPFTemplate;
import com.yucheng.estm.constants.CommonContant;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    private static Logger log = Logger.getLogger(FileUtil.class);

    private static final int  BUFFER_SIZE = 2 * 1024;

    /**
     * 根据模板生成word  doc文件2003
     * @param fileRealPath
     * @param templateFile
     * @param properties
     */
    public static void createDocByTemplate(String fileRealPath, File templateFile, Map<String,String> properties){

        try( FileInputStream fis = new FileInputStream(templateFile);
             ByteArrayOutputStream ostream = new ByteArrayOutputStream();
             OutputStream outs = new FileOutputStream(fileRealPath);) {

            HWPFDocument doc = new HWPFDocument(fis);
            Range bodyRange = doc.getRange();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                bodyRange.replaceText("{{" + entry.getKey() + "}}", entry.getValue());
            }
            doc.write(ostream);
            outs.write(ostream.toByteArray());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("生成文件失败！");
        }

        log.debug("生成文件" + fileRealPath + " 成功！");
    }

    /**
     * 根据模板生成word  docx文件2007
     * @param fileRealPath
     * @param templateFile
     * @param properties
     */
    public static void createDocxByTemplate(String fileRealPath, File templateFile, Map<String,String> properties){
        try {
            OPCPackage pack = POIXMLDocument.openPackage(templateFile.getPath());
            XWPFDocument doc = new XWPFDocument(pack);

            // 处理段落
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    boolean isSetText = false;
                    for (Map.Entry<String, String> entry : properties.entrySet()) {
                        String key = entry.getKey();
                        if (text.indexOf(key) != -1) {
                            isSetText = true;
                            text = text.replace("{{" + entry.getKey() + "}}",entry.getValue());
                        }
                    }
                    if (isSetText) {
                        run.setText(text, 0);
                    }
                }
            }

            // 处理表格
            Iterator<XWPFTable> it = doc.getTablesIterator();
            while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
                        for (XWPFParagraph paragraph : paragraphListTable) {
                            List<XWPFRun> runs = paragraph.getRuns();
                            for (XWPFRun run : runs) {
                                String text = run.getText(0);
                                boolean isSetText = false;
                                for (Map.Entry<String, String> entry : properties.entrySet()) {
                                    String key = entry.getKey();
                                    if (text.indexOf(key) != -1) {
                                        isSetText = true;
                                        text = text.replace("{{" + entry.getKey() + "}}",entry.getValue());
                                    }
                                }
                                if (isSetText) {
                                    run.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream(fileRealPath);
            doc.write(fos);
            fos.flush();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("生成文件失败！");
        }finally {
            //关闭流
        }

        log.info("生成文件" + fileRealPath + " 成功！");
    }


    /**
     * 压缩成ZIP
     * @param srcDir 压缩文件夹路径
     * @param out    压缩文件输出流
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩成ZIP
     * @param srcFiles 需要压缩的文件列表
     * @param out           压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            //不打包生成html文件
            if(sourceFile.getName().endsWith(".html")){
                return;
            }
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }


    // doc转换为html
    public static void docToHtml(String sourceFileName, String targetFileName, final String imagePathStr) throws Exception {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        // 保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType, String name, float width, float height) {
                try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                    out.write(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "image/" + name;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
    }

    // docx转换为html
    public static void docxToHtml(String sourceFileName, String targetFileName, String imagePathStr) throws Exception {
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String basePath = "audit"+ File.separator + "249520181013123030" + File.separator;

        //不动产申请书
        String reqCertTemplatePath = CommonContant.reqQertWordTemplatePath;
        String reqCertRealPath = basePath + "reqcert.doc";
        Map<String, String> reqCertWord = new HashMap<>();
        reqCertWord.put("attr1","你好");
        String reqCertHtmlRealPath = basePath + "reqcert.html";
        String reqCertImagePath = basePath + File.separator + "image_reqcert" + File.separator;

        //生成不动产申请书doc
        File reqCertTemplate = new File("E:\\workspaces\\estm\\src\\main\\resources\\templates\\word\\reqcert.doc");
        FileUtil.createDocByTemplate(reqCertRealPath, reqCertTemplate, reqCertWord);

        //生成不动产申请书html
        FileUtil.docToHtml(reqCertRealPath, reqCertHtmlRealPath, reqCertImagePath);
    }
}