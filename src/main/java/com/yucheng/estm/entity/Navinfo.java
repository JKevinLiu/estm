package com.yucheng.estm.entity;

import java.io.Serializable;
import java.util.List;

public class Navinfo implements Serializable {
    private String title;
    private List<SecondNavinfo> secondMenuList;


    public List<SecondNavinfo> getSecondMenuList() {
        return secondMenuList;
    }

    public void setSecondMenuList(List<SecondNavinfo> secondMenuList) {
        this.secondMenuList = secondMenuList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}