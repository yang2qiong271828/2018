package com.lingnet.vocs.action.workorder;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.OrderCount;
import com.lingnet.vocs.service.workorder.OrderCountService;

/**
 * 工单统计
 * 
 * @ClassName: OrderCountAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月4日 上午8:06:17
 * 
 */
public class OrderCountAction extends BaseAction {

	private static final long serialVersionUID = -1817257427855062811L;

	@Resource(name = "orderCountService")
	private OrderCountService orderCountService;

	private OrderCount orderCount;

	public String method;// 统计方式

	private String option;

	private String key;

	public String list() {
		return "list";
	}

	public String listdata() {
		String json = orderCountService.getListData(pager, id);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/************************************************************************************************************************************/

	public OrderCount getOrderCount() {
		return orderCount;
	}

	public OrderCountService getOrderCountService() {
		return orderCountService;
	}

	public void setOrderCountService(OrderCountService orderCountService) {
		this.orderCountService = orderCountService;
	}

	public void setOrderCount(OrderCount orderCount) {
		this.orderCount = orderCount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
