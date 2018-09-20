package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.lingnet.common.entity.BaseEntity;


@Entity
@Table(name = "supplier_case")
public class SupplierCase extends BaseEntity {
	
	private String classify;
	private String subtitle;
	private String imgpath;
	private String partnerid;
	
	@Column(name = "classify")
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	@Column(name = "subtitle")
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	
	@Column(name = "partnerid")
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	
	
	@Column(name = "main_image")
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	
	
	
	
	
}
