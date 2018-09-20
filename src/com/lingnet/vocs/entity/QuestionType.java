package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name="question_type")
public class QuestionType extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -7494836660501361154L;
    //类别名称
    private String typeName;
    //类别描述
    private String discribe;
    //父节点
    private String pId;
    
    private String pName;
    
   @Column(name="type_name")
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    @Column(name="discribe")
    public String getDiscribe() {
        return discribe;
    }
    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }
    
    @Column(name="pid")
    public String getpId() {
        return pId;
    }
    public void setpId(String pId) {
        this.pId = pId;
    }
    @Transient
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }
    
}
