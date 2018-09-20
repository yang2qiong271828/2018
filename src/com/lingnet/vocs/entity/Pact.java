package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "pact")
public class Pact extends BaseEntity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5123893410865551998L;
    //合同编号
    private String code;
    // 客户公司 Id（成单客户id）
    private String companyId;
    //签订日期
    private Date signDate;
    //合同到期时间
    private Date endDate;
    //付款方式r
    private String paymentType;
    //应收金额
    private Double accountReceivable;
    // 合同创建人
    private String partnerId;
    //合同类型  1试用 2 购买 3 租赁
    private String contractType;
    //备注说明
    private String bzsm;
    
    
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "company_id")
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Column(name = "sign_date")
    public Date getSignDate() {
        return signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Column(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    @Column(name = "account_receivable")
    public Double getAccountReceivable() {
        return accountReceivable;
    }
    public void setAccountReceivable(Double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }
    @Column(name = "contract_type")
    public String getContractType() {
        return contractType;
    }
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    @Column(name = "partnerId")
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    @Column(name = "bzsm")
    public String getBzsm() {
        return bzsm;
    }
    public void setBzsm(String bzsm) {
        this.bzsm = bzsm;
    }

}
