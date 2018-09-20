/**
 * @AccountflowAction.java
 * @com.lingnet.vocs.action.finance
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月13日
 */

package com.lingnet.vocs.action.finance;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AccountFlow;
import com.lingnet.vocs.service.finance.AccountFlowService;

/**
 * @ClassName: AccountflowAction
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月13日 上午10:50:18
 * 
 */
@ParentPackage("finance")
@Namespace("/finance")
public class AccountFlowAction extends BaseAction {
	private static final long serialVersionUID = -1966154977080152160L;
	private AccountFlow accountFlow;
	@Resource(name = "accountFlowService")
	private AccountFlowService accountFlowService;

	/**
	 * 账务流水：列表展示
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
	 * 账务流水：列表数据
	 * 
	 * @Title: getListData
	 * @return
	 * @author zy
	 * @since 2017年7月17日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getListData() {
		String areaId = getRequest().getParameter("areaId");
		HashMap m = accountFlowService.getListData(pager, areaId);
		String s = JsonUtil.Encode(m);
		return ajax(Status.success, s);
	}

	public AccountFlow getAccountFlow() {
		return accountFlow;
	}

	public void setAccountFlow(AccountFlow accountFlow) {
		this.accountFlow = accountFlow;
	}
}
