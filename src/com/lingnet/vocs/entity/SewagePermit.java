package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Table(name = "sewage_permit")
@Entity
public class SewagePermit extends BaseEntity {

	private String partnerId;

	private String address;

	private String industryCategory;

	private String area;

	private String issuingOrgan;

	private String licenseNumber;
	
	private String businessType;

	private String version;

	private String issuingDate;

	private String expirationDate;

	private String pollutantsCategory;

	private String pollutantsSpecies;

	private String pollutantsRule;

	private String emissionStandard;
	
	@Column(name = "partnerid")
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId=partnerId;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	@Column(name = "industry_category")
	public String getIndustryCategory() {
		return this.industryCategory;
	}
	
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory=industryCategory;
	}
	
	@Column(name = "area")
	public String getArea() {
		return this.area;
	}
	
	public void setArea(String area) {
		this.area=area;
	}
	
	@Column(name = "issuing_organ")
	public String getIssuingOrgan() {
		return this.issuingOrgan;
	}
	
	public void setIssuingOrgan(String issuingOrgan) {
		this.issuingOrgan=issuingOrgan;
	}
	
	@Column(name = "license_number")
	public String getLicenseNumber() {
		return this.licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber=licenseNumber;
	}
	
	@Column(name = "business_type")
	public String getBusinessType() {
		return this.businessType;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType=businessType;
	}
	
	@Column(name = "version")
	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version) {
		this.version=version;
	}
	
	@Column(name = "issuing_date")
	public String getIssuingDate() {
		return this.issuingDate;
	}
	
	public void setIssuingDate(String issuingDate) {
		this.issuingDate=issuingDate;
	}
	
	@Column(name = "expiration_date")
	public String getExpirationDate() {
		return this.expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate=expirationDate;
	}
	
	@Column(name = "pollutants_category")
	public String getPollutantsCategory() {
		return this.pollutantsCategory;
	}
	
	public void setPollutantsCategory(String pollutantsCategory) {
		this.pollutantsCategory=pollutantsCategory;
	}
	
	@Column(name = "pollutants_species")
	public String getPollutantsSpecies() {
		return this.pollutantsSpecies;
	}
	
	public void setPollutantsSpecies(String pollutantsSpecies) {
		this.pollutantsSpecies=pollutantsSpecies;
	}
	
	@Column(name = "pollutants_rule")
	public String getPollutantsRule() {
		return this.pollutantsRule;
	}
	
	public void setPollutantsRule(String pollutantsRule) {
		this.pollutantsRule=pollutantsRule;
	}
	
	@Column(name = "emission_standard")
	public String getEmissionStandard() {
		return this.emissionStandard;
	}
	
	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard=emissionStandard;
	}

}
