package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工单 - 区域负责人 中间表
 * @ClassName: WorkOrderAreaResp 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月30日 下午4:03:46 
 *
 */
@Entity
@Table(name="WORKORDER_AREARESP")
public class WorkOrderAreaResp extends BaseEntity  implements java.io.Serializable{

    private static final long serialVersionUID = -6176562911665745002L;
    
    //工单
    private String workOrder;
    //区域负责人
    private String areaResponsible;
    //接单 状态   0、未接单  1、接单
    private String state;
    

    @Column(name="work_order")
    public String getWorkOrder() {
        return workOrder;
    }
    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }
    
    @Column(name="area_responsible")
    public String getAreaResponsible() {
        return areaResponsible;
    }
    public void setAreaResponsible(String areaResponsible) {
        this.areaResponsible = areaResponsible;
    }
    
    @Column(name="state")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
}
