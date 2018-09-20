/**
 * @HomePageAction.java
 * @com.lingnet.vocs.action.homepage
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2017
 * @version V
 * @since 2017-7-11
 */
package com.lingnet.vocs.action.homepage;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.vocs.entity.WorkBench;
import com.lingnet.vocs.service.alarm.AbnormalAlarmService;
import com.lingnet.vocs.service.alarm.AbnormalHintService;
import com.lingnet.vocs.service.equipment.EquipmentService;
import com.lingnet.vocs.service.finance.ContractService;
import com.lingnet.vocs.service.workorder.WorkOrderService;

/**
 * @ClassName: HomePageAction
 * @Description: 首页
 * @author xuhp
 * @date 2017-7-11 下午2:44:00
 * 
 */

public class HomePageAction extends BaseAction implements Serializable {
	private static final long serialVersionUID = -2176943337187722630L;
	@Resource(name = "contractService")
	private ContractService contractService;
	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;
	@Resource(name = "abnormalAlarmService")
	private AbnormalAlarmService abnormalAlarmService;
	@Resource(name = "abnormalHintService")
	private AbnormalHintService abnormalHintService;
	@Resource(name = "workOrderService")
	private WorkOrderService workOrderService;

	private String content;// 内容

	/**
	 * 到期合同提醒
	 * 
	 * @Title: contractList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-11 V 1.0
	 */
	public String contractList() {
		return "contract_list";
	}

	/**
	 * 到期合同提醒数据
	 * 
	 * @Title: getContractListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-11 V 1.0
	 */
	public String getContractListData() {
		String json = "";
		try {
			json = contractService.getContractListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 设备到期提醒
	 * 
	 * @Title: equipmentList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-12 V 1.0
	 */
	public String equipmentList() {
		return "equipment_list";
	}

	/**
	 * 设备到期提醒数据
	 * 
	 * @Title: getEquipmentListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-12 V 1.0
	 */
	public String getEquipmentListData() {
		String json = "";
		try {
			json = equipmentService.getEquipmentListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 异常报警信息
	 * 
	 * @Title: alarmList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String alarmList() {
		return "alarm_list";
	}

	/**
	 * 异常报警信息数据
	 * 
	 * @Title: getAlarmListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String getAlarmListData() {
		String json = "";
		try {
			json = abnormalAlarmService.getAlarmListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 报警提示信息
	 * 
	 * @Title: hintList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String hintList() {
		return "hint_list";
	}

	/**
	 * 报警提示信息数据
	 * 
	 * @Title: getHintListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String getHintListData() {
		String json = "";
		try {
			json = abnormalHintService.getHintListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 我的工单
	 * 
	 * @Title: myOrderList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String myOrderList() {
		return "my_order_list";
	}

	/**
	 * 我的工单数据
	 * 
	 * @Title: getMyOrderListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String getMyOrderListData() {
		String json = "";
		try {
			json = workOrderService.getMyOrderListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 待指派工单（运维）-工单录入
	 * 
	 * @Title: orderList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String orderList() {
		return "order_list";
	}

	/**
	 * 待指派工单（运维）数据
	 * 
	 * @Title: getOrderListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String getOrderListData() {
		String json = "";
		try {
			json = workOrderService.getOrderListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 待指派工单（区域）-合作商工单
	 * 
	 * @Title: orderGroupList
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String orderGroupList() {
		return "order_group_list";
	}

	/**
	 * 待指派工单（区域）数据
	 * 
	 * @Title: getOrderGroupListData
	 * @return String
	 * @author xuhp
	 * @since 2017-7-13 V 1.0
	 */
	public String getOrderGroupListData() {
		String json = "";
		try {
			json = workOrderService.getOrderGroupListData(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, json);
	}

	/**
	 * 查看系统工作台内容
	 * 
	 * @Title: contentView
	 * @return String
	 * @author xuhp
	 * @since 2017-7-12 V 1.0
	 */
	public String contentView() {
		WorkBench workbench = getBeanByCriteria(WorkBench.class,
				Restrictions.eq("id", id));
		if (workbench != null) {
			content = workbench.getContent();
		}
		return "content";
	}

	// ////////////////////////////////////////////////////////
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
