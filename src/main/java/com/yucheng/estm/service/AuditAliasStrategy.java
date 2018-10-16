package com.yucheng.estm.service;

import com.yucheng.estm.dto.ItemDto;
import com.yucheng.estm.entity.AuditItem;

import java.util.List;

public interface AuditAliasStrategy {
    List<List<ItemDto>> MergeItemForAlias(List<AuditItem> itemList);
}