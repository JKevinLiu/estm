package com.yucheng.estm.service;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ItemDto;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;

import java.util.List;
import java.util.Map;

public interface AuditService {
    Audit createAuditOrder(int outUserId, Map<String, String> reqCertWord, Map<String, String> marriageWord,
                           Map<String, String> recogWord, List<DataImage> dataImageList);

    PageInfo<AuditDto> getAuditByCondtion(int curPage, int pageSize, Audit audit);

    List<List<ItemDto>> auditAliasInfo(String orderNo);

    Audit getAuditByOrderNo(String orderId);

    void commitAudit(int auditId, List<AuditItem> auditItemList, boolean isSuccess, String sendDate, String reson);
}