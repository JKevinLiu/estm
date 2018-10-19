package com.yucheng.estm.service;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ImgAlais;
import com.yucheng.estm.entity.Audit;

import java.util.List;

/**
 * 审批相关接口
 *
 * @Author liukw 20191019
 */

public interface AuditService {
    /**
     * 根据条件查询审批列表
     * @param curPage 当前第几页
     * @param pageSize 每页数量
     * @param audit 查询对象封装
     * @return 分页对象列表
     */
    PageInfo<AuditDto> getAuditByCondtion(int curPage, int pageSize, Audit audit);

    /**
     * 根据审批号查询审批对象
     * @param orderNo 审批单号
     * @return
     */
    Audit getAuditByOrderNo(String orderNo);

    /**
     * 根据审批单号和明细类型查找资源URI
     * @param orderNo
     * @param itemType
     * @return
     * @throws Exception
     */
    String getWordItemResource(String orderNo, int itemType) throws Exception;

    /**
     * 根据审批单号查找图片资源URI
     * @param orderNo
     * @return
     */
    List<ImgAlais> getImgAliasList(String orderNo);

    /**
     * 工作人员提交审批条目
     * @param orderNo 审批单号
     * @param isSuccess 是否成功
     * @param sendDate  推送时间
     * @param reson 原因
     */
    void commitAudit(String orderNo, boolean isSuccess, String sendDate, String reson);
}
