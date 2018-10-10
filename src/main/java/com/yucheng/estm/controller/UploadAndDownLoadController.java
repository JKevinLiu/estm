package com.yucheng.estm.controller;

import com.yucheng.estm.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadAndDownLoadController {
    private static Logger log = Logger.getLogger(UploadAndDownLoadController.class);

    @RequestMapping(value = "downloadall")
    public void test(String orderId, HttpServletResponse response) {

        String basePath = "audit"+ File.separator + orderId + File.separator;

        //1. 将申请表填充后保存
        try {
            String fileRealPath = basePath + "bdcsqb.doc";
            File templateFile = ResourceUtils.getFile("classpath:templates/word/test1.docx");
            Map<String,String> properties = new HashMap<>();
            properties.put("title", "申请书");
            FileUtil.createDocByTemplate(fileRealPath, templateFile,properties);
        } catch (FileNotFoundException e) {
            log.error(e);
            throw new RuntimeException("资料异常");
        }

        //2. 将具结承诺书填充后保存
        try {
            String fileRealPath = basePath + "jjbzs.doc";
            File templateFile = ResourceUtils.getFile("classpath:templates/word/test2.docx");
            Map<String,String> properties = new HashMap<>();
            properties.put("title", "申请书");
            FileUtil.createDocByTemplate(fileRealPath, templateFile,properties);
        } catch (FileNotFoundException e) {
            log.error(e);
            throw new RuntimeException("资料异常");
        }

        //3.压缩文件夹并返回
        try (OutputStream out = response.getOutputStream()){
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=ziliao.zip");

            FileUtil.toZip(basePath, out,false);
            out.flush();
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException("资料异常");
        }
    }
}