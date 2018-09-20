package com.lingnet.vocs.action.atlas;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AbnormalAlarm;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.GpsAtlas;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.alarm.AbnormalAlarmService;
import com.lingnet.vocs.service.atlas.AtlasService;
import com.lingnet.vocs.service.baseinfo.AreaService;
import com.lingnet.vocs.service.equipment.EquipmentService;
import com.lingnet.vocs.service.partner.PartnerService;

public class AtlasAction extends BaseAction {

	/**
     * 
     */
	private static final long serialVersionUID = 8598297875570236706L;

	@Resource(name = "atlasService")
	private AtlasService atlasService;
	@Resource(name = "partnerService")
	private PartnerService partnerService;
	@Resource(name = "areaService")
	private AreaService areaService;
	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;
	@Resource(name = "abnormalAlarmService")
	private AbnormalAlarmService abnormalAlarmService;
	@Resource(name = "adminService")
	private AdminService adminService;

	// 经度
	private String provinceId;// 省份id
	private String cityId;// 市id
	private String districtId;// 镇区id

	// 纬度
	private String provinceId2;// 省份id
	private String cityId2;// 市id
	private String districtId2;// 镇区id

	private String partnerId;
	private String type;
	private String name;
	private String sn;
	private HashMap<String, String> atlas;
	private Partner partner;// 客户合作商实体类
	private String strDate;
	private String endDate;
	private String lat;
	private String lng;
	private String address;

	/**
	 * 信息管理跳转对应页面页面
	 * 
	 * @Title: zList
	 * @return String
	 * @author lizk
	 * @since 2017年6月22日 V 1.0
	 */
	public String zList() {
		if ("1".equals(type)) {// 客户
			return kh(id);
		} else if ("2".equals(type)) {// 合作商
			return hzs(id);
		} else if ("3".equals(type)) {// 设备
			return sbdt(id);
		} else if ("4".equals(type)) {// 我的客户
			return mykh(id);
		} else if ("5".equals(type)) {// 我的合作商
			return myhzs(id);
		}

		return "";
	}

	public String hsj() {
		return "hsj";
	}

	public String staff() {
		return "staff";
	}
	
	
	public String ssjk(){
	    return "ssjk";
	}

	public String getListDataStaff() {
		String json = atlasService.getListDataStaff(pager, type);
		return ajax(Status.success, json);
	}

	// ///////////设备警告start//////////////////////////
	// 警告弹框
	public String jbtk() {
		atlas = new HashMap<String, String>();
		AbnormalAlarm ab = abnormalAlarmService.get(AbnormalAlarm.class, id);
		Equipment eq = equipmentService.get(Equipment.class,ab.getEquipmentId());
		if(eq != null){
		    atlas.put("ycsb", eq.getEquipmentCode());
		}
		if(ab != null){
		    atlas.put("bjyy", ab.getAlarmContent()); 
		}
		QxUsers qx = adminService.get(QxUsers.class,Restrictions.eq("id", ab.getHandlePeople()));
		if(qx != null){
		    atlas.put("name", qx.getUsername());
		}
		return "jbtk";
	}

	// 设备警告地图
	public String sbjg() {
		return "sbjg";
	}
	
	public String newSbjg() {
		return "newsbjg";
	}
	
	

	public String sbjg2() {
		name = "青岛一凌网";
		return "sbjg2";
	}

	/**
	 * 废弃
	 * 设备警告列表数据展示
	 * 
	 * @Title: sbjblist
	 * @return String
	 * @author lizk
	 * @since 2017年6月15日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String sbjblist() {
		String json = "";
		List list = new ArrayList();
		String[] code = { "jb001", "jb002", "jb003" };
		String[] adress = { "峨眉山路", "长江中路", "香江路" };
		String[] reason = { "报警原因1", "报警原因2", "报警原因3" };
		for (int i = 0; i < code.length; i++) {
			HashMap map = new HashMap();
			map.put("code", code[i]);
			map.put("adress", adress[i]);
			map.put("reason", reason[i]);
			map.put("bjsj", "2017-06-15");
			map.put("pdsj", "2017-06-15");
			map.put("jdsj", "2017-06-15");
			map.put("clwcsj", "2017-06-15");
			list.add(map);
		}
		json = JsonUtil.Encode(list);
		return ajax(Status.success, json);
	}

	public String searchsbjg() {
		String json = atlasService.searchsbjg(type, name);
		return ajax(Status.success, json);
	}
	
	
	 /**
     * 加载异常报警列表
     * 
     * @Title: getListData
     * @return String
     * @author wanl
     * @since 2017年7月12日 V 1.0
     */
    public String getSbjgListData() {
        if (partnerId == null) {
            partnerId = this.getSession("partnerId").toString();
        }
        return ajax(Status.success, abnormalAlarmService.getSbjgListData(pager));
    }
    
    public String getSbData(){
        return ajax(Status.success, abnormalAlarmService.getSbData(pager));
    }
    
    /**
     * 获取报警新的坐标点
     * @Title: getSbzb 
     * @return 
     * String 
     * @author lizk
     * @since 2017年7月21日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getSbzb(){
    	AbnormalAlarm aa = abnormalAlarmService.get(AbnormalAlarm.class,id);
    	Equipment eq = equipmentService.get(Equipment.class, aa.getEquipmentId());
    	List<GpsAtlas> gps = this.getBeanListByCriteria(GpsAtlas.class,Order.desc("time"), Restrictions.eq("sn", eq.getGpsSn()));
    	HashMap map = new HashMap();
    	if(gps != null && gps.size() != 0){
         	map.put("spanLat", gps.get(0).getLat());
         	map.put("spanLng", gps.get(0).getLon());
    	}
    	map.put("name", eq.getName());
    	String json = JsonUtil.Encode(map);
    	return json;
    }
	
	

	/**
	 * 警告次数
	 * @Title: jbcsList 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2017年7月20日 V 1.0
	 */
	public String jbcsList() {
		String json = atlasService.jbcsList();
		return ajax(Status.success, json);
	}

	// ///////////////设备警告end///////////////////////////////////

	// //////////////详细地图展示start///////////////////////////////
	// 初始地图显示
	public String xxShow() {
		String json = atlasService.xxShow();
		return ajax(Status.success, json);
	}

	// 搜索
	public String search() {
		String json = atlasService.search(type, name);
		return ajax(Status.success, json);
	}

	// 获取类型
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String hqType() {
		String json = "";
		List list = new ArrayList();
		String[] a = { "0", "1", "2" };
		String[] b = { "合作商", "客户", "设备" };
		for (int i = 0; i < 3; i++) {
			HashMap map = new HashMap();
			map.put("id", a[i]);
			map.put("text", b[i]);
			list.add(map);
		}
		json = JsonUtil.Encode(list);
		return ajax(Status.success, json);
	}

	// ///////////////详细地图展示end//////////////////////////////

	// //////////////客户地图分布start///////////////////////////////

	public String kh(String id) {
		if (!"".equals(id) && id != null) {// 客户选中的
			partner = partnerService.get(Partner.class,
					Restrictions.eq("id", id));
		}
		return "kh";
	}

	public String mykh(String id) {
		if (!"".equals(id) && id != null) {// 客户选中的
			partner = partnerService.get(Partner.class,
					Restrictions.eq("id", id));
		}
		return "mykh";
	}

	public String searchkh() {
		String json = "";
        try {
            json = atlasService.searchkh(type, id);
            return ajax(Status.success, json);
        } catch (Exception e) {
            return ajax(Status.error,e.getMessage());
        }
		
	}

	public String searchmykh() {
		String json = atlasService.searchmykh(type, id);
		return ajax(Status.success, json);
	}

	// ///////////////详细地图展示end//////////////////////////////

	// //////////////注册坐标start///////////////////////////////

	public String zczb() {
		if (districtId != null && !"".equals(districtId)) {
			Area a = areaService.get(Area.class,
					Restrictions.eq("id", districtId));// 镇区坐标
			if (a.getLongitude() != null && !"".equals(a.getLongitude())) {
				districtId = a.getLongitude();
				districtId2 = a.getLatitude();
			} else {
				districtId = "";
			}
		}
		if (cityId != null && !"".equals(cityId)) {
			Area a = areaService.get(Area.class, Restrictions.eq("id", cityId));// 市坐标
			if (a.getLongitude() != null && !"".equals(a.getLongitude())) {
				cityId = a.getLongitude();
				cityId2 = a.getLatitude();
			} else {
				cityId = "";
			}
		}
		if (provinceId != null && !"".equals(provinceId)) {
			Area a = areaService.get(Area.class,
					Restrictions.eq("id", provinceId));// 省坐标
			if (a.getLongitude() != null && !"".equals(a.getLongitude())) {
				provinceId = a.getLongitude();
				provinceId2 = a.getLatitude();
			} else {
				provinceId = "";
			}
		}
		if (StringUtils.isNotEmpty(address)) {
			try {
				address = URLDecoder.decode(address, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "zczb";
	}

	// ///////////////注册坐标end//////////////////////////////

	// //////////////治理单元与合作商的检索地图分布start///////////////////////////////

	public String zldyhzs() {
		return "zldyhzs";
	}

	public String searchzldyhzs() {
		String json = atlasService.searchzldy(type, name);
		return ajax(Status.success, json);
	}

	// ///////////////治理单元与合作商的检索地图展示end//////////////////////////////

	// //////////////治理单元与客户的检索地图分布start///////////////////////////////

	public String zldykh() {
		return "zldykh";
	}

	public String searchzldykh() {
		String json = atlasService.searchzldy(type, name);
		return ajax(Status.success, json);
	}

	// ///////////////治理单元与合作商的检索地图展示end//////////////////////////////

	// //////////////合作商地图分布start///////////////////////////////

	public String hzs(String id) {
		if (!"".equals(id) && id != null) {// 合作商选中的
			partner = partnerService.get(Partner.class,
					Restrictions.eq("id", id));
		}
		return "hzs";
	}

	public String myhzs(String id) {
		if (!"".equals(id) && id != null) {// 合作商选中的
			partner = partnerService.get(Partner.class,
					Restrictions.eq("id", id));
		}
		return "myhzs";
	}

	public String searchhzs() {
		String json = atlasService.searchhzs(type, id);
		return ajax(Status.success, json);
	}

	public String searchmyhzs() {
		String json = atlasService.searchmyhzs(type, id);
		return ajax(Status.success, json);
	}

	// ///////////////合作商地图展示end//////////////////////////////

	// //////////////设备路线展示start///////////////////////////////

	public String sblx() {
		return "sblx";
	}

	public String sblxList() {
		String json = "";
		try {
			json = atlasService.sblxList(sn, strDate, endDate);
		} catch (Exception e) {
			e.getMessage();
		}
		return ajax(Status.success, json);
	}

	public String searchsblx() {
		String json = atlasService.searchsblx(type, name);
		return ajax(Status.success, json);
	}

	public String getGpsData() throws Exception {
		String json = atlasService.getGpsData("441483", "2017-07-05");
		return ajax(Status.success, json);
	}

	// //////////////设备路线展示end//////////////////////////////

	// //////////////设备地图分布start///////////////////////////////

	public String sbdt(String eid) {
		if (!"".equals(eid) && eid != null) {// 设备选中的
			Equipment eq = equipmentService.get(Equipment.class,
					Restrictions.eq("id", eid));
			id = eq.getId();
			sn = eq.getGpsSn();
		}
		return "sbdt";
	}

	public String searchsbdt() {
		String json = atlasService.searchsbdt(pager, partnerId, id, sn);
		return ajax(Status.success, json);
	}

	// 历史数据json grid
	//废弃
	public String getAnnalsGridData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"name\":\"设备A\", \"company\":\"CO\", \"supplier\":\"20%\", \"type\":\"HCO2\", \"parameter \":\"10%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备B\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
				// +
				// " ,{ \"id\":\"1\", \"name\":\"设备C\", \"company\":\"CO2\", \"supplier\":\"20%\", \"type\":\"CO\", \"parameter \":\"20%\" }"
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
	//废弃
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

	// ///////////////设备地图展示end//////////////////////////////

	/**
	 * 根据地区id获取坐标
	 * @Title: getZb 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2017年10月19日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getZb(){
	    Area area = this.getBeanById(Area.class, id);
	    HashMap map = new HashMap();
	    map.put("latitude", area.getLatitude());
	    map.put("longitude", area.getLongitude());
	    return ajax(Status.success,JsonUtil.Encode(map));
	}
	
	
	
	
	// //////////////////////////////////////////////////////
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getAtlas() {
		return atlas;
	}

	public void setAtlas(HashMap<String, String> atlas) {
		this.atlas = atlas;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getProvinceId2() {
		return provinceId2;
	}

	public void setProvinceId2(String provinceId2) {
		this.provinceId2 = provinceId2;
	}

	public String getCityId2() {
		return cityId2;
	}

	public void setCityId2(String cityId2) {
		this.cityId2 = cityId2;
	}

	public String getDistrictId2() {
		return districtId2;
	}

	public void setDistrictId2(String districtId2) {
		this.districtId2 = districtId2;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
