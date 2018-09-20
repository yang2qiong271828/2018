package com.lingnet.vocs.entity;

public class OrderCount {

    private String wcl = "0";//未处理
    private String clz = "0";//处理中
    private String yjd = "0";//已结单
    private String ybh = "0";//已驳回
    private String yfq = "0";//已废弃
    private String sbgz = "0";//设备故障
    private String tfbj = "0";//脱附故障
    
    public String getWcl() {
        return wcl;
    }
    public void setWcl(String wcl) {
        this.wcl = wcl;
    }
    public String getClz() {
        return clz;
    }
    public void setClz(String clz) {
        this.clz = clz;
    }
    public String getYjd() {
        return yjd;
    }
    public void setYjd(String yjd) {
        this.yjd = yjd;
    }
    public String getYbh() {
        return ybh;
    }
    public void setYbh(String ybh) {
        this.ybh = ybh;
    }
    public String getSbgz() {
        return sbgz;
    }
    public void setSbgz(String sbgz) {
        this.sbgz = sbgz;
    }
    public String getTfbj() {
        return tfbj;
    }
    public void setTfbj(String tfbj) {
        this.tfbj = tfbj;
    }
    public String getYfq() {
        return yfq;
    }
    public void setYfq(String yfq) {
        this.yfq = yfq;
    }
    
}
