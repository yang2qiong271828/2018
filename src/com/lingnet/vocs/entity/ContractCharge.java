package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "contract_charge")
@Entity
public class ContractCharge extends BaseEntity {

    // 编号
    private String code;
    // 合同号
    private String contractCode;
    // 甲方类型 合作商或者客户
    private String partnerOrCustomer;
    // 甲方公司 Id
    private String companyId;
    // 甲方公司名称
    private String companyName;
    // 应收金额
    private Double accountReceivable;
    // 减免金额
    private Double discount;
    // 实收金额
    private Double paidupCapital;
    // 审核状态
    private String verifyStatus;
    // 维护人
    private String recorder;
    // 维护人名称
    private String recorderName;
    // 款项日期
    private Date financeDate;
    // 备注
    private String remark;
    // 发票号
    private String invoiceNo;
    // 合同乙方
    private String sponsor;
    // 合同乙方名称
    private String sponsorName;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "partner_or_customer")
    public String getPartnerOrCustomer() {
        return partnerOrCustomer;
    }

    public void setPartnerOrCustomer(String partnerOrCustomer) {
        this.partnerOrCustomer = partnerOrCustomer;
    }

    @Column(name = "company_id")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "account_receivable")
    public Double getAccountReceivable() {
        return accountReceivable;
    }

    public void setAccountReceivable(Double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Column(name = "paidup_capital")
    public Double getPaidupCapital() {
        return paidupCapital;
    }

    public void setPaidupCapital(Double paidupCapital) {
        this.paidupCapital = paidupCapital;
    }

    @Column(name = "verify_status")
    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    @Column(name = "recorder")
    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    @Transient
    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    @Transient
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "finance_date")
    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "invoice_no")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Column(name = "sponsor")
    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    @Transient
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
