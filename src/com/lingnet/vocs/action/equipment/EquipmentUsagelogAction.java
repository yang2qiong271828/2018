/**
 * @EquipmentUsageRecord.java
 * @com.lingnet.vocs.action.equipment
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.action.equipment;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.EquipmentUsageLog;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.equipment.EquipmentService;
import com.lingnet.vocs.service.equipment.EquipmentUsagelogService;
import com.lingnet.vocs.service.partner.PartnerService;

/**
 * 设备使用记录
 * 
 * @ClassName: EquipmentUsagelogAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月27日 下午2:29:15
 * 
 */
@ParentPackage("equipment")
@Namespace("/equipment")
public class EquipmentUsagelogAction extends BaseAction {
	private static final long serialVersionUID = -1290035646997957301L;

	@Resource(name = "equipmentUsagelogService")
	private EquipmentUsagelogService eqUsagelogService;
	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;
	@Resource(name = "partnerService")
	private PartnerService partnerService;

	private EquipmentUsageLog eqUsageLog;// 设备使用记录

	private String formdata;// form表单json

	private String eqId;// 设备id

	private String partnerId;// 客户合作商id

	private Equipment equipment;// 设备

	private Partner partner;// 客户 、合作商

	/**
	 * 设备使用记录列表显示页
	 * 
	 * @Title: list
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 跳转新增页面
	 * 
	 * @Title: add
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String add() {
		equipment = equipmentService.get(Equipment.class, eqId);
		partner = partnerService.get(Partner.class,
				equipment.getEquipmentUser());
		if (partner == null) {
			partner = new Partner();
		}
		if (equipment == null) {
			equipment = new Equipment();
		}
		return ADD;
	}

	/**
	 * 删除设备使用记录
	 * 
	 * @Title: remove
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String remove() {
		eqUsagelogService.delete(ids);
		eqUsageLog = eqUsagelogService.get(EquipmentUsageLog.class, ids[0]);
		operate("设备管理", "使用记录删除", eqUsageLog.getEquipmentCode());
		return ajax(Status.success, "success");
	}

	/**
	 * 编辑
	 * 
	 * @Title: edit
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String edit() {
		eqUsageLog = this.getBeanById(EquipmentUsageLog.class, id);
		partner = partnerService.get(Partner.class,
				eqUsageLog.getUsageCustomer());
		if (eqUsageLog == null) {
			eqUsageLog = new EquipmentUsageLog();
		}
		if (partner == null) {
			partner = new Partner();
		}
		return "edit";
	}

	/**
	 * 查看
	 * 
	 * @Title: look
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String look() {
		eqUsageLog = this.getBeanById(EquipmentUsageLog.class, id);
		partner = partnerService.get(Partner.class,
				eqUsageLog.getUsageCustomer());
		if (eqUsageLog == null) {
			eqUsageLog = new EquipmentUsageLog();
		}
		if (partner == null) {
			partner = new Partner();
		}
		return "look";
	}

	/**
	 * 获取设备记录
	 * 
	 * @Title: getListData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String getListData() {
		if (partnerId == null) {
			partnerId = this.getSession("partnerId").toString();
		}
		String json = eqUsagelogService.getListData(pager, partnerId, eqId);
		return ajax(Status.success, json);
	}

	/**
	 * 保存 修改设备使用记录
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月26日 V 1.0
	 */
	public String saveOrUpdate() {
		eqUsageLog = JsonUtil.toObject(formdata, EquipmentUsageLog.class);
		operate("设备管理", "增加编辑使用记录", eqUsageLog.getEquipmentCode());
		try {
			String result = "";
			result = eqUsagelogService.saveOrUpdate(eqUsageLog);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/*************************** get set ****************************************************/
	public EquipmentUsagelogService getEqUsagelogService() {
		return eqUsagelogService;
	}

	public void setEqUsagelogService(EquipmentUsagelogService eqUsagelogService) {
		this.eqUsagelogService = eqUsagelogService;
	}

	public EquipmentUsageLog getEqUsageLog() {
		return eqUsageLog;
	}

	public void setEqUsageLog(EquipmentUsageLog eqUsageLog) {
		this.eqUsageLog = eqUsageLog;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}
}
