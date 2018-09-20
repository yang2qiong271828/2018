package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name="SERVICE_OUTLAY")
public class ServiceOutlay extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5124885399573321276L;

    //服务费用
    private String price;
    //服务类别
    private String state;
    //服务区域
    private String area;
    //公司
    private String partnerId;
    
    
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    
}
