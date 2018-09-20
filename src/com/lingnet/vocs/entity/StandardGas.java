package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "STANDARD_GAS")
public class StandardGas extends BaseEntity  implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -8735027092246854554L;
    
    private String code;//编码
    private String casCode;//CAS编码
    private String chName;//中文名称
    private String enName;//英文名称
    private String spec;//规格
    
    
    
    
    public StandardGas() {
    }
    public StandardGas(String code, String casCode, String chName,
            String enName, String spec) {
        super();
        this.code = code;
        this.casCode = casCode;
        this.chName = chName;
        this.enName = enName;
        this.spec = spec;
    }
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "CAS_CODE")
    public String getCasCode() {
        return casCode;
    }
    public void setCasCode(String casCode) {
        this.casCode = casCode;
    }
    @Column(name = "CH_NAME")
    public String getChName() {
        return chName;
    }
    public void setChName(String chName) {
        this.chName = chName;
    }
    @Column(name = "EN_NAME")
    public String getEnName() {
        return enName;
    }
    public void setEnName(String enName) {
        this.enName = enName;
    }
    @Column(name = "SPEC")
    public String getSpec() {
        return spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }

    
}
