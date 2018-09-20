package com.lingnet.vocs.action.alarm;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.CustFeedback;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.service.alarm.CustFeedbackService;

/**
 * 客户异常反馈
 * 
 * @ClassName: CustFeedbackAction
 * @Description: TODO
 * @author zy
 * @date 2017年7月1日 下午2:53:13
 * 
 */

public class CustFeedbackAction extends BaseAction{

    private static final long serialVersionUID = 2001060871364883805L;
    @Resource(name = "custFeedbackService")
    private CustFeedbackService custFeedbackService;
    private CustFeedback custFeedback;
    private String formdata;

    /**
     * 客户异常反馈 展示页面
     * 
     * @Title: list
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    public String list(){
        return LIST;
    }

    /**
     * 客户异常反馈 获取列表
     * 
     * @Title: getListData
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public String getListData(){
        HashMap mapResult = custFeedbackService.getListData(pager, key);
        String json = JsonUtil.Encode(mapResult);
        return ajax(Status.success, json);
    }
    
    /**
     * 客户异常反馈 新增
     * 
     * @Title: add
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    public String add() {
        return ADD;
    }
    
    /**
     * 客户异常反馈 编辑
     * 
     * @Title: edit
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    public String edit() {
        custFeedback = custFeedbackService.get(CustFeedback.class, id);
        // 设置客户中文名
        Partner p = custFeedbackService.get(Partner.class,custFeedback.getEquipmentUserId());
        custFeedback.setEquipmentUserName(null == p ? "" : p.getName());
        return ADD;
    }
    
    /**
     * 客户异常反馈 查看
     * 
     * @Title: look
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    public String look() {
        getRequest().setAttribute("flag", "false");
        custFeedback = custFeedbackService.get(CustFeedback.class, id);
        // 设置客户中文名
        Partner p = custFeedbackService.get(Partner.class,custFeedback.getEquipmentUserId());
        custFeedback.setEquipmentUserName(null == p ? "" : p.getName());
        return ADD;
    }
    
    /**
     * 转至工单
     * 
     * @Title: transfer
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    public String transfer() {
        WorkOrder workOrder = new WorkOrder();
        custFeedback = custFeedbackService.get(CustFeedback.class, Restrictions.eq("id", id));
        if (null != custFeedback) {
            workOrder.setWorkOrderTitle(custFeedback.getName());
            workOrder.setFaultExplain(custFeedback.getDetail());
            workOrder.setEquipmentCode(custFeedback.getEquipmentCode());
            workOrder.setFaultType(custFeedback.getType());
            workOrder.setContacts(custFeedback.getContact());
            workOrder.setCustomerPhone(custFeedback.getPhone());
            workOrder.setWorkOrderType("1");
            workOrder.setTransferOrdersId(custFeedback.getId());
        }
        Equipment equip = custFeedbackService.get(Equipment.class,custFeedback.getEquipmentId());
        if(null!=equip){
            Partner p = custFeedbackService.get(Partner.class,equip.getEquipmentUser());
            if (null != p) {
                workOrder.setCustomer(p.getId());
                workOrder.setCustomerName(p.getName());
                workOrder.setCustomerAddress(p.getAddress());
            }
        }
        getRequest().setAttribute("workOrder", workOrder);
        return "transfer";
    }

    /**
     * 保存或更新
     * 
     * @Title: saveOrUpdate
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            custFeedback = JsonUtil.toObject(formdata, CustFeedback.class);
            if (null != custFeedback.getFaultDate()) {
                Long lOld = custFeedback.getFaultDate().getTime();
                custFeedback.setFaultDate(new Date(lOld - 28800000));
            }
            custFeedbackService.saveOrUpdate(custFeedback);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
                    "text/html;charset=UTF-8");
        }
    }
    /**
     * 客户异常反馈 移除
     * 
     * @Title: remove
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    public String remove(){
        try {
            custFeedbackService.remove(ids[0]);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "删除失败");
        }
    }
    
    /**
     * 废弃
     * 工单提交
     * 
     * @Title: submitWorkOrder
     * @return
     * @author zy
     * @since 2017年7月6日 V 1.0
     */
    public String submitWorkOrder() {
        try {
            // custFeedbackService.submitWorkOrder(id);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.error,
                    e.toString().substring(e.toString().indexOf(":") + 1));
        }
    }

    /**
     * @Title: getEquipmentUser
     * @return
     * @author zy
     * @since 2017年7月7日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String getEquipmentUser() {
        Equipment equip = custFeedbackService.get(Equipment.class, key);
        Partner p = custFeedbackService.get(Partner.class,
                equip.getEquipmentUser());
        if (null != p) {
            String s = JsonUtil.Encode(p);
            return ajax(Status.success, s);
        } else {
            HashMap m = new HashMap();
            m.put("error", "equipCodeNull");
            m.put("message",  "设备：" + equip.getEquipmentCode()+ "使用者为空");
            return ajax(Status.error, JsonUtil.Encode(m));
        }
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

    public CustFeedback getCustFeedback() {
        return custFeedback;
    }

    public void setCustFeedback(CustFeedback custFeedback) {
        this.custFeedback = custFeedback;
    }
    
}
