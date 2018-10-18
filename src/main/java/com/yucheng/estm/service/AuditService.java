package com.yucheng.estm.service;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ImgAlais;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;

import java.util.List;

public interface AuditService {
    PageInfo<AuditDto> getAuditByCondtion(int curPage, int pageSize, Audit audit);

    Audit getAuditByOrderNo(String orderNo);

    String getWordItemResource(String orderNo, int itemType) throws Exception;

    List<ImgAlais> getImgAliasList(String orderNo);

    void commitAudit(String orderNo, boolean isSuccess, String sendDate, String reson);
}
