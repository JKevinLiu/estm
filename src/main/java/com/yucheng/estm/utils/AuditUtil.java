package com.yucheng.estm.utils;

import com.yucheng.estm.entity.Audit;

import java.util.ArrayList;
import java.util.List;

public class AuditUtil {

    public static List<Audit> getAuditList() {
        List<Audit> auditList = new ArrayList<>();
        Audit audit1 = new Audit();
        audit1.setName("张三");
        audit1.setPhone("182XXXXXXX");
        audit1.setBusType("房屋>>买卖>>个人");
        audit1.setCommitDate("2018-10-07");
        audit1.setOptions("");

        Audit audit2 = new Audit();
        audit2.setName("李四");
        audit2.setPhone("183XXXXXXX");
        audit2.setBusType("房屋>>买卖>>个人");
        audit2.setCommitDate("2018-10-07");
        audit2.setOptions("");

        auditList.add(audit1);
        auditList.add(audit2);

        return auditList;
    }
}
