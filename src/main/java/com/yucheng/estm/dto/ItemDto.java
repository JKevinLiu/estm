package com.yucheng.estm.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {
    private String fileType;
    private String filePath;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}