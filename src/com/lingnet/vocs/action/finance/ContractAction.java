/**
 * @ContractAction.java
 * @com.lingnet.vocs.action.finance
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月13日
 */

package com.lingnet.vocs.action.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.Constants;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Attachments;
import com.lingnet.vocs.entity.Contract;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.attachments.AttachmentsService;
import com.lingnet.vocs.service.finance.ContractService;

/**
 * @ClassName: ContractAction
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月13日 上午10:21:07
 * 
 * 
 * 
 */
@ParentPackage("finance")
@Namespace("/finance")
public class ContractAction extends BaseAction {

	private static final long serialVersionUID = 2272339312729979458L;

	@Resource(name = "contractService")
	private ContractService contractService;
	@Resource(name = "attachmentsService")
	private AttachmentsService attachmentsService;

	private Contract contract;// 合同实体
	private String formdata;
	private ArrayList<Attachments> imgList;
	private String attachmentdata;// 页面上附件列表
	private String dhbz;
	/**
	 * 合同展现页面
	 * 
	 * @Title: list
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 新增合同
	 * 
	 * @Title: add
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 编辑合同
	 * 
	 * @Title: edit
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String edit() {
		contract = contractService.get(Contract.class,
				Restrictions.eq("id", id));
		// 设置imgList
		imgList = attachmentsService.getAttachmentListByEntityId(id);
		// 设置companyName
		Partner pa = getBeanById(Partner.class, contract.getCompanyId());
		if (pa != null) {
			contract.setCompanyName(pa.getName());
		}
		return ADD;
	}

	/**
	 * 查看合同
	 * 
	 * @Title: look
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String look() {
		getRequest().setAttribute("flag", "false");
		contract = contractService.get(Contract.class,
				Restrictions.eq("id", id));
		// 设置imgList
		imgList = attachmentsService.getAttachmentListByEntityId(id);
		Partner pa = getBeanById(Partner.class, contract.getCompanyId());
		if (pa != null) {
			contract.setCompanyName(pa.getName());
		}
		return ADD;
	}

	/**
	 * 保存或更新
	 * 
	 * @Title: saveOrUpdate
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			contract = JsonUtil.toObject(formdata, Contract.class);
			if (null != contract.getSignDate()) {
				Long lOld = contract.getSignDate().getTime();
				contract.setSignDate(new Date(lOld - 28800000));
			}
			if (null != contract.getEndDate()) {
				Long lOld = contract.getEndDate().getTime();
				contract.setEndDate(new Date(lOld - 28800000));
			}
			if (null != contract.getAllivalDate()) {
				Long lOld = contract.getAllivalDate().getTime();
				contract.setAllivalDate(new Date(lOld - 28800000));
			}
			contractService.saveOrUpdate(contract, attachmentdata);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 删除合同
	 * 
	 * @Title: remove
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String remove() {
		try {
			contractService.remove(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}

	/**
	 * 获取合同列表
	 * 
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		HashMap map = contractService
				.getContractListData(pager, searchData, id,dhbz);
		String json = JsonUtil.Encode(map);
		return ajax(Status.success, json);
	}

	/**
	 * 跳转：合同甲方选择页面 在合同添加/编辑页面，根据合作商类型要限定所能选的合作商。
	 * 
	 * @Title: firstParty
	 * @return
	 * @author zy
	 * @since 2017年6月28日 V 1.0
	 */
	public String firstParty() {
		String s = getRequest().getParameter("partnerOrCustomer");
		getRequest().setAttribute("partnerOrCustomer", s);
		return "firstparty";
	}

	/**
	 * 跳转设备归属转移公司选择页面
	 * 
	 * @Title: company
	 * @return String
	 * @author wanl
	 * @since 2017年7月17日 V 1.0
	 */
	public String company() {
		return "company";
	}

	/**
	 * 跳转选择合同页面
	 * 
	 * @Title: showContract
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String showContract() {
		return "showcontract";
	}

	/**
	 * 选合同 页面 数据获取
	 * 
	 * @Title: getShowContractListData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getShowContractListData() {
		String partnerId = this.getSession("partnerId").toString();
		System.out.println(partnerId);
		String json = contractService.getShowContractListData(pager, key,
				partnerId);
		
		System.out.println(json);
		return ajax(Status.success, JsonUtil.Encode(json));
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
			String s = contractService.validateCode(key);
			return ajax(Status.success, s);
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 提交审核
	 * 
	 * @Title: submitApplication
	 * @return
	 * @author zy
	 * @since 2017年7月1日 V 1.0
	 */
	public String submitApplication() {
		try {
			contractService.changeVerifyStatus(id,
					Constants.VERIFYSTATUS_SUBMITTED);
			contract = contractService.get(Contract.class, id);
			operate("合同信息管理", "提交", contract.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, "success");
	}

	/**
	 * 同意审核
	 * 
	 * @Title: approveApplication
	 * @return
	 * @author zy
	 * @since 2017年7月1日 V 1.0
	 */
	public String approveApplication() {
		try {
			contractService.changeVerifyStatus(id,
					Constants.VERIFYSTATUS_APPROVED);
			contract = contractService.get(Contract.class, id);
			operate("合同信息管理", "审核", contract.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, "success");
	}

	/**
	 * 撤回审核
	 * 
	 * @Title: cancelApplication
	 * @return
	 * @author zy
	 * @since 2017年7月1日 V 1.0
	 */
	public String cancelApplication() {
		try {
			contractService.changeVerifyStatus(id,
					Constants.VERIFYSTATUS_CANCELED);
			contract = contractService.get(Contract.class, id);
			operate("合同信息管理", "撤回", contract.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success, "success");
	}

	/**
	 * 驳回审核
	 * 
	 * @Title: reject
	 * @return
	 * @author zy
	 * @since 2017年7月1日 V 1.0
	 */
	public String reject() {
		return "reject";
	}

	/**
	 * 保存驳回意见，更新合同审核状态
	 * 
	 * @Title: saveRjectReason
	 * @return
	 * @author zy
	 * @since 2017年7月5日 V 1.0
	 */
	public String saveRejectReason() {
		try {
			// 保存驳回原因，驳回人
			contract = JsonUtil.toObject(formdata, Contract.class);
			contractService.saveRejectReason(contract);
			operate("合同信息管理", "驳回", contract.getCode());
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}

	/**
	 * 驳回意见提示窗
	 * 
	 * @Title: tipRejectReason
	 * @return
	 * @author zy
	 * @since 2017年7月5日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String tipRejectReason() {
		contract = contractService.get(Contract.class, id);
		HashMap map = new HashMap();
		map.put("rejectContent", contract.getRejectReason());
		String s = JsonUtil.Encode(map);
		return ajax(Status.success, s);
	}

	/**
	 * 反审核
	 * 
	 * @Title: getBackApplication
	 * @return
	 * @author zy
	 * @since 2017年7月1日 V 1.0
	 */
	public String getBackApplication() {
		try {
			contractService.changeVerifyStatus(id,
					Constants.VERIFYSTATUS_GETBACK);
			contract = contractService.get(Contract.class, id);
			operate("合同信息管理", "反审核", contract.getCode());
		} catch (Exception e) {
			// e.printStackTrace();
			return ajax(Status.error, e.getMessage());
		}
		return ajax(Status.success, "success");
	}

	/**
	 * @Title: getContractByEquipment
	 * @return
	 * @author zy
	 * @since 2017年7月18日 V 1.0
	 */
	public String getContractByEquipment() {
		String eqId = (String) getRequest().getParameter("eqId");
		String json = contractService.getContractByEquipment(pager, eqId, id);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 处理到期状态
	 * 
	 * @Title: dispose
	 * @return String
	 * @author lyz
	 * @since 2017年10月13日 V 1.0
	 */
	public String dispose() {
		String flag = contractService.dispose(id);
		return ajax(Status.success, flag);
	}

	/*********************************************** get set ***************************************/
	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public ArrayList<Attachments> getImgList() {
		return imgList;
	}

	public void setImgList(ArrayList<Attachments> imgList) {
		this.imgList = imgList;
	}

	public String getAttachmentdata() {
		return attachmentdata;
	}

	public void setAttachmentdata(String attachmentdata) {
		this.attachmentdata = attachmentdata;
	}

	public String getDhbz() {
		return dhbz;
	}

	public void setDhbz(String dhbz) {
		this.dhbz = dhbz;
	}
	
}
