package com.lingnet.vocs.action.statistics;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

/**
 * 图表综合统计
 * 
 * @ClassName: SynthesizeAction
 * @Description: TODO
 * @author xues
 * @date 2017年6月8日 下午1:47:32
 * 
 */
public class SynthesizeAction extends BaseAction {

	private static final long serialVersionUID = 1270126257212674671L;

	// 图表综合统计
	public String charts() {

		return "charts";
	}

	// 城市废气处理量
	public String getChartCityData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"city\":\"北京\", \"handling\":\"100000\" }"
				+ " ,{ \"id\":\"2\",  \"city\":\"青岛\", \"handling\":\"90000\"}"
				+ " ,{ \"id\":\"3\",  \"city\":\"济南\", \"handling\":\"80000\" }"
				+ " ,{ \"id\":\"4\",  \"city\":\"上海\", \"handling\":\"70000\" }"
				+ " ,{ \"id\":\"5\",  \"city\":\"南京\", \"handling\":\"60000\"}"
				+ " ,{ \"id\":\"6\",  \"city\":\"石家庄\", \"handling\":\"50000\" }"
				+ " ,{ \"id\":\"7\",  \"city\":\"衡水\", \"handling\":\"40000\" }"
				+ " ,{ \"id\":\"8\",  \"city\":\"济南\", \"handling\":\"30000\" }"
				+ " ,{ \"id\":\"9\",  \"city\":\"承德\", \"handling\":\"20000\"}"
				+ " ,{ \"id\":\"10\", \"city\":\"沧州\", \"handling\":\"10000\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 城市废气处理量
	public String getChartGasData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"gas\":\"CO\", \"handling\":\"100\" }"
				+ " ,{ \"id\":\"2\",  \"gas\":\"CO2\", \"handling\":\"90\"}"
				+ " ,{ \"id\":\"3\",  \"gas\":\"CO3\", \"handling\":\"80\" }"
				+ " ,{ \"id\":\"4\",  \"gas\":\"SO2\", \"handling\":\"70\" }"
				+ " ,{ \"id\":\"5\",  \"gas\":\"SO3\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"6\",  \"gas\":\"NH3\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"7\",  \"gas\":\"H2S\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"8\",  \"gas\":\"NO2\", \"handling\":\"30\" }"
				+ " ,{ \"id\":\"9\",  \"gas\":\"NO\", \"handling\":\"20\"}"
				+ " ,{ \"id\":\"10\", \"gas\":\"CL2\", \"handling\":\"10\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 频次
	public String getChartFrequencyData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"equipment\":\"设备A\", \"handling\":\"100\" }"
				+ " ,{ \"id\":\"2\",  \"equipment\":\"设备B\", \"handling\":\"90\"}"
				+ " ,{ \"id\":\"3\",  \"equipment\":\"设备C\", \"handling\":\"80\" }"
				+ " ,{ \"id\":\"4\",  \"equipment\":\"设备D\", \"handling\":\"70\" }"
				+ " ,{ \"id\":\"5\",  \"equipment\":\"设备E\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"6\",  \"equipment\":\"设备F\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"7\",  \"equipment\":\"设备G\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"8\",  \"equipment\":\"设备H\", \"handling\":\"30\" }"
				+ " ,{ \"id\":\"9\",  \"equipment\":\"设备I\", \"handling\":\"20\"}"
				+ " ,{ \"id\":\"10\", \"equipment\":\"设备J\", \"handling\":\"10\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 频次
	public String getChartProduceData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"month\":\"一月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"2\",  \"month\":\"二月\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"3\",  \"month\":\"三月\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"4\",  \"month\":\"四月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"5\",  \"month\":\"五月\", \"handling\":\"70\"}"
				+ " ,{ \"id\":\"6\",  \"month\":\"六月\", \"handling\":\"90\" }"
				+ " ,{ \"id\":\"7\",  \"month\":\"七月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"8\",  \"month\":\"八月\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"9\",  \"month\":\"九月\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"10\", \"month\":\"十月\", \"handling\":\"20\" }"
				+ " ,{ \"id\":\"10\", \"month\":\"十一月\", \"handling\":\"80\" }"
				+ " ,{ \"id\":\"10\", \"month\":\"十二月\", \"handling\":\"70\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 合作商 客户
	public String getChartPartnerData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"month\":\"一月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"2\",  \"month\":\"二月\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"3\",  \"month\":\"三月\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"4\",  \"month\":\"四月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"5\",  \"month\":\"五月\", \"handling\":\"70\"}"
				+ " ,{ \"id\":\"6\",  \"month\":\"六月\", \"handling\":\"90\" }"
				+ " ,{ \"id\":\"7\",  \"month\":\"七月\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"8\",  \"month\":\"八月\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"9\",  \"month\":\"九月\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"10\", \"month\":\"十月\", \"handling\":\"20\" }"
				+ " ,{ \"id\":\"11\", \"month\":\"十一月\", \"handling\":\"80\" }"
				+ " ,{ \"id\":\"12\", \"month\":\"十二月\", \"handling\":\"70\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 设备行业占比
	public String getChartTradeData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"trade\":\"石化\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"2\",  \"trade\":\"精工\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"3\",  \"trade\":\"制药\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"4\",  \"trade\":\"涂装\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"5\",  \"trade\":\"合成革\", \"handling\":\"70\"}"
				+ " ,{ \"id\":\"6\",  \"trade\":\"印刷和包装\", \"handling\":\"90\" }"
				+ " ,{ \"id\":\"7\",  \"trade\":\"制鞋\", \"handling\":\"50\" }"
				+ " ,{ \"id\":\"8\",  \"trade\":\"电子信息\", \"handling\":\"40\" }"
				+ " ,{ \"id\":\"9\",  \"trade\":\"建筑装饰\", \"handling\":\"60\"}"
				+ " ,{ \"id\":\"10\", \"trade\":\"化学纤维制造业\", \"handling\":\"20\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}
}
