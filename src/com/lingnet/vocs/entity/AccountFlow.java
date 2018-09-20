package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "ACCOUNT_FLOW")
@Entity
public class AccountFlow extends BaseEntity {
    // 账务流水编号
    private String code;
    // 维护人
    private String recorder;
    // 工单号
    private String workOrderCode;
    // 款项日期
    private Date financeDate;
    // 应收金额
    private Double accountReceivable;
    // 减免金额
    private Double discount;
    // 实收金额
    private Double paidupCapital;
    // 合作商客户
    private String partnerId;
    // 合同code
    private String contractCode;
    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String district;
    // 账务流水类型
    private String type;
    // 账务流水所属的合作商Id
    private String ownerId;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "recorder")
    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    @Column(name = "workorder_code")
    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    @Column(name = "finance_date")
    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
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

    @Column(name = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getType() {
        return type;
    }

    @Column(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "owner_id")
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
