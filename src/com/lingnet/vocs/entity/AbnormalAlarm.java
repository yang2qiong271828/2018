package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "abnormal_alarm")
public class AbnormalAlarm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4446815078802317494L;

    private String equipmentId;// 设备id
    private String alarmContent;// 报警内容
    private Date alarmDate;// 报警时间
    private String exceptionType;// 异常类型id
    private String partner;// 客户名称id
    private String handlePeople;// 处理人(当前登录人)
    private String noticePeople;// 通知人
    private String equipmentTypeId;// 设备类型id
    private String processingState;// 处理状态(0未处理,1已处理)
    // private String partnerId;

    // 临时字段
    private String equipmentName;// 设备名称
    private String equipmentType;// 设备类型
    private String partnerName;// 客户名称
    private String startTime;
    private String endTime;
    private String exceptionTypeName;// 异常类型名称
    private String handlePeopleName;

    @Column(name = "equipment_id")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Column(name = "alarm_content")
    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    @Column(name = "alarm_date")
    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    @Column(name = "exception_type")
    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Column(name = "partner")
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Column(name = "handle_people")
    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    @Column(name = "notice_people")
    public String getNoticePeople() {
        return noticePeople;
    }

    public void setNoticePeople(String noticePeople) {
        this.noticePeople = noticePeople;
    }

    @Column(name = "equipment_type_id")
    public String getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(String equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    @Column(name = "processing_state")
    public String getProcessingState() {
        return processingState;
    }

    public void setProcessingState(String processingState) {
        this.processingState = processingState;
    }

    @Transient
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    @Transient
    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    //
    // @Column(name = "partner_id")
    // public String getPartnerId() {
    // return partnerId;
    // }
    //
    // public void setPartnerId(String partnerId) {
    // this.partnerId = partnerId;
    // }

    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Transient
    public String getExceptionTypeName() {
        return exceptionTypeName;
    }

    public void setExceptionTypeName(String exceptionTypeName) {
        this.exceptionTypeName = exceptionTypeName;
    }

    @Transient
    public String getHandlePeopleName() {
        return handlePeopleName;
    }

    public void setHandlePeopleName(String handlePeopleName) {
        this.handlePeopleName = handlePeopleName;
    }

}
