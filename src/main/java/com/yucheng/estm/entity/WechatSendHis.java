package com.yucheng.estm.entity;

import java.util.Date;

public class WechatSendHis {
    private Integer id;

    private Integer auditId;

    private OutUser outUser;

    private String cotent;

    private Date createDate;

    private Date sendDate;

    private String templateId;

    private Date finishDate;

    private Integer state;

    private String sendLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public OutUser getOutUser() {
        return outUser;
    }

    public void setOutUser(OutUser outUser) {
        this.outUser = outUser;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent == null ? null : cotent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSendLog() {
        return sendLog;
    }

    public void setSendLog(String sendLog) {
        this.sendLog = sendLog == null ? null : sendLog.trim();
    }
}