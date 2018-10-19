package com.yucheng.estm.service;

import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.entity.Word;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 审批业务策略接口
 *
 * @Author liukw 20191019
 */

public interface AuditBuisnessStrategy {
    /**
     * 业务类型设置
     * @param busiType 业务类型
     */
    void setBusiType(Integer busiType);

    /**
     * 创建审批单
     * @param outUser 外部用户对象
     * @param orderNo 审核单号
     * @param files 上传文件流
     * @param wordList word对象列表
     * @throws Exception
     */
    void createAuditOrder(OutUser outUser, String orderNo, MultipartFile[] files, List<Word> wordList) throws Exception;
}