package com.yucheng.estm.constants;

import java.io.File;

/**
 * 业务常量
 *
 * @Author liukw 20191019
 */
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

    public final static int checkNum = 15;

    public final static String[] unCheckAppType = new String[]{
            "□土地所有权", "□国有建设用地使用权", "□宅基地使用权",
            "□集体建设用地使用权", "□土地承包经营权", "□林地使用权",
            "□海域使用权", "□无居民海岛使用权", "□房屋所有权 ",
            "□构筑物所有权", "□森林、林木所有权", "□森林、林木使用权 ",
            "□抵押权 ","□地役权 ", "□其他"
    };

    public final static String[] checkedAppType = new String[]{
            "☑土地所有权", "☑国有建设用地使用权", "☑宅基地使用权",
            "☑集体建设用地使用权", "☑土地承包经营权", "☑林地使用权",
            "☑海域使用权", "☑无居民海岛使用权", "☑房屋所有权 ",
            "☑构筑物所有权", "☑森林、林木所有权", "☑森林、林木使用权 ",
            "☑抵押权 ","☑地役权 ", "☑其他"
    };

    public final static String[] unCheckResType = new String[]{
            "□首次登记","□转移登记","□变更登记","□注销登记",
            "□更正登记","□异议登记","□预告登记","□查封登记"," □其他遗失补证"
    };

    public final static String[] checkedResType = new String[]{
            "☑首次登记","☑转移登记","☑变更登记","☑注销登记",
            "☑更正登记","☑异议登记","☑预告登记","☑查封登记","☑其他遗失补证"
    };

    public final static String[] unCheckFormType = new String[]{
            "□单一版","□集成版"
    };

    public final static String[] checkedFormType = new String[]{
            "☑单一版","☑集成版"
    };

    public final static String[] unCheckCertType = new String[]{
            "□是","□否"
    };

    public final static String[] checkedCertType = new String[]{
            "☑是","☑否"
    };

}