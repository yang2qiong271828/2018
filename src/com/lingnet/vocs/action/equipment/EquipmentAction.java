package com.lingnet.vocs.action.equipment;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.equipment.EquipmentDao;
import com.lingnet.vocs.entity.Cspz;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.Transfer;
import com.lingnet.vocs.service.equipment.EquipmentService;

/**
 * 设备信息
 * 
 * @ClassName: EquipmentAction
 * @Description: TODO
 * @author 薛硕getMyEquipmentListData
 * @date 2017年6月21日 下午6:02:03
 * 
 */
@ParentPackage("equipment")
@Namespace("/equipment")
public class EquipmentAction extends BaseAction {

	private static final long serialVersionUID = -2599680975627242239L;

	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;
	@Resource(name = "equipmentDao")
	private EquipmentDao equipmentDao;

	private Equipment equipment;// 设备信息表

	private Transfer transfer;// 转移记录

	private String formdata;// form表单json

	private String partnerId;

	private String isEq;

	private String airVolume;

	private String equipmentType;

	private String eqId;

	private int sbsize;

	private String plcIdentificationCode;

	private String eqType;

	private String userId;

	private String handlers;// 操作者

	private String handlersId;

	private String ownerId;

	private String areaId;// 区域id

	private String boxId;// 设备编号

	private String sbId;// 设备Id

	private String cbl1;// 选中的值

	private String cspzId;// cspz表的id

	private Cspz cs;

	public String pz() {
		
		/*try {
			boxId = URLDecoder.decode(boxId, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} */

		cs = getBeanByCriteria(Cspz.class, Restrictions.eq("sbId", sbId));
		equipment = equipmentService.get(Equipment.class, sbId);
		return "pz";
	}

	public String saveOrUpdatePz() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sbId", sbId);
		map.put("cbl1", cbl1);
		map.put("cspzId", cspzId);
		map.put("equipmentType", equipmentType);
		String flag = equipmentService.saveOrUpdatePz(map);
		return ajax(Status.success, flag);
	}

	/**
	 * 跳转plc关联页面
	 * 
	 * @Title: associationPlc
	 * @return String
	 * @author wanl
	 * @since 2017年7月7日 V 1.0
	 */
	public String associationPlc() {
		equipment = this.getBeanById(Equipment.class, id);
		if (equipment == null) {
			equipment = new Equipment();
		}
		return "plc";
	}

	/**
	 * 关联plc保存到数据库
	 * 
	 * @Title: savePlc
	 * @return String
	 * @author wanl
	 * @since 2017年7月7日 V 1.0
	 */
	public String savePlc() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("plcIdentificationCode", plcIdentificationCode);
		equipment = equipmentService.get(Equipment.class, id);
		operate("设备管理", "关联plc", equipment.getEquipmentCode());
		return ajax(Status.success, equipmentDao.savePlc(id, map));
	}

	/**
	 * 设备信息展示页
	 * 
	 * @Title: list
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 设备接收者（合作商和客户）页面
	 * 
	 * @Title: recipient
	 * @return String
	 * @author zhangyu
	 * @since 2017年6月20日 V 1.0
	 */
	public String recipient() {
		return "recipient";
	}

	/**
	 * 跳转我的设备展示页
	 * 
	 * @Title: myEquipments
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String myEquipments() {
		return "myequipments";
	}

	/**
	 * 增加设备信息
	 * 
	 * @Title: add
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 删除设备信息
	 * 
	 * @Title: remove
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String remove() {
		equipment = equipmentService.get(Equipment.class, id);
		equipmentService.delete(id);
		operate("设备管理", "删除设备", equipment.getEquipmentCode());
		return ajax(Status.success, "success");
	}

	/**
	 * 修改设备信息
	 * 
	 * @Title: edit
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String edit() {
		equipment = this.getBeanById(Equipment.class, id);
		if (equipment == null) {
			equipment = new Equipment();
		}
		return ADD;
	}

	/**
	 * 查看设备信息
	 * 
	 * @Title: look
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String look() {
		equipment = this.getBeanById(Equipment.class, id);
		if (equipment == null) {
			equipment = new Equipment();
		}
		return "look";
	}
	//添加设备定期运维
	public String  dqsbyx() {
		equipment = this.getBeanById(Equipment.class, id);
		if (equipment == null) {
				equipment = new Equipment();
		}
			return "dqsbyx";
	}
	/**
	 * 获取设备列表
	 * 
	 * @Title: getListData
	 * @return
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getListData() {
		if (partnerId == null) {
			partnerId = this.getSession("partnerId").toString();
		}
		String json = equipmentService.getListData(pager, key, partnerId);
		return ajax(Status.success, JsonUtil.Encode(json));

	}

	/**
	 * 弹框选择有合同的设备
	 * 
	 * @Title: getEqByconData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月5日 V 1.0
	 */
	public String getEqByconData() {
		if (partnerId == null) {
			partnerId = this.getSession("partnerId").toString();
		}
		String json = equipmentService.getEqByconData(pager, partnerId, userId);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 设备信息保存和修改
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String saveOrUpdate() {
		equipment = JsonUtil.toObject(formdata, Equipment.class);
		operate("设备管理", "添加编辑设备", equipment.getEquipmentCode());
		try {
			String result = "";
			result = equipmentService.saveOrUpdate(equipment);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 我的设备列表展示页
	 * 
	 * @Title: getMyEquipmentListData
	 * @return
	 * @throws ParseException
	 *             String
	 * @author 薛硕
	 * @since 2017年6月21日 V 1.0
	 */
	public String getMyEquipmentListData() {
		partnerId = this.getSession("partnerId").toString();
		String json = equipmentService.getMyEquipmentListData(pager, partnerId,
				areaId);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String getContractByEquipment() {
		String json = equipmentService.getContractByEquipment(pager, eqId);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 设备归属转移页面
	 * 
	 * @Title: transfer
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String transfer() {
		equipment = this.getBeanById(Equipment.class, id);
		QxUsers users = this.getBeanByCriteria(QxUsers.class,
				Restrictions.eq("username", ToolUtil.userName()));
		Partner partner = this.getBeanByCriteria(Partner.class,
				Restrictions.eq("id", users.getPartnerId()));
		equipment.setHandlersId(partner.getId());
		equipment.setHandlersName(partner.getName());
		return "transfer";
	}

	/**
	 * 保存转移记录
	 * 
	 * @Title: saveTransfer
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String saveTransfer() {
		transfer = JsonUtil.toObject(formdata, Transfer.class);
		System.out.println(transfer);
		String result;
		operate("设备管理", "设备归属转移", transfer.getEquipmentCode());
		try {
			result = equipmentService.saveTransfer(transfer);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 跳转到转移记录查看页面
	 * 
	 * @Title: searchTarOwn
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String searchTarOwn() {
		equipment = equipmentService.get(Equipment.class, id);
		return "searchtarown";
	}

	/**
	 * 根据设备id获取转移记录
	 * 
	 * @Title: getTarOwnData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getTarOwnData() {
		if (equipment == null) {
			equipment = new Equipment();
		}
		String json = equipmentService.getTarOwnData(pager, equipment.getId());
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 用户树
	 * 
	 * @Title: getTreeData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String getTreeData() {
		partnerId = this.getSession("partnerId").toString();
		String treeData = equipmentService.getTreeData(partnerId, id, areaId);
		return ajax(Status.success, treeData);
	}

	public String getEqTreeData() {
		if (partnerId == null) {
			partnerId = this.getSession("partnerId").toString();
		}
		ArrayList<Object> data = equipmentService.getEqTreeData(partnerId,
				eqType);
		this.getRequest().getSession().setAttribute("sbsize", data.size());
		String json = JsonUtil.Encode(data);
		return ajax(Status.success, json);
	}

	/**
	 * 设备归属转回
	 * 
	 * @Title: takeBack
	 * @return
	 * @author zy
	 * @since 2017年6月27日 V 1.0
	 */
	public String takeBack() {
		return "takeback";
	}

	/**
	 * 设备选择页面展示
	 * 
	 * @Title: show
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月28日 V 1.0
	 */
	public String show() {

		return "show";
	}

	/**
	 * 保存 归属转回
	 * 
	 * @Title: saveTakeBack
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String saveTakeBack() {
		try {
			String oldOwner = getRequest().getParameter("oldOwner");
			String contractId = getRequest().getParameter("contractId");
			String s = equipmentService.saveTakeBack(id, oldOwner, contractId);
			equipment = equipmentService.get(Equipment.class, id);
			operate("设备管理", "归属转回", equipment.getEquipmentCode());
			return ajax(Status.success, s);
		} catch (Exception e) {
			return ajax(Status.error,
					e.toString().substring(e.toString().indexOf(":") + 1));
		}
	}

	/**
	 * 点击设备类型查找风量值
	 * 
	 * @Title: getAirVolume
	 * @return String
	 * @author wanl
	 * @since 2017年6月29日 V 1.0
	 */
	public String getAirVolume() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("equipmentType", equipmentType);
		return ajax(Status.success, equipmentDao.getAirVolume(map));
	}

	/**
	 * 根据设备类型和风量值查询设备详细信息
	 * 
	 * @Title: getAirVolumeByEquipment
	 * @return String
	 * @author wanl
	 * @since 2017年6月29日 V 1.0
	 */
	public String getAirVolumeByEquipment() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("airVolume", airVolume);
		map.put("equipmentType", equipmentType);
		return ajax(Status.success, equipmentDao.getAirVolumeByEquipment(map));
	}

	public String getEqNumber() {
		Integer i = equipmentService.getEqNumberByPartnerId(key);

		return ajax(Status.success, i.toString());
	}

	/**
	 * 根据id获取实体类
	 * 
	 * @Title: getById
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月17日 V 1.0
	 */
	public String getById() {
		equipment = this.getBeanById(Equipment.class, id);
		return ajax(Status.success, JsonUtil.Encode(equipment));
	}

	/**
	 * 转回操作者
	 * 
	 * @Title: backcz
	 * @return String
	 * @author lyz
	 * @since 2017年10月13日 V 1.0
	 */
	public String backcz() {
		String flag = equipmentService.backcz(id);
		return ajax(Status.success, flag);
	}

	/**
	 * 转回所属人
	 * 
	 * @Title: backcz
	 * @return String
	 * @author lyz
	 * @since 2017年10月13日 V 1.0
	 */
	public String backss() {
		String flag = equipmentService.backss(id);
		return ajax(Status.success, flag);
	}

	/***********************************************************************************************************************************/
	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getIsEq() {
		return isEq;
	}

	public void setIsEq(String isEq) {
		this.isEq = isEq;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public void setAirVolume(String airVolume) {
		this.airVolume = airVolume;
	}

	public EquipmentDao getEquipmentDao() {
		return equipmentDao;
	}

	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public int getSbsize() {
		return sbsize;
	}

	public void setSbsize(int sbsize) {
		this.sbsize = sbsize;
	}

	public String getPlcIdentificationCode() {
		return plcIdentificationCode;
	}

	public void setPlcIdentificationCode(String plcIdentificationCode) {
		this.plcIdentificationCode = plcIdentificationCode;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHandlers() {
		return handlers;
	}

	public void setHandlers(String handlers) {
		this.handlers = handlers;
	}

	public String getHandlersId() {
		return handlersId;
	}

	public void setHandlersId(String handlersId) {
		this.handlersId = handlersId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	public String getCbl1() {
		return cbl1;
	}

	public void setCbl1(String cbl1) {
		this.cbl1 = cbl1;
	}

	public Cspz getCs() {
		return cs;
	}

	public void setCs(Cspz cs) {
		this.cs = cs;
	}

	public String getCspzId() {
		return cspzId;
	}

	public void setCspzId(String cspzId) {
		this.cspzId = cspzId;
	}

}
