package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: Supplier 
 * @Description: TODO 
 * @author yangqiong
 * @date 2018年4月14日 13:54:57 
 *
 */

@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 8032403927689760094L;

	private String supplier_code;//编码
	private String supplier_qyname;//企业名称
	private String supplier_type;//类型标识：0-治理厂家   1-监测厂家
	private String supplier_desc;//企业介绍
	private String supplier_shhxydm;//工商信息：统一社会信用代码
	private String supplier_zzjgdm;//工商信息：组织机构代码
	private String suppliier_zcnumber;//工商信息：注册号
	private String supplier_openstatus;//工商信息：经营状态
	private String supplier_qyzclx;//工商信息：企业注册类型
	private String supplier_newdate;//工商信息：成立日期
	private String supplier_openlimitdate;//工商信息：营业期限
	private String supplier_issuedate;//工商信息：发照日期
	private String supplier_fddbr;//工商信息：法定代表人
	private String supplier_qyzczb;//工商信息：注册资本
	private String supplier_qydjjg;//工商信息：登记机关
	private String supplier_qyaddress;//工商信息：企业地址
	private String supplier_businessscope;//工商信息：经营范围
	private String supplier_province;//工商信息：企业所在省
	private String supplier_city;//工商信息：企业所在市
	private String supplier_xzdistrict;//工商信息：企业所在区
	private String supplier_industrialdistrict;//工商信息：企业所在园区
	private String latitude;//经度
	private String longitude;//纬度

	@Column(name = "supplier_code")
	public String getSupplier_code() {
		return supplier_code;
	}
	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}
	@Column(name = "supplier_qyname")
	public String getSupplier_qyname() {
		return supplier_qyname;
	}
	public void setSupplier_qyname(String supplier_qyname) {
		this.supplier_qyname = supplier_qyname;
	}
	
	@Column(name = "supplier_type")
	public String getSupplier_type() {
		return supplier_type;
	}
	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}
	
	@Column(name = "supplier_desc")
	public String getSupplier_desc() {
		return supplier_desc;
	}
	public void setSupplier_desc(String supplier_desc) {
		this.supplier_desc = supplier_desc;
	}
	
	@Column(name = "supplier_shhxydm")
	public String getSupplier_shhxydm() {
		return supplier_shhxydm;
	}
	public void setSupplier_shhxydm(String supplier_shhxydm) {
		this.supplier_shhxydm = supplier_shhxydm;
	}
	
	@Column(name = "supplier_zzjgdm")
	public String getSupplier_zzjgdm() {
		return supplier_zzjgdm;
	}
	public void setSupplier_zzjgdm(String supplier_zzjgdm) {
		this.supplier_zzjgdm = supplier_zzjgdm;
	}
	
	@Column(name = "suppliier_zcnumber")
	public String getSuppliier_zcnumber() {
		return suppliier_zcnumber;
	}
	public void setSuppliier_zcnumber(String suppliier_zcnumber) {
		this.suppliier_zcnumber = suppliier_zcnumber;
	}
	
	@Column(name = "supplier_openstatus")
	public String getSupplier_openstatus() {
		return supplier_openstatus;
	}
	public void setSupplier_openstatus(String supplier_openstatus) {
		this.supplier_openstatus = supplier_openstatus;
	}
	
	@Column(name = "supplier_qyzclx")
	public String getSupplier_qyzclx() {
		return supplier_qyzclx;
	}
	public void setSupplier_qyzclx(String supplier_qyzclx) {
		this.supplier_qyzclx = supplier_qyzclx;
	}
	
	@Column(name = "supplier_newdate")
	public String getSupplier_newdate() {
		return supplier_newdate;
	}
	public void setSupplier_newdate(String supplier_newdate) {
		this.supplier_newdate = supplier_newdate;
	}
	
	@Column(name = "supplier_openlimitdate")
	public String getSupplier_openlimitdate() {
		return supplier_openlimitdate;
	}
	public void setSupplier_openlimitdate(String supplier_openlimitdate) {
		this.supplier_openlimitdate = supplier_openlimitdate;
	}
	
	@Column(name = "supplier_issuedate")
	public String getSupplier_issuedate() {
		return supplier_issuedate;
	}
	public void setSupplier_issuedate(String supplier_issuedate) {
		this.supplier_issuedate = supplier_issuedate;
	}
	
	@Column(name = "supplier_fddbr")
	public String getSupplier_fddbr() {
		return supplier_fddbr;
	}
	public void setSupplier_fddbr(String supplier_fddbr) {
		this.supplier_fddbr = supplier_fddbr;
	}
	
	@Column(name = "supplier_qyzczb")
	public String getSupplier_qyzczb() {
		return supplier_qyzczb;
	}
	public void setSupplier_qyzczb(String supplier_qyzczb) {
		this.supplier_qyzczb = supplier_qyzczb;
	}
	
	@Column(name = "supplier_qydjjg")
	public String getSupplier_qydjjg() {
		return supplier_qydjjg;
	}
	public void setSupplier_qydjjg(String supplier_qydjjg) {
		this.supplier_qydjjg = supplier_qydjjg;
	}
	
	@Column(name = "supplier_qyaddress")
	public String getSupplier_qyaddress() {
		return supplier_qyaddress;
	}
	public void setSupplier_qyaddress(String supplier_qyaddress) {
		this.supplier_qyaddress = supplier_qyaddress;
	}
	
	@Column(name = "supplier_businessscope")
	public String getSupplier_businessscope() {
		return supplier_businessscope;
	}
	public void setSupplier_businessscope(String supplier_businessscope) {
		this.supplier_businessscope = supplier_businessscope;
	}
	
	@Column(name = "supplier_province")
	public String getSupplier_province() {
		return supplier_province;
	}
	public void setSupplier_province(String supplier_province) {
		this.supplier_province = supplier_province;
	}
	
	@Column(name = "supplier_city")
	public String getSupplier_city() {
		return supplier_city;
	}
	public void setSupplier_city(String supplier_city) {
		this.supplier_city = supplier_city;
	}
	
	@Column(name = "supplier_xzdistrict")
	public String getSupplier_xzdistrict() {
		return supplier_xzdistrict;
	}
	public void setSupplier_xzdistrict(String supplier_xzdistrict) {
		this.supplier_xzdistrict = supplier_xzdistrict;
	}
	
	@Column(name = "supplier_industrialdistrict")
	public String getSupplier_industrialdistrict() {
		return supplier_industrialdistrict;
	}
	public void setSupplier_industrialdistrict(String supplier_industrialdistrict) {
		this.supplier_industrialdistrict = supplier_industrialdistrict;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
