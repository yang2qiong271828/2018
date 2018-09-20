package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "sewage_business")
public class SewageBusiness extends BaseEntity {

	
	private String partnerId;
	
	private String creditCode;

	private String mechanismCode;

	private String registerCode;

	private String operatingState;
	
	private String industry;

	private String foundDate;
	
	
	
	private String companyType;

	private String businessDate;

	private String legalPerson;

	private String licensingDate;

	private String registeredCapital;
	
	private String registrationAuthority;
	
	private String address;

	private String operationScope;
	
	

	
	
    

	@Column(name = "partnerid")
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId=partnerId;
	}
	
	@Column(name = "credit_code")
	public String getCreditCode() {
		return this.creditCode;
	}
	
	public void setCreditCode(String creditCode) {
		this.creditCode=creditCode;
	}
	
	@Column(name = "mechanism_code")
	public String getMechanismCode() {
		return this.mechanismCode;
	}
	
	public void setMechanismCode(String mechanismCode) {
		this.mechanismCode=mechanismCode;
	}
	
	@Column(name = "register_code")
	public String getRegisterCode() {
		return this.registerCode;
	}
	
	public void setRegisterCode(String registerCode) {
		this.registerCode=registerCode;
	}
	
	@Column(name = "operating_state")
	public String getOperatingState() {
		return this.operatingState;
	}
	
	public void setOperatingState(String operatingState) {
		this.operatingState=operatingState;
	}
	
	@Column(name = "industry")
	public String getIndustry() {
		return this.industry;
	}
	
	public void setIndustry(String industry) {
		this.industry=industry;
	}
	
	@Column(name = "found_date")
	public String getFoundDate() {
		return this.foundDate;
	}
	
	public void setFoundDate(String foundDate) {
		this.foundDate=foundDate;
	}
	
	@Column(name = "company_type")
	public String getCompanyType() {
		return this.companyType;
	}
	
	public void setCompanyType(String companyType) {
		this.companyType=companyType;
	}
	
	@Column(name = "business_date")
	public String getBusinessDate() {
		return this.businessDate;
	}
	
	public void setBusinessDate(String businessDate) {
		this.businessDate=businessDate;
	}
	
	@Column(name = "legal_person")
	public String getLegalPerson() {
		return this.legalPerson;
	}
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson=legalPerson;
	}
	
	@Column(name = "Licensing_date")
	public String getLicensingDate() {
		return this.licensingDate;
	}
	
	public void setLicensingDate(String licensingDate) {
		this.licensingDate=licensingDate;
	}
	
	@Column(name = "registered_capital")
	public String getRegisteredCapital() {
		return this.registeredCapital;
	}
	
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital=registeredCapital;
	}
	
	@Column(name = "registration_authority")
	public String getRegistrationAuthority() {
		return this.registrationAuthority;
	}
	
	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority=registrationAuthority;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	@Column(name = "operation_scope")
	public String getOperationScope() {
		return this.operationScope;
	}
	
	public void setOperationScope(String operationScope) {
		this.operationScope=operationScope;
	}

	
	
	
	


}
