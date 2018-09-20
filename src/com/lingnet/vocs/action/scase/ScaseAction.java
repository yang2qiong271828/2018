package com.lingnet.vocs.action.scase;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.util.JsonUtil;

import com.lingnet.vocs.entity.Supplier;
import com.lingnet.vocs.entity.SupplierCase;
import com.lingnet.vocs.service.scase.ScaseService;
import com.lingnet.vocs.service.supplier.SupplierService;


/**
 * 
 * @ClassName: SupplierAction
 * @Description: TODO
 * @author yangqiong
 * @date 2018年4月16日   15:05:33
 * 
 */

@Namespace("/scase")
@ParentPackage("scase")
public class ScaseAction extends BaseAction {
	

	@Resource(name = "scaseService")
	private ScaseService supplierCaseService;
	
	@Resource(name = "supplierService")
	private SupplierService supplierService;
	
	private String formdata;

	
	
	
	private String griddata;// 联系人列表

	private SupplierCase supplierCase;

	private String areaId;// 区域id
	
	
	
	

	

	public String list() {
		return LIST;
	}
	
	public String add() {
		return ADD;
	}

	public String edit() {
		supplierCase = getBeanById(SupplierCase.class, id);
		return ADD;
	}

	public String look() {
		getRequest().setAttribute("flag", "false");
		supplierCase = getBeanById(SupplierCase.class, id);
		return VIEW;
	}
	
	public String delete() {
		supplierCaseService.delete(id);
		return ajax(Status.success, "success");
		
	}
	
	public String saveOrUpdate() {
		try {
			supplierCase = JSONObject.parseObject(formdata, SupplierCase.class);
			String flg = supplierCaseService.saveOrUpdate(supplierCase);
			if (flg.equals("success")) {
				return ajax(Status.success, "success");
			} else {
				return ajax(Status.error, flg);
			}
		} catch (Exception e) {
			return ajax(Status.error,
					e.toString().substring(e.toString().indexOf(":") + 1));
		}
	}
	
	
	public String getData() {
		return ajax(Status.success, supplierCaseService.getData(pager, searchData, areaId, key));
	}
	
	
	
	
	
	
	
	//get 和   set 

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getGriddata() {
		return griddata;
	}

	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}

	public SupplierCase getSupplierCase() {
		return supplierCase;
	}

	public void setSupplierCase(SupplierCase supplierCase) {
		this.supplierCase = supplierCase;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
	

	
	
}
