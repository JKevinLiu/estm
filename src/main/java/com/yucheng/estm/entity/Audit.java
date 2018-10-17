package com.yucheng.estm.entity;

import java.util.Date;

public class Audit {
    private Integer id;

    private String orderNo;

    private Integer busType;

    private OutUser outUser;

    private InnerUser auditUser;

    private Integer state;

    private Date createDate;

    private Date auditDate;

    private String dirPath;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public OutUser getOutUser() {
        return outUser;
    }

    public void setOutUser(OutUser outUser) {
        this.outUser = outUser;
    }

    public InnerUser getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(InnerUser auditUser) {
        this.auditUser = auditUser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath == null ? null : dirPath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}