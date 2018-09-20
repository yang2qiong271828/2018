package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;


/**
 * 
 * @ClassName: Sewage 
 * @Description: TODO 
 * @author lyz
 * @date 2017年10月9日 下午1:52:38 
 *
 */

@Entity
@Table(name = "sewage")
public class Sewage extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -4816911298852191515L;

	private String code;//编号

	private String province;//省

	private String city;//市

	private String cdistrict;//城区

	private String ydistrict;//园区

	private String pwname;//排污企业名称

	private String dlwz;//地理位置

	private String qylxr;//企业联系人

	private String qyphone ;//企业联系人联系方式

	private String pwtype;//排污类型

	private String produce;//生产工艺

	private String pfnd;//排放浓度

	private String fl;//风量

	private String pfwz;//排放物质

	private String need;//治理要求

	private Date starttime;//开始时间

	private Date ystime;//验收时间

	private String governance;//目前治理状况(0 否 1 是) 

	private String hbzlqy;//

	private String hbzlgy;//

	private String hbzljh;//
	
	private String latitude;//经度

	private String longitude;//纬度
	
	private String userId;//添加人
	
	private String status;//1 意向客户
	/*********************************************************************/
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "cdistrict")
	public String getCdistrict() {
		return cdistrict;
	}

	public void setCdistrict(String cdistrict) {
		this.cdistrict = cdistrict;
	}

	@Column(name = "ydistrict")
	public String getYdistrict() {
		return ydistrict;
	}

	public void setYdistrict(String ydistrict) {
		this.ydistrict = ydistrict;
	}

	@Column(name = "pwname")
	public String getPwname() {
		return pwname;
	}

	public void setPwname(String pwname) {
		this.pwname = pwname;
	}

	@Column(name = "dlwz")
	public String getDlwz() {
		return dlwz;
	}

	public void setDlwz(String dlwz) {
		this.dlwz = dlwz;
	}

	@Column(name = "qylxr")
	public String getQylxr() {
		return qylxr;
	}

	public void setQylxr(String qylxr) {
		this.qylxr = qylxr;
	}

	@Column(name = "qyphone")
	public String getQyphone() {
		return qyphone;
	}

	public void setQyphone(String qyphone) {
		this.qyphone = qyphone;
	}

	@Column(name = "pwtype")
	public String getPwtype() {
		return pwtype;
	}

	public void setPwtype(String pwtype) {
		this.pwtype = pwtype;
	}

	@Column(name = "produce")
	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	@Column(name = "pfnd")
	public String getPfnd() {
		return pfnd;
	}

	public void setPfnd(String pfnd) {
		this.pfnd = pfnd;
	}

	@Column(name = "fl")
	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	@Column(name = "pfwz")
	public String getPfwz() {
		return pfwz;
	}

	public void setPfwz(String pfwz) {
		this.pfwz = pfwz;
	}

	@Column(name = "need")
	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	@Column(name = "starttime")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Column(name = "ystime")
	public Date getYstime() {
		return ystime;
	}

	public void setYstime(Date ystime) {
		this.ystime = ystime;
	}

	@Column(name = "governance")
	public String getGovernance() {
		return governance;
	}

	public void setGovernance(String governance) {
		this.governance = governance;
	}

	@Column(name = "hbzlqy")
	public String getHbzlqy() {
		return hbzlqy;
	}

	public void setHbzlqy(String hbzlqy) {
		this.hbzlqy = hbzlqy;
	}

	@Column(name = "hbzlgy")
	public String getHbzlgy() {
		return hbzlgy;
	}

	public void setHbzlgy(String hbzlgy) {
		this.hbzlgy = hbzlgy;
	}

	@Column(name = "hbzljh")
	public String getHbzljh() {
		return hbzljh;
	}

	public void setHbzljh(String hbzljh) {
		this.hbzljh = hbzljh;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	
	
}
