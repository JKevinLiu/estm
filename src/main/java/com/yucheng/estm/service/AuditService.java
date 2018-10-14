package com.yucheng.estm.service;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;

import java.util.List;
import java.util.Map;

public interface AuditService {
    Audit createAuditOrder(int outUserId, Map<String, String> reqCertWord, Map<String, String> marriageWord,
                           Map<String, String> recogWord, List<DataImage> dataImageList);

    PageInfo<Audit> getAuditByCondtion(int curPage, int pageSize, AuditDto auditDto);

    List<AuditItem> getAuditItemList(int orderId);

    Audit getAuditByOrderId(int orderId);
}
