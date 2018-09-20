package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "ACCOUNT_MGT")
@Entity
public class AccountMgt extends BaseEntity {
    // 账务编号
    private String code;
    // 合作商/客户Id
    private String partnerId;
    // 合作商/客户名称
    private String partnerName;
    // 款项日期
    private Date financeDate;
    // 实际到款日期
    private Date actFinanceDate;
    // 应收服务费
    private Double serviceReceivable;
    // 减免服务费
    private Double serviceDiscount;
    // 实收服务费
    private Double serviceReceived;
    // 应收物料费
    private Double materialReceivable;
    // 减免物料费
    private Double materialDiscount;
    // 实收物料费
    private Double materialReceived;
    // 工单code
    private String workOrderCode;
    // 维护人Id
    private String recorderId;
    // 维护人Name
    private String recorderName;
    // 合作商/客户标识
    private String partnerOrCustomer;
    // 审核状态
    private String verifyStatus;
    // 发票号
    private String invoiceNo;
    // 工单收费所属的合作商
    private String ownerId;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Column(name = "finance_date")
    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    @Column(name = "work_order_code")
    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    @Column(name = "recorder")
    public String getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    @Column(name = "partner_or_customer")
    public String getPartnerOrCustomer() {
        return partnerOrCustomer;
    }

    public void setPartnerOrCustomer(String partnerOrCustomer) {
        this.partnerOrCustomer = partnerOrCustomer;
    }

    @Transient
    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    @Column(name = "service_receivable")
    public Double getServiceReceivable() {
        return serviceReceivable;
    }

    public void setServiceReceivable(Double serviceReceivable) {
        this.serviceReceivable = serviceReceivable;
    }

    @Column(name = "service_discount")
    public Double getServiceDiscount() {
        return serviceDiscount;
    }

    public void setServiceDiscount(Double serviceDiscount) {
        this.serviceDiscount = serviceDiscount;
    }

    @Column(name = "service_received")
    public Double getServiceReceived() {
        return serviceReceived;
    }

    public void setServiceReceived(Double serviceReceived) {
        this.serviceReceived = serviceReceived;
    }

    @Column(name = "material_receivable")
    public Double getMaterialReceivable() {
        return materialReceivable;
    }

    public void setMaterialReceivable(Double materialReceivable) {
        this.materialReceivable = materialReceivable;
    }

    @Column(name = "material_discount")
    public Double getMaterialDiscount() {
        return materialDiscount;
    }

    public void setMaterialDiscount(Double materialDiscount) {
        this.materialDiscount = materialDiscount;
    }

    @Column(name = "material_received")
    public Double getMaterialReceived() {
        return materialReceived;
    }

    public void setMaterialReceived(Double materialReceived) {
        this.materialReceived = materialReceived;
    }

    @Column(name = "verify_status")
    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    @Column(name = "act_finance_date")
    public Date getActFinanceDate() {
        return actFinanceDate;
    }

    public void setActFinanceDate(Date actFinanceDate) {
        this.actFinanceDate = actFinanceDate;
    }

    @Column(name = "invoice_no")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Column(name = "owner_id")
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
