package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name="AREA_RESPONSIBLE")
public class AreaResponsible extends BaseEntity implements Serializable  {

    private static final long serialVersionUID = -8752094602128702487L;
    //负责人名称
    private String name;
    //负责人编号
    private String code;
    //区域名称
    private String areaName;
    //负责 区域
    private String area;
    //负责人 性别
    private String sex;
    //负责人 电话
    private String phone;
    //负责人 手机号
    private String cellPhone;
    //负责人邮箱
    private String email;
    //公司
    private String partnerId;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCellPhone() {
        return cellPhone;
    }
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    @Column(name="area_name")
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
}
