package com.lingnet.vocs.action.equipment;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.ClewFile;
import com.lingnet.vocs.entity.EquipmentBase;
import com.lingnet.vocs.entity.SupplierCase;
import com.lingnet.vocs.service.equipment.EquipmentBaseService;
import com.lingnet.vocs.service.reported.ClewFileService;

/**
 * 设备基础信息
 * 
 * @ClassName: EquipmentBaseAction
 * @author wanl
 * @date 2017年6月28日 上午10:23:51
 * 
 */

@ParentPackage("equipment")
@Namespace("/equipment")
public class EquipmentBaseAction extends BaseAction {

	private static final long serialVersionUID = 821728569213136203L;

	@Resource(name = "equipmentBaseService")
	private EquipmentBaseService equipmentBaseService;
	
    @Resource(name = "clewFileService")
	private ClewFileService clewFileService;

	
	private ClewFile clewFile;// 企业文件信息对象or工艺图保存地址
	
	private EquipmentBase equipmentBase;

	private String formdata;

	private String partnerId;


	private String threadId;
	

	

	/**
	 * 设备基础信息列表页跳转
	 * 
	 * @Title: list
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 设备基础信息增加
	 * 
	 * @Title: add
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String add() {
		return ADD;
	}
	//上传工艺图保存
	
	public String addfile() {
		return "addfile";
	}
	//查看工艺图
	public String lookfile() {
		clewFile = clewFileService.findByThreadId(threadId);
		
		return "lookfile";
	}
	
	//保存工艺图
	public String saveOrUpdatefile() {
		try {
			clewFile = JSONObject.parseObject(formdata, ClewFile.class);
			String flg = clewFileService.saveOrUpdate(clewFile);
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
	/**
	 * 设备基础信息删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String remove() {
		equipmentBaseService.delete(ids);
		return ajax(Status.success, "success");
	}

	/**
	 * 设备基础信息编辑跳转
	 * 
	 * @Title: edit
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String edit() {
		equipmentBase = this.getBeanById(EquipmentBase.class, id);
		if (equipmentBase == null) {
			equipmentBase = new EquipmentBase();
		}
		return ADD;
	}

	/**
	 * 设备基础信息查看
	 * 
	 * @Title: look
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String look() {
		equipmentBase = this.getBeanById(EquipmentBase.class, id);
		if (equipmentBase == null) {
			equipmentBase = new EquipmentBase();
		}
		return "look";
	}

	/**
	 * 设备基础信息展示
	 * 
	 * @Title: getListData
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String getListData() {
		if (partnerId == null) {
			partnerId = this.getSession("partnerId").toString();
		}
		String json = equipmentBaseService.getListData(pager, partnerId);
		return ajax(Status.success, JsonUtil.Encode(json));

	}

	/**
	 * 设备基础信息修改保存
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author wanl
	 * @since 2017年6月28日 V 1.0
	 */
	public String saveOrUpdate() {
		equipmentBase = JsonUtil.toObject(formdata, EquipmentBase.class);
		operate("设备管理", "设备基础信息增加编辑", equipmentBase.getModel());
		try {
			return ajax(Status.success,
					equipmentBaseService.saveOrUpdate(equipmentBase));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}
	
	
	/*public String saveOrUpdateFile() {
		equipmentBase = JsonUtil.toObject(formdata, EquipmentBase.class);
		
		try {
			return ajax(Status.success,
					equipmentBaseService.saveOrUpdate(equipmentBase));
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}*/


	public EquipmentBase getEquipmentBase() {
		return equipmentBase;
	}

	public void setEquipmentBase(EquipmentBase equipmentBase) {
		this.equipmentBase = equipmentBase;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
	
	

	public ClewFile getClewFile() {
		return clewFile;
	}

	public void setClewFile(ClewFile clewFile) {
		this.clewFile = clewFile;
	}
	
}
