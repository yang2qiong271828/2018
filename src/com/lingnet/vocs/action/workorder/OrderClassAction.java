package com.lingnet.vocs.action.workorder;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.OrderClass;
import com.lingnet.vocs.service.workorder.OrderClassService;

/**
 * 工单问题分类
 * 
 * @ClassName: OrderClassAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月29日 上午10:37:45
 * 
 */
@Controller
public class OrderClassAction extends BaseAction {

	private static final long serialVersionUID = 7111726885822373478L;

	private OrderClass orderClass;

	private String formdata;

	@Resource(name = "orderClassService")
	private OrderClassService orderClassService;

	public String add() {
		return ADD;
	}

	public String saveOrUpdate() {
		orderClass = JsonUtil.toObject(formdata, OrderClass.class);
		try {
			String result = "";
			result = orderClassService.saveOrUpdate(orderClass);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String edit() {
		orderClass = orderClassService.get(OrderClass.class, id);
		if (orderClass == null) {
			orderClass = new OrderClass();
		}
		OrderClass orc = orderClassService.get(OrderClass.class,
				orderClass.getPartnerId());
		if (orc != null) {
			orderClass.setpName(orc.getTypeName());
		}
		return ADD;
	}

	public String remove() {
		try {
			orderClassService.remove(ids);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String getListData() {
		String json = orderClassService.getListData(pager);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String list() {
		return "list";
	}

	public String tree() {
		return "tree";
	}

	public String showParent() {

		return "showparent";
	}

	/****************************************************************************************/
	public OrderClass getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(OrderClass orderClass) {
		this.orderClass = orderClass;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public OrderClassService getOrderClassService() {
		return orderClassService;
	}

	public void setOrderClassService(OrderClassService orderClassService) {
		this.orderClassService = orderClassService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
