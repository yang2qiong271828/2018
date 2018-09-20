package com.lingnet.vocs.action.workorder;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.ServiceOutlay;
import com.lingnet.vocs.service.workorder.ServiceOutlayService;

/**
 * 维护保养费
 * 
 * @ClassName: ServiceOutlayAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月15日 下午1:56:15
 * 
 */
public class ServiceOutlayAction extends BaseAction {

	private static final long serialVersionUID = -8646430481288770180L;

	private HashMap<String, String> service;

	public String formdata;

	@Resource(name = "serviceOutlayService")
	public ServiceOutlayService serviceOutlayService;

	public ServiceOutlay serviceOutlay;

	public String area;

	public String list() {

		return LIST;
	}

	public String getListData() {
		String partnerId = this.getSession("partnerId").toString();
		String json = "";
		json = serviceOutlayService.getListData(pager, partnerId, area);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String add() {

		return ADD;
	}

	public String saveOrUpdate() {
		serviceOutlay = JsonUtil.toObject(formdata, ServiceOutlay.class);
		String partnerId = this.getSession("partnerId").toString();
		serviceOutlay.setPartnerId(partnerId);
		try {
			String result = "";
			result = serviceOutlayService.saveOrUpdate(serviceOutlay);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String remove() {
		operate("维护费用管理", "删除", "");
		serviceOutlayService.delete(ids);
		return ajax(Status.success, "success");
	}

	public String edit() {
		serviceOutlay = serviceOutlayService.get(ServiceOutlay.class, id);
		if (serviceOutlay == null) {
			serviceOutlay = new ServiceOutlay();
		}
		return ADD;
	}

	public String look() {
		serviceOutlay = serviceOutlayService.get(ServiceOutlay.class, id);
		if (serviceOutlay == null) {
			serviceOutlay = new ServiceOutlay();
		}
		return "look";
	}

	public String commit() {

		return ajax(Status.success, "success");
	}

	public String adjust() {
		service = new HashMap<String, String>();
		service.put("cust", "客户A");
		service.put("text", "设备损坏，换修");
		service.put("perice", "100");
		service.put("class", "1");
		service.put("dat", "2016-10-25 ");
		service.put("paid", "50");
		service.put("adjust", "20");
		return "adjust";
	}

	/******************************************* get set ********************************************/
	public HashMap<String, String> getService() {
		return service;
	}

	public void setService(HashMap<String, String> service) {
		this.service = service;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public ServiceOutlayService getServiceOutlayService() {
		return serviceOutlayService;
	}

	public void setServiceOutlayService(
			ServiceOutlayService serviceOutlayService) {
		this.serviceOutlayService = serviceOutlayService;
	}

	public ServiceOutlay getServiceOutlay() {
		return serviceOutlay;
	}

	public void setServiceOutlay(ServiceOutlay serviceOutlay) {
		this.serviceOutlay = serviceOutlay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
