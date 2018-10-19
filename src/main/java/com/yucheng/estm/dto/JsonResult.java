package com.yucheng.estm.dto;

import java.io.Serializable;

/**
 * json返回-传输对象
 *
 * @Author liukw 20191019
 */
public class JsonResult implements Serializable {

    private String status;
    private String desc;
    private Object result;

    public JsonResult status(String status) {
        this.status = status;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}


