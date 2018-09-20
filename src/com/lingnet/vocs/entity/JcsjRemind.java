package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 提醒
 * @ClassName: JcsjRemind 
 * @Description: TODO 
 * @author 邹云鹏
 * @date 2014-12-5 上午11:48:37 
 *
 */
@Entity
@Table(name = "JCSJ_REMIND")
public class JcsjRemind extends BaseEntity implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -4591422861833469391L;
    
    private String createMan;//创建人
    private String operater;//操作人
    private String moduleName;//模块名称
    private String trxCode;//单据编号
    private String url;//链接地址
    private String content;//内容
    private String ynRemind;//是否提醒 0：不再提醒 1：提醒
    private String status;//完成状态 0：未完成 1：完成
    private String bz;//备注

    // Constructors

    /** default constructor */
    public JcsjRemind() {
    }

    /** full constructor */
    public JcsjRemind(String createMan, String operater, String moduleName,
            String trxCode, String url, String content, String ynRemind,
            String status,String bz) {
        this.createMan = createMan;
        this.operater = operater;
        this.moduleName = moduleName;
        this.trxCode = trxCode;
        this.url = url;
        this.content = content;
        this.ynRemind = ynRemind;
        this.status = status;
        this.bz = bz;
    }

    @Column(name = "CREATE_MAN")
    public String getCreateMan() {
        return this.createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    @Column(name = "OPERATER")
    public String getOperater() {
        return this.operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    @Column(name = "MODULE_NAME")
    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Column(name = "TRX_CODE")
    public String getTrxCode() {
        return this.trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    @Column(name = "URL")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "CONTENT")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "YN_REMIND", length = 1)
    public String getYnRemind() {
        return this.ynRemind;
    }

    public void setYnRemind(String ynRemind) {
        this.ynRemind = ynRemind;
    }

    @Column(name = "STATUS", length = 1)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}