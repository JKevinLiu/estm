package com.yucheng.estm.constants;

import java.io.File;

public class CommonContant {
    public static String reqQertWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "reqcert.doc";
    public static String marriageWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "marriage.docx";
    public static String recogWordTemplatePath = "classpath:templates" + File.separator + "word" + File.separator + "recog.docx";

    public static int STATE_WAITAUDIT = 0;   //等待审核
    public static int STATE_AUDITTING = 1;   //审核中
    public static int STATE_AUDITPASS = 2;   //审核通过
    public static int STATE_AUDITBACK = 1;   //审核不通过
}
