package com.lingnet.vocs.action.monit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.github.abel533.echarts.axis.TimeAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.util.EnhancedOption;
import com.lingnet.common.action.BaseAction;
import com.lingnet.util.AdsVarName;
import com.lingnet.util.DesVarName;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.entity.Cspz;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.service.monit.MonitoringService;

/**
 * 实时监控
 * 
 * @ClassName: MonitoringAction
 * @Description:
 * @author xues
 * @date 2017年6月6日 下午2:24:00
 * 
 */
public class MonitoringAction extends BaseAction {
	private static final long serialVersionUID = 27399829946389237L;

	private String name;
	@Resource(name = "monitoringService")
	private MonitoringService monitoringService;
	@Resource(name = "toolUtil")
	private ToolUtil toolUtil;
	@Resource(name = "toolNewUtil")
	private ToolNewUtil toolNewUtil;

	private String option;
	private List<String> options;
	private String boxId;
	private String navVals;// 选中项
	private String navVal;//
	private String start;
	private String end;

	/**
	 * 进入主页面 默认显示图标信息
	 * */
	public String list() {

		return "list";
	}

	/**
	 * 数据监测--实时数据
	 */
	public String list2() {

		return "list2";
	}

	/**
	 * 一体机显示图标信息
	 * */
	public String ytjlist() {

		return "ytjlist";
	}
	
	
	
	public String constant() {
		// boxId = "HTR-50HAC-0001";
		return "constant";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String constantdata() {

		// 根据设备id判断设备是吸附还是脱附设备
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));

		// 查询设备有几个监控点 n
		options = new ArrayList<String>();
		if (navVals != null && !"".equals(navVals)) {
			navVals = navVals.substring(1);// 去除开始的逗号
			String[] vals = navVals.split(",");
			List naVal = Arrays.asList(vals);
			if ("0".equals(eq.getEquipmentType())) {
				for (AdsVarName varName : AdsVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantData(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
	                            Object[] obs = new Object[2];
	                            obs[0] = object[2];
	                            obs[1] = object[1];
	                            mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			}else if ("3".equals(eq.getEquipmentType())) {
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantData(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
								Object[] obs = new Object[2];
								obs[0] = object[2];
								obs[1] = object[1];
								mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			} else {
				for (DesVarName varName : DesVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {

						List<Object[]> datas = monitoringService
								.findConstantData(boxId, varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						Line bar = new Line();// 存放数据
						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);
						TimeAxis timeAxis = new TimeAxis();
						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								// 将得到的类型转换成int类型
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
	                            Object[] obs = new Object[2];
	                            obs[0] = object[2];
	                            obs[1] = object[1];
	                            mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
								
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);

					}
				}
			}
		} else {
			if ("0".equals(eq.getEquipmentType())) {
				for (AdsVarName varName : AdsVarName.values()) {

					// 如果包含
					List<Object[]> datas = monitoringService.findConstantData(
							eq.getPlcIdentificationCode(), varName.getName());
					// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
					// HH:mm:ss
					SimpleDateFormat from = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					EnhancedOption op = new EnhancedOption();
					op.title().text(varName.getName());
					op.tooltip().trigger(Trigger.axis);

					Line bar = new Line();// 存放数据

					bar.type(SeriesType.line);
					bar.showAllSymbol(false);
					// bar.hoverable(false);

					TimeAxis timeAxis = new TimeAxis();
					Line bar1 = new Line();// 存放报警线
					bar1.type(SeriesType.line);
					bar1.xAxisIndex(0);
					bar1.yAxisIndex(0);
					bar1.itemStyle().normal().color0("red");
					Line line1 = new Line();
					line1.smooth(true).name("警报线").stack("警报线").itemStyle()
							.normal().color("red");
					if (datas != null && datas.size() > 0) {

						for (int j = 0; j < datas.size(); j++) {
							Object[] object = datas.get(j);
							HashMap mp = new HashMap();
							mp.put("name", object[2]);
                            Object[] obs = new Object[2];
                            obs[0] = object[2];
                            obs[1] = object[1];
                            mp.put("value", obs);
							bar.data().add(mp);
							bar1.data().add(25);
							line1.data().add("25");
						}

					}
					timeAxis.splitLine().show(false);
					op.xAxis(timeAxis);
					ValueAxis valueAxis = new ValueAxis();
					valueAxis.boundaryGap(0d, 1d);
					valueAxis.splitLine().show(false);
					op.yAxis(valueAxis);
					op.series(bar, line1);

					option = GsonUtil.format(op);

					options.add(option);

				}
			}else if ("3".equals(eq.getEquipmentType())) {
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {

					// 如果包含
					List<Object[]> datas = monitoringService.findConstantData(
							eq.getPlcIdentificationCode(), varName.getName());
					// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
					// HH:mm:ss
					SimpleDateFormat from = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					EnhancedOption op = new EnhancedOption();
					op.title().text(varName.getName());
					op.tooltip().trigger(Trigger.axis);

					Line bar = new Line();// 存放数据

					bar.type(SeriesType.line);
					bar.showAllSymbol(false);
					// bar.hoverable(false);

					TimeAxis timeAxis = new TimeAxis();
					Line bar1 = new Line();// 存放报警线
					bar1.type(SeriesType.line);
					bar1.xAxisIndex(0);
					bar1.yAxisIndex(0);
					bar1.itemStyle().normal().color0("red");
					Line line1 = new Line();
					line1.smooth(true).name("警报线").stack("警报线").itemStyle()
							.normal().color("red");
					if (datas != null && datas.size() > 0) {

						for (int j = 0; j < datas.size(); j++) {
							Object[] object = datas.get(j);
							HashMap mp = new HashMap();
							mp.put("name", object[2]);
							Object[] obs = new Object[2];
							obs[0] = object[2];
							obs[1] = object[1];
							mp.put("value", obs);
							bar.data().add(mp);
							bar1.data().add(25);
							line1.data().add("25");
						}

					}
					timeAxis.splitLine().show(false);
					op.xAxis(timeAxis);
					ValueAxis valueAxis = new ValueAxis();
					valueAxis.boundaryGap(0d, 1d);
					valueAxis.splitLine().show(false);
					op.yAxis(valueAxis);
					op.series(bar, line1);

					option = GsonUtil.format(op);

					options.add(option);

				}
			} else {
				for (DesVarName varName : DesVarName.values()) {

					List<Object[]> datas = monitoringService.findConstantData(
							eq.getPlcIdentificationCode(), varName.getName());
					// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
					// HH:mm:ss
					SimpleDateFormat from = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					EnhancedOption op = new EnhancedOption();
					op.title().text(varName.getName());
					op.tooltip().trigger(Trigger.axis);
					Line bar = new Line();// 存放数据
					bar.type(SeriesType.line);
					bar.showAllSymbol(false);
					// bar.hoverable(false);
					TimeAxis timeAxis = new TimeAxis();
					Line line1 = new Line();
					line1.smooth(true).name("警报线").stack("警报线").itemStyle()
							.normal().color("red");
					if (datas != null && datas.size() > 0) {

						for (int j = 0; j < datas.size(); j++) {
							Object[] object = datas.get(j);
							// 将得到的类型转换成int类型
							HashMap mp = new HashMap();
							mp.put("name", object[2]);
                            Object[] obs = new Object[2];
                            obs[0] = object[2];
                            obs[1] = object[1];
                            mp.put("value", obs);
							bar.data().add(mp);
							line1.data().add("25");
						}

					}
					timeAxis.splitLine().show(false);
					op.xAxis(timeAxis);
					ValueAxis valueAxis = new ValueAxis();
					valueAxis.boundaryGap(0d, 1d);
					valueAxis.splitLine().show(false);
					op.yAxis(valueAxis);
					op.series(bar, line1);

					option = GsonUtil.format(op);

					options.add(option);
				}
			}
		}
		return ajax(Status.success, JsonUtil.toJson(options));
	}

	/**
	 * 查找吸附设备实时数据，并图标展示
	 * 
	 * @Title: findConstantData
	 * @return String
	 * @author duanjj
	 * @since 2017年7月13日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findConstantData() {

		// 根据设备id判断设备是吸附还是脱附设备
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));

		// 查询设备有几个监控点 n
		options = new ArrayList<String>();
		if (navVals != null && !"".equals(navVals)) {
			navVals = navVals.substring(1);// 去除开始的逗号
			String[] vals = navVals.split(",");
			List naVal = Arrays.asList(vals);
			if ("0".equals(eq.getEquipmentType())) { // 如果等于0那就是吸附设备
				for (AdsVarName varName : AdsVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantData(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
	                            Object[] obs = new Object[2];
	                            obs[0] = object[2];
	                            obs[1] = object[1];
	                            mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			}else if ("3".equals(eq.getEquipmentType())) { // 如果等于0那就是吸附设备
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantData(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
								Object[] obs = new Object[2];
								obs[0] = object[2];
								obs[1] = object[1];
								mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			} else {
				for (DesVarName varName : DesVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {

						List<Object[]> datas = monitoringService
								.findConstantData(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						Line bar = new Line();// 存放数据
						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);
						TimeAxis timeAxis = new TimeAxis();
						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								// 将得到的类型转换成int类型
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
	                            Object[] obs = new Object[2];
	                            obs[0] = object[2];
	                            obs[1] = object[1];
	                            mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);

					}
				}
			}
		} /*
		 * else { if ("0".equals(eq.getEquipmentType())) { for (AdsVarName
		 * varName : AdsVarName.values()) {
		 * 
		 * // 如果包含 List<Object[]> datas = monitoringService.findConstantData(
		 * eq.getPlcIdentificationCode(), varName.getName()); // datas 1.name
		 * 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd // HH:mm:ss SimpleDateFormat
		 * from = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); EnhancedOption
		 * op = new EnhancedOption(); op.title().text(varName.getName());
		 * op.tooltip().trigger(Trigger.axis);
		 * 
		 * Line bar = new Line();// 存放数据
		 * 
		 * bar.type(SeriesType.line); bar.showAllSymbol(false);
		 * //bar.hoverable(false);
		 * 
		 * TimeAxis timeAxis = new TimeAxis(); Line bar1 = new Line();// 存放报警线
		 * bar1.type(SeriesType.line); bar1.xAxisIndex(0); bar1.yAxisIndex(0);
		 * bar1.itemStyle().normal().color0("red"); Line line1 = new Line();
		 * line1.smooth(true).name("警报线").stack("警报线").itemStyle()
		 * .normal().color("red"); if (datas != null && datas.size() > 0) {
		 * 
		 * for (int j = 0; j < datas.size(); j++) { Object[] object =
		 * datas.get(j); HashMap mp = new HashMap(); mp.put("name",
		 * from.format(object[2])); Object[] obs = new Object[2]; obs[0] =
		 * from.format(object[2]); obs[1] = object[1]; mp.put("value", obs);
		 * bar.data().add(mp); bar1.data().add(25); line1.data().add("25"); }
		 * 
		 * 
		 * } timeAxis.splitLine().show(false); op.xAxis(timeAxis); ValueAxis
		 * valueAxis = new ValueAxis(); valueAxis.boundaryGap(0d, 1d);
		 * valueAxis.splitLine().show(false); op.yAxis(valueAxis);
		 * op.series(bar, line1);
		 * 
		 * option = GsonUtil.format(op);
		 * 
		 * options.add(option);
		 * 
		 * } } else { for (DesVarName varName : DesVarName.values()) {
		 * 
		 * List<Object[]> datas = monitoringService.findConstantData(
		 * eq.getPlcIdentificationCode(), varName.getName()); // datas 1.name
		 * 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd // HH:mm:ss SimpleDateFormat
		 * from = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); EnhancedOption
		 * op = new EnhancedOption(); op.title().text(varName.getName());
		 * op.tooltip().trigger(Trigger.axis); Line bar = new Line();// 存放数据
		 * bar.type(SeriesType.line); bar.showAllSymbol(false);
		 * //bar.hoverable(false); TimeAxis timeAxis = new TimeAxis(); Line
		 * line1 = new Line();
		 * line1.smooth(true).name("警报线").stack("警报线").itemStyle()
		 * .normal().color("red"); if (datas != null && datas.size() > 0) {
		 * 
		 * for (int j = 0; j < datas.size(); j++) { Object[] object =
		 * datas.get(j); // 将得到的类型转换成int类型 HashMap mp = new HashMap();
		 * mp.put("name", from.format(object[2])); Object[] obs = new Object[2];
		 * obs[0] = from.format(object[2]); obs[1] = object[1]; mp.put("value",
		 * obs); bar.data().add(mp); line1.data().add("25"); }
		 * 
		 * 
		 * } timeAxis.splitLine().show(false); op.xAxis(timeAxis); ValueAxis
		 * valueAxis = new ValueAxis(); valueAxis.boundaryGap(0d, 1d);
		 * valueAxis.splitLine().show(false); op.yAxis(valueAxis);
		 * op.series(bar, line1);
		 * 
		 * option = GsonUtil.format(op);
		 * 
		 * options.add(option); } } }
		 */
		return ajax(Status.success, JsonUtil.toJson(options));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getTjtb() {

		// 根据设备id判断设备是吸附还是脱附设备
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));

		// 查询设备有几个监控点 n
		options = new ArrayList<String>();
		if (navVals != null && !"".equals(navVals)) {
			if ("0".equals(eq.getEquipmentType())) { // 如果等于0那就是吸附设备
				for (AdsVarName varName : AdsVarName.values()) {
					if (navVals.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantDataNew(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
								Object[] obs = new Object[2];
								obs[0] = object[2];
								obs[1] = object[1];
								mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			} else if ("3".equals(eq.getEquipmentType())) { // 如果等于0那就是吸附设备
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					if (navVals.contains(String.valueOf(varName.getIndex()))) {
						// 如果包含
						List<Object[]> datas = monitoringService
								.findConstantDataNew(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						// -----
						op.tooltip().axisPointer().type(PointerType.cross);
						op.legend().data(varName.name(), "报警线");
						op.toolbox().show(true);

						// -----

						Line bar = new Line();// 存放数据

						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);

						TimeAxis timeAxis = new TimeAxis();

						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
								Object[] obs = new Object[2];
								obs[0] = object[2];
								obs[1] = object[1];
								mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);
					}
				}
			} else {
				for (DesVarName varName : DesVarName.values()) {
					if (navVals.contains(String.valueOf(varName.getIndex()))) {

						List<Object[]> datas = monitoringService
								.findConstantDataNew(
										eq.getPlcIdentificationCode(),
										varName.getName());
						// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd
						// HH:mm:ss
						SimpleDateFormat from = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						EnhancedOption op = new EnhancedOption();
						op.title().text(varName.getName());
						op.tooltip().trigger(Trigger.axis);
						Line bar = new Line();// 存放数据
						bar.type(SeriesType.line);
						bar.showAllSymbol(false);
						// bar.hoverable(false);
						TimeAxis timeAxis = new TimeAxis();
						Line line1 = new Line();
						line1.smooth(true).name("警报线").stack("警报线").itemStyle()
								.normal().color("red");
						if (datas != null && datas.size() > 0) {

							for (int j = 0; j < datas.size(); j++) {
								Object[] object = datas.get(j);
								// 将得到的类型转换成int类型
								HashMap mp = new HashMap();
								mp.put("name", object[2]);
								Object[] obs = new Object[2];
								obs[0] = object[2];
								obs[1] = object[1];
								mp.put("value", obs);
								bar.data().add(mp);
								line1.data().add("25");
							}

						}
						timeAxis.splitLine().show(false);
						op.xAxis(timeAxis);
						ValueAxis valueAxis = new ValueAxis();
						valueAxis.boundaryGap(0d, 1d);
						valueAxis.splitLine().show(false);
						op.yAxis(valueAxis);
						op.series(bar, line1);

						option = GsonUtil.format(op);
						options.add(option);

					}
				}
			}
		}
		return ajax(Status.success, JsonUtil.toJson(options));
	}

	@SuppressWarnings("rawtypes")
	public String getCspz() {
		Cspz cspz = getBeanByCriteria(Cspz.class, Restrictions.eq("sbId", id));
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));
		if ("0".equals(eq.getEquipmentType())) {
			Map adsMap = toolUtil.getAdsMap();
			if (cspz != null) {
				if (StringUtils.isNotEmpty(cspz.getCbl1())) {
					String[] s = cspz.getCbl1().split(",");
					for (int i = 0; i < s.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						int index = Integer.parseInt(s[i]);
						if (adsMap.containsKey(index)) {
							map.put("name", adsMap.get(index));
							map.put("index", index);
							list.add(map);
						}
					}
				}
			}
		}else if ("3".equals(eq.getEquipmentType())) {
			Map adsMap = toolNewUtil.getAdsZCRMap();
			if (cspz != null) {
				if (StringUtils.isNotEmpty(cspz.getCbl1())) {
					String[] s = cspz.getCbl1().split(",");
					for (int i = 0; i < s.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						int index = Integer.parseInt(s[i]);
						if (adsMap.containsKey(index)) {
							map.put("name", adsMap.get(index));
							map.put("index", index);
							list.add(map);
						}
					}
				}
			}
		} else {
			Map DesMap = toolUtil.getDesMap();
			if (cspz != null) {
				if (StringUtils.isNotEmpty(cspz.getCbl1())) {
					String[] s = cspz.getCbl1().split(",");
					for (int i = 0; i < s.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						int index = Integer.parseInt(s[i]);
						if (DesMap.containsKey(index)) {
							map.put("name", DesMap.get(index));
							map.put("index", index);
							list.add(map);
						}
					}
				}
			}
		}

		return ajax(Status.success, JsonUtil.toJson(list));
	}

	public String findNavsData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Equipment eq = monitoringService.get(Equipment.class,
				eq("id", boxId));
		if(eq!=null){
			if ("0".equals(eq.getEquipmentType())) { //蜂窝活性炭吸附浓缩一体机
				for (AdsVarName varName : AdsVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name", varName.getName());
					map.put("index", varName.getIndex());
					list.add(map);
				}
			}else if ("3".equals(eq.getEquipmentType())) { //ZCR一体机
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name", varName.getName());
					map.put("index", varName.getIndex());
					list.add(map);
				}
			}else {
				for (DesVarName varName : DesVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name", varName.getName());
					map.put("index", varName.getIndex());
					list.add(map);
				}
			}
		}
		

		return ajax(Status.success, JsonUtil.toJson(list));
	}

	public String findNavsDataAn() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));
		Cspz cs = getBeanByCriteria(Cspz.class, Restrictions.eq("sbId", id));
		if (cs != null) {
			String[] css = cs.getCbl1().split(",");
			if ("0".equals(eq.getEquipmentType())) {
				for (AdsVarName varName : AdsVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					for (int i = 0; i < css.length; i++) {
						if ((varName.getIndex() + "").equals(css[i])) {
							map.put("name", varName.getName());
							map.put("index", varName.getIndex());
							list.add(map);
						}
					}
				}
			}else if ("3".equals(eq.getEquipmentType())) {
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					for (int i = 0; i < css.length; i++) {
						if ((varName.getIndex() + "").equals(css[i])) {
							map.put("name", varName.getName());
							map.put("index", varName.getIndex());
							list.add(map);
						}
					}
				}
			} else {
				for (DesVarName varName : DesVarName.values()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					for (int i = 0; i < css.length; i++) {
						if ((varName.getIndex() + "").equals(css[i])) {
							map.put("name", varName.getName());
							map.put("index", varName.getIndex());
							list.add(map);
						}
					}
				}
			}
		}
		return ajax(Status.success, JsonUtil.toJson(list));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findLastData() {
		// 根据设备id判断设备是吸附还是脱附设备
		Equipment eq = monitoringService.get(Equipment.class,
				eq("equipmentCode", boxId));
		// 查询设备有几个监控点 n
		List<HashMap> datas = new ArrayList<HashMap>();
		if (navVals != "undefined" && navVals != null && !"".equals(navVals)) {
			navVals = navVals.substring(1);// 去除开始的逗号
			String[] vals = navVals.split(",");
			List naVal = Arrays.asList(vals);
			if ("0".equals(eq.getEquipmentType())) {
				for (AdsVarName varName : AdsVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						Object[] data = monitoringService.findLastData(
								eq.getPlcIdentificationCode(),
								varName.getName());
						SimpleDateFormat form = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						HashMap mp = new HashMap();
						String name = form.format(new Date());
						mp.put("name", name);
						Object[] obs = new Object[2];
						obs[0] = name;
						obs[1] = data[1];
						mp.put("value", obs);
						datas.add(mp);
					}
				}
			}else if ("3".equals(eq.getEquipmentType())) {
				for (AdsZCRVarName varName : AdsZCRVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						Object[] data = monitoringService.findLastData(
								eq.getPlcIdentificationCode(),
								varName.getName());
						SimpleDateFormat form = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						HashMap mp = new HashMap();
						String name = form.format(new Date());
						mp.put("name", name);
						Object[] obs = new Object[2];
						obs[0] = name;
						obs[1] = data[1];
						mp.put("value", obs);
						datas.add(mp);
					}
				}
			} else {
				for (DesVarName varName : DesVarName.values()) {
					if (naVal.contains(String.valueOf(varName.getIndex()))) {
						Object[] data = monitoringService.findLastData(
								eq.getPlcIdentificationCode(),
								varName.getName());
						SimpleDateFormat form = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						HashMap mp = new HashMap();
						String name = form.format(new Date());
						mp.put("name", name);
						Object[] obs = new Object[2];
						obs[0] = name;
						obs[1] = data[1];
						mp.put("value", obs);

						datas.add(mp);
					}
				}
			}
		}/*
		 * else { if ("0".equals(eq.getEquipmentType())) { for (AdsVarName
		 * varName : AdsVarName.values()) { Object[] data =
		 * monitoringService.findLastData(eq.getPlcIdentificationCode(),
		 * varName.getName()); SimpleDateFormat form = new SimpleDateFormat(
		 * "yyyy-MM-dd HH:mm:ss"); HashMap mp = new HashMap(); String name =
		 * form.format(new Date()); mp.put("name", name); Object[] obs = new
		 * Object[2]; obs[0] = name; obs[1] = data[1]; mp.put("value", obs);
		 * datas.add(mp); } } else { for (DesVarName varName :
		 * DesVarName.values()) { Object[] data =
		 * monitoringService.findLastData(eq.getPlcIdentificationCode(),
		 * varName.getName()); SimpleDateFormat form = new SimpleDateFormat(
		 * "yyyy-MM-dd HH:mm:ss"); HashMap mp = new HashMap(); String name =
		 * form.format(new Date()); mp.put("name", name); Object[] obs = new
		 * Object[2]; obs[0] = name; obs[1] = data[1]; mp.put("value", obs);
		 * 
		 * datas.add(mp); } } }
		 */

		return ajax(Status.success, JsonUtil.toJson(datas));
	}

	public String blowing() {

		return "blowing";
	}

	public String saturation() {

		return "saturation";
	}

	public String frequency() {

		return "frequency";
	}

	/**
	 * 
	 * @Title: start 冷启动时间
	 * @return String
	 * @author duanjj
	 * @since 2017年7月21日 V 1.0
	 */
	public String startTime() {

		return "start";
	}

	public String temperature() {

		return "temperature";
	}

	/**
	 * 排烟温度
	 * 
	 * @Title: temperature1
	 * @return String
	 * @author duanjj
	 * @since 2017年7月21日 V 1.0
	 */
	public String temperature1() {

		return "temperature1";
	}

	public String temperature2() {

		return "temperature2";
	}

	public String temperature3() {

		return "temperature3";
	}

	public String temperature4() {

		return "temperature4";
	}

	public String rssyl() {

		return "rssyl";
	}

	public String fqnd() {

		return "fqnd";
	}

	public String jcknd() {

		return "jcknd";
	}

	public String num() {

		return "num";
	}

	public String getNumData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:37\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备B\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A002\", \"date1\":\"2017-06-18 11:56:38\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备C\", \"content\":\"50  \", \"content1\":\"30 ppm\", \"standard\":\"A003\", \"date1\":\"2017-06-18 11:56:39\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备D\", \"content\":\"40 \", \"content1\":\"30 ppm\", \"standard\":\"A004\", \"date1\":\"2017-06-18 11:56:40\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备E\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A005\", \"date1\":\"2017-06-18 11:56:41\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备F\", \"content\":\"56 \", \"content1\":\"30 ppm\", \"standard\":\"A006\", \"date1\":\"2017-06-18 11:56:42\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备G\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A007\", \"date1\":\"2017-06-18 11:56:43\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备H\", \"content\":\"70 \", \"content1\":\"30 ppm\", \"standard\":\"A008\", \"date1\":\"2017-06-18 11:56:45\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备I\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A009\", \"date1\":\"2017-06-18 11:56:46\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备J\", \"content\":\"50 \", \"content1\":\"30 ppm\", \"standard\":\"A0010\", \"date1\":\"2017-06-18 11:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getJckndData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:37\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:38\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm \", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:39\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"40 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:40\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:41\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"56 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:42\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:43\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"70 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:45\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:46\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"content1\":\"30 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getNdData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:37\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:38\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:39\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"40 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:40\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:41\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"56 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:42\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:43\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"70 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:45\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:46\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 ppm\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getYlData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:37\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:38\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:39\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"40 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:40\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:41\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"56 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:42\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:43\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"70 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:45\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:46\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"50 kpa\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getStartData() {
		// 获取风量数据返回
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("navVal", navVal);
		map.put("start", start);
		map.put("end", end);
		map.put("equipmentCode", boxId);
		mapList = monitoringService.findPagerData(pager, map);
		HashMap result = new HashMap();
		result.put("data", mapList);
		result.put("total", pager.getTotalCount());
		return ajax(Status.success, JsonUtil.Encode(result));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getTemperatureData() {
		// 获取风量数据返回
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("navVal", navVal);
		map.put("start", start);
		map.put("end", end);
		map.put("equipmentCode", boxId);
		mapList = monitoringService.findPagerData(pager, map);
		HashMap result = new HashMap();
		result.put("data", mapList);
		result.put("total", pager.getTotalCount());
		return ajax(Status.success, JsonUtil.Encode(result));

	}

	public String getFrequencyData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:37\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:38\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"3 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:39\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1\", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:40\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:41\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"3 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:42\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:43\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:45\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:46\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 \", \"standard\":\"A001\", \"date1\":\"2017-06-18 11:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getSaturationData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:37\", \"endDate\":\"2017-06-18 15:56:47\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:38\", \"endDate\":\"2017-06-18 15:56:47\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"3 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:39\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1.5 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:40\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:41\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"3 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:42\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1.2 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:43\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1.3 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:45\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"1 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:46\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ " ,{ \"id\":\"1\", \"name\":\"设备A\", \"content\":\"2 h\", \"standard\":\"A001\", \"startDate\":\"2017-06-18 11:56:47\", \"endDate\":\"2017-06-18 15:56:47\"}"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getBlowingData() {
		// 获取风量数据返回
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("navVal", navVal);
		map.put("start", start);
		map.put("end", end);
		map.put("equipmentCode", boxId);
		mapList = monitoringService.findPagerData(pager, map);
		HashMap result = new HashMap();
		result.put("data", mapList);
		result.put("total", pager.getTotalCount());
		return ajax(Status.success, JsonUtil.Encode(result));
	}

	// 历史数据json grid
	public String getAnnalsGridData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"company\":\"CO\", \"supplier\":\"20%\", \"type\":\"HCO2\", \"parameter \":\"10%\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备B\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				+ " ,{ \"id\":\"1\", \"name\":\"设备C\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备D\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备E\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备F\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备G\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备H\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备I\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				+ "]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 历史数据图表json
	public String getAnnalsChartData() {
		String jsonString = "[]";
		jsonString = "[{\"data1\":\"1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1\",\"data2\":\"1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1\"}]";

		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 实时获取数据
	public String getNowChartData() {

		java.util.Random r = new java.util.Random();
		int result = r.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10

		String jsonString = "[{\"data1\":\"" + result + "\"}]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getPotencyData() {
		// 获取数据库中最新的一条数据
		java.util.Random r = new java.util.Random();
		int result = r.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
		String jsonString = "[{\"data1\":\"" + result + "\"}]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getPressData() {
		java.util.Random r = new java.util.Random();
		int result = r.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
		String jsonString = "[{\"data1\":\"" + result + "\"}]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	public String getTempData() {
		java.util.Random r = new java.util.Random();
		int result = r.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
		String jsonString = "[{\"data1\":\"" + result + "\"}]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

	// 获取设备列表树
	@SuppressWarnings("all")
	public String showDeviceTreeData() {
		HashMap map = new HashMap();
		map.put("id", "001");
		map.put("text", "合作商1");
		HashMap map2 = new HashMap();
		map2.put("id", "001001");
		map2.put("text", "设备A");
		map2.put("pid", "0010011");
		HashMap map11 = new HashMap();
		map11.put("id", "0010011");
		map11.put("text", "客户A");
		map11.put("pid", "001");
		HashMap map12 = new HashMap();
		map12.put("id", "0010012");
		map12.put("text", "客户B");
		map12.put("pid", "001");
		HashMap map121 = new HashMap();
		map121.put("id", "00100121");
		map121.put("text", "设备F");
		map121.put("pid", "0010012");
		HashMap map122 = new HashMap();
		map122.put("id", "00100122");
		map122.put("text", "设备E");
		map122.put("pid", "0010012");
		HashMap map3 = new HashMap();
		map3.put("id", "001002");
		map3.put("text", "设备B");
		map3.put("pid", "001");
		HashMap map4 = new HashMap();
		map4.put("id", "002");
		map4.put("text", "合作商2");
		HashMap map5 = new HashMap();
		map5.put("id", "002001");
		map5.put("text", "设备C");
		map5.put("pid", "002");
		HashMap map6 = new HashMap();
		map6.put("id", "002002");
		map6.put("text", "设备D");
		map6.put("pid", "002");
		ArrayList arrlist = new ArrayList();
		arrlist.add(map);
		arrlist.add(map2);
		arrlist.add(map3);
		arrlist.add(map4);
		arrlist.add(map5);
		arrlist.add(map6);
		arrlist.add(map11);
		arrlist.add(map12);
		arrlist.add(map121);
		arrlist.add(map122);
		String json = JsonUtil.Encode(arrlist);
		return ajax(Status.success, json);
	}

	public String getDuData() {
		return ajax(Status.success, monitoringService.getDuData(boxId));
	}

	/************************************************************* get set *************************************************************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getNavVals() {
		return navVals;
	}

	public void setNavVals(String navVals) {
		this.navVals = navVals;
	}

	public String getNavVal() {
		return navVal;
	}

	public void setNavVal(String navVal) {
		this.navVal = navVal;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
