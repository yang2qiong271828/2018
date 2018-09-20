package com.lingnet.vocs.action.maintenance;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.RegularMaintenance;
import com.lingnet.vocs.service.maintenance.MaintenanceService;

@ParentPackage("maintenance")
@Namespace("/maintenance")
public class MaintenanceAction extends BaseAction {
	@Resource(name = "maintenanceService")
	private MaintenanceService maintenanceService;
	
	
	private String formdata;
	private RegularMaintenance regularMaintenance;
	
	
	
	
	/**
	 * 保存或更新
	 * 
	 * @Title: saveOrUpdate
	 * @return
	 * @author lxy
	 * @since 2018年5月24日 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			regularMaintenance = JsonUtil.toObject(formdata, RegularMaintenance.class);
			maintenanceService.saveOrUpdate(regularMaintenance);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
		
	}
	
	
	
	
	
	
	
	
	

	public RegularMaintenance getRegularMaintenance() {
		return regularMaintenance;
	}

	public void setRegularMaintenance(RegularMaintenance regularMaintenance) {
		this.regularMaintenance = regularMaintenance;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
}
