package com.lingnet.vocs.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "regular_maintenance")
public class RegularMaintenance extends BaseEntity{
	private String ywname;//运维事项
	private String ywrank;//运维等级
	private String ywcontent;//运维内容
	private String parts;//拆换零件
	private String ywcycle;	//运维周期	
	private String remdtime;	//提醒周期
	private String initiator;	//服务发起方
	private String receiver;	//服务接收方
	private String equipmentId;// 设备id
	
	
	

	@Column(name = "ywname")
	public String getYwname() {
		return ywname;
	}
	public void setYwname(String ywname) {
		this.ywname = ywname;
	}
	@Column(name = "ywrank")
	public String getYwrank() {
		return ywrank;
	}
	public void setYwrank(String ywrank) {
		this.ywrank = ywrank;
	}
	@Column(name = "ywcontent")
	public String getYwcontent() {
		return ywcontent;
	}
	public void setYwcontent(String ywcontent) {
		this.ywcontent = ywcontent;
	}
	@Column(name = "parts")
	public String getParts() {
		return parts;
	}
	public void setParts(String parts) {
		this.parts = parts;
	}
	@Column(name = "ywcycle")
	public String getYwcycle() {
		return ywcycle;
	}
	public void setYwcycle(String ywcycle) {
		this.ywcycle = ywcycle;
	}
	@Column(name = "remdtime")
	public String getRemdtime() {
		return remdtime;
	}
	public void setRemdtime(String remdtime) {
		this.remdtime = remdtime;
	}
	@Column(name = "initiator")
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	@Column(name = "receiver")
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	@Column(name = "equipmentId")
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
}
