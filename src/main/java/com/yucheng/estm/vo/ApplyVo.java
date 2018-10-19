package com.yucheng.estm.vo;

import com.yucheng.estm.entity.Marriage;
import com.yucheng.estm.entity.Recognizance;
import com.yucheng.estm.entity.ReqCert;

import java.io.Serializable;

public class ApplyVo implements Serializable {

    private Integer outUserId;
    private int busiType;
    private ReqCert reqCert;
    private Marriage marriage;
    private Recognizance recognizance;

    public Integer getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(Integer outUserId) {
        this.outUserId = outUserId;
    }

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

    public ReqCert getReqCert() {
        return reqCert;
    }

    public void setReqCert(ReqCert reqCert) {
        this.reqCert = reqCert;
    }

    public Marriage getMarriage() {
        return marriage;
    }

    public void setMarriage(Marriage marriage) {
        this.marriage = marriage;
    }

    public Recognizance getRecognizance() {
        return recognizance;
    }

    public void setRecognizance(Recognizance recognizance) {
        this.recognizance = recognizance;
    }
}