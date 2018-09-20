package com.lingnet.vocs.action.finance;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Contract;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.Refund;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.service.finance.RefundService;

public class RefundAction extends BaseAction {

	/**
     * 
     */
	private static final long serialVersionUID = -3851529910447372536L;

	@Resource(name = "refundService")
	private RefundService refundService;

	private Refund refund;
	private String formdata;

	/**
	 * @Title: list
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * @Title: add
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String add() {
		getRequest().setAttribute("radio1", "1");
		return ADD;
	}

	/**
	 * @Title: edit
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String edit() {
		refund = refundService.get(Refund.class, id);
		Partner p = refundService.get(Partner.class, refund.getPartnerId());
		refund.setPartnerName(null == p ? "" : p.getName());
		if (StringUtils.isEmpty(refund.getWorkOrderCode())
				&& StringUtils.isNotEmpty(refund.getContractCode())) {
			getRequest().setAttribute("radio1", "1");
		} else if (StringUtils.isNotEmpty(refund.getWorkOrderCode())
				&& StringUtils.isEmpty(refund.getContractCode())) {
			getRequest().setAttribute("radio1", "0");
		}

		return ADD;
	}

	public String look() {
		getRequest().setAttribute("flag", "false");
		refund = refundService.get(Refund.class, id);
		Partner p = refundService.get(Partner.class, refund.getPartnerId());
		refund.setPartnerName(null == p ? "" : p.getName());
		if (StringUtils.isEmpty(refund.getWorkOrderCode())
				&& StringUtils.isNotEmpty(refund.getContractCode())) {
			getRequest().setAttribute("radio1", "1");
		} else if (StringUtils.isNotEmpty(refund.getWorkOrderCode())
				&& StringUtils.isEmpty(refund.getContractCode())) {
			getRequest().setAttribute("radio1", "0");
		}

		return ADD;
	}

	/**
	 * @Title: remove
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String remove() {
		refund = refundService.get(Refund.class, ids[0]);
		refundService.delete(refund);
		operate("退款管理", "删除", refund.getCode());
		return ajax(Status.success, "success");
	}

	/**
	 * @Title: saveOrUpdate
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String saveOrUpdate() {
		refund = JsonUtil.toObject(formdata, Refund.class);
		if (null != refund.getRefundDate()) {
			Long lOld = refund.getRefundDate().getTime();
			refund.setRefundDate(new Date(lOld - 28800000));
		}
		refundService.saveOrUpdate(refund);
		return ajax(Status.success, "success");
	}

	/**
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		HashMap m = refundService.getListData(pager, id);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	/**
	 * @Title: getContract
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String getContract() {
		Contract contract = refundService.get(Contract.class,
				Restrictions.eq("id", key));
		String json = JsonUtil.Encode(contract);
		return ajax(Status.success, json);
	}

	/**
	 * @Title: getWorkOrder
	 * @return
	 * @author zy
	 * @since 2017年7月21日 V 1.0
	 */
	public String getWorkOrder() {
		WorkOrder workOrder = refundService.get(WorkOrder.class,
				Restrictions.eq("id", key));
		String json = JsonUtil.Encode(workOrder);
		return ajax(Status.success, json);
	}

	public String approveApplication() {
		try {
			refundService.changeVerifyStatus(id, "1");
			refund = refundService.get(Refund.class, id);
			operate("退款管理", "审核", refund.getCode());
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

}
