package com.lingnet.vocs.action.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.vocs.dao.partner.ContactsDao;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Contract;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.ProjectPreparation;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.service.baseinfo.CategoryService;
import com.lingnet.vocs.service.customer.CustomerService;
import com.lingnet.vocs.service.equipment.EquipmentUsagelogService;
import com.lingnet.vocs.service.finance.ContractService;
import com.lingnet.vocs.service.partner.PartnerService;
import com.lingnet.vocs.service.sewage.SewageService;

public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 8521820161005025046L;
	private String provinceId;// 省份id
	private String cityId;// 市区id
	private Partner partner;// 客户实体类
	private Contacts contacts;// 联系人
	private String formdata;// 主页面数据
	private String griddata;// 子页面数据
	private String name;

	@Resource(name = "customerService")
	private CustomerService customerService;// 客户
	@Resource(name = "partnerService")
	private PartnerService partnerService;// 合作商
	@Resource(name = "contactsDao")
	private ContactsDao contactsDao;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "equipmentUsagelogService")
	private EquipmentUsagelogService eulservice;
	@Resource(name = "adminService")
	private AdminService adminService;
	@Resource(name = "contractService")
	private ContractService contractService;
	@Resource(name = "sewageService")
    private SewageService sewageService;

	private ProjectPreparation projectPreparation;//报备表
	private Sewage sewage;//排污企业表
	
	
	

	private String higherAgentName;
	private String str;// 开始时间
	private String end;// 结束时间
	private String year;
	private String month;
	private String higherAgent;
	private String flag;
	private String partnerName;
	private String areaId;// 区域id
	private String status;

	/**
	 * VOCS所有客户展现页面
	 * 
	 * @Title: list
	 * @return String
	 * @author zmf
	 * @since 2016-5-31 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 当前用户下的数据显示
	 * 
	 * @Title: myList
	 * @return String
	 * @author lizk
	 * @since 2017年7月3日 V 1.0
	 */
	public String myList() {
		return "mylist";
	}

	/**
	 * 选择客户
	 * 
	 * @Title: xzList
	 * @return String
	 * @author lizk
	 * @since 2017年7月13日 V 1.0
	 */
	public String xzList() {
		return "xzlist";
	}

	/**
	 * 新增VOCS客户
	 * 
	 * @Title: add
	 * @return String
	 * @author zmf
	 * @since 2016-5-31 V 1.0
	 */
	public String add() {
		// 将用户名存入request作用域
		QxUsers user = adminService.get(QxUsers.class,
				Restrictions.eq("username", LingUtil.userName()));
		String userId = user.getPartnerId();
		Partner p = partnerService.get(Partner.class, userId);// 上级归属
		higherAgentName = p.getName();
		partner = new Partner();
		partner.setHigherAgent(p.getId());
		return ADD;
	}

	public String myAdd() {
		// 将用户名存入request作用域
		QxUsers user = adminService.get(QxUsers.class,
				Restrictions.eq("username", LingUtil.userName()));
		String userId = user.getPartnerId();
		Partner p = partnerService.get(Partner.class, userId);// 上级归属
		higherAgentName = p.getName();
		higherAgent = p.getId();
		return "myadd";
	}

	/**
	 * 编辑VOCS客户
	 * 
	 * @Title: list
	 * @return String
	 * @author zmf
	 * @since 2016-5-31 V 1.0
	 */
	public String edit() {
	/*	partner = partnerService.get(Partner.class, id);
		Partner p = partnerService.get(Partner.class,
				Restrictions.eq("id", partner.getHigherAgent()));// 上级归属
		higherAgentName = p.getName();
		partner.setHigherAgent(partner.getHigherAgent());*/
		
	    sewage = getBeanById(Sewage.class, id);
		//projectPreparation.setCusComName(sewage.getPwname());
		return "edit";
	}


	public String myEdit() {
		partner = partnerService.get(Partner.class, id);

		QxUsers user = adminService.get(QxUsers.class,
				Restrictions.eq("username", LingUtil.userName()));
		String userId = user.getPartnerId();
		Partner p = partnerService.get(Partner.class, userId);// 上级归属
		higherAgentName = p.getName();
		return "myadd";
	}

	public String look() {
		partner = partnerService.get(Partner.class, id);
		Partner p = partnerService.get(Partner.class, partner.getHigherAgent());// 上级归属
		higherAgentName = p.getName();
		flag = "false";
		return ADD;
	}

	public String myLook() {
		partner = partnerService.get(Partner.class, id);
		Partner p = partnerService.get(Partner.class, partner.getHigherAgent());// 上级归属
		higherAgentName = p.getName();
		flag = "false";
		return "myadd";
	}

	/**
	 * 查看VOCS客户
	 * 
	 * @Title: view
	 * @return String
	 * @author zmf
	 * @since 2016-6-28 V 1.0
	 */
	public String view() {
		return VIEW;
	}

	/**
	 * 获取VOCS客户信息
	 * 
	 * @Title: getListData
	 * @return String
	 * @author zmf
	 * @since 2016-8-12 V 1.0
	 */
	public String getListData() {
		String json = customerService.getListData(pager, name, key,
				partnerName, areaId);
		return ajax(Status.success, json);
	}

	/**
	 * 我的客户列表
	 * 
	 * @Title: getMyListData
	 * @return String
	 * @author lizk
	 * @since 2017年7月13日 V 1.0
	 */
	public String getMyListData() {
		String json = customerService.getMyListData(pager, name, key, areaId);
		return ajax(Status.success, json);
	}

	/**
	 * 客户选择
	 * 
	 * @Title: getListData
	 * @return String
	 * @author lizk
	 * @since 2017年7月13日 V 1.0
	 */
	public String getListCustomer() {
		String json = customerService.getListCustomer(pager, name, key);
		return ajax(Status.success, json);
	}

	/**
	 * 保存或编辑VOCS客户
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author zmf
	 * @since 2016-8-12 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			return ajax(Status.success,
					customerService.saveOrUpdate(formdata, griddata));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 保存或编辑当前用户的客户
	 * 
	 * @Title: saveUpdate
	 * @return String
	 * @author lizk
	 * @since 2017年7月4日 V 1.0
	 */
	public String saveUpdate() {
		try {
			return ajax(Status.success,
					customerService.saveUpdate(formdata, griddata));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * VOCS客户--删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author zmf
	 * @since 2017-06-01 V 1.0
	 */
	public String remove() {
		try {
			List<Contract> c = contractService.getList(Contract.class,
					Restrictions.eq("companyId", id));
			if (c.size() > 0) {
				throw new Exception("该客户存在合同不能删除");
			}
			Partner p = partnerService.get(Partner.class, ids[0]);
			p.setIsDeleted("1");
			partnerService.update(p);
			operate("客户管理", "删除", LingUtil.userName());
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}

	/**
	 * VOCS客户--删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author zmf
	 * @since 2017-06-01 V 1.0
	 */
	public String myRemove() {
		try {
			List<Contract> c = contractService.getList(Contract.class,
					Restrictions.eq("companyId", id));
			if (c.size() > 0) {
				throw new Exception("该客户存在合同不能删除");
			}
			Partner p = partnerService.get(Partner.class, ids[0]);
			p.setIsDeleted("1");
			partnerService.update(p);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}

	/**
	 * Vocs 信息统计页面
	 * 
	 * @Title: vocsindex
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月20日 V 1.0
	 */
	public String vocsindex() {
		return "vocsindex";
	}

	/**
	 * 设备使用频次
	 * 
	 * @Title: getSbList
	 * @return String
	 * @author lizk
	 * @since 2017年6月30日 V 1.0
	 */
	public String getSbList() {
		String json = "";
		try {
			json = customerService.getSbList(str, end, id);
			return ajax(Status.success, json);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, e.getMessage());
		}

	}

	/**
	 * 频次详细信息
	 * 
	 * @Title: getSbPcxxList
	 * @return String
	 * @author lizk
	 * @since 2017年6月30日 V 1.0
	 */
	public String getSbPcxxList() {
		String json = customerService.getSbPcxxList(pager, str, end, id, year,
				month);
		return ajax(Status.success, json);
	}

	/**
	 * Vocs 信息统计 左侧列表
	 * 
	 * @Title: getMenu
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月20日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getMenu() {
		List<Partner> p = partnerService.getList(Partner.class,
				Restrictions.ne("id", "1"));
		ArrayList arrlist = new ArrayList();
		for (int i = 0; i < p.size(); i++) {
			HashMap map = new HashMap();
			map.put("id", p.get(i).getId());
			map.put("text", p.get(i).getName());
			map.put("pid", p.get(i).getHigherAgent());
			if ("P".equals(p.get(i).getPartnerOrCustomer())) {
				map.put("img", "../template/system/images/hezuos.png");
			} else {
				map.put("img", "../template/system/images/kehu.png");
			}
			arrlist.add(map);
		}
		String json = JsonUtil.Encode(arrlist);
		return ajax(Status.success, json);
	}

	/**
	 * VOCs处理量
	 * 
	 * @Title: vocsoperation
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月7日 V 1.0
	 */
	public String vocsoperation() {
		return "vocsoperation";
	}

	public String frequency() {
		return "frequency";
	}

	public String capacity() {
		return "capacity";
	}

	/**
	 * VOCs达标情况
	 * 
	 * @Title: vocsuptostandard
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月7日 V 1.0
	 */
	public String vocsuptostandard() {
		return "vocsuptostandard";
	}

	/**
	 * VOCs异常情况
	 * 
	 * @Title: vocsabnormal
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月7日 V 1.0
	 */
	public String vocsabnormal() {
		return "vocsabnormal";
	}

	/**
	 * VOCs使用频次
	 * 
	 * @Title: vocsfrequence
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月7日 V 1.0
	 */
	public String vocsfrequence() {
		return "vocsfrequence";
	}

	/**
	 * @Title: map
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月7日 V 1.0
	 */
	public String map() {
		return "map";
	}

	/**
	 * @Title: getChartCustomerData
	 * @return String
	 * @author zmf
	 * @since 2017年6月7日 V 1.0
	 */
	public String getChartCustomerData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"city\":\"合作商1\", \"handling\":\"100000\" }"
				+ " ,{ \"id\":\"2\",  \"city\":\"合作商2\", \"handling\":\"90000\"}"
				+ " ,{ \"id\":\"3\",  \"city\":\"客户1\", \"handling\":\"80000\" }"
				+ " ,{ \"id\":\"4\",  \"city\":\"客户2\", \"handling\":\"70000\" }"
				+ " ,{ \"id\":\"5\",  \"city\":\"客户3\", \"handling\":\"60000\"}"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getChartFrequencyData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"equipment\":\"设备A\", \"handling\":\"100\" }"
				+ " ,{ \"id\":\"2\",  \"equipment\":\"设备B\", \"handling\":\"90\"}"
				+ " ,{ \"id\":\"3\",  \"equipment\":\"设备C\", \"handling\":\"80\" }"
				+ " ,{ \"id\":\"4\",  \"equipment\":\"设备D\", \"handling\":\"70\" }"
				+ " ,{ \"id\":\"5\",  \"equipment\":\"设备E\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"6\",  \"equipment\":\"设备F\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"7\",  \"equipment\":\"设备G\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"8\",  \"equipment\":\"设备H\", \"handling\":\"30\" }"
				+ " ,{ \"id\":\"9\",  \"equipment\":\"设备I\", \"handling\":\"20\"}"
				+ " ,{ \"id\":\"10\", \"equipment\":\"设备J\", \"handling\":\"10\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String abnormalrecord() {
		return "abnormalrecord";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getVocsAbnormalList() {
		ArrayList arrlist = new ArrayList();
		HashMap map = new HashMap();
		map.put("id", "AB0001");
		map.put("name", "15年第26次异常处理");
		map.put("content", "VOCs箱体损坏，催化剂泄露，场地污染");
		map.put("date", "2015-12-25");
		arrlist.add(map);
		HashMap mapResult = new HashMap();
		mapResult.put("data", arrlist);
		mapResult.put("total", 1);

		String json = JsonUtil.Encode(mapResult);
		return ajax(Status.success, json);
	}

	/**
	 * 客户，合作商 用户树
	 * 
	 * @Title: getCustomerTree
	 * @return String
	 * @author lizk
	 * @since 2017年7月5日 V 1.0
	 */
	public String getCustomerTree() {
		QxUsers user = (QxUsers) getBeanByCriteria(QxUsers.class,
				Restrictions.eq("username", LingUtil.userName()));
		String treeData = customerService.getCustomerTree(user.getPartnerId(),
				key, areaId);
		return ajax(Status.success, treeData);
	}

	
	/**
	 * 潜在客户展示页
	 * @Title: qzkh 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String qzkh(){
	    return "qzkh";
	}
	
	/**
	 * 潜在客户信息展示
	 * @Title: getQzKhList 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String getQzKhList(){
	    return ajax(Status.success,
	            customerService.getQzKhData(pager, searchData));
	}
	
	/**
	 * 意向客户展示页
	 * @Title: yxkh 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String yxkh(){
	    return "yxkh";
	}
	
	/**
	 * 意向客户信息列表
	 * @Title: getYxkhList 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String getYxkhList(){
	    return ajax(Status.success,
                customerService.getYxkhList(pager, searchData,status));
	}
	/**
	 * 签单
	 * @Title: qd 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月23日 V 1.0
	 */
	public String qd(){
	    try {
            Sewage s = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", id));
            s.setStatus("3");;//签单后改为成单客户
            sewageService.update(s);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "签单失败");
        }
	}
	
	/**
	 * 驳回  成单客户改为潜在客户
	 * @Title: bh 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月23日 V 1.0
	 */
	public String bh(){
        try {
            Sewage s = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", id));
            s.setStatus("1");//
            sewageService.update(s);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "驳回失败");
        }
    }
	
	
	/**
	 * 承担客户展示页
	 * @Title: cdkh 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String cdkh(){
	    return "cdkh";
	}
	
	/**
	 * 承担客户信息列表
	 * @Title: getCdkhList 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月7日 V 1.0
	 */
	public String getCdkhList(){
	    return "";
	}
	
	
	
	/**
	 * 
	 * @Title: getProvinceData
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月20日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getProvinceData() {
		HashMap map = new HashMap();
		map.put("id", "shandong");
		map.put("name", "山东省");
		HashMap map2 = new HashMap();
		map2.put("id", "zhejiang");
		map2.put("name", "浙江省");
		ArrayList arrlist = new ArrayList();
		arrlist.add(map);
		arrlist.add(map2);
		String json = JsonUtil.Encode(arrlist);
		return ajax(Status.success, json);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCityData() {
		if (getProvinceId().equals("shandong")) {
			HashMap map = new HashMap();
			map.put("id", "jinan");
			map.put("name", "济南市");
			HashMap map2 = new HashMap();
			map2.put("id", "qingdao");
			map2.put("name", "青岛市");
			ArrayList arrlist = new ArrayList();
			arrlist.add(map);
			arrlist.add(map2);
			String json = JsonUtil.Encode(arrlist);
			return ajax(Status.success, json);
		} else if (getProvinceId().equals("zhejiang")) {
			HashMap map = new HashMap();
			map.put("id", "hangzhou");
			map.put("name", "杭州市");
			ArrayList arrlist = new ArrayList();
			arrlist.add(map);
			String json = JsonUtil.Encode(arrlist);
			return ajax(Status.success, json);
		}
		return NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDistrictData() {
		if (cityId.equals("jinan")) {
			HashMap map = new HashMap();
			map.put("id", "lixia");
			map.put("name", "历下区");
			HashMap map2 = new HashMap();
			map2.put("id", "tianqiao");
			map2.put("name", "天桥区");
			ArrayList arrlist = new ArrayList();
			arrlist.add(map);
			arrlist.add(map2);
			String json = JsonUtil.Encode(arrlist);
			return ajax(Status.success, json);
		} else if (cityId.equals("qingdao")) {
			HashMap map = new HashMap();
			map.put("id", "huangdao");
			map.put("name", "黄岛区");
			HashMap map2 = new HashMap();
			map2.put("id", "shinan");
			map2.put("name", "市南区");
			ArrayList arrlist = new ArrayList();
			arrlist.add(map);
			arrlist.add(map2);
			String json = JsonUtil.Encode(arrlist);
			return ajax(Status.success, json);
		} else if (cityId.equals("hangzhou")) {
			HashMap map = new HashMap();
			map.put("id", "yuhang");
			map.put("name", "余杭区");
			HashMap map2 = new HashMap();
			map2.put("id", "xiaoshan");
			map2.put("name", "萧山区");
			ArrayList arrlist = new ArrayList();
			arrlist.add(map);
			arrlist.add(map2);
			String json = JsonUtil.Encode(arrlist);
			return ajax(Status.success, json);
		}
		return NONE;

	}

	/**
	 * @return the provinceId
	 */

	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId
	 *            the provinceId to set
	 */

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the cityId
	 */

	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

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

	public String getHigherAgentName() {
		return higherAgentName;
	}

	public void setHigherAgentName(String higherAgentName) {
		this.higherAgentName = higherAgentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getHigherAgent() {
		return higherAgent;
	}

	public void setHigherAgent(String higherAgent) {
		this.higherAgent = higherAgent;
	}

	public String getStr() {
		return str;
	}

	public String getEnd() {
		return end;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	public ContactsDao getContactsDao() {
		return contactsDao;
	}

	public void setContactsDao(ContactsDao contactsDao) {
		this.contactsDao = contactsDao;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public EquipmentUsagelogService getEulservice() {
		return eulservice;
	}

	public void setEulservice(EquipmentUsagelogService eulservice) {
		this.eulservice = eulservice;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public ProjectPreparation getProjectPreparation() {
		return projectPreparation;
	}

	public void setProjectPreparation(ProjectPreparation projectPreparation) {
		this.projectPreparation = projectPreparation;
	}

	public Sewage getSewage() {
		return sewage;
	}

	public void setSewage(Sewage sewage) {
		this.sewage = sewage;
	}

}
