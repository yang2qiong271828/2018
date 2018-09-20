package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "jhpact")
public class Jhpact extends BaseEntity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5123893410865551998L;
    //回款阶段
    private String stage;
    //回款方式
    private String type;
    //约定应回款时间
    private Date date;
    //回款比例
    private Double proportion;
    //约定应回款金额
    private Double hkje;
    //是否开票
    private String iskp;
    //开票金额
    private Double kpje;
    //创建人
    private String userId;
    private String partnerId;
    //合同id
    private String htId;
    
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getProportion() {
        return proportion;
    }
    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
    public Double getHkje() {
        return hkje;
    }
    public void setHkje(Double hkje) {
        this.hkje = hkje;
    }
    public String getIskp() {
        return iskp;
    }
    public void setIskp(String iskp) {
        this.iskp = iskp;
    }
    public Double getKpje() {
        return kpje;
    }
    public void setKpje(Double kpje) {
        this.kpje = kpje;
    }
    
    public String getHtId() {
        return htId;
    }
    public void setHtId(String htId) {
        this.htId = htId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
