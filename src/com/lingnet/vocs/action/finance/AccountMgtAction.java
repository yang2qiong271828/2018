package com.lingnet.vocs.action.finance;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Constants;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AccountMgt;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.service.finance.AccountMgtService;

/**
 * @ClassName: AccountmgtAction
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月13日 下午5:16:04
 * 
 */
@ParentPackage("finance")
@Namespace("/finance")
public class AccountMgtAction extends BaseAction {
	private static final long serialVersionUID = -3328769581973472728L;
	@Resource(name = "accountMgtService")
	private AccountMgtService accountMgtService;
	private String formdata;
	private AccountMgt accountMgt;
	private String sfbz;
	/**
	 * 账务管理：列表页
	 * 
	 * @Title: list
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 账务管理：增加页
	 * 
	 * @Title: add
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 账务管理：编辑页
	 * 
	 * @Title: edit
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String edit() {
		accountMgt = accountMgtService.get(AccountMgt.class, id);
		Partner p = accountMgtService.get(Partner.class,
				accountMgt.getPartnerId());
		accountMgt.setPartnerName(null == p ? "" : p.getName());
		QxUsers user = accountMgtService.get(QxUsers.class,
				accountMgt.getRecorderId());
		accountMgt.setRecorderName(null == user ? "" : user.getName());
		return ADD;
	}

	/**
	 * 账务管理：查看页
	 * 
	 * @Title: look
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String look() {
		getRequest().setAttribute("flag", "false");
		accountMgt = accountMgtService.get(AccountMgt.class, id);
		Partner p = accountMgtService.get(Partner.class,
				accountMgt.getPartnerId());
		accountMgt.setPartnerName(null == p ? "" : p.getName());
		QxUsers user = accountMgtService.get(QxUsers.class,
				accountMgt.getRecorderId());
		accountMgt.setRecorderName(null == user ? "" : user.getName());
		return ADD;
	}

	/**
	 * 保存或更新
	 * 
	 * @Title: saveOrUpdate
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			accountMgt = JsonUtil.toObject(formdata, AccountMgt.class);
			if (null != accountMgt.getFinanceDate()) {
				Long lOld = accountMgt.getFinanceDate().getTime();
				accountMgt.setFinanceDate(new Date(lOld - 28800000));
			}
			accountMgtService.saveOrUpdate(accountMgt);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 账务管理：删除
	 * 
	 * @Title: remove
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	public String remove() {
		try {
			accountMgtService.remove(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "error");
		}
	}

	/**
	 * 账务管理：获取列表数据
	 * 
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		HashMap map = accountMgtService.getListData(pager, key, id,sfbz);
		String json = JsonUtil.Encode(map);
		return ajax(Status.success, json);
	}

	/**
	 * 获取WorkOrder详细信息
	 * 
	 * @Title: findWoDetails
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findWoDetails() {
		WorkOrder wo = accountMgtService.get(WorkOrder.class,
				Restrictions.eq("id", key));
		Partner p = accountMgtService.get(Partner.class,
				Restrictions.eq("id", wo.getCustomer()));
		QxUsers user = accountMgtService.get(QxUsers.class,
				Restrictions.eq("id", wo.getReplayPerson()));
		HashMap m = new HashMap();
		if (null != p) {
			m.put("partnerOrCustomer", p.getPartnerOrCustomer());
			m.put("partnerId", p.getId());
			m.put("partnerName", p.getName());
		}
		if (null != wo) {
			m.put("workOrderCode", wo.getWorkOrderCode());
			Double dSReceivable = 0.0;
			Double dSReceived = 0.0;
			Double dSDiscount = 0.0;
			if (wo.getRecMainterCharges() != null) {
				dSReceivable = wo.getRecMainterCharges();
			}
			if (wo.getReaMainterCharges() != null) {
				dSReceived = wo.getReaMainterCharges();
			}
			dSDiscount = dSReceivable - dSReceived;
			m.put("serviceReceivable", dSReceivable);// 服务费 应收
			m.put("serviceReceived", dSReceived);// 实收
			m.put("serviceDiscount", dSDiscount);// 服务费 减免
			Double dMReceivable = 0.0;
			Double dMReceived = 0.0;
			Double dMDiscount = 0.0;
			if (wo.getRecItemCharges() != null) {
				dMReceivable = wo.getRecItemCharges();
			}
			if (wo.getReaItemCharges() != null) {
				dMReceived = wo.getReaItemCharges();
			}
			dMDiscount = dMReceivable - dMReceived;
			m.put("materialReceivable", dMReceivable);// 物料费 应收
			m.put("materialReceived", dMReceived);// 实收
			m.put("materialDiscount", dMDiscount);// 物料费 应收
			if (null != wo.getCheckDate()) {
				String sd = wo.getCheckDate().toString().substring(0, 10);
				m.put("financeDate", sd);// 结单日期
			}
		}
		if (null != user) {
			m.put("replayPersonId", user.getId());// 区域负责人Id
			m.put("replayPersonName", user.getName());// 区域负责人Name
		}
		String json = JsonUtil.Encode(m);
		return ajax(Status.success, json);
	}

	/**
	 * @Title: approveApplication
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String approveApplication() {
		try {
			String s = accountMgtService.changeVerifyStatus(id,
					Constants.AM_APPROVED);
			return ajax(Status.success, s);
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 验证唯一性
	 * 
	 * @Title: validateCode
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String validateCode() {
		try {
			String s = accountMgtService.validateCode(key);
			return ajax(Status.success, s);
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public AccountMgt getAccountMgt() {
		return accountMgt;
	}

	public void setAccountMgt(AccountMgt accountMgt) {
		this.accountMgt = accountMgt;
	}

	public String getSfbz() {
		return sfbz;
	}

	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	
}
