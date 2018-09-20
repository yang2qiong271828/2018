package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "sewage_tax")
@Entity
public class SewageTax extends BaseEntity {

	private String partnerId;

	private String goods;

	private String unit;

	private String num;

	private String solventContent;

	private String salesUnit;

	private String purchaseUnit;

	private String openingTime;

	private String type;
	
	@Column(name = "partnerid")
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId=partnerId;
	}
	
	@Column(name = "goods")
	public String getGoods() {
		return this.goods;
	}
	
	public void setGoods(String goods) {
		this.goods=goods;
	}

	@Column(name = "unit")
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String unit) {
		this.unit=unit;
	}

	@Column(name = "num")
	public String getNum() {
		return this.num;
	}
	
	public void setNum(String num) {
		this.num=num;
	}

	@Column(name = "solventcontent")
	public String getSolventContent() {
		return this.solventContent;
	}
	
	public void setSolventContent(String solventContent) {
		this.solventContent=solventContent;
	}

	@Column(name = "salesunit")
	public String getSalesUnit() {
		return this.salesUnit;
	}
	
	public void setSalesUnit(String salesUnit) {
		this.salesUnit=salesUnit;
	}

	@Column(name = "purchaseunit")
	public String getPurchaseUnit() {
		return this.purchaseUnit;
	}
	
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit=purchaseUnit;
	}

	@Column(name = "openingtime")
	public String getOpeningTime() {
		return this.openingTime;
	}
	
	public void setOpeningTime(String openingTime) {
		this.openingTime=openingTime;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type=type;
	}


}
