/**
 * @Partner.java
 * @com.lingnet.vocs.entity
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

/**
 * @ClassName: Partner
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月2日 下午3:38:24
 * 
 */
@Entity
@Table(name = "PARTNER")
public class Partner extends BaseEntity {
    // 共有字段
    private String name;// 名称
    private String address;// 详细地址
    private String code;// 编号
    private String partnerOrCustomer;// 合作商或客户 P合作商C客户H华世洁
    private String source;// 来源方式
    private String province;// 省
    private String city;// 市
    private String district;// 区
    private String industry;// 行业
    private String remark;// 备注
    private String higherAgent;// 上级、归属
    private String higherAgentName;// 上级、归属名称
    private String nature;// 企业性质
	private float latitude;// 纬度
	private float longitude;// 经度
    private String isDeleted;// 是否删除

    // 合作商独有
	private Date joinDate;// 加盟日期
    private String partnerLevel;// 合作商等级
    private String jurisdictionArea;// 管辖区域
    private String jurisdictionCn;// 管辖区域中文

    private String email;// 邮箱
    private String legalPerson;// 企业法人
    private String legalPersonPhone;// 法人电话
    private String uniformCode;// 统一信用代码证
    private String capitalAsset;// 固定资产
    private String productionScale;// 生产规模
    private String registerCapital;// 注册资金
    private String website;// 网址
    
    private String djr;//对接人
    private String status;//状态 0草稿，1已提交，2.审核通过，3审核不通过，4重新提交，5不符合要求
    private String bhyj;//驳回意见
    private Date tjsj;//提交时间
    private String jfstatus;//交费状态  0，交费。1.未交费
    private String threadId;//上传材料
    // 客户独有
    private String temperatureMax;//VOCs温度下限
    private String temperatureMin;//VOCs温度上限
    private String pressureMax;//VOCs压力上限
    private String pressureMin;//VOCs压力下限
    private String densityEnter;//VOCs进口浓度
    private String densityExit;//VOCs出口浓度
    private String densityDiff;//VOCs浓度差值
    private String salesman;//销售人员
    private String salesmanContact;//销售人员联系方式
    
    
    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the code
     */
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the partnerOrCustomer
     */
    @Column(name = "partner_or_customer")
    public String getPartnerOrCustomer() {
        return partnerOrCustomer;
    }

    /**
     * @param partnerOrCustomer
     *            the partnerOrCustomer to set
     */

    public void setPartnerOrCustomer(String partnerOrCustomer) {
        this.partnerOrCustomer = partnerOrCustomer;
    }

    /**
     * @return the source
     */
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the province
     */
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     *            the province to set
     */

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the district
     */
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    /**
     * @param district
     *            the district to set
     */

    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the industry
     */
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry
     *            the industry to set
     */

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * @return the remark
     */
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the higherAgent
     */
    @Column(name = "higher_agent")
    public String getHigherAgent() {
        return higherAgent;
    }

    /**
     * @param higherAgent
     *            the higherAgent to set
     */

    public void setHigherAgent(String higherAgent) {
        this.higherAgent = higherAgent;
    }

    /**
     * @return the joinDate
     */
    @Column(name = "joinDate")
	public Date getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate
     *            the joinDate to set
     */

	public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return the partnerLevel
     */
    @Column(name = "partner_level")
    public String getPartnerLevel() {
        return partnerLevel;
    }

    /**
     * @param partnerLevel
     *            the partnerLevel to set
     */

    public void setPartnerLevel(String partnerLevel) {
        this.partnerLevel = partnerLevel;
    }

    /**
     * @return the jurisdictionArea
     */
    @Column(name = "jurisdiction_area")
    public String getJurisdictionArea() {
        return jurisdictionArea;
    }

    /**
     * @param jurisdictionArea
     *            the jurisdictionArea to set
     */

    public void setJurisdictionArea(String jurisdictionArea) {
        this.jurisdictionArea = jurisdictionArea;
    }

    /**
     * @return the nature
     */
    @Column(name = "nature")
    public String getNature() {
        return nature;
    }

    /**
     * @param nature
     *            the nature to set
     */

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Column(name = "temperatureMax")
	public String getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(String temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	
	@Column(name = "temperatureMin")
	public String getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	@Column(name = "pressureMax")
	public String getPressureMax() {
		return pressureMax;
	}

	public void setPressureMax(String pressureMax) {
		this.pressureMax = pressureMax;
	}

	@Column(name = "pressureMin")
	public String getPressureMin() {
		return pressureMin;
	}

	public void setPressureMin(String pressureMin) {
		this.pressureMin = pressureMin;
	}

	@Column(name = "densityEnter")
	public String getDensityEnter() {
		return densityEnter;
	}

	public void setDensityEnter(String densityEnter) {
		this.densityEnter = densityEnter;
	}

	@Column(name = "densityExit")
	public String getDensityExit() {
		return densityExit;
	}

	public void setDensityExit(String densityExit) {
		this.densityExit = densityExit;
	}

	@Column(name = "densityDiff")
	public String getDensityDiff() {
		return densityDiff;
	}

	public void setDensityDiff(String densityDiff) {
		this.densityDiff = densityDiff;
	}

	@Column(name = "salesman")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Column(name = "salesmanContact")
	public String getSalesmanContact() {
		return salesmanContact;
	}

	public void setSalesmanContact(String salesmanContact) {
		this.salesmanContact = salesmanContact;
	}

	@Column(name = "latitude")
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

    @Column(name = "IS_DELETED")
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "legal_person")
    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Column(name = "legal_person_phone")
    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone;
    }

    @Column(name = "uniform_code")
    public String getUniformCode() {
        return uniformCode;
    }

    public void setUniformCode(String uniformCode) {
        this.uniformCode = uniformCode;
    }

    @Column(name = "capital_asset")
    public String getCapitalAsset() {
        return capitalAsset;
    }

    public void setCapitalAsset(String capitalAsset) {
        this.capitalAsset = capitalAsset;
    }

    @Column(name = "production_scale")
    public String getProductionScale() {
        return productionScale;
    }

    public void setProductionScale(String productionScale) {
        this.productionScale = productionScale;
    }

    @Column(name = "register_capital")
    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }

    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "jurisdiction_cn")
    public String getJurisdictionCn() {
        return jurisdictionCn;
    }

    public void setJurisdictionCn(String jurisdictionCn) {
        this.jurisdictionCn = jurisdictionCn;
    }

    @Transient
    public String getHigherAgentName() {
        return higherAgentName;
    }

    public void setHigherAgentName(String higherAgentName) {
        this.higherAgentName = higherAgentName;
    }

    @Column(name = "djr")
    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBhyj() {
        return bhyj;
    }

    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }

    public Date getTjsj() {
        return tjsj;
    }

    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }

    public String getJfstatus() {
        return jfstatus;
    }

    public void setJfstatus(String jfstatus) {
        this.jfstatus = jfstatus;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

}
