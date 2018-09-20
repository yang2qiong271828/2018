package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "machine")
public class Machine extends BaseEntity implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -4854886689016521467L;
    private String equipmentCode;//设备编号
    private String equipmentName;//设备名称
	private String equipmentType;//工艺类型
    private String imgpath;//设备封面
    private String equipmentfirm;//设备厂商
    private String description;//工作原理描述
    private String point;//产品特点
    private String trade;//适用行业
    private int num;//市场投放数量
    private String describe;//描述
    private String partnerId;//公司名称
    private String status;//发布状态（0发布，1撤回）
    private String isrm;//是否热门（0热门，1撤回）
    private String iszd;//是否置顶（0置顶，1撤回）
    private Double price;//价格
    private String models;//设备规格型号
    private String wxcc;//设备外形尺寸
    private String cslxr;//厂商联系人
    private String csphone;//厂商联系方式
    private String supplierid;//供应商名称
    
    @Column(name="supplierid")
    public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	@Column(name="equipmentcode")
    public String getEquipmentCode() {
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
    
    @Column(name="equipmentName")
    public String getEquipmentName() {
        return equipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    
    @Column(name="equipmentType")
    public String getEquipmentType() {
        return equipmentType;
    }
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
    
    
    @Column(name="equipmentfirm")
    public String getEquipmentfirm() {
        return equipmentfirm;
    }
    public void setEquipmentfirm(String equipmentfirm) {
        this.equipmentfirm = equipmentfirm;
    }
    
    @Column(name="description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="point")
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    
    @Column(name="trade")
    public String getTrade() {
        return trade;
    }
    public void setTrade(String trade) {
        this.trade = trade;
    }
    
    @Column(name="num")
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    
    @Column(name="equipmentpicture")
    public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	@Column(name="describe")
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    @Column(name="status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="partnerId")
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
	public String getIsrm() {
		return isrm;
	}
	public void setIsrm(String isrm) {
		this.isrm = isrm;
	}
	public String getIszd() {
		return iszd;
	}
	public void setIszd(String iszd) {
		this.iszd = iszd;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="models")
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	@Column( name="wxcc" )
	public String getWxcc() {
		return wxcc;
	}
	public void setWxcc(String wxcc) {
		this.wxcc = wxcc;
	}
	@Column( name = "cslxr" )
	public String getCslxr() {
		return cslxr;
	}
	public void setCslxr(String cslxr) {
		this.cslxr = cslxr;
	}
	@Column( name = "csphone" )
	public String getCsphone() {
		return csphone;
	}
	public void setCsphone(String csphone) {
		this.csphone = csphone;
	}
	
    
    
}
