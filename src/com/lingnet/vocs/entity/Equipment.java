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
 * 设备 实体
 * 
 * @ClassName: Equipment
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月21日 上午8:23:35
 *
 */
@Entity
@Table(name = "EQUIPMENT")
public class Equipment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1600250157614318512L;

    // 设备编号
    private String equipmentCode;
    // 设备类型
    private String equipmentType;
    // 设备名称
    private String name;
    // 设备型号
    private String model;
    // 生产日期
    private Date manufactureDate;
    // 出厂日期
    private Date productionDate;
    // 所属人
    private String owner;
    // 使用者
    private String equipmentUser;
    // 工作介质(废气组分)
    private String workingMedium;
    // 工作地点
    private String position;
    // 重量
    private String weight;
    // 工作温度
    private String temperature;
    // 处理负荷
    private String handlingLoad;
    // 活性炭类型
    private String carbonType;
    // 活性炭规格
    private String carbonSpec;
    // 活性炭厂家
    private String carbonManu;
    // 活性炭装填量
    private String carbonCapacity;
    // 活性炭装填时间
    private Date carbonLoadDate;
    // 设备长度
    private String eqLength;
    // 设备宽度
    private String eqWidth;
    // 设备高度
    private String eqHeight;
    // 操作者
    private String handlersId;
    // 风量
    private String airVolume;
    // 出口浓度
    private String exportConcentration;
    // 压差
    private String pressureDifference;
    // 图片
    private String imgpath;
    // 设备标识
    private String deviceIdentification;
    // 温度上限
    private String temperatureLimit;
    // 吸附容量
    private String adsorptionCapacity;
    // PLC设备标识码
    private String plcIdentificationCode;

    private String status; //0、离线 1、在线 2、故障
    // 临时字段
    // 所属人名称
    private String ownerName;
    // 开始时间 用于查询 出厂时间
    private Date beginDate;
    // 结束时间 用于查询 出厂时间
    private Date endDate;
    // 使用者
    private String eqUserName;// 使用者名字
    // 操作者公司名称
    private String handlersName;
    // 设备类型名称
    private String typeName;
    // gps设备sn
    private String gpsSn;

    /********************************************************************************************************/
    @Column(name = "equipment_code")
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Column(name = "equipment_type")
    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "equipment_user")
    public String getEquipmentUser() {
        return equipmentUser;
    }

    public void setEquipmentUser(String equipmentUser) {
        this.equipmentUser = equipmentUser;
    }

    @Column(name = "working_medium")
    public String getWorkingMedium() {
        return workingMedium;
    }

    public void setWorkingMedium(String workingMedium) {
        this.workingMedium = workingMedium;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "temperature")
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHandlingLoad() {
        return handlingLoad;
    }

    public void setHandlingLoad(String handlingLoad) {
        this.handlingLoad = handlingLoad;
    }

    @Column(name = "carbon_type")
    public String getCarbonType() {
        return carbonType;
    }

    public void setCarbonType(String carbonType) {
        this.carbonType = carbonType;
    }

    @Column(name = "carbon_spec")
    public String getCarbonSpec() {
        return carbonSpec;
    }

    public void setCarbonSpec(String carbonSpec) {
        this.carbonSpec = carbonSpec;
    }

    @Column(name = "carbon_manu")
    public String getCarbonManu() {
        return carbonManu;
    }

    public void setCarbonManu(String carbonManu) {
        this.carbonManu = carbonManu;
    }

    @Column(name = "carbon_capacity")
    public String getCarbonCapacity() {
        return carbonCapacity;
    }

    public void setCarbonCapacity(String carbonCapacity) {
        this.carbonCapacity = carbonCapacity;
    }

    @Column(name = "manufactureDate")
    @Temporal(TemporalType.DATE)
    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    @Column(name = "productionDate")
    @Temporal(TemporalType.DATE)
    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Column(name = "carbonLoad_date")
    @Temporal(TemporalType.DATE)
    public Date getCarbonLoadDate() {
        return carbonLoadDate;
    }

    public void setCarbonLoadDate(Date carbonLoadDate) {
        this.carbonLoadDate = carbonLoadDate;
    }

    public String getEqLength() {
        return eqLength;
    }

    public void setEqLength(String eqLength) {
        this.eqLength = eqLength;
    }

    public String getEqWidth() {
        return eqWidth;
    }

    public void setEqWidth(String eqWidth) {
        this.eqWidth = eqWidth;
    }

    public String getEqHeight() {
        return eqHeight;
    }

    public void setEqHeight(String eqHeight) {
        this.eqHeight = eqHeight;
    }

    @Transient
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Transient
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Transient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public String getEqUserName() {
        return eqUserName;
    }

    public void setEqUserName(String eqUserName) {
        this.eqUserName = eqUserName;
    }

    @Transient
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "air_volume")
    public String getAirVolume() {
        return airVolume;
    }

    public void setAirVolume(String airVolume) {
        this.airVolume = airVolume;
    }

    @Column(name = "export_concentration")
    public String getExportConcentration() {
        return exportConcentration;
    }

    public void setExportConcentration(String exportConcentration) {
        this.exportConcentration = exportConcentration;
    }

    @Column(name = "pressure_difference")
    public String getPressureDifference() {
        return pressureDifference;
    }

    public void setPressureDifference(String pressureDifference) {
        this.pressureDifference = pressureDifference;
    }

    @Column(name = "device_identification")
    public String getDeviceIdentification() {
        return deviceIdentification;
    }

    public void setDeviceIdentification(String deviceIdentification) {
        this.deviceIdentification = deviceIdentification;
    }

    @Column(name = "temperature_limit")
    public String getTemperatureLimit() {
        return temperatureLimit;
    }

    public void setTemperatureLimit(String temperatureLimit) {
        this.temperatureLimit = temperatureLimit;
    }

    @Column(name = "adsorption_capacity")
    public String getAdsorptionCapacity() {
        return adsorptionCapacity;
    }

    public void setAdsorptionCapacity(String adsorptionCapacity) {
        this.adsorptionCapacity = adsorptionCapacity;
    }

    @Column(name = "gps_sn")
    public String getGpsSn() {
        return gpsSn;
    }

    public void setGpsSn(String gpsSn) {
        this.gpsSn = gpsSn;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Column(name = "plc_identification_code")
    public String getPlcIdentificationCode() {
        return plcIdentificationCode;
    }

    public void setPlcIdentificationCode(String plcIdentificationCode) {
        this.plcIdentificationCode = plcIdentificationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
