package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "hkpact")
public class Hkpact extends BaseEntity implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5019464227885006397L;
    private Date hkDate;//回款时间
    private Date fpDate;//发票时间
    private Double ssje;//实收金额
    private Double fpje;//发票金额
    private String qx;//第几期
    private String partnerId;//经销商id
    private String userId;
    private String htId;//合同id

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Date getHkDate() {
        return hkDate;
    }

    public void setHkDate(Date hkDate) {
        this.hkDate = hkDate;
    }

    public Date getFpDate() {
        return fpDate;
    }

    public void setFpDate(Date fpDate) {
        this.fpDate = fpDate;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getHtId() {
        return htId;
    }

    public void setHtId(String htId) {
        this.htId = htId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getSsje() {
        return ssje;
    }

    public void setSsje(Double ssje) {
        this.ssje = ssje;
    }

    public Double getFpje() {
        return fpje;
    }

    public void setFpje(Double fpje) {
        this.fpje = fpje;
    }

   
}
