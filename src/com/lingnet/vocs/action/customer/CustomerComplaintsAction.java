package com.lingnet.vocs.action.customer;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.customer.CustomerComplaintsDao;
import com.lingnet.vocs.entity.CustomerComplaints;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.customer.CustomerComplaintsService;

/**
 * 客户投诉
 * 
 * @ClassName: CustomerComplaintsAction
 * @Description: TODO
 * @author wanl
 * @date 2017年6月16日 下午5:22:51
 * 
 */

public class CustomerComplaintsAction extends BaseAction {

	private static final long serialVersionUID = 3077819679848484590L;

	@Resource(name = "customerComplaintsService")
	private CustomerComplaintsService customerComplaintsService;
	@Resource(name = "customerComplaintsDao")
	private CustomerComplaintsDao customerComplaintsDao;

	private CustomerComplaints customerComplaints;

	private String complaintsName;

	private String name;

	private String formdata;// form表单json

	private String clientType;

	@Resource(name = "toolUtil")
	private ToolUtil toolUtil;
	
	private String khtszt;
	/**
	 * 跳转投诉列表页面
	 * 
	 * @Title: list
	 * @return String
	 * @author wanl
	 * @since 2017年6月16日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 跳转增加投诉页面
	 * 
	 * @Title: add
	 * @return String
	 * @author wanl
	 * @since 2017年6月16日 V 1.0
	 */
	public String add() {
		if (customerComplaints == null) {
			customerComplaints = new CustomerComplaints();
		}
		customerComplaints.setStateOfComplaint("0");
		customerComplaints.setComplaintsDate(new Date());
		return ADD;
	}

	/**
	 * 跳转到投诉查看页面
	 * 
	 * @Title: look
	 * @return String
	 * @author wanl
	 * @since 2017年6月21日 V 1.0
	 */
	public String look() {
		customerComplaints = this.getBeanById(CustomerComplaints.class, id);
		if (customerComplaints == null) {
			customerComplaints = new CustomerComplaints();
		}
		Partner partner = this.getBeanById(Partner.class,
				customerComplaints.getComplaintsName());
		if (partner != null) {
			customerComplaints.setPartnerName(partner.getName());
		}
		return "look";
	}

	/**
	 * 投诉信息编辑
	 * 
	 * @Title: edit
	 * @return String
	 * @author wanl
	 * @since 2017年6月16日 V 1.0
	 */
	public String edit() {
		customerComplaints = this.getBeanById(CustomerComplaints.class, id);
		if (customerComplaints == null) {
			customerComplaints = new CustomerComplaints();
		}
		Partner partner = this.getBeanById(Partner.class,
				customerComplaints.getComplaintsName());
		if (partner != null) {
			customerComplaints.setPartnerName(partner.getName());
		}
		return ADD;
	}

	/**
	 * 跳转弹出选择客户页面
	 * 
	 * @Title: customerlist
	 * @return String
	 * @author wanl
	 * @since 2017年6月22日 V 1.0
	 */
	public String customerlist() {

		return "customerlist";
	}

	/**
	 * 全部投诉列表查询方法
	 * 
	 * @Title: getComplaintsList
	 * @return String
	 * @author wanl
	 * @since 2017年6月19日 V 1.0
	 */
	public String getComplaintsListData() {
		String partnerId = this.getSession("partnerId").toString();
		return ajax(Status.success,
				customerComplaintsService.getListData(pager, partnerId,khtszt));
	}

	/**
	 * 添加客户投诉信息时查询客户列表
	 * 
	 * @Title: getCustomerListData
	 * @return String
	 * @author wanl
	 * @since 2017年6月22日 V 1.0
	 */
	public String getCustomerListData() {
		return ajax(Status.success,
				customerComplaintsService.getCustomerListData(clientType, name,
						pager));
	}

	/**
	 * 选择客户时根据选中的客户名自动查询客户信息
	 * 
	 * @Title: getClientData
	 * @return String
	 * @author wanl
	 * @since 2017年6月21日 V 1.0
	 */
	public String getClientData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("complaintsName", complaintsName);
		return ajax(Status.success, customerComplaintsDao.getClientData(map));
	}

	/**
	 * 同一客户的所有投诉信息查询方法
	 * 
	 * @Title: getPersonalComplaintsList
	 * @return String
	 * @author wanl
	 * @since 2017年6月21日 V 1.0
	 */
	public String getPersonalComplaintsListData() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		// try {
		// complaintsName = URLDecoder.decode(complaintsName, "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		map.put("complaintsName", complaintsName);
		return ajax(Status.success,
				customerComplaintsService.getPersonalComplaintsListData(map,
						pager));
	}

	/**
	 * 保存更新方法
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author wanl
	 * @since 2017年6月16日 V 1.0
	 */
	public String saveOrUpdate() {
		customerComplaints = JsonUtil.toObject(formdata,
				CustomerComplaints.class);
		// QxUsers users = this.getBeanByCriteria(QxUsers.class,
		// Restrictions.eq("username", ToolUtil.userName()));
		// Partner partner = this.getBeanByCriteria(Partner.class,
		// Restrictions.eq("id", users.getPartnerId()));
		customerComplaints.setEnterThePerson(toolUtil.getUserId());
		operate("客户投诉", "添加投诉", "");
		try {
			return ajax(Status.success,
					customerComplaintsService.saveOrUpdate(customerComplaints));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 删除客户投诉信息
	 * 
	 * @Title: delete
	 * @return String
	 * @author wanl
	 * @since 2017年6月21日 V 1.0
	 */
	public String remove() {
		customerComplaintsService.remove(ids);
		operate("客户投诉", "删除", "");
		// customerComplaintsService.delete(ids);
		return ajax(Status.success, "success");
	}

	/**
	 * 结单
	 * 
	 * @Title: changeStatus
	 * @return String
	 * @author wanl
	 * @since 2017年7月4日 V 1.0
	 */
	public String changeStatus() {
		customerComplaintsDao.changeStatus(id);
		operate("客户投诉", "结单", "");
		return ajax(Status.success, "success");
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////

	public CustomerComplaints getCustomerComplaints() {
		return customerComplaints;
	}

	public void setCustomerComplaints(CustomerComplaints customerComplaints) {
		this.customerComplaints = customerComplaints;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getComplaintsName() {
		return complaintsName;
	}

	public void setComplaintsName(String complaintsName) {
		this.complaintsName = complaintsName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getKhtszt() {
		return khtszt;
	}

	public void setKhtszt(String khtszt) {
		this.khtszt = khtszt;
	}
	
	
}
