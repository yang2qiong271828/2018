package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;


/**
 * 
 * @ClassName: Cspz 
 * @Description: TODO 
 * @author lyz
 * @date 2017年10月25日 上午8:24:54 
 *
 */

@Entity
@Table(name = "cspz")
public class Cspz extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -4816911298852191515L;

	private String sbId;//设备id

	private String type;//设备类型
	
	private String cbl1;
	
	

	@Column(name = "sbId" )
	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}
	
	@Column(name = "type" )
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "cbl1" )
	public String getCbl1() {
		return cbl1;
	}

	public void setCbl1(String cbl1) {
		this.cbl1 = cbl1;
	}
	
	
	
	
}
