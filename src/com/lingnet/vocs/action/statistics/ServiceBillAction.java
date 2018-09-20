package com.lingnet.vocs.action.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.util.EnhancedOption;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.service.statistics.ServiceBillService;

/**
 * 服务账单
 * 
 * @ClassName: ServiceBill
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月31日 上午8:43:15
 * 
 */
@SuppressWarnings("all")
public class ServiceBillAction extends BaseAction {

	private static final long serialVersionUID = -5284672963151176660L;

	@Resource(name = "serviceBillService")
	private ServiceBillService serviceBillService;

	public String method;// 统计方式

	private String option;

	private String key;

	/**
	 * 跳转到服务费用统计页面
	 * 
	 * @Title: charts
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月31日 V 1.0
	 */
	public String charts() {
		return "charts";
	}

	/**
	 * 服务账单图表
	 * 
	 * @Title: getChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String getChartsData() {
		String json = "";
		List<Object[]> list = serviceBillService.getChartsData(method, key);
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EnhancedOption op = new EnhancedOption();
		if ("0".equals(method)) {
			op.title().text("年度");
		} else if ("1".equals(method)) {
			op.title().text("季度");
		} else if ("2".equals(method)) {
			op.title().text("月份");
		} else if ("3".equals(method)) {
			op.title().text("地区");
		} else if ("4".equals(method)) {
			op.title().text("客户");
		} else if ("5".equals(method)) {
			op.title().text("日期");
		}
		op.tooltip().trigger(Trigger.axis);
		op.legend("账单费用");
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		List res4 = new ArrayList();
		for (int j = 0; j < list.size(); j++) {
			Object[] object = list.get(j);
			res1.add(object[0]);
			res3.add(object[1]);
			double zhangdan = Double.parseDouble(object[6] == null ? "0.00"
					: object[6].toString());
			if ("1".equals(method)) {
				if ("1".equals(object[5].toString())) {
					res2.add("春季");
				} else if ("2".equals(object[5].toString())) {
					res2.add("夏季");
				} else if ("3".equals(object[5].toString())) {
					res2.add("秋季");
				} else if ("4".equals(object[5].toString())) {
					res2.add("冬季");
				}
			} else if ("2".equals(method)) {
				res2.add(Integer.parseInt(object[5].toString()) + "月");
			} else if ("3".equals(method)) {
				Area area = serviceBillService.get(Area.class,
						object[5].toString());
				res2.add(area == null ? "" : area.getName());
			} else {
				res2.add(object[5] == null ? "" : object[5].toString());
			}

			res4.add(String.format("%.2f", zhangdan));
		}

		CategoryAxis categoryAxis = new CategoryAxis();
		if ("0".equals(method)) {
			categoryAxis.name("年份").data(res2.toArray()).axisLabel().rotate(30);
		} else if ("1".equals(method)) {
			categoryAxis.name("季节").data(res2.toArray()).axisLabel().rotate(30);
		} else if ("2".equals(method)) {
			categoryAxis.name("月份").data(res2.toArray()).axisLabel().rotate(30);
		} else if ("3".equals(method)) {
			categoryAxis.name("地区").data(res2.toArray()).axisLabel().rotate(30);
		} else if ("4".equals(method)) {
			categoryAxis.name("客户").data(res2.toArray()).axisLabel().rotate(30);
		} else if ("5".equals(method)) {
			categoryAxis.name("日期").data(res2.toArray()).axisLabel();
		}

		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("账单费用"));

		Bar bar = new Bar();
		bar.name("账单费用").stack("账单费用").symbol(Symbol.circle)
				.data(res4.toArray()).itemStyle().normal().color("#00FFFF");
		op.series(bar);
		option = GsonUtil.format(op);

		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 服务账单grid
	 * 
	 * @Title: getGridData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String getGridData() {
		String json = "";
		json = serviceBillService.getGridData(pager, method, key);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public ServiceBillService getServiceBillService() {
		return serviceBillService;
	}

	public void setServiceBillService(ServiceBillService serviceBillService) {
		this.serviceBillService = serviceBillService;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
