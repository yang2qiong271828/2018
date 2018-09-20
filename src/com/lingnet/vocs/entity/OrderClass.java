package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name="ORDERCLASS")
public class OrderClass extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -7494836660501361154L;
    //类别名称
    private String typeName;
    //类别描述
    private String discribe;
    //父节点
    private String partnerId;
    
    private String pName;
    
   @Column(name="type_name")
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getDiscribe() {
        return discribe;
    }
    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }
    @Column(name="pId")
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    @Transient
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }
    
}
