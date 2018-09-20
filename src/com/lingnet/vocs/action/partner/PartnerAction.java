/**
 * @PartnerAction.java
 * @com.lingnet.vocs.action.partner
 * @Description：
 * 
 * @author zy 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.action.partner;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.util.Constants;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Demand;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.service.baseinfo.AreaService;
import com.lingnet.vocs.service.partner.ContactsService;
import com.lingnet.vocs.service.partner.PartnerService;

/**
 * 合作商
 * 
 * @ClassName: PartnerAction
 * @Description: TODO
 * @author zy
 * @date 2017年6月2日 下午3:28:30
 * 
 */
@ParentPackage("partner")
@Namespace("/partner")
public class PartnerAction extends BaseAction {
	private static final long serialVersionUID = 3468554186829112057L;
	@Resource(name = "partnerService")
	private PartnerService partnerService;
	@Resource(name = "areaService")
	private AreaService areaService;
	@Resource(name = "contactsService")
	private ContactsService contactsService;
	@Resource(name = "adminService")
    private AdminService adminService;
	@Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
	private Partner partner;
	private String provinceId;// 省份id
	private String cityId;// 城市id
	private String formdata;// 表单数据
	private String griddata;// 联系人列表
	private String areaId;// 区域id
	
	private Joint joint;//对接人表
	private String partnerId;//合作商id
	private String bhyj;
	public String threadId;
	
	
	private SewageBusiness business;

	
	/**
	 * 列表页
	 * 
	 * @Title: list
	 * @return String
	 * @author zy
	 * @since 2017年6月2日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 列表页：我的合作商
	 * 
	 * @Title: hsjlist
	 * @return
	 * @author zy
	 * @since 2017年7月4日 V 1.0
	 */
	public String myList() {
		return "mylist";
	}

	/**
	 * 合作商拥有设备
	 * 
	 * @Title: ownedequipment
	 * @return String
	 * @author zy
	 * @since 2017年6月5日 V 1.0
	 */
	public String ownedequipment() {
		return "ownedequipment";
	}

	/**
	 * 合作商地图（废弃）
	 * 
	 * @Title: map
	 * @return String
	 * @author zy
	 * @since 2017年6月20日 V 1.0
	 */
	public String map() {
		return "map";
	}

	/**
	 * 设备地图（废弃）
	 * 
	 * @Title: equipmentmap
	 * @return String
	 * @author zy
	 * @since 2017年6月20日 V 1.0
	 */
	public String equipmentmap() {
		return "equipment_map";
	}

	/**
	 * 定向跳转 到 未被占用的设备页面
	 * 
	 * @Title: equipmentlist
	 * @return String
	 * @author zy
	 * @since 2017年6月5日 V 1.0
	 */
	@Action(value = "equipmentlist", results = { @Result(name = "equipmentlist", location = "/equipmentlist.jsp") })
	public String equipmentlist() {
		return "equipmentlist";
	}

	/**
	 * 添加页
	 * 
	 * @Title: add
	 * @return String
	 * @author zy
	 * @since 2017年6月2日 V 1.0
	 */
	public String add() {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
	    threadId =simpleDateFormat.format(new Date());// 编码
		return ADD;
	}

	/**
	 * 编辑页
	 * 
	 * @Title: edit
	 * @return String
	 * @author zy
	 * @since 2017年6月2日 V 1.0
	 */
	public String edit() {
		partner = partnerService.get(Partner.class, id);
		if (null == partner) {
			partner = new Partner();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
            threadId =simpleDateFormat.format(new Date());// 编码
		}else{
		    if(StringUtils.isEmpty(partner.getThreadId())){
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
	            threadId =simpleDateFormat.format(new Date());// 编码
		    }else{
		        threadId = partner.getThreadId();
		    }
		}
		
		
		// 手动设置 管辖区域 的中文名称
		/*
		 * String[] arr = partner.getJurisdictionArea().split(","); String
		 * sJurisdictionCn = ""; for (int i = 0; i < arr.length; ++i) {
		 * sJurisdictionCn += areaService.get(Area.class, arr[i]).getName()+
		 * ","; } sJurisdictionCn = sJurisdictionCn.substring(0,
		 * sJurisdictionCn.length() - 1);
		 * partner.setJurisdictionCn(sJurisdictionCn);
		 */
		// 上级、归属
		String higherAgentName = partnerService.get(Partner.class,
				partner.getHigherAgent()).getName();
		partner.setHigherAgentName(higherAgentName);
		
		business = partnerService.getBusinessBySewageId(id);
		return ADD;
	}
	
	
	
	/**
	 * 查看页
	 * 
	 * @Title: look
	 * @return String
	 * @author zy
	 * @since 2017年6月3日 V 1.0
	 */
	public String look() {
		getRequest().setAttribute("flag", "false");
		partner = partnerService.get(Partner.class, id);
		if (null == partner) {
			partner = new Partner();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
            threadId =simpleDateFormat.format(new Date());// 编码
		}else{
            threadId = partner.getThreadId();
        }
		// 上级、归属
		String higherAgentName = partnerService.get(Partner.class,
				partner.getHigherAgent()).getName();
		partner.setHigherAgentName(higherAgentName);
		business = partnerService.getBusinessBySewageId(id);
		return ADD;
	}

	/**
	 * 添加页：我的合作商
	 * 
	 * @Title: hsjAdd
	 * @return
	 * @author zy
	 * @since 2017年7月4日 V 1.0
	 */
	public String myAdd() {
		partner = new Partner();
		QxUsers currentUser = (QxUsers) partnerService.get(QxUsers.class,
				Restrictions.eq("username", LingUtil.userName()));
		partner.setHigherAgent(currentUser.getPartnerId());
		Partner p = partnerService.get(Partner.class,
				Restrictions.eq("id", currentUser.getPartnerId()));
		partner.setHigherAgentName(p == null ? "" : p.getName());
		return "myadd";
	}

	/**
	 * 编辑页：合作商信息管理
	 * 
	 * @Title: myEdit
	 * @return
	 * @author zy
	 * @since 2017年7月4日 V 1.0
	 */
	public String myEdit() {
		partner = partnerService.get(Partner.class, id);
		if (null == partner) {
			partner = new Partner();
		}
		// 上级、归属
		String higherAgentName = partnerService.get(Partner.class,
				partner.getHigherAgent()).getName();
		partner.setHigherAgentName(higherAgentName);
		return "myadd";
	}

	/**
	 * 查看页：合作商信息管理
	 * 
	 * @Title: myLook
	 * @return
	 * @author zy
	 * @since 2017年7月10日 V 1.0
	 */
	public String myLook() {
		getRequest().setAttribute("flag", "false");
		partner = partnerService.get(Partner.class, id);
		if (null == partner) {
			partner = new Partner();
		}
		// 上级、归属
		String higherAgentName = partnerService.get(Partner.class,
				partner.getHigherAgent()).getName();
		partner.setHigherAgentName(higherAgentName);
		return "myadd";
	}

	/**
	 * 删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author zy
	 * @since 2017年6月3日 V 1.0
	 */
	public String remove() {
		try {
			partnerService.remove(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, e.getMessage());
		}
	}

	/**
	 * 删除 我的合作商
	 * 
	 * @Title: remove
	 * @return String
	 * @author zy
	 * @since 2017年7月26日 V 1.0
	 */
	public String myRemove() {
		try {
			partnerService.remove(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}

	/**
	 * 获取列表数据
	 * 
	 * @Title: getMyListData
	 * @return String
	 * @author zy
	 * @since 2017年6月3日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getMyListData() {
		HashMap result = partnerService.getMyPartnerListData(pager,
				Constants.PARTNER, areaId);
		String json = JsonUtil.Encode(result);
		return ajax(Status.success, json);
	}

	/**
	 * 跳转审核页面
	 * @Title: check 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月26日 V 1.0
	 */
	 public String check(){
        partner = this.getBeanById(Partner.class, id);
        return  "check";
    }
	
	 
	 public String sh() {
	        try {
	            partner=getBeanById(Partner.class, id);
	            partner.setStatus("2");
	            partnerService.update(partner);
	            backendRoleService.createRoleAuto(partner.getId(),
	                  partner.getPartnerOrCustomer(),partner.getName(),partner.getCode());
	            return ajax(Status.success, "success");
	        } catch (Exception e) {
	            return ajax(Status.success, "审核失败");
	        }
    }
    
    public String shno() {
        try {
            partner=getBeanById(Partner.class, id);
            partner.setStatus("3");
            partner.setBhyj(bhyj);
            partnerService.update(partner);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
    
    public String jf() {
        try {
            partner=getBeanById(Partner.class, id);
            partner.setJfstatus("0");
            partnerService.update(partner);
            return ajax(Status.success, "success");
            
        } catch (Exception e) {
            return ajax(Status.success, "提交失败");
        }
    }
    
    public String tj() {
        try {
            partner=getBeanById(Partner.class, id);
            partner.setStatus("1");
            partner.setTjsj(new Date());
            partner.setBhyj("");
            partnerService.update(partner);
            return ajax(Status.success, "success");
            
        } catch (Exception e) {
            return ajax(Status.success, "提交失败");
        }
    }
    
    public String ntj() {
        
        try {
            partner=getBeanById(Partner.class, id);
            partner.setBhyj(bhyj);;
            partner.setStatus("4");
            
            partnerService.update(partner);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "重新提交失败");
        }
    } 
	    
	    
	/**
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年7月4日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		if (partner == null) {
			partner = new Partner();
		}
		HashMap result = partnerService.getPartnerListData(pager,
				partner.getId(), areaId);
		String json = JsonUtil.Encode(result);
		return ajax(Status.success, json);
	}

	/**
	 * 联系人列表
	 * 
	 * @Title: getContactListData
	 * @return String
	 * @author zy
	 * @since 2017年6月5日 V 1.0
	 */
	public String getContactListData() {
		String partnerId = getRequest().getParameter("partnerId");
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(partnerService
					.getContactListData(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}

	/**
	 * 根据客户获取联系人
	 * 
	 * @Title: getContactByPartnertData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月5日 V 1.0
	 */
	public String getContactByPartnertData() {
		if (partner == null) {
			partner = new Partner();
		}
		String partnerId = partner.getId();
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(partnerService
					.getContactListData(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}

	/**
	 * 合作商拥有设备列表
	 * 
	 * @Title: getPartnerEquipmentlist
	 * @return String
	 * @author zy
	 * @since 2017年6月6日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public String getPartnerEquipmentlist() {
		pager.setSearchData(searchData);
		if (id == null) {
			id = this.getSession("partnerId").toString();
		}
		HashMap mapResult = partnerService.getPartnerEquipList(pager, id);
		String json = JsonUtil.Encode(mapResult);
		return ajax(Status.success, json);
	}

	/**
	 * 未被占用的设备的列表
	 * 
	 * @Title: getAvailableEquipmentlist
	 * @return String
	 * @author zy
	 * @since 2017年6月6日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAvailableEquipmentlist() {
		HashMap map = new HashMap();
		map.put("id", "EQ0005");
		map.put("equipmentCode", "EQ0005");
		map.put("equipmentName", "蜂窝活性炭吸附回收");
		map.put("equipmentPosition", "山东青岛");
		map.put("equipmentOwner", "华世洁");

		HashMap map2 = new HashMap();
		map2.put("id", "EQ0006");
		map2.put("equipmentCode", "EQ0006");
		map2.put("equipmentName", "活性炭颗粒吸附回收");
		map2.put("equipmentPosition", "山东青岛");
		map2.put("equipmentOwner", "华世洁");
		ArrayList arrlist = new ArrayList();
		arrlist.add(map);
		arrlist.add(map2);
		HashMap mapResult = new HashMap();
		mapResult.put("data", arrlist);
		mapResult.put("total", 1);
		String json = JsonUtil.Encode(mapResult);
		return ajax(Status.success, json);
	}

	/**
	 * 保存或更新
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author zy
	 * @since 2017年6月3日 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			partner = JSONObject.parseObject(formdata, Partner.class);
			String business = getRequest().getParameter("formdataBusiness");
			//partnerService.saveOrUpdate(partner, griddata);
			partnerService.saveOrUpdate(partner, griddata,business);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 区域三级联动：获取全部省份
	 * 
	 * @Title: getProvinceData
	 * @return String
	 * @author zy
	 * @since 2017年6月20日 V 1.0
	 */
	public String getProvinceData() {
		ArrayList<HashMap<String, String>> arrList = areaService
				.getChildrenArea("0");
		String json = JsonUtil.Encode(arrList);
		return ajax(Status.success, json);
	}

	/**
	 * 区域三级联动：根据省份id获取全部城市
	 * 
	 * @Title: getCityData
	 * @return String
	 * @author zy
	 * @since 2017年6月20日 V 1.0
	 */
	public String getCityData() {
		ArrayList<HashMap<String, String>> arrList = areaService
				.getChildrenArea(provinceId);
		String json = JsonUtil.Encode(arrList);
		return ajax(Status.success, json);
	}

	/**
	 * 区域三级联动：根据城市id获取全部市区
	 * 
	 * @Title: getDistrictData
	 * @return String
	 * @author zy
	 * @since 2017年6月20日 V 1.0
	 */
	public String getDistrictData() {
		ArrayList<HashMap<String, String>> arrList = areaService
				.getChildrenArea(cityId);
		String json = JsonUtil.Encode(arrList);
		return ajax(Status.success, json);
	}

	/**
	 * 输入名字时验证唯一性
	 * 
	 * @Title: validateName
	 * @return
	 * @author zy
	 * @since 2017年6月21日 V 1.0
	 */
	public String validateName() {
		try {
			String name = URLDecoder.decode(getRequest().getParameter("name"),
					"utf-8");
			String s = partnerService.validateName(name);
			return ajax(Status.success, s);
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 删除联系人
	 * 
	 * @Title: removeContact
	 * @return
	 * @author zy
	 * @since 2017年6月23日 V 1.0
	 */
	public String removeContact() {
		try {
			Contacts contacts = contactsService.get(Contacts.class, id);
			if (null != contacts) {
				contactsService.delete(id);
			}
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.error, "删除失败");
		}
	}

	/**
	 * 合作商选择
	 * 
	 * @Title: chosing
	 * @return
	 * @author zy
	 * @since 2017年6月22日 V 1.0
	 */
	public String chosing() {
		if (partner == null) {
			partner = new Partner();
		}
		return "chosing";
	}

	/**
	 * 获取合作商表的数据
	 * 
	 * @Title: partnerData
	 * @return String
	 * @author duanjj
	 * @since 2017年6月23日 V 1.0
	 */
	public String partnerData() {
		List<HashMap<String, String>> result = partnerService
				.partnerData(searchData);
		String json = JsonUtil.Encode(result);
		return ajax(Status.success, json);
	}

	/**
	 * 获取全部合作商、客户
	 * 
	 * @Title: getAllPartnerAndCustomer
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getAllPartnerAndCustomer() {
		String partnerId = (String) (getRequest().getSession()
				.getAttribute("partnerId"));
		// 没有选合作商类型，应该key=undifined，但如果打开页面过快，key可能会是空串""，可能是miniui还未加载到合作商/客户的DOM
		/*
		 * if (StringUtils.isBlank(key)) { key = "undefined"; }
		 */
		HashMap m = partnerService.getPandC4FirstParty(pager, partnerId, key);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	/**
	 * 设备归属转移选择公司
	 * 
	 * @Title: getAllCustomer
	 * @return String
	 * @author wanl
	 * @since 2017年7月17日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getAllCustomer() {
		String partnerId = (String) (getRequest().getSession()
				.getAttribute("partnerId"));
		HashMap m = partnerService.getPandC4FirstParty(pager, partnerId, key);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	/**
	 * 根据id 获取 客户 或者合作商
	 * 
	 * @Title: getById
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String getById() {
		partner = partnerService.get(Partner.class, id);
		return ajax(Status.success, JsonUtil.Encode(partner));
	}

	/**
	 * 跳转设备使用者选择页
	 * 
	 * @Title: equipUser
	 * @return
	 * @author zy
	 * @since 2017年7月14日 V 1.0
	 */
	public String equipUser() {
		return "equip_user";
	}

	/**
	 * 设备使用者 列表
	 * 
	 * @Title: getEquipUserList
	 * @return
	 * @author zy
	 * @since 2017年7月14日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getEquipUserList() {
		HashMap m = partnerService.getEquipUserList(pager);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	/**
	 * 能进行合作商上级归属编辑的只有华世杰 进入此函数也只能在编辑页面 递归验证所选合作商是否是当前合作商的子、孙单位
	 * 
	 * @Title: validateHigherAgent
	 * @return
	 * @author zy
	 * @since 2017年7月24日 V 1.0
	 */
	public String validateHigherAgent() {
		String chosenId = getRequest().getParameter("chosenId");
		String s = partnerService.validateHigherAgent(id, chosenId);
		return ajax(Status.success, s);
	}

	
	
	
	
	/**
     * 跳转对接人列表
     * @Title: joint 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月2日 V 1.0
     */
    public String joint(){
        return "joint_list";
    }
    public String jointAdd(){
        return "joint_add";
    }
    public String jointEdit(){
        joint = this.getBeanById(Joint.class, id);
        return "joint_add";
    }
    public String jointLook(){
        joint = this.getBeanById(Joint.class, id);
        getRequest().setAttribute("flag", "false");
        return "joint_add";
    }

    /**
     * 对接人展示列表
     * @Title: getJointData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月2日 V 1.0
     */
    public String getJointData(){
        String json = partnerService.getJointData(pager);
        return ajax(Status.success, json);
    }

    /**
     * 保存编辑对接人
     * @Title: saveJoint 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月2日 V 1.0
     */
    public String saveJoint(){
        try {
            String json = partnerService.saveJoint(formdata);
            return ajax(Status.success,json);
        } catch (Exception e) {
            return ajax(Status.error,e.getMessage());
        }
    }
	/**
	 * 删除对接人
	 * @Title: deleteJoint 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月2日 V 1.0
	 */
    public String deleteJoint(){
        try {
            Joint j = this.getBeanByCriteria(Joint.class, Restrictions.eq("id", id));
            QxUsers user = adminService.load(QxUsers.class,j.getUserId());
            user.setIsDeleted("1");
            adminService.update(user);
            this.deleteByCriteria(Joint.class, Restrictions.eq("id", id));
            return ajax(Status.success, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, "删除失败");
        }
    }
	
    /**
     * 跳转指定对接人页面
     * @Title: zddjr 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月3日 V 1.0
     */
    public String zddjr(){
        return "zddjr";
    }
	
    /**
     * 合作商保存对接人
     * @Title: saveDjr 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月3日 V 1.0
     */
    public String saveDjr(){
        try {
            Partner partner = this.getBeanByCriteria(Partner.class, Restrictions.eq("id", partnerId));
            partner.setDjr(id);
            partnerService.update(partner);
            return ajax(Status.success,"success");
        } catch (Exception e) {
            return ajax(Status.error,"指定失败");
        }
    }
    
    /**
     * 跳转对接人负责的合作商
     * @Title: hzs 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月3日 V 1.0
     */
    public String hzs(){
        return "hzs";
    }
    
    public String getHzcData(){
        String json = partnerService.getHzcData(pager,id);
        return ajax(Status.success, json);
    }
	
	///////////////////////////////////////////////////////////////////////
	
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

	/**
	 * @return the partner
	 */

	public Partner getPartner() {
		return partner;
	}

	/**
	 * @param partner
	 *            the partner to set
	 */

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	/**
	 * @return the formdata
	 */

	public String getFormdata() {
		return formdata;
	}

	/**
	 * @param formdata
	 *            the formdata to set
	 */

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	/**
	 * @return the griddata
	 */

	public String getGriddata() {
		return griddata;
	}

	/**
	 * @param griddata
	 *            the griddata to set
	 */

	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

    public Joint getJoint() {
        return joint;
    }

    public void setJoint(Joint joint) {
        this.joint = joint;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getBhyj() {
        return bhyj;
    }

    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    
    
    public SewageBusiness getBusiness() {
		return business;
	}

	public void setBusiness(SewageBusiness business) {
		this.business = business;
	}

    
}
