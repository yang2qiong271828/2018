package com.lingnet.vocs.action.customer;

import com.lingnet.common.action.BaseAction;

/**
 * 
 * @ClassName: CustomerGasAction
 * @Description: 客户监测气体维护Action
 * @author duanjj
 * @date 2017年6月7日 上午9:14:15
 * 
 */
public class CustomerGasAction extends BaseAction {

	/**
     * 
     */
	private static final long serialVersionUID = 4112433819510469696L;

	/**
	 * 跳转到list页面
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 跳转到ADD页面
	 */
	public String add() {
		return ADD;
	}

}
