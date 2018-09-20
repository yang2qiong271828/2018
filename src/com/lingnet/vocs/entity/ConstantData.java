package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 实时数据保存表
 * @ClassName: ConstantData 
 * @Description: 根据时间获取最新数据保存
 * @author duanjj
 * @date 2017年7月4日 下午3:29:29 
 *
 */
@Entity
@Table(name = "constant_data")
public class ConstantData extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6820416721074491782L;
    
    private String fboxUid;//FBoxUID
    private Date timeStamp; //时间戳
    private String uid;//数据监控点UID
    private String name;//数据监控点名称
    private String value;//变量数值
    private String quality;//变量特性
    private String flag;//变量单位
    
    public ConstantData(){
        
    }
    
    public ConstantData(String fboxUid, Date timeStamp, String uid,
            String name, String value, String quality, String flag) {
        super();
        this.fboxUid = fboxUid;
        this.timeStamp = timeStamp;
        this.uid = uid;
        this.name = name;
        this.value = value;
        this.quality = quality;
        this.flag = flag;
    }

    public String getFboxUid() {
        return fboxUid;
    }

    public void setFboxUid(String fboxUid) {
        this.fboxUid = fboxUid;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
}
