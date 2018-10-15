package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.AliasAudit;
import com.yucheng.estm.entity.AuditItem;
import com.yucheng.estm.service.AuditAliasStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("common")
public class CommonAuditAliasImpl implements AuditAliasStrategy {
    @Override
    public List<AliasAudit> MergeItemForAlias(List<AuditItem> itemList) {
        System.out.println("i am common !");
        return null;
    }
}