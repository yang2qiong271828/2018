package com.lingnet.vocs.action.statistics;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.util.EnhancedOption;
import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.statistics.TaskScoreService;

/**
 * 现场任务及分数统计
 * 
 * @ClassName: TaskScoreAction
 * @Description: TODO
 * @author zy
 * @date 2017年7月27日 下午2:00:55
 * 
 */

public class TaskScoreAction extends BaseAction {
	private static final long serialVersionUID = 673512936107374917L;
	@Resource(name = "taskScoreService")
	private TaskScoreService taskScoreService;

	private String option;

	/**
	 * @Title: charts
	 * @return
	 * @author zy
	 * @since 2017年7月27日 V 1.0
	 */
	public String charts() {
		return "charts";
	}

	/**
	 * @Title: getChartsData
	 * @return
	 * @author zy
	 * @since 2017年7月27日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getChartsData() {
		List<Object[]> listChartData = taskScoreService.getChartsData(key);
		JSONObject jo = JSONObject.parseObject(key);
		String radio1 = jo.getString("radio1");
		// 统计口径
		String caliber = jo.getString("caliber");
		// 统计内容
		String content = jo.getString("content");
		// 图形类型
		EnhancedOption op = new EnhancedOption();
		if ("barline".equals(radio1)) {// 柱状图，线形图
			Legend legend = new Legend();
			Object[] legendArrs = null;
			if ("avgScore".equals(jo.get("content"))) {
				legendArrs = new Object[1];
				legendArrs[0] = "平均分数";
				op.title().text("平均分数");
			} else if ("taskCount".equals(jo.get("content"))) {
				legendArrs = new Object[1];
				legendArrs[0] = "任务数量";
				op.title().text("任务数量");
			} else {
				legendArrs = new Object[2];
				legendArrs[0] = "平均分数";
				legendArrs[1] = "任务数量";
				op.title().text("平均分数/任务数量");
			}
			legend.data(legendArrs);
			op.legend(legend);
			List resX = new ArrayList();
			List resY1 = new ArrayList();// 平均分数或任务数量
			List resY2 = new ArrayList();// 平均分数和任务数量都有的时候，是任务数量
			for (int i = 0; i < listChartData.size(); ++i) {
				resX.add(listChartData.get(i)[0]);

				if (!"taskCount".equals(jo.get("content"))) {
					double res = Double
							.parseDouble(listChartData.get(i)[1] == null ? "0"
									: listChartData.get(i)[1].toString());
					resY1.add(String.format("%.2f", res));
				} else {
					resY1.add(listChartData.get(i)[1] == null ? "0"
							: listChartData.get(i)[1].toString());
				}

				if (StringUtils.isEmpty(content)) {
					resY2.add(listChartData.get(i)[2] == null ? 0
							: listChartData.get(i)[2]);
				}
			}
			op.toolbox().show(true);
			op.toolbox()
					.show(true)
					.feature(new MagicType(Magic.line, Magic.bar).show(true),
							Tool.restore, Tool.saveAsImage);
			op.tooltip().trigger(Trigger.axis);
			op.calculable(true);

			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.name(getCaliberCn(caliber)).data(resX.toArray())
					.axisLabel().rotate(25);
			op.xAxis(categoryAxis);
			if ("avgScore".equals(content)) {
				AxisLabel aLeft = new AxisLabel();
				aLeft.formatter("{value} 分");
				op.yAxis(new ValueAxis().name((String) legendArrs[0])
						.position("left").axisLabel(aLeft).max(5).min(0));
				Bar bar = new Bar();
				bar.name((String) legendArrs[0]).stack((String) legendArrs[0])
						.symbol(Symbol.circle).data(resY1.toArray())
						.itemStyle().normal().color("#00FFFF");
				bar.yAxisIndex(0);
				op.series(bar);
			} else if ("taskCount".equals(content)) {
				AxisLabel aRight = new AxisLabel();
				aRight.formatter("{value} 次");
				op.yAxis(new ValueAxis().name((String) legendArrs[0])
						.position("right").axisLabel(aRight).max(15).min(0));
				Bar bar2 = new Bar();
				bar2.name((String) legendArrs[0]).stack((String) legendArrs[0])
						.symbol(Symbol.circle).data(resY1.toArray())
						.itemStyle().normal().color("#00AAAA");
				bar2.yAxisIndex(0);
				op.series(bar2);
			} else {
				AxisLabel aLeft = new AxisLabel();
				aLeft.formatter("{value} 分");
				op.yAxis(new ValueAxis().name((String) legendArrs[0])
						.position("left").axisLabel(aLeft).max(5).min(0));
				Bar bar = new Bar();
				bar.name((String) legendArrs[0]).stack((String) legendArrs[0])
						.symbol(Symbol.circle).data(resY1.toArray())
						.itemStyle().normal().color("#00FFFF");
				bar.yAxisIndex(0);
				op.series(bar);
				AxisLabel aRight = new AxisLabel();
				aRight.formatter("{value} 次");
				op.yAxis(new ValueAxis().name((String) legendArrs[1])
						.position("right").axisLabel(aRight).max(15).min(0));
				Bar bar2 = new Bar();
				bar2.name((String) legendArrs[1]).stack((String) legendArrs[1])
						.symbol(Symbol.circle).data(resY2.toArray())
						.itemStyle().normal().color("#00AAAA");
				bar2.yAxisIndex(1);
				op.series(bar2);
			}

		} else if ("pie".equals(radio1)) {// 饼图
			op.toolbox().show(true);
			op.toolbox().show(true).feature(Tool.restore, Tool.saveAsImage);
			op.tooltip().show(true);
			List resX = new ArrayList();
			if (StringUtils.isEmpty(content)) {
				Object[] oA1 = new Object[listChartData.size()];
				Object[] oA2 = new Object[listChartData.size()];
				for (int i = 0; i < listChartData.size(); ++i) {
					resX.add(listChartData.get(i)[0]);
					double res = Double
							.parseDouble(listChartData.get(i)[1] == null ? "0"
									: listChartData.get(i)[1].toString());
					oA1[i] = new Data((String) (listChartData.get(i)[0]),
							String.format("%.2f", res));
					oA2[i] = new Data((String) (listChartData.get(i)[0]),
							listChartData.get(i)[2]);
				}
				Pie pie1 = new Pie();
				Pie pie2 = new Pie();
				pie1.name("平均分数");
				pie2.name("任务数量");
				Object[] oc1 = { "25%", "50%" };
				Object[] oc2 = { "75%", "50%" };
				pie1.clockWise(false).data(oA1).setCenter(oc1);
				pie2.clockWise(false).data(oA2).setCenter(oc2);
				op.series(pie1);
				op.series(pie2);
			} else {
				Object[] objectArrs = new Object[listChartData.size()];
				for (int i = 0; i < listChartData.size(); ++i) {
					resX.add(listChartData.get(i)[0]);
					objectArrs[i] = new Data(
							(String) (listChartData.get(i)[0]),
							listChartData.get(i)[1]);
				}
				Pie pie = new Pie();
				pie.name(getContentCn(content));
				pie.clockWise(false).data(objectArrs);
				op.series(pie);
			}
			Legend legend = new Legend();
			legend.data(resX);
			op.legend(legend);
		}

		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.Encode(option));
	}

	/**
	 * @Title: getGridData
	 * @return
	 * @author zy
	 * @since 2017年7月27日 V 1.0
	 */
	public String getGridData() {
		String companyCn = "";
		try {
			companyCn = URLDecoder.decode(getRequest()
					.getParameter("companyCn"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String s = taskScoreService.getGridData(key, pager, companyCn);
		return ajax(Status.success, s);
	}

	private String getCaliberCn(String caliber) {
		if ("employee".equals(caliber)) {
			return "员工";
		} else if ("depart".equals(caliber)) {
			return "部门";
		} else if ("company".equals(caliber)) {
			return "公司";
		} else {
			return "";
		}
	}

	private String getContentCn(String content) {
		if ("taskCount".equals(content)) {
			return "任务数量";
		} else if ("avgScore".equals(content)) {
			return "平均分数";
		} else {
			return "";
		}
	}

	/**
	 * @Title: getDepartByPartner
	 * @return
	 * @author zy
	 * @since 2017年8月2日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDepartByPartner() {
		String partnerId = getRequest().getParameter("partnerId");
		List<QxDepartment> allDepart = taskScoreService.getList(
				QxDepartment.class, eq("isDeleted", "0"),
				eq("partnerId", partnerId));
		List result = new ArrayList();
		for (QxDepartment depart : allDepart) {
			HashMap m = new HashMap();
			m.put("id", depart.getId());
			m.put("name", depart.getName());
			result.add(m);
		}
		return ajax(Status.success, JsonUtil.Encode(result));
	}

	/**
	 * @Title: getAllPartner
	 * @return
	 * @author zy
	 * @since 2017年8月2日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllPartner() {
		Disjunction disjun = Restrictions.disjunction();
		disjun.add(eq("partnerOrCustomer", "P"));
		disjun.add(eq("partnerOrCustomer", "H"));
		disjun.add(eq("partnerOrCustomer", "C"));
		List<Partner> partners = taskScoreService.getListByOrder(Partner.class,
				Order.asc("name"), eq("isDeleted", "0"), disjun);
		List result = new ArrayList();
		for (Partner p : partners) {
			HashMap m = new HashMap();
			m.put("id", p.getId());
			m.put("name", p.getName());
			result.add(m);
		}
		return ajax(Status.success, JsonUtil.Encode(result));
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
