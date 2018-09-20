package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * @ClassName: Dictionary
 * @Description: TODO
 * @author xuhp
 * @date 2016-1-8 上午11:33:33
 */
@Entity
@Table(name = "B_DATEDICTIONARY")
public class Dictionary extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 8537879100178113990L;
	private String code;// 编码 #
	private String name;// 名称 #
	private String description;// 描述 #
	private String parentId;// 上级目录id #
	private int orderNumber;// 顺序号 #
	private String isValidflg;// 有效标志（1有效 0无效） #
//    private String parentName;//上级目录名字
//    private String leverid;//目录id
//    private String isDefault;//是否默认
//    private String isSystem;//是否系统固定数据
//    private String abbName;//缩写名称
//    private String parentCode;//上级目录code

	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** full constructor */
	public Dictionary(String code, String name, String description, String parentId, int orderNumber, String isValidflg) {
		this.code = code;
		this.name = name;
		this.description = description;
		// this.parentName = parentName;
		this.parentId = parentId;
		this.orderNumber = orderNumber;
		this.isValidflg = isValidflg;
//    	this.leverid = leverid;
//    	this.isDefault = isDefault;
//    	this.isSystem = isSystem;
//    	this.abbName = abbName;
//    	this.parentCode = parentCode;
		// this.description = description;
//    	this.sysHardcode = sysHardcode;
//    	this.isRequiredAction = isRequiredAction;
	}

	// Property accessors
	/** 编码 */
	@Column(name = "code", length = 36)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 名称 */
	@Column(name = "name", length = 36)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//    @Column(name = "up_lever_name", length = 36)
//    public String getParentName() {
//    	return parentName;
//    }
//    
//    public void setParentName(String parentName) {
//    	this.parentName = parentName;
//    }
	/** 上级目录id */
	@Column(name = "up_lever_id", length = 32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 顺序号 */
	@Column(name = "order_number", length = 36)
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/** 有效标志（1有效 0无效） */
	@Column(name = "is_validflg", length = 1)
	public String getIsValidflg() {
		return isValidflg;
	}

	public void setIsValidflg(String isValidflg) {
		this.isValidflg = isValidflg;
	}

//    @Column(name = "lever_id", length = 36)
//	public String getLeverid() {
//		return leverid;
//	}
//
//	public void setLeverid(String leverid) {
//		this.leverid = leverid;
//	}
//    @Column(name = "is_default", length = 1)
//	public String getIsDefault() {
//		return isDefault;
//	}
//
//	public void setIsDefault(String isDefault) {
//		this.isDefault = isDefault;
//	}
//    @Column(name = "is_system", length = 1)
//	public String getIsSystem() {
//		return isSystem;
//	}
//
//	public void setIsSystem(String isSystem) {
//		this.isSystem = isSystem;
//	}
	/** 描述 */
	@Column(name = "description", length = 36)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//	@Column(name = "up_lever_code", length = 255)
//	public String getParentCode() {
//		return parentCode;
//	}
//	
//	public void setParentCode(String parentCode) {
//		this.parentCode = parentCode;
//	}
//	@Column(name = "abbname", length = 36)
//	public String getAbbName() {
//		return abbName;
//	}
//
//
//	public void setAbbName(String abbName) {
//		this.abbName = abbName;
//	}
//	
//	@Column(name = "sys_hardcode", length = 1)
//	public String getSysHardcode() {
//		return sysHardcode;
//	}
//	
//	
//	public void setSysHardcode(String sysHardcode) {
//		this.sysHardcode = sysHardcode;
//	}
//	
//	@Column(name = "is_required_action", length = 1)
//	public String getIsRequiredAction() {
//		return isRequiredAction;
//	}
//	
//	
//	public void setIsRequiredAction(String isRequiredAction) {
//		this.isRequiredAction = isRequiredAction;
//	}

}