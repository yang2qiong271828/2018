package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "sewage_situation")
public class SewageSituation extends BaseEntity {

	private String partnerId;

	private String workshop;
	
	private String pollutantsType;
	
	private String production;
	
	private String airVolume;
	
	private String concentration;
	
	private String emission;
	
	private String emissionConcentration;
	
	private String standardName;
	
	private String standardValue;
	
	private String governanceMeasures;
	
	@Column(name = "partnerid")
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId=partnerId;
	}
	
	@Column(name = "workshop")
    public String getWorkshop() {
        return workshop;
    }
	
	public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }
	
	@Column(name = "pollutants_type")
    public String getPollutantsType() {
        return pollutantsType;
    }
	
	public void setPollutantsType(String pollutantsType) {
        this.pollutantsType = pollutantsType;
    }
	
	@Column(name = "production")
    public String getProduction() {
        return production;
    }
	
	public void setProduction(String production) {
        this.production = production;
    }
	
	@Column(name = "air_volume")
    public String getAirVolume() {
        return airVolume;
    }
	
	public void setAirVolume(String airVolume) {
        this.airVolume = airVolume;
    }
	
	@Column(name = "concentration")
    public String getConcentration() {
        return concentration;
    }
	
	public void setConcentration(String concentration) {
        this.concentration = concentration;
    }
	
	@Column(name = "emission")
    public String getEmission() {
        return emission;
    }
	
	public void setEmission(String emission) {
        this.emission = emission;
    }
	
	@Column(name = "emission_concentration")
    public String getEmissionConcentration() {
        return emissionConcentration;
    }
	
	public void setEmissionConcentration(String emissionConcentration) {
        this.emissionConcentration = emissionConcentration;
    }
	
	@Column(name = "standard_name")
    public String getStandardName() {
        return standardName;
    }
	
	public void setStandardName(String standardName) {
        this.standardName = standardName;
    }
	
	@Column(name = "standard_value")
    public String getStandardValue() {
        return standardValue;
    }
	
	public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }
	
	@Column(name = "governance_measures")
    public String getGovernanceMeasures() {
        return governanceMeasures;
    }
	
	public void setGovernanceMeasures(String governanceMeasures) {
        this.governanceMeasures = governanceMeasures;
    }
	
}
