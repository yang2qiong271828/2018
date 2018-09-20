package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "sewage_report")
public class SewageReport extends BaseEntity{
	
	private String partnerId;
	
	private String filename;
	
	private String filepath;

	@Column(name = "partnerid")
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId=partnerId;
	}
	
	@Column(name = "filename")
	public String getFilename() {
		return this.filename;
	}
	
	public void setFilename(String filename) {
		this.filename=filename;
	}
	
	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath=filepath;
	}

}
