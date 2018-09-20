package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: Area 
 * @Description: 地区表
 * @author adam
 * @date 2016-2-5 下午2:34:41 
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true) 
@Table(name = "b_area")
public class Area extends BaseEntity implements java.io.Serializable{

    
    private static final long serialVersionUID = -432769497415686657L;
    
    /** 名称  */
    private String name;
    /** 层级  */
    private Integer level;
    /** 父id  */
    private String pid;
    /** 排序  */
    private Integer sort;
    /** 全名 */
    private String fullname;
    private Integer stopflag; //是否停用(0:否;1:是)
    private String areacode; //地区编号
    private String areadesp; //地区描述 
    private String latitude;// 纬度
	private String longitude;// 经度
    
    public Area() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "LEVELS")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getStopflag() {
        return stopflag;
    }

    public void setStopflag(Integer stopflag) {
        this.stopflag = stopflag;
    }

    @Column(name = "AREACODE", length = 50)
    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    @Column(name = "AREADESP", length = 50)
    public String getAreadesp() {
        return areadesp;
    }

    public void setAreadesp(String areadesp) {
        this.areadesp = areadesp;
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
