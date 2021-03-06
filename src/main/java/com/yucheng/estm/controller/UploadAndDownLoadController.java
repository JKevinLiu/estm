package com.yucheng.estm.controller;

import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.service.AuditService;
import com.yucheng.estm.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传下载控制器
 *
 * @Author liukw 20191019
 */

@Controller
public class UploadAndDownLoadController {
    private static Logger log = Logger.getLogger(UploadAndDownLoadController.class);

    @Autowired
    private AuditService auditService;

    /**
     * 打印word
     */
    @RequestMapping(value = "print/{req_cert}")
    public void printDoc(HttpServletResponse response, String orderId){
        /*String htmlPath ="";
        try (FileInputStream in = new FileInputStream(htmlPath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
             OutputStream outputStream = response.getOutputStream();
        ){
            byte[] b = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(b);
            outputStream.write(b);
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }*/
    }

    /**
     * 下载所有资料
     */
    @RequestMapping(value = "print/download_all")
    public void downloadAll(HttpServletResponse response, String orderNo){
        String basePath = "audit"+ File.separator + orderNo + File.separator;

        Audit audit = auditService.getAuditByOrderNo(orderNo);

        String attachName = audit.getOutUser().getName() + audit.getOutUser().getPhone()+".zip";

        //3.压缩文件夹并返回
        try (OutputStream out = response.getOutputStream()){
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + attachName);

            FileUtil.toZip(basePath, out,false);
            out.flush();
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException("资料异常");
        }
    }
}