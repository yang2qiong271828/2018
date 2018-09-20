package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工单
 * 
 * @ClassName: WorkOrder
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月28日 上午8:54:43
 *
 */
@Entity
@Table(name = "WORKORDER")
public class WorkOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4299230960187789412L;

    // 标题
    private String workOrderTitle;
    // 工单号
    private String workOrderCode;
    // 工单 类型
    private String workOrderType;
    // 故障类别
    private String faultType;
    // 商定维修时间
    private String expectDate;
    // 优先级
    private String workOrderLevel;
    // 状态0.新建 1.已发布 2.指派中 3.待处理 4.待评价 5.已评价 6.已结单 7.转派中 8.已废弃 9.已退回 10.处理中
    private String state;
    // 设备编号
    private String equipmentCode;
    // 工单来源
    private String resource;
    // 创建人
    private String createperson;
    // 客户
    private String customer;
    // 客户 联系人
    private String contacts;
    // 客户 电话
    private String customerPhone;
    // 客户 手机号
    private String customerCellphone;
    // 客户 地址
    private String customerAddress;
    // 地区关联总负责人
    private String partner;
    // 地区关联总负责人 电话
    private String partnerPhone;
    // 地区关联总负责人 手机号
    private String partnerCellphone;
    // 区域责任人
    private String replayPerson;
    // 区域责任人 电话
    private String replayPhone;
    // 区域责任人 手机号
    private String replayCellphone;
    // 销售人
    private String sales;
    // 销售人电话呢
    private String salesPhone;
    // 特殊要求
    private String specificReq;
    // 故障说明
    private String faultExplain;
    // 附件
    private String uploadFile;
    // 工单跟进
    private String workOrderFollow;
    // 接单时间
    private Date receive;
    // 结单时间
    private Date checkDate;
    // 申请转派原因
    private String cause;
    // 应收物料费用
    private Double recItemCharges;
    // 实收物料费用
    private Double reaItemCharges;
    // 应收维护费用
    private Double recMainterCharges;
    // 时候维护费用
    private Double reaMainterCharges;
    // 转工单id
    private String transferOrdersId;
    // 实际维修时间
    private String actualMaintenanceDate;
    // 评分
    private String score;
    // 意见
    private String opinion;
    //客户确认签名
    private String confirm;
    //客户确认签字时间
    private Date confirmDate;
    
    private String province;// 省
    private String city;// 市
    private String district;// 区
    
    // 临时字段 用于接收转派页面传入的多个维护人员id
    private String replayPersonList;
    private String customerName;
    private String partnerName;
    
    private String startTime;
    
    private String endTime;
    // 处理结果
    private String hfjg;
    private String czType;
    

    /********************************************************** get set ****************************************************/
    @Column(name = "work_order_title")
    public String getWorkOrderTitle() {
        return workOrderTitle;
    }

    public void setWorkOrderTitle(String workOrderTitle) {
        this.workOrderTitle = workOrderTitle;
    }

    @Column(name = "work_order_code")
    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    @Column(name = "work_order_type")
    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    @Column(name = "fault_type")
    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    @Column(name = "expect_date")
    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    @Column(name = "work_order_level")
    public String getWorkOrderLevel() {
        return workOrderLevel;
    }

    public void setWorkOrderLevel(String workOrderlevel) {
        this.workOrderLevel = workOrderlevel;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Column(name = "resource")
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Column(name = "createperson")
    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    @Column(name = "customer")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "customer_phone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Column(name = "customer_cell_phone")
    public String getCustomerCellphone() {
        return customerCellphone;
    }

    public void setCustomerCellphone(String customerCellphone) {
        this.customerCellphone = customerCellphone;
    }

    @Column(name = "partner")
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Column(name = "partner_phone")
    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    @Column(name = "partner_cell_phone")
    public String getPartnerCellphone() {
        return partnerCellphone;
    }

    public void setPartnerCellphone(String partnerCellphone) {
        this.partnerCellphone = partnerCellphone;
    }

    @Column(name = "replay_person")
    public String getReplayPerson() {
        return replayPerson;
    }

    public void setReplayPerson(String replayPerson) {
        this.replayPerson = replayPerson;
    }

    @Column(name = "replay_phone")
    public String getReplayPhone() {
        return replayPhone;
    }

    public void setReplayPhone(String replayPhone) {
        this.replayPhone = replayPhone;
    }

    @Column(name = "replay_cell_phone")
    public String getReplayCellphone() {
        return replayCellphone;
    }

    public void setReplayCellphone(String replayCellphone) {
        this.replayCellphone = replayCellphone;
    }

    @Column(name = "sales")
    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    @Column(name = "sales_phone")
    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    @Column(name = "specific_req")
    public String getSpecificReq() {
        return specificReq;
    }

    public void setSpecificReq(String specificReq) {
        this.specificReq = specificReq;
    }

    @Column(name = "fault_explain")
    public String getFaultExplain() {
        return faultExplain;
    }

    public void setFaultExplain(String faultExplain) {
        this.faultExplain = faultExplain;
    }

    @Column(name = "upload_file")
    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    @Column(name = "work_order_follow")
    public String getWorkOrderFollow() {
        return workOrderFollow;
    }

    public void setWorkOrderFollow(String workOrderFollow) {
        this.workOrderFollow = workOrderFollow;
    }

    @Column(name = "customer_address")
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Column(name = "check_date")
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Transient
    public String getReplayPersonList() {
        return replayPersonList;
    }

    public void setReplayPersonList(String replayPersonList) {
        this.replayPersonList = replayPersonList;
    }

    @Column(name = "rec_item_charges")
    public Double getRecItemCharges() {
        return recItemCharges;
    }

    public void setRecItemCharges(Double recItemCharges) {
        this.recItemCharges = recItemCharges;
    }

    @Column(name = "rea_item_charges")
    public Double getReaItemCharges() {
        return reaItemCharges;
    }

    public void setReaItemCharges(Double reaItemCharges) {
        this.reaItemCharges = reaItemCharges;
    }

    @Column(name = "rec_mainter_charges")
    public Double getRecMainterCharges() {
        return recMainterCharges;
    }

    public void setRecMainterCharges(Double recMainterCharges) {
        this.recMainterCharges = recMainterCharges;
    }

    @Column(name = "rea_mainter_charges")
    public Double getReaMainterCharges() {
        return reaMainterCharges;
    }

    public void setReaMainterCharges(Double reaMainterCharges) {
        this.reaMainterCharges = reaMainterCharges;
    }

    @Transient
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Transient
    public String getHfjg() {
        return hfjg;
    }

    public void setHfjg(String hfjg) {
        this.hfjg = hfjg;
    }
    public Date getReceive() {
        return receive;
    }
    public void setReceive(Date receive) {
        this.receive = receive;
    }

    @Column(name = "transfer_orders_id")
    public String getTransferOrdersId() {
        return transferOrdersId;
    }

    public void setTransferOrdersId(String transferOrdersId) {
        this.transferOrdersId = transferOrdersId;
    }
    @Column(name = "actual_maintenanceDate")
    public String getActualMaintenanceDate() {
        return actualMaintenanceDate;
    }

    public void setActualMaintenanceDate(String actualMaintenanceDate) {
        this.actualMaintenanceDate = actualMaintenanceDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    @Transient
    public String getCzType() {
        return czType;
    }

    public void setCzType(String czType) {
        this.czType = czType;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    @Column(name="confirm_date")
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

  
}
