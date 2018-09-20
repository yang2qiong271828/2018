package com.lingnet.vocs.action.baseinfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.service.baseinfo.AreaService;

/**
 * @ClassName: AreaAction (地区信息)
 * @author 逄阳
 * @date 2017-6-20 下午3:47:17
 */
public class AreaAction extends BaseAction {

	private static final long serialVersionUID = -739213555316086962L;
	@Resource(name = "areaService")
	private AreaService areaService;

	private Area area;// 地区对象

	private String pid;

	/**
	 * 
	 * @Title: list 展示页面
	 * @return String
	 * @author xues
	 * @since 2017-2-5 V 1.0
	 */
	public String list() {
		return "list";
	}

	/**
	 * 
	 * @Title: getdata 获取地区树数据
	 * @return String
	 * @author xues
	 * @since 2017-2-16 V 1.0
	 */
	public String getdata() {

		List<HashMap<String, String>> data = areaService.getParentAll(pid);

		// List<HashMap<String, String>> data = areaService.getAll();
		String json = JsonUtil.Encode(data);
		return ajax(Status.success, json);
	}

	/**
	 * 
	 * @Title: delete 删除
	 * @return String
	 * @author xues
	 * @since 2017-2-16 V 1.0
	 */
	public String delete() {
		Area area = areaService.get(Area.class, id);
		List<Area> list = areaService.getList(Area.class,
				Restrictions.eq("pid", area.getId()));
		if (list.size() > 0) {
			return ajax(Status.error, area.getName() + "--存在下级地区，请先删除下级地区！");
		}
		areaService.delete(id);
		return ajax(Status.success, "success");
	}

	/**
	 * 
	 * @Title: saveOrUpdata 保存
	 * @return String
	 * @author xues
	 * @since 2017-1-11 V 1.0
	 */
	public String saveOrUpdata() {
		if (area == null) {
			return ajax(Status.success, "请重新操作！");
		}
		String message = areaService.saveOrUpdate(area);
		return ajax(Status.success, message);
	}

	/**
	 * 
	 * @Title: selectJson 地区数据
	 * @return String
	 * @author xues
	 * @since 2017-2-18 V 1.0
	 */
	public String selectJson() {
		return ajax(Status.success, areaService.selectJson());
	}

	/**
	 * 地区树
	 * 
	 * @Title: getAreaTreeByParData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月5日 V 1.0
	 */
	public String getAreaTreeByParData() {
		String myField = getRequest().getParameter("myField");
		String partnerId = this.getSession("partnerId").toString();
		String json = "";
		json = JsonUtil.Encode(areaService.getAll(partnerId, id, key, myField));
		return ajax(Status.success, json);
	}

	/**
	 * 地区树
	 * 
	 * @Title: getAllAreaTreeData
	 * @return String
	 * @author xues
	 * @since 2017年6月15日 V 1.0
	 */
	public String getAreaTreeData() {
		String json = "";
		json = JsonUtil.Encode(areaService.getAll());
		return ajax(Status.success, json);
	}

	/**
	 * 获取Area Tree，懒加载
	 * 
	 * @Title: getAreaTreeDataLazy
	 * @return
	 * @author zy
	 * @since 2017年6月22日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public String getAreaTreeDataLazy() {
		String myField = getRequest().getParameter("myField");
		List list = areaService.getAreaListLazy(myField);
		String json = JsonUtil.Encode(list);
		return ajax(Status.success, json);
	}

	public String getDa() {
		List<Area> area = this.getBeanListByCriteria(Area.class);
		for (Area a : area) {
			if (StringUtils.isEmpty(a.getLatitude())) {
				if (StringUtils.isNotEmpty(a.getFullname())) {
					Map<String, BigDecimal> latAndLngByAddress = ToolUtil
							.getLatAndLngByAddress(a.getFullname());
					Area ar = this.getBeanById(Area.class, a.getId());
					ar.setLatitude(latAndLngByAddress.get("lat").toString());
					ar.setLongitude(latAndLngByAddress.get("lng").toString());
					this.update(ar);
				}
			}
		}
		return "success";
	}

	// ========================set/get ==========================//
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
