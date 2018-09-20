package com.lingnet.vocs.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "joint")
public class Joint extends BaseEntity implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 184013492725504278L;

    private String name;
    private String imgpath;
    private String tel;
    private String xmjy;
    private String userId;
    private String username;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getXmjy() {
        return xmjy;
    }
    public void setXmjy(String xmjy) {
        this.xmjy = xmjy;
    }
    public String getImgpath() {
        return imgpath;
    }
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
