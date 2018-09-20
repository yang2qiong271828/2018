package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "equipment_maintenance")
public class EquipmentMaintenance extends BaseEntity {
	
	private String equipmentCode;//设备编号
	private String equipmentName;//设备名称
	private String maintainanceContent;//维护内容
	private String maintainancePerson;//维护人
	private Date maintainanceDate;//维护日期
	private String equipmentId;//设备id
	private String partnerId;//使用人
	private String owner;//归属人
	private String isDeleted;// 是否删除
	
	
	@Column(name = "equipment_code")
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	@Column(name = "equipment_name")
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	@Column(name = "maintainance_content")
	public String getMaintainanceContent() {
		return maintainanceContent;
	}
	public void setMaintainanceContent(String maintainanceContent) {
		this.maintainanceContent = maintainanceContent;
	}
	@Column(name = "maintainance_person")
	public String getMaintainancePerson() {
		return maintainancePerson;
	}
	public void setMaintainancePerson(String maintainancePerson) {
		this.maintainancePerson = maintainancePerson;
	}
	@Column(name = "maintainance_date")
	public Date getMaintainanceDate() {
		return maintainanceDate;
	}
	public void setMaintainanceDate(Date maintainanceDate) {
		this.maintainanceDate = maintainanceDate;
	}
	@Column(name = "equipment_id")
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	@Column(name = "partner_id")
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Column(name = "IS_DELETED")
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
	
	
}
