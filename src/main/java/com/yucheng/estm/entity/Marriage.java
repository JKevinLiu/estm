package com.yucheng.estm.entity;

public class Marriage implements Word{
    private Integer id;

    private Integer auditItemId;

    private String curName;

    private String cardNo;

    private String proYear;

    private String proMonth;

    private String proDay;

    private Integer marType;

    private String promiseName;

    private String year;

    private String month;

    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuditItemId() {
        return auditItemId;
    }

    public void setAuditItemId(Integer auditItemId) {
        this.auditItemId = auditItemId;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName == null ? null : curName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getProYear() {
        return proYear;
    }

    public void setProYear(String proYear) {
        this.proYear = proYear == null ? null : proYear.trim();
    }

    public String getProMonth() {
        return proMonth;
    }

    public void setProMonth(String proMonth) {
        this.proMonth = proMonth == null ? null : proMonth.trim();
    }

    public String getProDay() {
        return proDay;
    }

    public void setProDay(String proDay) {
        this.proDay = proDay == null ? null : proDay.trim();
    }

    public Integer getMarType() {
        return marType;
    }

    public void setMarType(Integer marType) {
        this.marType = marType;
    }

    public String getPromiseName() {
        return promiseName;
    }

    public void setPromiseName(String promiseName) {
        this.promiseName = promiseName == null ? null : promiseName.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }
}