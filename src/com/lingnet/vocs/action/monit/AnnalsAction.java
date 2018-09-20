package com.lingnet.vocs.action.monit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.TimeAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.util.EnhancedOption;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.util.AdsVarName;
import com.lingnet.util.DesVarName;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.service.equipment.EquipmentService;
import com.lingnet.vocs.service.monit.AnnalsService;

/**
 * vocs历史数据
 * 
 * @ClassName: AnnalsAction
 * @Description: TODO
 * @author xues
 * @date 2017年6月6日 下午2:23:07
 * 
 */
@SuppressWarnings("all")
public class AnnalsAction extends BaseAction {
	private static final long serialVersionUID = 27399829946389237L;

	private String name;

	@Resource(name = "annalsService")
	private AnnalsService annalsService;

	private String eqCode;
	private String eqId;
	private String option;

	private String startdate;
	private String enddate;

	private int index;

	/**
	 * 进入主页面 默认显示图标信息
	 * */
	public String list() {

		return "list";
	}

	public String list2() {

		return "list2";
	}

	public String list3() {

		return "list3";
	}

	public String showGrid() {

		return "grid";
	}

	public String showlist() {
		return "showlist";
	}

	/**
	 * 温度 获取列表
	 * 
	 * @Title: getTemperatureData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月13日 V 1.0
	 */
	public String getTemperatureData() {
		String json = "";
		json = annalsService.getTemperatureData(pager, eqCode, startdate,
				enddate);
		return ajax(Status.success, json);

	}

	public String getTemperatureChart() {
		// 查询设备有几个监控点 n
		Equipment equipment = annalsService.get(Equipment.class,
				eq("equipmentCode", eqCode));
		String tiele = "";
		if ("0".equals(equipment.getEquipmentType())) {
			for (AdsVarName varName : AdsVarName.values()) {
				if (index == varName.getIndex()) {
					tiele = varName.getName();
				}
			}
		} else {
			for (DesVarName varName : DesVarName.values()) {
				if (index == varName.getIndex()) {
					tiele = varName.getName();
				}
			}
		}
		List<Object[]> datas = annalsService
				.getTemperatureChart(equipment.getPlcIdentificationCode(),
						tiele, startdate, enddate);
		// datas 1.name 监控点名称 2.value 值 3.timeStamp 时间yyyy-MM-dd HH:mm:ss
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/* if (datas != null && datas.size() > 0) { */
		EnhancedOption op = new EnhancedOption();
		// op.title().text(tiele);// .subtext("按阶段");对应页面上的大字
		op.tooltip().trigger(Trigger.axis);
		op.legend(tiele);
		op.legend("警报线");
		op.toolbox().show(true);
		op.toolbox()
				.show(true)
				.feature(new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		for (int j = 0; j < datas.size(); j++) {
			Object[] object = datas.get(j);
			res1.add(object[1]);
			res2.add(from.format(object[2]));
			if (tiele.contains("温度")) {
				res3.add(equipment.getTemperatureLimit());
			} else if (tiele.contains("浓度")) {
				res3.add(equipment.getExportConcentration());
			} else if (tiele.contains("压差")) {
				res3.add(equipment.getPressureDifference());
			} else {
			}

		}
		op.xAxis(new CategoryAxis().name("时间").boundaryGap(false)
				.data(res2.toArray()));
		op.yAxis(new ValueAxis().name(tiele));
		op.dataZoom().show(true).start(0).end(100);
		Line line = new Line();
		line.smooth(true).name(tiele).stack(tiele).symbol(Symbol.circle)
				.data(res1.toArray()).itemStyle().normal().color("#00FFFF");
		op.series(line);
		Line line1 = new Line();
		line1.smooth(true).name("警报线").stack("警报线").symbol(Symbol.circle)
				.data(res3.toArray()).itemStyle().normal().color("red");
		op.series(line1);

		option = GsonUtil.format(op);
		/*
		 * } else { EnhancedOption op = new EnhancedOption(); option =
		 * GsonUtil.format(op); }
		 */
		return ajax(Status.success, JsonUtil.toJson(option));
	}

	public String findNavsData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Equipment eq = annalsService.get(Equipment.class,
				eq("equipmentCode", eqId));
		if ("0".equals(eq.getEquipmentType())) {
			for (AdsVarName varName : AdsVarName.values()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", varName.getName());
				map.put("index", varName.getIndex());
				list.add(map);
			}
		} else {
			for (DesVarName varName : DesVarName.values()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", varName.getName());
				map.put("index", varName.getIndex());
				list.add(map);
			}
		}
		return ajax(Status.success, JsonUtil.toJson(list));
	}

	/************************************************************* get set *************************************************************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AnnalsService getAnnalsService() {
		return annalsService;
	}

	public void setAnnalsService(AnnalsService annalsService) {
		this.annalsService = annalsService;
	}

	public String getEqCode() {
		return eqCode;
	}

	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
