package com.lingnet.vocs.entity;    
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "ITEM")
public class Item extends BaseEntity implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 8993951767844906987L; 
    
    public static final String DELETE = "1";
    
    private String itemCode;        //商品编号
    private String itemclassId;     //商品种类
    private String itemclass;       //种类名称
    private String name;            //名称
    private String des1;            //规格1
    private String des2;            //规格2
    private String unit;            //库存单位
    private String allowPo;          //是否允许采购
    private String allowSo;          //是否允许销售
    private String defaultPurLocId;    //默认收货仓库
    private Double locMinQty;              //最低库存量
    private Double locMaxQty;             //最高库存量
    private String remark;               //备注信息
    private String is_delete;
    private Double price;              //最低库存量
    private String ckname;              //品牌名称
    private double num;              //物料数量
    private String  perice;          //金额
    
    
    @Column(name = "ITEM_CODE")
    public String getItemCode() {
        return this.itemCode;    
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;    
    }


    @Column(name = "ITEMCLASS_ID")
    public String getItemclassId() {
        return this.itemclassId;    
    }

    public void setItemclassId(String itemclassId) {
        this.itemclassId = itemclassId;    
    }


    @Column(name = "NAME")
    public String getName() {
        return this.name;    
    }

    public void setName(String name) {
        this.name = name;    
    }

    @Column(name = "DES1")
    public String getDes1() {
        return this.des1;    
    }

    public void setDes1(String des1) {
        this.des1 = des1;    
    }

    @Column(name = "DES2")
    public String getDes2() {
        return this.des2;    
    }

    public void setDes2(String des2) {
        this.des2 = des2;    
    }

    @Column(name = "UNIT")
    public String getUnit() {
        return this.unit;    
    }

    public void setUnit(String unit) {
        this.unit = unit;    
    }


    @Column(name = "ALLOW_PO")
    public String getAllowPo() {
        return this.allowPo;    
    }

    public void setAllowPo(String allowPo) {
        this.allowPo = allowPo;    
    }


    @Column(name = "ALLOW_SO")
    public String getAllowSo() {
        return this.allowSo;    
    }

    public void setAllowSo(String allowSo) {
        this.allowSo = allowSo;    
    }


    @Column(name = "DEFAULT_PUR_LOC_ID")
    public String getDefaultPurLocId() {
        return this.defaultPurLocId;    
    }

    public void setDefaultPurLocId(String defaultPurLocId) {
        this.defaultPurLocId = defaultPurLocId;    
    }



    @Column(name = "LOC_MIN_QTY", precision = 18, scale = 4)
    public Double getLocMinQty() {
        return this.locMinQty;    
    }

    public void setLocMinQty(Double locMinQty) {
        this.locMinQty = locMinQty;    
    }

    @Column(name = "LOC_MAX_QTY", precision = 18, scale = 4)
    public Double getLocMaxQty() {
        return this.locMaxQty;    
    }

    public void setLocMaxQty(Double locMaxQty) {
        this.locMaxQty = locMaxQty;    
    }



    @Column(name = "REMARK")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;    
    }


    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    @Column(name = "PRICE", precision = 18, scale = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Column(name = "ckname")
    public String getCkname() {
        return ckname;
    }

    public void setCkname(String ckname) {
        this.ckname = ckname;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
    
    public String getPerice() {
        return perice;
    }

    public void setPerice(String perice) {
        this.perice = perice;
    }

    @Transient
    public String getItemclass() {
        return itemclass;
    }

    public void setItemclass(String itemclass) {
        this.itemclass = itemclass;
    }
    
    
    
}