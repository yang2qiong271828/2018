package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "CUST_FEEDBACK")
public class CustFeedback extends BaseEntity {
    private String equipmentId;// 设备Id
    private String equipmentCode;// 设备编号，为了方便取值
    private String equipmentUserId;// 设备使用者
    private String equipmentUserName;// 设备使用者名称，只为了显示，不保存
    private String name;// 故障名称
    private String type;// 故障类型
    private String contact;// 联系人
    private String phone;// 联系方式
    private String detail;// 故障详细
    private Date faultDate;// 故障时间
    private String submited;// 工单提交标识
    private String state;//状态 标示 是否已经转工单
    
    @Column(name = "equipment_id")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "fault_date")
    public Date getFaultDate() {
        return faultDate;
    }

    public void setFaultDate(Date faultDate) {
        this.faultDate = faultDate;
    }

    @Column(name = "submited")
    public String getSubmited() {
        return submited;
    }

    public void setSubmited(String submited) {
        this.submited = submited;
    }

    @Column(name = "equipment_user_id")
    public String getEquipmentUserId() {
        return equipmentUserId;
    }

    public void setEquipmentUserId(String equipmentUserId) {
        this.equipmentUserId = equipmentUserId;
    }

    @Transient
    public String getEquipmentUserName() {
        return equipmentUserName;
    }

    public void setEquipmentUserName(String equipmentUserName) {
        this.equipmentUserName = equipmentUserName;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
