package com.yucheng.estm.constants;

import java.io.File;

public class CommonContant {

    /**
     * 模板文件classpath路径
     */
    public final static String reqQertWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "reqcert.doc";
    public final static String marriageWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "marriage.docx";
    public final static String recogWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "recog.docx";

    /**
     * 审核状态
     */
    public final static int STATE_WAITAUDIT = 1;   //等待审核
    public final static int STATE_AUDITTING = 2;   //审核中
    public final static int STATE_AUDITPASS = 3;   //审核通过
    public final static int STATE_AUDITBACK = 4;   //审核不通过

    /**
     * item_type，明细类型
     */
    public final static int IT_REQ_CERT = 1;
    public final static int IT_MARRIAGE = 2;
    public final static int IT_RECOG = 3;
    public final static int IT_CARD = 4;
    public final static int IT_REG_RESIDENCE = 5;
    public final static int IT_CONTRACT = 6;

    /**
     * file_type, 明细文件类型
     */
    public final static int FT_WORD = 1;
    public final static int FT_IMG = 2;

}