package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name="WO_LOG")
public class WorkOrderLog extends BaseEntity implements java.io.Serializable{
    
    private static final long serialVersionUID = -6343473640402407789L;
    
    private String czUser;//操作用户
    private Date czDate;//操作时间
    private String czType;//操作类型
    private String czdj;//操作的哪张单据
    private String hfnr;//回复内容
    private String fzorhz;
    //临时字段
    private String czName;//操作用户姓名
    
    
    public String getCzUser() {
        return czUser;
    }
    public void setCzUser(String czUser) {
        this.czUser = czUser;
    }
    public Date getCzDate() {
        return czDate;
    }
    public void setCzDate(Date czDate) {
        this.czDate = czDate;
    }
    public String getCzType() {
        return czType;
    }
    public void setCzType(String czType) {
        this.czType = czType;
    }
    public String getCzdj() {
        return czdj;
    }
    public void setCzdj(String czdj) {
        this.czdj = czdj;
    }
    public String getHfnr() {
        return hfnr;
    }
    public void setHfnr(String hfnr) {
        this.hfnr = hfnr;
    }
    @Transient
    public String getCzName() {
        return czName;
    }
    public void setCzName(String czName) {
        this.czName = czName;
    }
	public String getFzorhz() {
		return fzorhz;
	}
	public void setFzorhz(String fzorhz) {
		this.fzorhz = fzorhz;
	}
    
}
