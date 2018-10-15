package com.yucheng.estm.entity;

import java.util.List;

public class Catalog {
    private Integer id;

    private Integer parentId;

    private String name;

    private List<Catalog> childList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Catalog> getChildList() {
        return childList;
    }

    public void setChildList(List<Catalog> childList) {
        this.childList = childList;
    }
}