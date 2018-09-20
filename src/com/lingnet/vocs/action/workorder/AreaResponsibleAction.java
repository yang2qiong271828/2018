package com.lingnet.vocs.action.workorder;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AreaResponsible;
import com.lingnet.vocs.service.workorder.AreaResponsibleService;

/**
 * 区域负责人
 * 
 * @ClassName: AreaResponsibleAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月28日 下午6:03:57
 * 
 */
public class AreaResponsibleAction extends BaseAction {

	private static final long serialVersionUID = -8646430481288770180L;

	@Resource(name = "areaResponsibleService")
	private AreaResponsibleService areaResponsibleService;

	private AreaResponsible areaRes;

	private String area;

	private String formdata;

	private QxUsers qxUser;

	private boolean flag = true;

	/**
	 * 列表展示
	 * 
	 * @Title: list
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String list() {

		return LIST;
	}

	/**
	 * 列表展示页获取列表数据
	 * 
	 * @Title: getListData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String getListData() {
		String partnerId = this.getSession("partnerId").toString();
		String json = areaResponsibleService
				.getListData(pager, partnerId, area);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String add() {

		return ADD;
	}

	/**
	 * 删除负责人
	 * 
	 * @Title: remove
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String remove() {
		areaRes = this.getBeanById(AreaResponsible.class, ids[0]);
		operate("区域负责人管理", "删除", areaRes.getCode());
		areaResponsibleService.delete(ids);
		return ajax(Status.success, "success");
	}

	/**
	 * 修改区域负责人
	 * 
	 * @Title: edit
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String edit() {
		areaRes = areaResponsibleService.get(AreaResponsible.class, id);
		if (areaRes == null) {
			areaRes = new AreaResponsible();
		}
		qxUser = this.getBeanById(QxUsers.class, areaRes.getName());
		if (qxUser == null) {
			qxUser = new QxUsers();
		}
		return ADD;
	}

	/**
	 * 查看区域负责人
	 * 
	 * @Title: look
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String look() {
		areaRes = areaResponsibleService.get(AreaResponsible.class, id);
		if (areaRes == null) {
			areaRes = new AreaResponsible();
		}
		flag = false;
		qxUser = this.getBeanById(QxUsers.class, areaRes.getName());
		if (qxUser == null) {
			qxUser = new QxUsers();
		}
		return ADD;
	}

	/**
	 * 保存区域负责人
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String saveOrUpdate() {
		String partnerId = this.getSession("partnerId").toString();
		areaRes = JsonUtil.toObject(formdata, AreaResponsible.class);
		areaRes.setPartnerId(partnerId);
		try {
			String result = "";
			result = areaResponsibleService.saveOrUpdate(areaRes);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 弹出区域负责人
	 * 
	 * @Title: showAreaRes
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String showAreaRes() {

		return "showareares";
	}

	/******************************************* get set ********************************************/

	public AreaResponsibleService getAreaResponsibleService() {
		return areaResponsibleService;
	}

	public void setAreaResponsibleService(
			AreaResponsibleService areaResponsibleService) {
		this.areaResponsibleService = areaResponsibleService;
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

	public AreaResponsible getAreaRes() {
		return areaRes;
	}

	public void setAreaRes(AreaResponsible areaRes) {
		this.areaRes = areaRes;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public QxUsers getQxUser() {
		return qxUser;
	}

	public void setQxUser(QxUsers qxUser) {
		this.qxUser = qxUser;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
