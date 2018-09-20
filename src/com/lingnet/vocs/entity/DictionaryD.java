
package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: DictionaryD 
 * @Description: TODO 
 * @author xuhp
 * @date 2016-1-8 上午11:31:47 
 *
 */
@Entity
@Table(name = "B_DATEDICTIONARY_D")
public class DictionaryD extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 8537879100178113990L;

	private String parentId;// 所属目录id #

	private String code;// 编码 #
	private String name;// 名称 #
	private String description;// 描述 #

	private String value;// # 值(varchar 32)
	private int orderNumber;// 顺序号 #
	private String isValidflg;// 有效标志（1有效 0无效） #

	private String isDefault;// 是否默认 #
	private String isSystem;// 是否系统固定数据 #

	// Constructors

	/** default constructor */
	public DictionaryD() {
	}

	/** full constructor */
	public DictionaryD(String code, String name, String description, String value, String parentId, int orderNumber, String isValidflg, String isDefault, String isSystem

	// String abbName
	) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
		this.orderNumber = orderNumber;
		this.isValidflg = isValidflg;
		this.isDefault = isDefault;
		this.isSystem = isSystem;
		// this.abbName = abbName;
		this.value = value;
	}

	// Property accessors
	/** 编码  */
	@Column(name = "code", length = 36)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 名称  */
	@Column(name = "name", length = 36)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 所属目录id */
	@Column(name = "parent_id", length = 32)
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

	/** 是否默认  */
	@Column(name = "is_default", length = 1)
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	/** 是否系统固定数据  */
	@Column(name = "is_system", length = 1)
	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	/** 描述  */
	@Column(name = "description", length = 36)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** 值 */
	@Column(name = "value", length = 32)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	@Column(name = "abb_name", length = 36)
//	public String getAbbName() {
//		return abbName;
//	}
//
//
//	public void setAbbName(String abbName) {
//		this.abbName = abbName;
//	}
//	

}