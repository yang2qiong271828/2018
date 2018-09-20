package com.lingnet.vocs.action.statistics;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

/**
 * 活性炭使用记录
 * 
 * @ClassName: ActicarbonUseAction
 * @Description: TODO
 * @author xues
 * @date 2017年6月13日 上午8:11:48
 * 
 */
public class ActicarbonUseAction extends BaseAction {

	private static final long serialVersionUID = -3766217122343593564L;

	public String list() {

		return LIST;
	}

	public String getListData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"company\":\"客户A\",\"content\":\"2017-6-12\",\"date1\":\"20kg\", \"type\":\"5天\" }"
				+ " ,{ \"id\":\"2\", \"name\":\"设备B\", \"company\":\"客户B\",\"content\":\"2017-6-12\",\"date1\":\"20kg\",\"type\":\"6天\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}
}
