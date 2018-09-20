package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.lingnet.common.entity.BaseEntity;

/** 仓库设置 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "JCSJ_CK")
public class JcsjCk extends BaseEntity implements java.io.Serializable{

    private static final long serialVersionUID = -6734830669962672919L;
    
    public static final String DELETE = "1";
    
    /**
     * 仓库代号(CKDH)
     */
    public String ckdh;
    /**
     * 仓库名称(CKMC)
     */
    
    public String ckmc;
    /**
     * 供应商代号(GYSDH)
     */
    public String gysdh;
    /**
     * 地点(DD)
     */
    public String dd;
    /**
     * 盘点状态(PDZT)
     */
    public int pdzt;
    /**
     * MRP
     */
    public int mrp;
    /**
     * 外协仓(WXC)
     */
    public int wxc;
    /**
     * 预留区(YLQ)
     */
    public int ylq;
    /**
     * 临时收货区(LSSHQ)
     */
    public int lsshq;
    /**
     * 样品检验仓(YPJYC)
     */
    public int ypjyc;
    /**
     * IQC合格区(IQCHGQ)
     */
    public int iqchgq;
    /**
     * IQC坏品区(IQCHPQ)
     */
    public int iqchpq;
    /**
     * 采购退货仓(CGTHC)
     */
    public int cgthc;
    /**
     * 发料区(FLQ)
     */
    public int flq;
    /**
     * WIP区(WIP)
     */
    public int wip;
    /**
     * WIP坏料退回区(WIPHLTHQ)
     */
    public int wiphlthq;
    /**
     * WIP好料退回区(WIPHALTHQ)
     */
    public int wiphalthq;
    /**
     * 成品区(CPQ)
     */
    public int cpq;
    /**
     * FQC合格区(FQCHGQ)
     */
    public int fqchgq;
    /**
     * FQC坏品区(FQCHPQ)
     */
    public int fqchpq;
    /**
     * 反退仓(FTC)
     */
    public int ftc;
    /**
     * 出货仓(CHC)
     */
    public int chc;
    /**
     * 客退区(KTQ)
     */
    public int ktq;
    /**
     * 商品分仓控制(WLFCKZ)
     */
    public int wlfckz;
    /**
     * 商品入库区(CPRKQ)
     */
    public int cprkq;
    /**
     * 父类ID(PARENT_ID)
     */
    public String parent_id;
    
    /**
     * 部门ID
     */
    public String department_id;
    
    //逄阳 2014-4-25
    public String status;
    
    public String remark;
    
    public String is_delete;//删除状态

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 仓库代号(CKDH)
     * @return 仓库代号(CKDH)
     */
    @Column(name = "CKDH")
    public String getCkdh() {
        return ckdh;
    }

    /**
     * 仓库代号(CKDH)
     * @param ckdh 仓库代号(CKDH)
     */
    public void setCkdh(String ckdh) {
        this.ckdh = ckdh;
    }

    /**
     * 仓库名称(CKMC)
     * @return 仓库名称(CKMC)
     */
    @Column(name = "CKMC")
    public String getCkmc() {
        return ckmc;
    }

    /**
     * 仓库名称(CKMC)
     * @param ckmc 仓库名称(CKMC)
     */
    public void setCkmc(String ckmc) {
        this.ckmc = ckmc;
    }

    /**
     * 供应商代号(GYSDH)
     * @return 供应商代号(GYSDH)
     */
    @Column(name = "GYSDH")
    public String getGysdh() {
        return gysdh;
    }

    /**
     * 供应商代号(GYSDH)
     * @param gysdh 供应商代号(GYSDH)
     */
    public void setGysdh(String gysdh) {
        this.gysdh = gysdh;
    }

    /**
     * 地点(DD)
     * @return 地点(DD)
     */
    @Column(name = "DD")
    public String getDd() {
        return dd;
    }

    /**
     * 地点(DD)
     * @param dd 地点(DD)
     */
    public void setDd(String dd) {
        this.dd = dd;
    }

    /**
     * 盘点状态(PDZT)
     * @return 盘点状态(PDZT)
     */
    @Column(name = "PDZT")
    public int getPdzt() {
        return pdzt;
    }

    /**
     * 盘点状态(PDZT)
     * @param pdzt 盘点状态(PDZT)
     */
    public void setPdzt(int pdzt) {
        this.pdzt = pdzt;
    }

    /**
     * MRP
     * @return MRP
     */
    @Column(name = "MRP")
    public int getMrp() {
        return mrp;
    }

    /**
     * MRP
     * @param mrp MRP
     */
    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    /**
     * 外协仓(WXC)
     * @return 外协仓(WXC)
     */
    @Column(name = "WXC")
    public int getWxc() {
        return wxc;
    }

    /**
     * 外协仓(WXC)
     * @param wxc 外协仓(WXC)
     */
    public void setWxc(int wxc) {
        this.wxc = wxc;
    }

    /**
     * 预留区(YLQ)
     * @return 预留区(YLQ)
     */
    @Column(name = "YLQ")
    public int getYlq() {
        return ylq;
    }

    /**
     * 预留区(YLQ)
     * @param ylq 预留区(YLQ)
     */
    public void setYlq(int ylq) {
        this.ylq = ylq;
    }

    /**
     * 临时收货区(LSSHQ)
     * @return 临时收货区(LSSHQ)
     */
    @Column(name = "LSSHQ")
    public int getLsshq() {
        return lsshq;
    }

    /**
     * 临时收货区(LSSHQ)
     * @param lsshq 临时收货区(LSSHQ)
     */
    public void setLsshq(int lsshq) {
        this.lsshq = lsshq;
    }

    /**
     * 样品检验仓(YPJYC)
     * @return 样品检验仓(YPJYC)
     */
    @Column(name = "YPJYC")
    public int getYpjyc() {
        return ypjyc;
    }

    /**
     * 样品检验仓(YPJYC)
     * @param ypjyc 样品检验仓(YPJYC)
     */
    public void setYpjyc(int ypjyc) {
        this.ypjyc = ypjyc;
    }

    /**
     * IQC合格区(IQCHGQ)
     * @return IQC合格区(IQCHGQ)
     */
    @Column(name = "IQCHGQ")
    public int getIqchgq() {
        return iqchgq;
    }

    /**
     * IQC合格区(IQCHGQ)
     * @param iqchgq IQC合格区(IQCHGQ)
     */
    public void setIqchgq(int iqchgq) {
        this.iqchgq = iqchgq;
    }

    /**
     * IQC坏品区(IQCHPQ)
     * @return IQC坏品区(IQCHPQ)
     */
    @Column(name = "IQCHPQ")
    public int getIqchpq() {
        return iqchpq;
    }

    /**
     * IQC坏品区(IQCHPQ)
     * @param iqchpq IQC坏品区(IQCHPQ)
     */
    public void setIqchpq(int iqchpq) {
        this.iqchpq = iqchpq;
    }

    /**
     * 采购退货仓(CGTHC)
     * @return 采购退货仓(CGTHC)
     */
    @Column(name = "CGTHC")
    public int getCgthc() {
        return cgthc;
    }

    /**
     * 采购退货仓(CGTHC)
     * @param cgthc 采购退货仓(CGTHC)
     */
    public void setCgthc(int cgthc) {
        this.cgthc = cgthc;
    }

    /**
     * 发料区(FLQ)
     * @return 发料区(FLQ)
     */
    @Column(name = "FLQ")
    public int getFlq() {
        return flq;
    }

    /**
     * 发料区(FLQ)
     * @param flq 发料区(FLQ)
     */
    public void setFlq(int flq) {
        this.flq = flq;
    }

    /**
     * WIP区(WIP)
     * @return WIP区(WIP)
     */
    @Column(name = "WIP")
    public int getWip() {
        return wip;
    }

    /**
     * WIP区(WIP)
     * @param wip WIP区(WIP)
     */
    public void setWip(int wip) {
        this.wip = wip;
    }

    /**
     * WIP坏料退回区(WIPHLTHQ)
     * @return WIP坏料退回区(WIPHLTHQ)
     */
    @Column(name = "WIPHLTHQ")
    public int getWiphlthq() {
        return wiphlthq;
    }

    /**
     * WIP坏料退回区(WIPHLTHQ)
     * @param wiphlthq WIP坏料退回区(WIPHLTHQ)
     */
    public void setWiphlthq(int wiphlthq) {
        this.wiphlthq = wiphlthq;
    }

    /**
     * WIP好料退回区(WIPHALTHQ)
     * @return WIP好料退回区(WIPHALTHQ)
     */
    @Column(name = "WIPHALTHQ")
    public int getWiphalthq() {
        return wiphalthq;
    }

    /**
     * WIP好料退回区(WIPHALTHQ)
     * @param wiphalthq WIP好料退回区(WIPHALTHQ)
     */
    public void setWiphalthq(int wiphalthq) {
        this.wiphalthq = wiphalthq;
    }

    /**
     * 成品区(CPQ)
     * @return 成品区(CPQ)
     */
    @Column(name = "CPQ")
    public int getCpq() {
        return cpq;
    }

    /**
     * 成品区(CPQ)
     * @param cpq 成品区(CPQ)
     */
    public void setCpq(int cpq) {
        this.cpq = cpq;
    }

    /**
     * FQC合格区(FQCHGQ)
     * @return FQC合格区(FQCHGQ)
     */
    @Column(name = "FQCHGQ")
    public int getFqchgq() {
        return fqchgq;
    }

    /**
     * FQC合格区(FQCHGQ)
     * @param fqchgq FQC合格区(FQCHGQ)
     */
    public void setFqchgq(int fqchgq) {
        this.fqchgq = fqchgq;
    }

    /**
     * FQC坏品区(FQCHPQ)
     * @return FQC坏品区(FQCHPQ)
     */
    @Column(name = "FQCHPQ")
    public int getFqchpq() {
        return fqchpq;
    }

    /**
     * FQC坏品区(FQCHPQ)
     * @param fqchpq FQC坏品区(FQCHPQ)
     */
    public void setFqchpq(int fqchpq) {
        this.fqchpq = fqchpq;
    }

    /**
     * 反退仓(FTC)
     * @return 反退仓(FTC)
     */
    @Column(name = "FTC")
    public int getFtc() {
        return ftc;
    }

    /**
     * 反退仓(FTC)
     * @param ftc 反退仓(FTC)
     */
    public void setFtc(int ftc) {
        this.ftc = ftc;
    }

    /**
     * 出货仓(CHC)
     * @return 出货仓(CHC)
     */
    @Column(name = "CHC")
    public int getChc() {
        return chc;
    }

    /**
     * 出货仓(CHC)
     * @param chc 出货仓(CHC)
     */
    public void setChc(int chc) {
        this.chc = chc;
    }

    /**
     * 客退区(KTQ)
     * @return 客退区(KTQ)
     */
    @Column(name = "KTQ")
    public int getKtq() {
        return ktq;
    }

    /**
     * 客退区(KTQ)
     * @param ktq 客退区(KTQ)
     */
    public void setKtq(int ktq) {
        this.ktq = ktq;
    }

    /**
     * 商品分仓控制(WLFCKZ)
     * @return 商品分仓控制(WLFCKZ)
     */
    @Column(name = "WLFCKZ")
    public int getWlfckz() {
        return wlfckz;
    }

    /**
     * 商品分仓控制(WLFCKZ)
     * @param wlfckz 商品分仓控制(WLFCKZ)
     */
    public void setWlfckz(int wlfckz) {
        this.wlfckz = wlfckz;
    }

    /**
     * 商品入库区(CPRKQ)
     * @return 商品入库区(CPRKQ)
     */
    @Column(name = "CPRKQ")
    public int getCprkq() {
        return cprkq;
    }

    /**
     * 商品入库区(CPRKQ)
     * @param cprkq 商品入库区(CPRKQ)
     */
    public void setCprkq(int cprkq) {
        this.cprkq = cprkq;
    }
    
    
    //判断是否为父级仓库
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 父类ID(PARENT_ID)
     * @return 父类ID(PARENT_ID)
     */
    @Column(name = "PARENT_ID")
    public String getParent_id() {
        return parent_id;
    }

    /**
     * 父类ID(PARENT_ID)
     * @param parent_id 父类ID(PARENT_ID)
     */
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    @Column(name = "DEPARTMENT_ID")
    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }
    
}
