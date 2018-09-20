package com.lingnet.vocs.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GPS_ATLAS")
public class GpsAtlas implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -189769777822150522L;

    private String id;//id
    private long time;//时间戳
    private BigDecimal gpslat;//原始纬度
    private BigDecimal gpslon;//原始经度
    private BigDecimal lat;//谷歌纬度
    private BigDecimal lon;//谷歌经度
    private Integer speed;//GPS速度(km/h)
    private Integer radius;//定位精度(米)
    private String sn;
    
    
    
    public GpsAtlas() {
    }
    public GpsAtlas(String id, long time, BigDecimal gpslat, BigDecimal gpslon,
            BigDecimal lat, BigDecimal lon, Integer speed, Integer radius, String sn) {
        super();
        this.id = id;
        this.time = time;
        this.gpslat = gpslat;
        this.gpslon = gpslon;
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
        this.radius = radius;
        this.sn = sn;
    }
    
    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public BigDecimal getGpslat() {
        return gpslat;
    }
    public void setGpslat(BigDecimal gpslat) {
        this.gpslat = gpslat;
    }
    public BigDecimal getGpslon() {
        return gpslon;
    }
    public void setGpslon(BigDecimal gpslon) {
        this.gpslon = gpslon;
    }
    public BigDecimal getLat() {
        return lat;
    }
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
    public BigDecimal getLon() {
        return lon;
    }
    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
    public Integer getSpeed() {
        return speed;
    }
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public Integer getRadius() {
        return radius;
    }
    public void setRadius(Integer radius) {
        this.radius = radius;
    }
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
    
    
}
