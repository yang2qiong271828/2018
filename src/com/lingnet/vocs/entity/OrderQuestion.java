package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工单问题库
 * @ClassName: OrderQuestion 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年7月6日 下午14:28:34 
 *
 */
@Entity
@Table(name="ORDERQUESTION")
public class OrderQuestion extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 5485972033440727179L;

    private String bzwt;
    private String xswt;
    private String wtfl;
    private String bzda;
    private String gzsm;
    
    public String getBzwt() {
        return bzwt;
    }
    public void setBzwt(String bzwt) {
        this.bzwt = bzwt;
    }
    public String getXswt() {
        return xswt;
    }
    public void setXswt(String xswt) {
        this.xswt = xswt;
    }
    public String getWtfl() {
        return wtfl;
    }
    public void setWtfl(String wtfl) {
        this.wtfl = wtfl;
    }
    public String getBzda() {
        return bzda;
    }
    public void setBzda(String bzda) {
        this.bzda = bzda;
    }
    public String getGzsm() {
        return gzsm;
    }
    public void setGzsm(String gzsm) {
        this.gzsm = gzsm;
    }
}
