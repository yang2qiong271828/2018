package com.lingnet.vocs.action.vocsmanage;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

public class VocsUseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * VOCs治理单元使用时间及频次统计
	 * 
	 * @Title: list
	 * @return
	 * @author Lipx
	 * @since 2017年6月5日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	public String getEquipmentCount() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"company\":\"CO\", \"supplier\":\"20%\", \"type\":\"HCO2\", \"parameter \":\"10%\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备B\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备C\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getUseData() {
		String jsonString = "[]";
		jsonString = "[{\"data1\":\"1,2,5,8,9,5,4,8,10,15,18,12\",\"data2\":\"7,8,9,10,1,2,3,8,14,10,15,13\"}]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}
}
