package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 设备使用记录
 * @ClassName: EquipmentUsageLog 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月26日 下午2:50:10 
 *
 */
@Entity
@Table(name = "EQUIPMENT_USAGELOG")
public class EquipmentUsageLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1282351512284113673L;
    //设备编号
    private String equipmentCode;
    //设备名称
    private String equipmentName;
    //设备  类型
    private String equipmentType;
    //使用开始时间
    private Date usageStart;
    //使用结束时间
    private Date usageEnd;
    //使用地点
    private String usagePlace;
    //使用人
    private String usageCustomer;
    //维护人
    private String maintenancePerson;
    
    /************************************ get  set   ***************************************************************/
    @Column(name="equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
    @Column(name="equipment_name")
    public String getEquipmentName() {
        return equipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    @Column(name="equipment_type")
    public String getEquipmentType() {
        return equipmentType;
    }
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
    public Date getUsageStart() {
        return usageStart;
    }
    public void setUsageStart(Date usageStart) {
        this.usageStart = usageStart;
    }
    public Date getUsageEnd() {
        return usageEnd;
    }
    public void setUsageEnd(Date usageEnd) {
        this.usageEnd = usageEnd;
    }
    @Column(name="usage_place")
    public String getUsagePlace() {
        return usagePlace;
    }
    public void setUsagePlace(String usagePlace) {
        this.usagePlace = usagePlace;
    }
    @Column(name="usage_customer")
    public String getUsageCustomer() {
        return usageCustomer;
    }
    public void setUsageCustomer(String usageCustomer) {
        this.usageCustomer = usageCustomer;
    }
    @Column(name="maintenance_person")
    public String getMaintenancePerson() {
        return maintenancePerson;
    }
    public void setMaintenancePerson(String maintenancePerson) {
        this.maintenancePerson = maintenancePerson;
    }
    
    
    
}
