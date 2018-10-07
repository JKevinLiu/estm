package com.yucheng.estm.entity;

import java.io.Serializable;
import java.util.List;

public class SecondNavinfo implements Serializable {
    private List<ItemNavinfo> itemList;

    public List<ItemNavinfo> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemNavinfo> itemList) {
        this.itemList = itemList;
    }
}