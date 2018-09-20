package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "GRADE")
public class Grade extends BaseEntity implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 9022921585761080902L;
    
    private int js; //技术 
    private int jfpw; //甲方评委
    private int pfxz;//评分细则
    private int gxnx;//关系内线 
    private int tzys;//投资预算
    private int zz;//资质
    private int alyj;//案例业绩
    private int fs;//分包方案/招标方式
    private int zf;//总分
    private String status;//状态,0、草稿,1、提交,2.审批通过,3.审批不通过
    private String bz;//备注
    private String reportId;//报备id
    private String partnerId;//经销商id
    private String userId;//创建者用户id
    private String yijian;//意见
    private Date shsj;//审核时间
    
    
    public int getJs() {
        return js;
    }
    public void setJs(int js) {
        this.js = js;
    }
    public int getJfpw() {
        return jfpw;
    }
    public void setJfpw(int jfpw) {
        this.jfpw = jfpw;
    }
    public int getPfxz() {
        return pfxz;
    }
    public void setPfxz(int pfxz) {
        this.pfxz = pfxz;
    }
    public int getGxnx() {
        return gxnx;
    }
    public void setGxnx(int gxnx) {
        this.gxnx = gxnx;
    }
    public int getTzys() {
        return tzys;
    }
    public void setTzys(int tzys) {
        this.tzys = tzys;
    }
    public int getZz() {
        return zz;
    }
    public void setZz(int zz) {
        this.zz = zz;
    }
   
    public int getFs() {
        return fs;
    }
    public void setFs(int fs) {
        this.fs = fs;
    }
    public int getZf() {
        return zf;
    }
    public void setZf(int zf) {
        this.zf = zf;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBz() {
        return bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getReportId() {
        return reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getAlyj() {
        return alyj;
    }
    public void setAlyj(int alyj) {
        this.alyj = alyj;
    }
    public String getYijian() {
        return yijian;
    }
    public void setYijian(String yijian) {
        this.yijian = yijian;
    }
    public Date getShsj() {
        return shsj;
    }
    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }
    
} 
