package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 问题现象
 * @ClassName: Question 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 上午10:31:08 
 *
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {
    
    private String equipmentCode;//设备编号
    private String theme;//问题主题
    private String typeId;//问题类型
    private Date collectTime;//问题收集时间
    private Date diagnoseTime;//问题诊断时间
    private String diagnosePerson;//问题诊断人
    private String describe;//问题描述
    private String status;//问题状态
     
    @Column(name = "equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
    
    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    @Column(name = "type_id")
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    
    @Column(name = "collect_time")
    public Date getCollectTime() {
        return collectTime;
    }
    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
    
    @Column(name = "diagnose_time")
    public Date getDiagnoseTime() {
        return diagnoseTime;
    }
    public void setDiagnoseTime(Date diagnoseTime) {
        this.diagnoseTime = diagnoseTime;
    }
    
    @Column(name = "diagnose_person")
    public String getDiagnosePerson() {
        return diagnosePerson;
    }
    public void setDiagnosePerson(String diagnosePerson) {
        this.diagnosePerson = diagnosePerson;
    }
    
    @Column(name = "describe")
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    

}
