package com.yucheng.estm.service;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.AliasAudit;
import com.yucheng.estm.entity.Audit;

import java.util.List;
import java.util.Map;

public interface AuditService {
    Audit createAuditOrder(int outUserId, Map<String, String> reqCertWord, Map<String, String> marriageWord,
                           Map<String, String> recogWord, List<DataImage> dataImageList);

    PageInfo<Audit> getAuditByCondtion(int curPage, int pageSize, Audit audit);

    List<AliasAudit> auditAliasInfo(String orderNo);

    Audit getAuditByOrderNo(String orderId);
}