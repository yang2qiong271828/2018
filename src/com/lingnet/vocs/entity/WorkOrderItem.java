package com.lingnet.vocs.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工单物料
 * @ClassName: WorkOrderItem 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年7月6日 上午8:15:31 
 *
 */
@Entity
@Table(name="WO_ITEM")
public class WorkOrderItem extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8590422093305682822L;
    
    private String workOrder;
  
    private String item;

    private double num;
    
    private double price;

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
