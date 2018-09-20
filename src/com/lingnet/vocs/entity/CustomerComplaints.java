package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

/**
 * 客户投诉实体类
 * 
 * @ClassName: CustomerComplaints
 * @Description: TODO
 * @author wanl
 * @date 2017年7月1日 下午5:19:05
 * 
 */

@Entity
@Table(name = "CUSTOMER_COMPLAINTS")
public class CustomerComplaints extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3634032678704420092L;

    private String complaintsName;// 客户id
    private String complaintsPhone;// 投诉人电话(非客户联系电话)
    private Date complaintsDate;// 投诉时间
    private String complaintContent;// 投诉内容
    private String stateOfComplaint;// 投诉状态(0未处理,1已处理)
    private String processResult;// 处理结果
    private String enterThePerson;

    private String partnerName;

    @Column(name = "complaintsName")
    public String getComplaintsName() {
        return complaintsName;
    }

    public void setComplaintsName(String complaintsName) {
        this.complaintsName = complaintsName;
    }

    public String getComplaintsPhone() {
        return complaintsPhone;
    }

    public void setComplaintsPhone(String complaintsPhone) {
        this.complaintsPhone = complaintsPhone;
    }

    public Date getComplaintsDate() {
        return complaintsDate;
    }

    public void setComplaintsDate(Date complaintsDate) {
        this.complaintsDate = complaintsDate;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public String getStateOfComplaint() {
        return stateOfComplaint;
    }

    public void setStateOfComplaint(String stateOfComplaint) {
        this.stateOfComplaint = stateOfComplaint;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getEnterThePerson() {
        return enterThePerson;
    }

    public void setEnterThePerson(String enterThePerson) {
        this.enterThePerson = enterThePerson;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

}
