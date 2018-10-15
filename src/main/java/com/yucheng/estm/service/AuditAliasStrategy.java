package com.yucheng.estm.service;

import com.yucheng.estm.entity.AliasAudit;
import com.yucheng.estm.entity.AuditItem;

import java.util.List;

public interface AuditAliasStrategy {
    List<AliasAudit> MergeItemForAlias(List<AuditItem> itemList);
}