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
    public final static String STATE_WAITAUDIT_DESC = "待审核";

    public final static int STATE_AUDITTING = 2;   //审核中
    public final static String STATE_AUDITTING_DESC = "审核中";

    public final static int STATE_AUDITPASS = 3;   //审核通过
    public final static String STATE_AUDITPASS_DESC = "审核通过";

    public final static int STATE_AUDITBACK = 4;   //审核不通过
    public final static String STATE_AUDITBACK_DESC = "审核不通过";

    /**
     * item_type，明细类型
     */
    public final static int IT_REQ_CERT = 1;//不动产申请书
    public final static int IT_MARRIAGE = 2;//婚姻状况证明
    public final static int IT_RECOG = 3;//具结保证书
    public final static int IT_CARD = 4;//身份证
    public final static int IT_REG_RESIDENCE = 5;//户口本
    public final static int IT_CONTRACT = 6;//合同

    /**
     * file_type, 明细文件类型
     */
    public final static int FT_WORD = 1;
    public final static String FT_WORD_DESC = "WORD";
    public final static int FT_IMG = 2;
    public final static String FT_IMG_DESC = "IMG";

}