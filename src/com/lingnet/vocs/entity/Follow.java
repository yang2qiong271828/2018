package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "follow")
public class Follow extends BaseEntity implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5019464227885006397L;
    private String status;//阶段  费用
    private String reportId;//关联报备
    private Date bftime;//拜访时间
    private String bfnr;//拜访内容
    private String cyr;//参与人
    private String khcyr;//客户参与人
    private String bz;//备注
    private String bffs;//拜访方式
    private String userId;//创建人
    private String partnerId;//经销商id
    private String threadId;//上传资料
    
    private String projectName;//项目名称
    private String cusComName;//客户名称
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    public Date getBftime() {
        return bftime;
    }

    public void setBftime(Date bftime) {
        this.bftime = bftime;
    }

    public String getBfnr() {
        return bfnr;
    }

    public void setBfnr(String bfnr) {
        this.bfnr = bfnr;
    }

    public String getCyr() {
        return cyr;
    }

    public void setCyr(String cyr) {
        this.cyr = cyr;
    }

    public String getKhcyr() {
        return khcyr;
    }

    public void setKhcyr(String khcyr) {
        this.khcyr = khcyr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBffs() {
        return bffs;
    }

    public void setBffs(String bffs) {
        this.bffs = bffs;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCusComName() {
        return cusComName;
    }

    public void setCusComName(String cusComName) {
        this.cusComName = cusComName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }


   
}
