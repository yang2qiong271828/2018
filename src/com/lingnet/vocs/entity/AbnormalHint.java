package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "ABNORMAL_HINT")
@Entity
public class AbnormalHint extends BaseEntity {
    // 设备id
    private String equipmentId;
    // 设备code
    private String equipmentCode;
    // 设备类型
    private String equipmentType;
    // 提示信息
    private String msgContent;
    // 报警时间
    private Date alarmDate;
    // 客户名
    private String partnerName;
    // 客户Id
    private String partnerId;
    // 处理人
    private String operator;
    // 处理状态
    private String msgStatus;
    // 通知人
    private String notifyPerson;

    @Column(name = "equipment_id")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Column(name = "equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Column(name = "msg_content")
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Column(name = "alarm_date")
    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "msg_status")
    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    @Column(name = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Column(name = "notify_person")
    public String getNotifyPerson() {
        return notifyPerson;
    }

    public void setNotifyPerson(String notifyPerson) {
        this.notifyPerson = notifyPerson;
    }

    @Column(name = "equipment_type")
    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

}
