package com.lingnet.vocs.action.finance;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Constants;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Contract;
import com.lingnet.vocs.entity.ContractCharge;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.finance.ContractChargeService;

/**
 * 
 * @ClassName: ContractChargeAction
 * @Description: TODO
 * @author zy
 * @date 2017年8月15日 上午11:36:18 薛硕 2017-08-15 修改
 */
public class ContractChargeAction extends BaseAction {
	private static final long serialVersionUID = 5490230746966197455L;
	@Resource(name = "contractChargeService")
	private ContractChargeService contractChargeService;

	private ContractCharge contractCharge;
	private String formdata;
	private String htsf;
	/**
	 * @Title: list
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * @Title: add
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * @Title: edit
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String edit() {
		contractCharge = contractChargeService.get(ContractCharge.class, id);
		Partner p = contractChargeService.get(Partner.class,
				Restrictions.eq("id", contractCharge.getCompanyId()));
		contractCharge.setCompanyName(null == p ? "" : p.getName());
		QxUsers u = contractChargeService.get(QxUsers.class,
				Restrictions.eq("id", contractCharge.getRecorder()));
		contractCharge.setRecorderName(null == u ? "" : u.getName());
		return ADD;
	}

	/**
	 * @Title: look
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String look() {
		getRequest().setAttribute("flag", "false");
		contractCharge = contractChargeService.get(ContractCharge.class, id);
		Partner p = contractChargeService.get(Partner.class,
				Restrictions.eq("id", contractCharge.getCompanyId()));
		contractCharge.setCompanyName(null == p ? "" : p.getName());
		QxUsers u = contractChargeService.get(QxUsers.class,
				Restrictions.eq("id", contractCharge.getRecorder()));
		contractCharge.setRecorderName(null == u ? "" : u.getName());
		return ADD;
	}

	/**
	 * @Title: remove
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String remove() {
		contractCharge = contractChargeService
				.get(ContractCharge.class, ids[0]);
		contractChargeService.delete(contractCharge);
		operate("合同收费管理", "删除", contractCharge.getCode());
		return ajax(Status.success, "success");
	}

	/**
	 * @Title: saveOrUpdate
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	public String saveOrUpdate() {
		contractCharge = JsonUtil.toObject(formdata, ContractCharge.class);
		if (null != contractCharge.getFinanceDate()) {
			Long lOld = contractCharge.getFinanceDate().getTime();
			contractCharge.setFinanceDate(new Date(lOld - 28800000));
		}
		try {
			contractChargeService.saveOrUpdate(contractCharge);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		HashMap m = contractChargeService.getListData(pager, id,htsf);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	/**
	 * @Title: findCoDetails
	 * @return
	 * @author zy
	 * @since 2017年7月20日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findCoDetails() {
		Contract contract = contractChargeService.get(Contract.class,
				Restrictions.eq("id", key));
		Partner p = contractChargeService.get(Partner.class,
				Restrictions.eq("id", contract.getCompanyId()));
		QxUsers u = contractChargeService.get(QxUsers.class,
				Restrictions.eq("id", contract.getCreatePerson()));
		HashMap m = new HashMap();
		m.put("code", contract.getCode());
		m.put("partnerOrCustomer", contract.getPartnerOrCustomer());
		m.put("companyId", contract.getCompanyId());
		m.put("companyName", null == p ? "" : p.getName());
		m.put("userId", u.getId());
		m.put("userName", u.getName());
		m.put("accountReceivable", contract.getAccountReceivable());
		m.put("discount", contract.getDiscount());
		m.put("paidupCapital", contract.getPaidupCapital());
		if (contract.getEndDate() != null) {
			String s = contract.getEndDate().toString().substring(0, 10);
			m.put("financeDate", s);
		}
		String json = JsonUtil.Encode(m);
		return ajax(Status.success, json);
	}

	public String approveApplication() {
		try {
			contractChargeService.changeVerifyStatus(id, Constants.CC_APPROVED);
			contractCharge = contractChargeService
					.get(ContractCharge.class, id);
			operate("合同收费管理", "审核", contractCharge.getCode());
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	public ContractCharge getContractCharge() {
		return contractCharge;
	}

	public void setContractCharge(ContractCharge contractCharge) {
		this.contractCharge = contractCharge;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getHtsf() {
		return htsf;
	}

	public void setHtsf(String htsf) {
		this.htsf = htsf;
	}
	
}
