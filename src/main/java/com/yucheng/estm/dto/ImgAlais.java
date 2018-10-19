package com.yucheng.estm.dto;

/**
 * 图片类型-传输对象
 *
 * @Author liukw 20191019
 */
import java.io.Serializable;
import java.util.List;

public class ImgAlais implements Serializable {
    private int type;
    private List<String> imgUriList;

    public ImgAlais(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getImgUriList() {
        return imgUriList;
    }

    public void setImgUriList(List<String> imgUriList) {
        this.imgUriList = imgUriList;
    }
}