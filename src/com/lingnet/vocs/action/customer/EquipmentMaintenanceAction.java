/**
 * @EquipmentMaintenance.java
 * @com.lingnet.vocs.action.customer
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.action.customer;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.EquipmentMaintenance;
import com.lingnet.vocs.service.customer.EquipmentMaintenanceService;
import com.lingnet.vocs.service.equipment.EquipmentService;

/**
 * @ClassName: EquipmentMaintenance
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月12日 下午3:56:10
 * 
 */

public class EquipmentMaintenanceAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8755593166073422130L;

	@Resource(name = "equipmentMaintenanceService")
	private EquipmentMaintenanceService equipmentMaintenanceService;
	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;
	private String formdata;
	private EquipmentMaintenance mtc;

	private String code;
	private String name;
	private String partnerId;
	private String eqId;
	private String strDate;
	private String endDate;
	private String flag;

	public String list() {
		return LIST;
	}

	public String add() {
		Equipment e = equipmentService.get(Equipment.class,
				Restrictions.eq("id", eqId));
		mtc = new EquipmentMaintenance();
		mtc.setEquipmentCode(e.getEquipmentCode());
		mtc.setEquipmentId(eqId);
		mtc.setEquipmentName(e.getName());
		mtc.setOwner(e.getOwner());
		mtc.setPartnerId(e.getEquipmentUser());
		return ADD;
	}

	public String edit() {
		mtc = this.getBeanById(EquipmentMaintenance.class, id);
		return ADD;
	}

	public String look() {
		mtc = this.getBeanById(EquipmentMaintenance.class, id);
		flag = "false";
		return ADD;
	}

	public String view() {
		return VIEW;
	}

	/**
	 * 设备选择
	 * 
	 * @Title: em
	 * @return String
	 * @author lizk
	 * @since 2017年6月26日 V 1.0
	 */
	public String em() {
		return "em";
	}

	/**
	 * 设备选择
	 * 
	 * @Title: getListDataEm
	 * @return String
	 * @author lizk
	 * @since 2017年6月26日 V 1.0
	 */
	public String getListDataEm() {
		String json = equipmentMaintenanceService.getListDataEm(name, code);
		return ajax(Status.success, json);
	}

	public String remove() throws Exception {
		// equipmentMaintenanceService.delete(id);
		EquipmentMaintenance eq = equipmentMaintenanceService.get(
				EquipmentMaintenance.class, id);
		eq.setIsDeleted("1");
		equipmentMaintenanceService.update(eq);
		return ajax(Status.success, "success");
	}

	public String getListData() {
		String json = "";
		json = JsonUtil.Encode(equipmentMaintenanceService.getListData(pager,
				name, strDate, endDate, partnerId, eqId));

		return ajax(Status.success, json);
	}

	public String saveOrUpdate() {
		try {
			return ajax(Status.success,
					equipmentMaintenanceService.saveOrUpdate(formdata));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public EquipmentMaintenance getMtc() {
		return mtc;
	}

	public void setMtc(EquipmentMaintenance mtc) {
		this.mtc = mtc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
