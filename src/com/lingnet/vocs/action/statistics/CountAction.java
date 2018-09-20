package com.lingnet.vocs.action.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

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
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.dao.statistics.CountDao;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.service.statistics.CountService;

/**
 * 统计
 * 
 * @ClassName: CountAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月4日 上午8:06:17
 * 
 */
public class CountAction extends BaseAction {

	private static final long serialVersionUID = -1817257427855062811L;

	@Resource(name = "countService")
	private CountService countService;

	public String method;// 统计方式

	private String option;

	private String key;

	private String partnerId;

	private String top;

	@Resource(name = "countDao")
	private CountDao countDao;

	public String listCharts() {
		return "listcharts";
	}

	/**
	 * 工单统计图形
	 * 
	 * @Title: getChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月24日 V 1.0
	 */
	@SuppressWarnings("all")
	public String getChartsData() {
		List<Object[]> list = countService.getChartsData(method, key);
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
		}
		op.tooltip().trigger(Trigger.axis);
		op.legend("总数");
		op.legend("完成数");
		op.legend("平均费用");
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
			String wanchengshu = object[1].toString();
			String wuliao = object[3] == null ? "0" : object[3].toString();
			String fuwu = object[4] == null ? "0" : object[4].toString();
			double a = Double.parseDouble(wanchengshu);
			double b = Double.parseDouble(fuwu);
			double c = Double.parseDouble(wuliao);
			if (a == 0.0) {
				res4.add(0.00);
			} else {
				res4.add(String.format("%.2f", (b + c) / a));
			}
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
				Area area = countService.get(Area.class, object[5] == null ? ""
						: object[5].toString());
				res2.add(area == null ? "" : area.getName());
			} else {
				res2.add(object[5] == null ? "" : object[5].toString());
			}
		}
		CategoryAxis categoryAxis = new CategoryAxis();
		if ("0".equals(method)) {
			categoryAxis.name("年份").data(res2.toArray()).axisLabel().rotate(25);
		} else if ("1".equals(method)) {
			categoryAxis.name("季节").data(res2.toArray()).axisLabel().rotate(25);
		} else if ("2".equals(method)) {
			categoryAxis.name("月份").data(res2.toArray()).axisLabel().rotate(25);
		} else if ("3".equals(method)) {
			categoryAxis.name("地区").data(res2.toArray()).axisLabel().rotate(25);
		} else if ("4".equals(method)) {
			categoryAxis.name("客户").data(res2.toArray()).axisLabel().rotate(25);
		}

		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("工单数量"));
		op.yAxis(new ValueAxis().name("费用"));

		Bar bar = new Bar();
		bar.name("总数").stack("总数").symbol(Symbol.circle).data(res1.toArray())
				.itemStyle().normal().color("#00FFFF");
		op.series(bar);
		bar = new Bar();
		bar.name("完成数").stack("完成数").symbol(Symbol.circle).data(res3.toArray())
				.itemStyle().normal().color("#DA70D6");
		op.series(bar);
		bar = new Bar();
		bar.name("平均费用").stack("平均费用").symbol(Symbol.circle)
				.data(res4.toArray()).itemStyle().normal().color("#FFB6C1");
		bar.setyAxisIndex(1);
		op.series(bar);
		option = GsonUtil.format(op);

		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 工单统计列表
	 * 
	 * @Title: getGridData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月24日 V 1.0
	 */
	public String getGridData() {
		String json = "";
		json = countService.getGridData(method, key, pager);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String eqCharts() {

		return "eqcharts";
	}

	/**
	 * 设备维修统计
	 * 
	 * @Title: getEqChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月31日 V 1.0
	 */
	@SuppressWarnings("all")
	public String getEqChartsData() {
		List<Object[]> list = countService.getEqChartsData(partnerId, top, key);
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> m = JsonUtil.parseProperties(key);
		EnhancedOption op = new EnhancedOption();
		op.title().text("设备");
		op.tooltip().trigger(Trigger.axis);
		op.legend("次数");
		op.legend("平均故障时间(小时)");
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		for (int j = 0; j < list.size(); j++) {
			Object[] object = list.get(j);
			String code = list.get(j)[1].toString();
			List<Object[]> orderList = countDao.getEqCountData(partnerId, top,
					key);
			if (orderList != null && orderList.size() > 0) {
				Object[] one = orderList.get(0);
				Object[] end = orderList.get(orderList.size() - 1);
				Calendar cal = Calendar.getInstance();
				Date onedate = null;
				try {
					onedate = from.parse(one[1].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cal.setTime(onedate);
				Long lSignDate = cal.getTimeInMillis();
				Date date = new Date();
				if (StringUtils.isNotEmpty(m.get("endtime"))) {
					try {
						date = from.parse(end[1].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				cal.setTime(date);
				Long lEndDate = cal.getTimeInMillis();
				if ((1000 * 3600 * (orderList.size() - 1)) != 0) {
					double daysBetween = ((lEndDate - lSignDate))
							/ (1000 * 3600 * (orderList.size() - 1));
					res2.add(daysBetween == 0 ? 1 : daysBetween);
				} else {
					res2.add(0);
				}

			}
			res1.add(object[0]);
			res3.add(object[1]);
		}
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.name("次数").data(res3.toArray()).axisLabel().rotate(20);
		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("维修次数"));
		op.yAxis(new ValueAxis().name("平均故障时间(小时)"));
		Bar bar = new Bar();
		bar.name("次数").stack("次数").symbol(Symbol.circle).data(res1.toArray())
				.itemStyle().normal().color("#00FFFF");
		op.series(bar);
		bar = new Bar();
		bar.name("平均故障时间(小时)").stack("平均故障时间(小时)").symbol(Symbol.circle)
				.data(res2.toArray()).itemStyle().normal().color("#BCEE68");
		bar.setyAxisIndex(1);
		op.series(bar);
		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 设备统计grid
	 * 
	 * @Title: getEqGridData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String getEqGridData() {
		String json = "";
		json = countService.getEqGridData(partnerId, top, pager, key);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 跳转到物料使用统计页面
	 * 
	 * @Title: itemCount
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String itemCount() {

		return "itemcount";
	}

	/**
	 * 物料使用统计页面 图形
	 * 
	 * @Title: getItemChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings("all")
	public String getItemChartsData() {
		List<Object[]> list = countService.getItemChartsData(partnerId, top,
				key);
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EnhancedOption op = new EnhancedOption();
		op.title().text("物料");
		op.tooltip().trigger(Trigger.axis);
		op.legend("使用次数");
		op.legend("使用数量");
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		for (int j = 0; j < list.size(); j++) {
			Object[] object = list.get(j);
			res1.add(object[0]);
			res3.add(object[1]);
			res2.add(object[2]);
		}
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.name("使用次数").data(res3.toArray()).axisLabel().rotate(20);
		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("使用次数"));
		op.yAxis(new ValueAxis().name("使用数量"));
		Bar bar = new Bar();
		bar.name("使用次数").stack("使用次数").symbol(Symbol.circle)
				.data(res1.toArray()).itemStyle().normal().color("#00FFFF");
		op.series(bar);
		bar = new Bar();
		bar.name("使用数量").stack("使用数量").symbol(Symbol.circle)
				.data(res2.toArray()).itemStyle().normal().color("#BCEE68");
		bar.setyAxisIndex(1);
		op.series(bar);
		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 物料统计grid
	 * 
	 * @Title: getItemGridData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String getItemGridData() {
		String json = "";
		json = countService.getItemGridData(partnerId, top, pager, key);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 跳转客户满意度统计页面
	 * 
	 * @Title: satisfaction
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String satisfaction() {

		return "satisfaction";
	}

	/**
	 * 客户满意度图形
	 * 
	 * @Title: getSatisfactionChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings("all")
	public String getSatisfactionChartsData() {
		List<Object[]> list = countService.getSatisfactionChartsData(partnerId,
				top, key);
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EnhancedOption op = new EnhancedOption();
		op.title().text("满意度");
		op.tooltip().trigger(Trigger.axis);
		op.legend("一分");
		op.legend("二分");
		op.legend("三分");
		op.legend("四分");
		op.legend("五分");
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
		List res5 = new ArrayList();
		List res6 = new ArrayList();
		for (int j = 0; j < list.size(); j++) {
			Object[] object = list.get(j);
			res1.add(object[2]);
			res3.add(object[1]);
			res2.add(object[3]);
			res4.add(object[4]);
			res5.add(object[5]);
			res6.add(object[6]);
		}
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.name("公司").data(res3.toArray()).axisLabel().rotate(20);
		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("数量"));
		Bar bar = new Bar();
		bar.name("一分").stack("一分").symbol(Symbol.circle).data(res1.toArray())
				.itemStyle().normal().color("#00FFFF");
		op.series(bar);
		bar = new Bar();
		bar.name("二分").stack("二分").symbol(Symbol.circle).data(res2.toArray())
				.itemStyle().normal().color("#BCEE68");
		op.series(bar);
		bar = new Bar();
		bar.name("三分").stack("三分").symbol(Symbol.circle).data(res4.toArray())
				.itemStyle().normal().color("#BCD2EE");
		op.series(bar);
		bar = new Bar();
		bar.name("四分").stack("四分").symbol(Symbol.circle).data(res5.toArray())
				.itemStyle().normal().color("#BFEFFF");
		op.series(bar);
		bar = new Bar();
		bar.name("五分").stack("五分").symbol(Symbol.circle).data(res6.toArray())
				.itemStyle().normal().color("#B4EEB4");
		op.series(bar);
		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 客户满意度grid
	 * 
	 * @Title: getSatisfactionChartsData
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月1日 V 1.0
	 */
	public String getSatisfactionGridData() {
		String json = "";
		json = countService.getSatisfactionGridData(partnerId, top, pager, key);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/************************************************************************************************************************************/

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

	public CountService getCountService() {
		return countService;
	}

	public void setCountService(CountService countService) {
		this.countService = countService;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

}
