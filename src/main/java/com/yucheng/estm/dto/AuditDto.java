package com.yucheng.estm.dto;

import java.io.Serializable;
import java.util.Date;

public class AuditDto implements Serializable{

    private String orderNo;

    private String busType;

    private String phone;

    private String outUserName;

    private Integer state;

    private Date createDate;

    private Date auditDate;



}
