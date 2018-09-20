package com.lingnet.vocs.action.supplier;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.StringToDate;
import com.lingnet.vocs.entity.Machine;
import com.lingnet.vocs.entity.Supplier;
import com.lingnet.vocs.service.baseinfo.AreaService;
import com.lingnet.vocs.service.equipment.EquipmentUseService;
import com.lingnet.vocs.service.partner.ContactsService;
import com.lingnet.vocs.service.partner.PartnerService;
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

@Results({ @Result(name = "show1", type = "redirect", location = "http://61.149.215.198:18081/map") })
@Namespace("/supplier")
@ParentPackage("supplier")
public class SupplierAction extends BaseAction {

	private static final long serialVersionUID = 8434236536055234861L;
	

	@Resource(name = "partnerService")
	private PartnerService partnerService;

	
	@Resource(name = "supplierService")
	private SupplierService supplierService;

	@Resource(name = "scaseService")
	private ScaseService supplierCaseService;
	
	@Resource(name = "contactsService")
	private ContactsService contactsService;

	@Resource(name = "areaService")
	private AreaService areaService;
	
	@Resource(name = "equipmentUseService")
	private EquipmentUseService equipmentUseService;

	private String formdata;

	private String griddata;// 联系人列表

	private Supplier supplier;

	private String areaId;// 区域id
	
	private List<HashMap> classify;//案例分类
	
	private List<Machine> equipmentpic;//设备图展示
	
	private String createDate;
	
	private String issuedate;
	
	//省
	private String province;
	//市
	private String city;
	//区
	private String cdistrict;

	

	

	

	public String list() {
		return LIST;
	}

	public String add() {
		return ADD;
	}


	public String edit() {		

	    supplier = getBeanById(Supplier.class, id);
		return ADD;
	}

	public String look() {
		System.out.println(id);
		getRequest().setAttribute("flag", "false");
		supplier = getBeanById(Supplier.class, id);
		if (supplier.getCreateDate()!=null&&!"".equals(supplier.getCreateDate())) {
			createDate=(String) StringToDate.toDate(supplier.getCreateDate());
		}
		if (supplier.getSupplier_issuedate()!=null&&!"".equals(supplier.getSupplier_issuedate())){
			issuedate = (String) StringToDate.toDate(supplier.getSupplier_issuedate());
		}
		if (supplier.getSupplier_province()!=null&&!"".equals(supplier.getSupplier_province())) {
			
			province = partnerService.getCSQ(supplier.getSupplier_province());
		}
		if (supplier.getSupplier_city()!=null&&!"".equals(supplier.getSupplier_city())) {
			city = partnerService.getCSQ(supplier.getSupplier_city());
		}
		if (supplier.getSupplier_xzdistrict()!=null&&!"".equals(supplier.getSupplier_xzdistrict())) {
			cdistrict = partnerService.getCSQ(supplier.getSupplier_xzdistrict());
		}
		
		
		classify=supplierCaseService.findByClassify(id);
		equipmentpic = equipmentUseService.findBySupplierId(id);
		return VIEW;
	}
	
	public String remove() {
		try {
			supplierService.delete(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}
	
	public String getContactsBySupplierId() {
		String partnerId = getRequest().getParameter("partnerId");
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(supplierService.getContactsBySupplierId(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}
	
	public String getData() {
		return ajax(Status.success, supplierService.getData(pager, searchData, areaId, key));
	}

	public String saveOrUpdate() {
		try {
			supplier = JSONObject.parseObject(formdata, Supplier.class);
			supplierService.saveOrUpdate(supplier, griddata);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1), "text/html;charset=UTF-8");
		}
	}
	
	

	
	
	

	/***********getter & setter**********/
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public List<HashMap> getClassify() {
		return classify;
	}

	public void setClassify(List<HashMap> classify) {
		this.classify = classify;
	}

	public List<Machine> getEquipmentpic() {
		return equipmentpic;
	}

	public void setEquipmentpic(List<Machine> equipmentpic) {
		this.equipmentpic = equipmentpic;
	}
	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCdistrict() {
		return cdistrict;
	}

	public void setCdistrict(String cdistrict) {
		this.cdistrict = cdistrict;
	}
	
}
