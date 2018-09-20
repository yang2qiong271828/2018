package com.lingnet.vocs.action.statistics;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.abel533.echarts.Legend;
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
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Item;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.aominfo.ItemService;
import com.lingnet.vocs.service.statistics.ServiceChargeService;

public class ServiceChargeAction extends BaseAction {
	private static final long serialVersionUID = -5179404342309757430L;
	private String option;
	@Resource(name = "serviceChargeService")
	private ServiceChargeService serviceChargeService;
	@Resource(name = "itemService")
	private ItemService itemService;

	private String top;

	public String charts() {
		return "charts";
	}

	/**
	 * @Title: getChartsData
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public String getChartsData() {
		List<Item> items = itemService.getListByOrder(Item.class,
				Order.desc("itemCode"), eq("is_delete", "0"));
		List<Object[]> listFromDB = serviceChargeService.getChartsData(key);
		Map mParam = JsonUtil.parseProperties(key);
		String content = (String) mParam.get("content");
		String caliber = (String) mParam.get("caliber");
		String radio1 = (String) mParam.get("radio1");

		EnhancedOption op = new EnhancedOption();
		if (content.equals("serviceMaterial")) {
			if ("barline".equals(radio1)) {
				op = getBarChart2(op, caliber, listFromDB);
			} else if ("pie".equals(radio1)) {
				op = getPieChart2(op, caliber, listFromDB);
			}
		} else if (content.equals("materialOnly")) {
			if ("barline".equals(radio1)) {
				op = getBarChart(op, caliber, items, listFromDB);
			} else if ("pie".equals(radio1)) {
				op = getPieChart(op, caliber, items, listFromDB);
			}
		}
		/*
		 * op.title().x(X.center); op.legend().orient(Orient.vertical);
		 * op.legend().x(X.left);
		 */
		option = GsonUtil.format(op);
		String s = JsonUtil.Encode(option);
		return ajax(Status.success, s);
	}

	/**
	 * @Title: getCaliberCn
	 * @param caliber
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	private String getCaliberCn(String caliber) {
		if (caliber.equals("year"))
			return "年度";
		else if (caliber.equals("season"))
			return "季度";
		else if (caliber.equals("month"))
			return "月份";
		else
			return "";
	}

	/**
	 * 物料费，获取pie的op
	 * 
	 * @Title: getPieChart
	 * @param op
	 * @param caliber
	 * @param items
	 * @param listFromDB
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private EnhancedOption getPieChart(EnhancedOption op, String caliber,
			List<Item> items, List<Object[]> listFromDB) {
		op.title().text(getCaliberCn(caliber));
		op.tooltip().show(true);
		op.toolbox().show(true);
		op.toolbox().show(true).feature(Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		if (caliber.equals("datePeriod")) {
			List listLegend = new ArrayList();
			int num = items.size();
			if (top != null) {
				if ("0".equals(top)) {
					num = items.size();
				} else if ("1".equals(top)) {
					if (items.size() > 10)
						num = 10;
				} else if ("2".equals(top)) {
					if (items.size() > 20)
						num = 20;
				}
			}
			Object[] dataArrs = new Object[num];
			for (int j = 0; j < num; j++) {
				listLegend.add(items.get(j).getItemCode());
				dataArrs[j] = new Data(items.get(j).getItemCode(),
						listFromDB.get(0)[j] == null ? 0 : listFromDB.get(0)[j]);
			}
			Legend legend = new Legend();
			legend.data(listLegend);
			op.legend(legend);
			Pie pie = new Pie();
			pie.setName("物料费用");
			pie.clockWise(false).data(dataArrs);
			op.series(pie);
		}
		return op;
	}

	/**
	 * 服务费/物料费，获取pie的op
	 * 
	 * @Title: getPieChart2
	 * @param op
	 * @param caliber
	 * @param listFromDB
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private EnhancedOption getPieChart2(EnhancedOption op, String caliber,
			List<Object[]> listFromDB) {
		op.title().text(getCaliberCn(caliber));
		op.tooltip().show(true);
		op.toolbox().show(true);
		op.toolbox().show(true).feature(Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		if (caliber.equals("datePeriod")) {
			List listLegend = new ArrayList();
			listLegend.add("应收物料费");
			listLegend.add("实收物料费");
			listLegend.add("应收服务费");
			listLegend.add("实收服务费");
			Legend legend = new Legend();
			legend.data(listLegend);
			op.legend(legend);
			Object[] dataArrs = new Object[4];
			dataArrs[0] = new Data("应收物料费", listFromDB.get(0)[0]);
			dataArrs[1] = new Data("实收物料费", listFromDB.get(0)[1]);
			dataArrs[2] = new Data("应收服务费", listFromDB.get(0)[2]);
			dataArrs[3] = new Data("实收服务费", listFromDB.get(0)[3]);
			Pie pie = new Pie();
			pie.setName("物料费/服务费");
			pie.clockWise(false).data(dataArrs);
			op.series(pie);
		}
		return op;
	}

	/**
	 * 物料费，获取bar、line的op
	 * 
	 * @Title: getBarChart
	 * @param op
	 * @param caliber
	 * @param items
	 * @param listFromDB
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private EnhancedOption getBarChart(EnhancedOption op, String caliber,
			List<Item> items, List<Object[]> listFromDB) {
		op.title().text(getCaliberCn(caliber));
		op.tooltip().trigger(Trigger.axis);
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		int num = items.size();
		if (top != null) {
			if ("0".equals(top)) {
				num = items.size();
			} else if ("1".equals(top)) {
				if (items.size() > 10)
					num = 10;
			} else if ("2".equals(top)) {
				if (items.size() > 20)
					num = 20;
			}
		}
		// 如果是 year season month，分组将有两层
		if (!caliber.equals("datePeriod")) {
			// 在时间区间中，没有分series，所以没有legend
			Legend legend = new Legend();

			Object[] legendArrs = new Object[num];
			for (int i = 0; i < num; ++i) {
				legendArrs[i] = items.get(i).getItemCode();
			}
			legend.data(legendArrs);
			op.legend(legend);

			// 1、首先声明结果list，每一项是一个map，第一个map是echart横轴分组，第二个以后的每一个map，对应echarts的每一个series
			List<HashMap> listResult = new ArrayList<HashMap>();
			for (int i = 0; i < num + 1; ++i) {// 大小为items+1
				HashMap hm = new HashMap();
				if (i == 0) {// 第一项是yearSeasonMonth
					hm.put("name", "yearSeasonMonth");
				} else {
					hm.put("name", items.get(i - 1).getItemCode());
				}
				hm.put("data", new Object[listFromDB.size()]);
				listResult.add(hm);
			}
			// 遍历每一行
			for (int i = 0; i < listFromDB.size(); ++i) {
				// 遍历每一列
				for (int j = 0; j < num + 1; ++j) {
					Object[] temp = (Object[]) listResult.get(j).get("data");// 取出map中声明好的数组，添加值
					if (null != listFromDB.get(i)[j]) {
						if (j == 0) {
							temp[i] = listFromDB.get(i)[j]
									+ getCaliberCn(caliber);
						} else {
							temp[i] = listFromDB.get(i)[j];
						}
					} else {
						temp[i] = 0;
					}
				}
			}
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.name(getCaliberCn(caliber))
					.data((Object[]) listResult.get(0).get("data")).axisLabel()
					.rotate(30);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("费用金额"));

			for (HashMap mm : listResult) {
				if (!mm.get("name").equals("yearSeasonMonth")) {
					Bar bar = new Bar();
					String name = (String) mm.get("name");
					bar.name(name).stack(name).symbol(Symbol.circle)
							.data((Object[]) mm.get("data")).itemStyle()
							.normal();
					op.series(bar);
				}
			}
		} else {
			// 没有 yearSeasonMonth 列，只有数据列，且只有一行
			CategoryAxis categoryAxis = new CategoryAxis();
			List listItemCode = new ArrayList();
			int da = 0;
			for (Item it : items) {
				if (da < num) {
					da++;
					listItemCode.add(it.getItemCode());
				}
			}
			List listData = new ArrayList();
			for (int i = 0; i < num; ++i) {// null值转0
				Object o = null;
				if (listFromDB.size() > 0) {
					o = listFromDB.get(0)[i];
				}
				listData.add(o == null ? 0 : o);
			}
			categoryAxis.name(getCaliberCn(caliber))
					.data(listItemCode.toArray()).axisLabel().rotate(30);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("费用金额"));
			Bar bar = new Bar();
			String name = "时间区间";
			bar.name(name).stack(name).symbol(Symbol.circle)
					.data(listData.toArray()).itemStyle().normal();
			op.series(bar);
		}
		return op;
	}

	/**
	 * @Title: getAllUser
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getUserByPartner() {
		String partnerId = getRequest().getParameter("partnerId");
		Conjunction conjun = Restrictions.conjunction();
		conjun.add(eq("isDeleted", "0"));
		conjun.add(eq("partnerId", partnerId));
		List<QxUsers> allUsers = serviceChargeService.getList(QxUsers.class,
				conjun);
		List result = new ArrayList();
		for (QxUsers user : allUsers) {
			HashMap m = new HashMap();
			m.put("id", user.getId());
			m.put("name", user.getName());
			result.add(m);
		}
		return ajax(Status.success, JsonUtil.Encode(result));
	}

	/**
	 * 服务费/物料费，获取bar、line的op
	 * 
	 * @Title: getBarChart2
	 * @param op
	 * @param caliber
	 * @param listFromDB
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private EnhancedOption getBarChart2(EnhancedOption op, String caliber,
			List<Object[]> listFromDB) {
		op.title().text(getCaliberCn(caliber));
		op.tooltip().trigger(Trigger.axis);
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		if (!caliber.equals("datePeriod")) {
			// 在时间区间中，没有分series，所以没有legend
			Legend legend = new Legend();
			Object[] legendArrs = new Object[4];
			legendArrs[0] = "应收物料费";
			legendArrs[1] = "实收物料费";
			legendArrs[2] = "应收服务费";
			legendArrs[3] = "实收服务费";
			legend.data(legendArrs);
			op.legend(legend);
			ArrayList yearSeasonMonth = new ArrayList();
			ArrayList mReceivable = new ArrayList();
			ArrayList mReceived = new ArrayList();
			ArrayList sReceivable = new ArrayList();
			ArrayList sReceived = new ArrayList();
			for (Object[] o : listFromDB) {
				yearSeasonMonth.add(o[0] + getCaliberCn(caliber));
				mReceivable.add(o[1]);
				mReceived.add(o[2]);
				sReceivable.add(o[3]);
				sReceived.add(o[4]);
			}
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.name(getCaliberCn(caliber))
					.data(yearSeasonMonth.toArray()).axisLabel().rotate(30);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("费用金额"));
			Bar bar = new Bar();
			Bar bar2 = new Bar();
			Bar bar3 = new Bar();
			Bar bar4 = new Bar();
			String name = "应收物料费";
			String name2 = "实收物料费";
			String name3 = "应收服务费";
			String name4 = "实收服务费";
			bar.name(name).stack(name).symbol(Symbol.circle)
					.data(mReceivable.toArray()).itemStyle().normal();
			bar2.name(name2).stack(name2).symbol(Symbol.circle)
					.data(mReceived.toArray()).itemStyle().normal();
			bar3.name(name3).stack(name3).symbol(Symbol.circle)
					.data(sReceivable.toArray()).itemStyle().normal();
			bar4.name(name4).stack(name4).symbol(Symbol.circle)
					.data(sReceived.toArray()).itemStyle().normal();
			op.series(bar);
			op.series(bar2);
			op.series(bar3);
			op.series(bar4);
		} else {
			ArrayList xNames = new ArrayList();
			xNames.add("应收物料费");
			xNames.add("实收物料费");
			xNames.add("应收服务费");
			xNames.add("实收服务费");
			ArrayList theData = new ArrayList();
			theData.add(listFromDB.get(0)[0]);
			theData.add(listFromDB.get(0)[1]);
			theData.add(listFromDB.get(0)[2]);
			theData.add(listFromDB.get(0)[3]);
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.name(getCaliberCn(caliber)).data(xNames.toArray())
					.axisLabel().rotate(30);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("费用金额"));
			Bar bar = new Bar();
			String name = "费用金额";
			bar.name(name).stack(name).symbol(Symbol.circle)
					.data(theData.toArray()).itemStyle().normal();
			op.series(bar);
		}
		return op;
	}

	/**
	 * @Title: getGridData
	 * @return
	 * @author zy
	 * @since 2017年8月1日 V 1.0
	 */
	public String getGridData() {
		String engineerCn = "";
		try {
			engineerCn = URLDecoder.decode(
					getRequest().getParameter("engineerCn"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = serviceChargeService.getGridData(key, pager, engineerCn);
		return ajax(Status.success, s);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllPartner() {
		Disjunction disjun = Restrictions.disjunction();
		disjun.add(eq("partnerOrCustomer", "P"));
		disjun.add(eq("partnerOrCustomer", "H"));
		disjun.add(eq("partnerOrCustomer", "C"));
		List<Partner> partners = serviceChargeService.getListByOrder(
				Partner.class, Order.asc("name"), eq("isDeleted", "0"), disjun);
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

	public ServiceChargeService getServiceChargeService() {
		return serviceChargeService;
	}

	public void setServiceChargeService(
			ServiceChargeService serviceChargeService) {
		this.serviceChargeService = serviceChargeService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

}
