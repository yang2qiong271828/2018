package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;


/**
 * 设备 转移记录
 * @ClassName: Equipment 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月21日 上午8:23:35 
 *
 */
@Entity
@Table(name = "TRANSFER")
public class Transfer extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -5580151614178613554L;

    //设备Id
    private  String equipmentId;
    //合同Id   所属公司id
    private  String contractId;
    //合同到期时间
    private  Date contractEndDate;
    //合同类型  1试用 2 购买 3 租赁
    private  String coutractType;
    //拥有者类型  合作商或者客户
    private  String partnerOrCustomer;
    //所属人ID
    private  String ownerId;
    //操作者
    private String handlersId;
    
    //临时字段
    //所属人 公司名称
    private String ownerName;
    //合同编号
    private String contractCode;
    //设备编号
    private String equipmentCode;
    //操作者公司名称
    private String handlersName;
    private String porc;
    private String cont;
    
    private String isDeleted;
    
    /******************************************************************************************************************************/
    public String getEquipmentId() {
        return equipmentId;
    }
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getContractId() {
        return contractId;
    }
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    @Temporal(TemporalType.DATE)
    public Date getContractEndDate() {
        return contractEndDate;
    }
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }
    
    public String getCoutractType() {
        return coutractType;
    }
    public void setCoutractType(String coutractType) {
        this.coutractType = coutractType;
    }
    @Column(name="partOrCust")
    public String getPartnerOrCustomer() {
        return partnerOrCustomer;
    }
    public void setPartnerOrCustomer(String partnerOrCustomer) {
        this.partnerOrCustomer = partnerOrCustomer;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    @Transient 
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    @Transient 
    public String getContractCode() {
        return contractCode;
    }
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    @Transient 
    public String getEquipmentCode() {
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
    
    public String getHandlersId() {
        return handlersId;
    }
    public void setHandlersId(String handlersId) {
        this.handlersId = handlersId;
    }
    @Transient
    public String getHandlersName() {
        return handlersName;
    }
    public void setHandlersName(String handlersName) {
        this.handlersName = handlersName;
    }
    @Transient
    public String getPorc() {
        return porc;
    }
    public void setPorc(String porc) {
        this.porc = porc;
    }
    @Transient
    public String getCont() {
        return cont;
    }
    public void setCont(String cont) {
        this.cont = cont;
    }

    @Column(name = "is_deleted")
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
