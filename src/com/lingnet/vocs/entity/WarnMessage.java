package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 报警信息保存表
 * @ClassName: WarnMessage 
 * @Description: 通过时间获取最新数据并保存于该表
 * @author duanjj
 * @date 2017年7月4日 下午3:28:49 
 *
 */
@Entity
@Table(name = "warn_message")
public class WarnMessage extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5951662047495352770L;
    private String fboxUid;//FBoxUID
    private Date timeStamp; //时间戳
    private String warningContent;//预警文本内容
    private String uid;//预警记录UID
    private String name;//预警记录名称
    
    public WarnMessage(){
        
    }
    
    public WarnMessage(String fboxUid, Date timeStamp, String warningContent,
            String uid, String name) {
        super();
        this.fboxUid = fboxUid;
        this.timeStamp = timeStamp;
        this.warningContent = warningContent;
        this.uid = uid;
        this.name = name;
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
    public String getWarningContent() {
        return warningContent;
    }
    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent;
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
    
    
}
