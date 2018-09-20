package com.lingnet.vocs.action.sewage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
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
import com.lingnet.util.ConvertToPdf;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.StringToDate;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.entity.SewagePermit;
import com.lingnet.vocs.service.baseinfo.AreaService;
import com.lingnet.vocs.service.partner.ContactsService;
import com.lingnet.vocs.service.partner.PartnerService;
import com.lingnet.vocs.service.sewage.SewageService;
import com.opensymphony.xwork2.ActionContext;


/**
 * 
 * @ClassName: CountAction
 * @Description: TODO
 * @author lyz
 * @date 2017年10月9日 下午2:21:35
 * 
 */
@Results({ @Result(name = "show1", type = "redirect", location = "http://61.149.215.198:18081/map") })
public class SewageAction extends BaseAction {

	private static final long serialVersionUID = 7492910134753733797L;

	@Resource(name = "sewageService")
	private SewageService sewageService;

	@Resource(name = "contactsService")
	private ContactsService contactsService;
	
	@Resource(name = "partnerService")
	private PartnerService partnerService;

	@Resource(name = "areaService")
	private AreaService areaService;

	private String formdata;

	private String griddata;// 联系人列表

	private Sewage sewage;

	private SewageBusiness business;

	private SewagePermit permit;

	private String pwname;
	
	private ArrayList<HashMap> report;

	private String jsondata;// 治理计划json

	private String partnerId;

	private String areaId;// 区域id

	private String option;// 柱状图统计

	private String pwtype;// 排污行业

	private File uploadFile;// 导入的文件

	private String uid;
	//前台时间显示
	//开始时间
	private String starttime;
	//验收时间
	private String ystime;
	//发证日期
	private String issuingDate;
	//有效期限
	private String expirationDate;
	//省
	private String province;
	//市
	private String city;
	//区
	private String cdistrict;
	
	

	private String foundDate;
	
	private String licensingDate;
	
	

	public String list() {
		return LIST;
	}

	public String add() {
		return ADD;
	}

	public String edit() {
		sewage = getBeanById(Sewage.class, id);
		business = sewageService.getBusinessBySewageId(id);
		permit = sewageService.getPermitBySewageId(id);
		return ADD;
	}

	public String look() {
		getRequest().setAttribute("flag", "false");
		sewage = getBeanById(Sewage.class, id);
		if (sewage.getStarttime()!=null&&!"".equals(sewage.getStarttime())) {
			starttime=(String) StringToDate.toDate((sewage.getStarttime()));
		}
		if (sewage.getYstime()!=null&&!"".equals(sewage.getYstime())) {
			ystime = (String) StringToDate.toDate(sewage.getYstime());
		}
		business = sewageService.getBusinessBySewageId(id);
		if (business.getFoundDate()!=null&&!"".equals(business.getFoundDate())) {
			foundDate=StringToDate.toDate((business.getFoundDate()));
		}
		if (business.getLicensingDate()!=null&&!"".equals(business.getLicensingDate())) {
			licensingDate = StringToDate.toDate(business.getLicensingDate());
		}
		
		permit = sewageService.getPermitBySewageId(id);
		if (permit.getIssuingDate()!=null&&!"".equals(permit.getIssuingDate())) {
			issuingDate=StringToDate.toDate(permit.getIssuingDate());
		}
		if (permit.getExpirationDate()!=null&&!"".equals(permit.getExpirationDate())) {
			expirationDate = StringToDate.toDate(permit.getExpirationDate());
		}
		if (sewage.getProvince()!=null&&!"".equals(sewage.getProvince())) {
		
			province = partnerService.getCSQ(sewage.getProvince());
		}
		if (sewage.getCity()!=null&&!"".equals(sewage.getCity())) {
			city = partnerService.getCSQ(sewage.getCity());
		}
		if (sewage.getCdistrict()!=null&&!"".equals(sewage.getCdistrict())) {
			cdistrict = partnerService.getCSQ(sewage.getCdistrict());
		}

		HashMap map = sewageService.getSewageReportBySewageId(id);		
		report=(ArrayList)map.get("data");
		
		return VIEW;
	}
	
	
	
	public String qk() {
		return "qk";
	}

	public String tab1() {
		return "tab1";
	}

	public String tab2() {
		return "tab2";
	}

	/**
	 * 删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author lyz
	 * @since 2017年10月10日 V 1.0
	 */
	public String remove() {
		try {
			sewageService.delete(ids[0]);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}

	/**
	 * 
	 * @Title: getContactListData
	 * @return String
	 * @author lyz
	 * @since 2017年10月10日 V 1.0
	 */
	public String getContactsBySewageId() {
		String partnerId = getRequest().getParameter("partnerId");
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(sewageService.getContactsBySewageId(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}

	/**
	 * 
	 * @Title: getSituationListData
	 * @return String
	 * @author Glen
	 * @since 2017年10月10日 V 1.0
	 */
	public String getSituationBySewageId() {
		String partnerId = getRequest().getParameter("partnerId");
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(sewageService.getSituationBySewageId(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}

	/**
	 * 
	 * @Title: getSewageTaxData
	 * @return String
	 * @author Glen
	 * @since 2017年10月10日 V 1.0
	 */
	public String getSewageTaxBySewageId() {
		String partnerId = getRequest().getParameter("partnerId");
		String type = getRequest().getParameter("type");

		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(sewageService.getSewageTaxBySewageId(partnerId, type));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}
	
	/**
	 * 
	 * @Title: getSewageReportData
	 * @return String
	 * @author Glen
	 * @since 2017年10月10日 V 1.0
	 */
	public String getSewageReportBySewageId() {
		String partnerId = getRequest().getParameter("partnerId");
		if (StringUtils.isNotEmpty(partnerId)) {
			String json = JsonUtil.Encode(sewageService.getSewageReportBySewageId(partnerId));
			return ajax(Status.success, json);
		} else {
			return ajax(Status.success, NONE);
		}
	}


	/**
	 * 
	 * @Title: getData
	 * @return String
	 * @author lyz
	 * @since 2017年10月9日 V 1.0
	 */
	public String getData() {
		return ajax(Status.success, sewageService.getData(pager, searchData, areaId, key));
	}

	public String saveOrUpdate() {
		try {
			sewage = JSONObject.parseObject(formdata, Sewage.class);
			String business = getRequest().getParameter("formdataBusiness");
			String situation = getRequest().getParameter("griddataSituation");
			String permit = getRequest().getParameter("formdataPermit");
			String sewageTax = getRequest().getParameter("griddataSewageTax");
			String sewageReport = getRequest().getParameter("griddataSewageReport");
			sewageService.saveOrUpdate(sewage, griddata, business, situation, permit, sewageTax, sewageReport);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1), "text/html;charset=UTF-8");
		}
	}

	public String manageMap() {
		if (!"".equals(id) && id != null) {// 合作商选中的
			sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", id));
		}
		return "manage_map";
	}

	public String getCompany() {
		List<Sewage> list = this.getBeanListByCriteria(Sewage.class, Restrictions.eq("governance", "1"));
		ArrayList<Object> data = new ArrayList<Object>();
		HashMap<String, Object> da = new HashMap<String, Object>();
		da.put("id", "1");
		da.put("text", "环保治理企业汇总");
		da.put("pid", "-1");
		da.put("img", "../template/system/images/parDpar.png");
		da.put("latitude", "");
		da.put("longitude", "");
		da.put("qyphone", "");
		da.put("qylxr", "");
		da.put("pwtype", "");
		data.add(da);
		for (Sewage se : list) {
			da = new HashMap<String, Object>();
			da.put("id", se.getId());
			da.put("text", se.getPwname() + "(已治理)");
			da.put("pid", "1");
			da.put("img", "../template/system/images/parDpar.png");
			da.put("latitude", se.getLatitude());
			da.put("longitude", se.getLongitude());
			Contacts cs = contactsService.get(Contacts.class, Restrictions.eq("partnerId", se.getId()),
					Restrictions.eq("picMain", "1"));
			if (cs != null) {
				da.put("name", cs.getPicName());// 姓名
				da.put("tel", cs.getPicPhone());// 联系方式
			} else {
				da.put("name", "");// 姓名
				da.put("tel", "");// 联系方式
			}
			da.put("pwtype", se.getPwtype());
			da.put("addr", se.getDlwz() == null ? "" : se.getDlwz());

			data.add(da);
		}
		return ajax(Status.success, JsonUtil.Encode(data));
	}

	@SuppressWarnings("rawtypes")
	public String initMap() {
		List<HashMap> data = new ArrayList<HashMap>();
		List<Sewage> list = this.getBeanListByCriteria(Sewage.class, Restrictions.eq("governance", "1"));

		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> record = new HashMap<String, String>();
			Sewage p = list.get(i);

			String w = String.valueOf(p.getLatitude());
			String l = String.valueOf(p.getLongitude());
			record.put("qyxxmc", p.getPwname());// 企业详细名称
			record.put("spanLat", w);// 纬度
			record.put("spanLng", l);// 经度
			if (p.getDlwz() != null) {
				record.put("address", p.getDlwz());// 住址
			} else {
				record.put("address", "");
			}

			Contacts cs = contactsService.get(Contacts.class, Restrictions.eq("partnerId", p.getId()),
					Restrictions.eq("picMain", "1"));
			if (cs != null) {
				record.put("name", cs.getPicName());// 姓名
				record.put("tel", cs.getPicPhone());// 联系方式
			} else {
				record.put("name", "");// 姓名
				record.put("tel", "");// 联系方式
			}
			record.put("type", "1");
			data.add(record);
		}

		return ajax(Status.success, JsonUtil.Encode(data));
	}

	public String govern() {
		return "govern";
	}

	public String chosing() {
		return "chosing";
	}

	public String search() {
		String json = sewageService.search(areaId);
		return ajax(Status.success, json);
	}

	public String show() {
		sewage = getBeanById(Sewage.class, partnerId);
		if (sewage.getHbzljh() != null) {
			return ajax(Status.success, JsonUtil.Encode(sewage.getHbzljh()));
		} else {
			return ajax(Status.success, "暂无治理计划数据！");
		}

	}

	public String getGovernCompany() {
		List<Sewage> list = this.getOrderBeanListByCriteria(Sewage.class, Order.desc("governance"));
		ArrayList<Object> data = new ArrayList<Object>();
		HashMap<String, Object> da = new HashMap<String, Object>();
		da.put("id", "1");
		da.put("text", "环保治理企业汇总");
		da.put("pid", "-1");
		da.put("img", "../template/system/images/parDpar.png");
		da.put("latitude", "");
		da.put("longitude", "");
		da.put("qyphone", "");
		da.put("qylxr", "");
		da.put("pwtype", "");
		data.add(da);
		for (Sewage se : list) {
			da = new HashMap<String, Object>();
			da.put("id", se.getId());
			if (se.getGovernance().equals("0")) {
				da.put("text", se.getPwname() + "<font color='red'>(未治理)</font>");
				da.put("img", "../template/system/images/pw2.png");
			} else if (se.getGovernance().equals("1")) {
				da.put("text", se.getPwname() + "<font color='orange'>(治理中)</font>");
				da.put("img", "../template/system/images/pw0.png");
			} else {
				da.put("text", se.getPwname() + "<font color='green'>(已治理)</font>");
				da.put("img", "../template/system/images/pw1.png");
			}
			da.put("pid", "1");
			da.put("latitude", se.getLatitude());
			da.put("longitude", se.getLongitude());
			Contacts cs = contactsService.get(Contacts.class, Restrictions.eq("partnerId", se.getId()),
					Restrictions.eq("picMain", "1"));
			if (cs != null) {
				da.put("name", cs.getPicName());// 姓名
				da.put("tel", cs.getPicPhone());// 联系方式
			} else {
				da.put("name", "");// 姓名
				da.put("tel", "");// 联系方式
			}
			da.put("pwtype", se.getPwtype());
			da.put("addr", se.getDlwz() == null ? "" : se.getDlwz());

			data.add(da);
		}
		return ajax(Status.success, JsonUtil.Encode(data));
	}

	public String zljh() {
		return "zljh";
	}

	public String czzljh() {
		sewage = getBeanById(Sewage.class, id);
		return "czzljh";
	}

	public String saveOrUpdatezljh() {
		try {
			sewage = JSONObject.parseObject(jsondata, Sewage.class);
			sewageService.saveOrUpdatezljh(sewage);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1), "text/html;charset=UTF-8");
		}
	}

	public String getAreaDatalist() {
		String json = sewageService.getAreaDatalist(partnerId);
		return ajax(Status.success, json);
	}

	public String getConn() {

		/*
		 * String surl = "http://182.18.24.12:8082/login"; ToolUtil.getConn(surl,
		 * "username=huashijie&password=123456");
		 * 
		 * surl = "http://182.18.24.12:8082/map"; jsondata = ToolUtil.getConn(surl,
		 * "username=huashijie&password=123456");
		 */

		// 登陆 Url
		String loginUrl = "http://61.149.215.198:18081/login";
		// 需登陆后访问的 Url
		String dataUrl = "http://61.149.215.198:18081/map";
		HttpClient httpClient = new HttpClient();

		// 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
		PostMethod postMethod = new PostMethod(loginUrl);

		// 设置登陆时要求的信息，用户名和密码
		NameValuePair[] data = { new NameValuePair("username", "huashijie"), new NameValuePair("password", "123456") };
		postMethod.setRequestBody(data);
		try {
			// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
			httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			int statusCode = httpClient.executeMethod(postMethod);

			// 获得登陆后的 Cookie
			Cookie[] cookies = httpClient.getState().getCookies();
			StringBuffer tmpcookies = new StringBuffer();
			for (Cookie c : cookies) {
				tmpcookies.append(c.toString() + ";");

				// System.out.println("cookies = "+c.toString());
			}
			javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie(cookies[0].toString(),
					cookies[1].toString());
			// cook.setPath(uri);
			// cook.setMaxAge(expiry);
			cook.setDomain("http://61.149.215.198:18081");
			cook.setSecure(false);
			this.getResponse().addCookie(cook);
			if (statusCode == 200) {// 重定向到新的URL
				// System.out.println("模拟登录成功");
				// 进行登陆后的操作
				GetMethod getMethod = new GetMethod(dataUrl);
				// 每次访问需授权的网址时需带上前面的 cookie 作为通行证
				getMethod.setRequestHeader("cookie", tmpcookies.toString());
				// 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
				// 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
				postMethod.setRequestHeader("Referer", "http://passport.mop.com/");
				postMethod.setRequestHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
				httpClient.executeMethod(getMethod);
				// 打印出返回数据，检验一下是否成功
				String text = getMethod.getResponseBodyAsString();
				// System.out.println(text);
				jsondata = text;
			} else {
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "show";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getItemChartsData() {
		List<Object[]> areaList = areaService.getCountOrder();
		// List<Area> areaList = areaService.getList(Area.class,
		// Restrictions.eq("level", 0));
		List<Sewage> list = null;
		EnhancedOption op = new EnhancedOption();
		op.title().text("统计图表");
		op.tooltip().trigger(Trigger.axis);
		/*
		 * op.legend("使用次数"); op.legend("使用数量");
		 */
		op.toolbox().show(true);
		op.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar).show(true), Tool.restore,
				Tool.saveAsImage);
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		String ss = null;
		CategoryAxis categoryAxis = new CategoryAxis();
		if (StringUtils.isNotEmpty(areaId) && !areaId.equals("null") && !areaId.equals("0")) {
			List typeList = sewageService.getType();
			for (Object obj : typeList) {
				res3.add(obj);
				res1.add(sewageService
						.getList(Sewage.class, Restrictions.eq("pwtype", obj),
								Restrictions.or(Restrictions.or(Restrictions.eq("province", areaId),
										Restrictions.eq("city", areaId)), Restrictions.eq("cdistrict", areaId)))
						.size());
			}
			categoryAxis.name("排污行业").data(res3.toArray()).axisLabel().rotate(20);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("排污行业数量"));
			// op.yAxis(new ValueAxis().name(""));
			Bar bar = new Bar();
			bar.name("排污行业数量").stack("排污行业数量").symbol(Symbol.circle).data(res1.toArray()).itemStyle().normal()
					.color("#00FFFF");
			op.series(bar);
			bar = new Bar();
		} else {
			try {
				ss = java.net.URLDecoder.decode(pwtype, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} // 转码UTF8
			if (StringUtils.isNotEmpty(ss) && !ss.equals("null")) {
				for (Object[] obj : areaList) {
					if(ss!=null&&!ss.equals("undefined")){
						list = sewageService.getList(Sewage.class,
								Restrictions.eq("pwtype", ss),
								Restrictions.eq("province", obj[0]));
					}else{
						list = sewageService.getList(Sewage.class,
								Restrictions.eq("province", obj[0]));
					}
					res3.add(obj[1]);
					res1.add(list.size());
				}
			} else {
				list = sewageService.getAllList(Sewage.class);
				for (Object[] obj : areaList) {
					res3.add(obj[1]);
					res1.add(sewageService.getList(Sewage.class, Restrictions.eq("province", obj[0])).size());
				}
				for (Sewage se : list) {
					res2.add(se.getCode());
				}
			}
			categoryAxis.name("区域(省,市,区)").data(res3.toArray()).axisLabel().rotate(20);
			op.xAxis(categoryAxis);
			op.yAxis(new ValueAxis().name("排污行业数量"));
			// op.yAxis(new ValueAxis().name(""));
			Bar bar = new Bar();
			bar.name("排污行业数量").stack("排污行业数量").symbol(Symbol.circle).data(res1.toArray()).itemStyle().normal()
					.color("#00FFFF");
			op.series(bar);
			bar = new Bar();
			/*
			 * bar.name("企业编号").stack("企业编号").symbol(Symbol.circle).data(res2.
			 * toArray()).itemStyle().normal().color("#BCEE68"); bar.setyAxisIndex(1);
			 * op.series(bar);
			 */

		}
		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.toJson(option));
	}

	/**
	 * 获取排污类型(行业)
	 * 
	 * @Title: getPwType
	 * @return String
	 * @author lyz
	 * @since 2017年10月20日 V 1.0
	 */
	public String getPwType() {
		String json = sewageService.getPwType();
		return ajax(Status.success, json);
	}

	/**
	 * 导入页面跳转
	 * 
	 * @Title: upload
	 * @return String
	 * @author lyz
	 * @since 2017年10月20日 V 1.0
	 */
	public String upload() {
		return "upload";
	}

	public String uploadReport() {

		return "upload_report";
	}

	/**
	 * 导入企业信息
	 * 
	 * @Title:upload
	 * @return String
	 * @author lyz
	 * @since 2017年10月20日 V 1.0
	 */
	public String uploadInfos() {
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
		// 获取上传文件的名字
		String[] fileName = wrapper.getFileNames("uploadFile");
		if (fileName.length == 0) {
			return ajax(Status.success, "请选择上传文件！");
		}
		// 文件后缀
		String endSuffix = fileName[0].substring(fileName[0].lastIndexOf("."), fileName[0].length());
		if (!endSuffix.toLowerCase().endsWith("xls") && !endSuffix.toLowerCase().endsWith("xlsx")) {
			return ajax(Status.success, "请选择正确的文件类型，必须是以.xls或.xlsx结尾！");
		}
		try {
			String message = sewageService.saveImportInfos(endSuffix, uploadFile);
			String msg = "";
			if (message.indexOf("@") == 0) {
				msg = message.substring(1, message.length());// "|本次导入"+ (banklist.size()-1)+"条，重复导入"+j
			}
			ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", msg);
			return ajax(Status.success, message);
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.indexOf("@") == 0) {
				message = message.substring(1, message.length());
			}
			ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", message);
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 导入企业信息
	 * 
	 * @Title:upload
	 * @return String
	 * @author lyz
	 * @throws IOException
	 * @since 2017年10月20日 V 1.0
	 */
	public String uploadReports() throws IOException {
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
		// 获取上传文件的名字
		String[] fileName = wrapper.getFileNames("uploadFile");
		if (fileName.length == 0) {
			return ajax(Status.success, "请选择上传文件！");
		}
		// 文件后缀
		String endSuffix = fileName[0].substring(fileName[0].lastIndexOf("."), fileName[0].length());
		if (!endSuffix.toLowerCase().endsWith("xls") && !endSuffix.toLowerCase().endsWith("xlsx")
				&& !endSuffix.toLowerCase().endsWith("doc") && !endSuffix.toLowerCase().endsWith("docx")
				&& !endSuffix.toLowerCase().endsWith("ppt") && !endSuffix.toLowerCase().endsWith("ppts")
				&& !endSuffix.toLowerCase().endsWith("pdf")) {
			return ajax(Status.success, "请选择正确的文件类型，必须是以.pdf或.doc或.docx或.ppt或.pptx或.xls或.xlsx结尾！");
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String fileNewName = String.valueOf(date.getTime() / 1000);
		String realpath = ServletActionContext.getServletContext()
				.getRealPath("/uploads/files/" + formatter.format(date));
		File savefile = new File(new File(realpath), fileNewName + endSuffix);
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
		FileUtils.copyFile(uploadFile, savefile);

		if(endSuffix!=".pdf") {
			ConvertToPdf ctp=new ConvertToPdf();
			ctp.convert2PDF(realpath+"/"+fileNewName+endSuffix, realpath+"/"+fileNewName+".pdf");
		}
			
		
		return ajax(Status.success, "/uploads/files/" + formatter.format(date) + '/' + fileNewName + endSuffix);

		// try {
		// String message = sewageService.saveImportInfos(endSuffix, uploadFile);
		// String msg = "";
		// if (message.indexOf("@") == 0) {
		// msg = message.substring(1, message.length());// "|本次导入"+
		// (banklist.size()-1)+"条，重复导入"+j
		// }
		// ServletActionContext.getRequest().getSession().setAttribute("_repeatItem",
		// msg);
		// return ajax(Status.success, message);
		// } catch (Exception e) {
		// String message = e.getMessage();
		// if (message.indexOf("@") == 0) {
		// message = message.substring(1, message.length());
		// }
		// ServletActionContext.getRequest().getSession().setAttribute("_repeatItem",
		// message);
		// return ajax(Status.error, e.getMessage());
		// }
	}

	/**
	 * 导出重复公司名称
	 * 
	 * @Title: download
	 * @return String
	 * @author lizk
	 * @since 2018年3月21日 V 1.0
	 */
	public String download() {
		HttpServletResponse response = getResponse();
		String path = null;
		try {
			String name = "重复数据统计导出";
			HSSFWorkbook wb = sewageService.exporta(name, formdata);
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 根据地址获取经纬度
	 * 
	 * @Title: getLatAndLngByAddress
	 * @param addr
	 * @return Map<String,BigDecimal>
	 * @author lyz
	 * @since 2017年10月23日 V 1.0
	 */
	public String getLatAndLngByAddress() {
		String lat = "";
		String lng = "";
		List<Sewage> list = getAllList(Sewage.class);
		if (list != null && !list.isEmpty()) {
			for (Sewage se : list) {
				String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
						+ "ak=0uWsZHIe02KA62w3Mp9lWBQTuC7PdX7a&output=json&address=%s", se.getPwname());
				URL myURL = null;
				URLConnection httpsConn = null;
				// 进行转码
				try {
					myURL = new URL(url);
				} catch (MalformedURLException e) {

				}
				try {
					httpsConn = (URLConnection) myURL.openConnection();
					if (httpsConn != null) {
						InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
						BufferedReader br = new BufferedReader(insr);
						String data = br.readLine();
						if (StringUtils.isNotEmpty(data) && data != null) {
							if (data.contains("msg")) {
								return ajax(Status.success,
										"“" + se.getPwname() + "”" + "经纬度数据获取异常,请手动在企业名称加上对应城市后再点击自动填充按钮!");
							} else {
								lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(),
										data.indexOf("},\"precise\""));
								lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(),
										data.indexOf(",\"lat\""));
							}
						}
						insr.close();
					}
				} catch (IOException e) {
					return ajax(Status.success, "填充异常!");
				}
				sewage = getBeanById(Sewage.class, se.getId());
				sewage.setLongitude(lng);
				sewage.setLatitude(lat);
				sewageService.update(sewage);
			}
			return ajax(Status.success, "success");
		}
		return ajax(Status.success, "没有数据,无法填充!");
	}

	/*******************************/
	public String getLicensingDate() {
		return licensingDate;
	}

	public void setLicensingDate(String licensingDate) {
		this.licensingDate = licensingDate;
	}

	public String getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}
	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getGriddata() {
		return griddata;
	}

	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}

	public Sewage getSewage() {
		return sewage;
	}

	public void setSewage(Sewage sewage) {
		this.sewage = sewage;
	}

	public SewageBusiness getBusiness() {
		return business;
	}

	public void setBusiness(SewageBusiness business) {
		this.business = business;
	}

	public SewagePermit getPermit() {
		return permit;
	}

	public void setPermit(SewagePermit permit) {
		this.permit = permit;
	}

	public String getPwname() {
		return pwname;
	}

	public void setPwname(String pwname) {
		this.pwname = pwname;
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getPwtype() {
		return pwtype;
	}

	public void setPwtype(String pwtype) {
		this.pwtype = pwtype;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public ArrayList<HashMap> getReport() {
		return report;
	}

	public void setReport(ArrayList<HashMap> report) {
		this.report = report;
	}
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getYstime() {
		return ystime;
	}

	public void setYstime(String ystime) {
		this.ystime = ystime;
	}

	public String getIssuingDate() {
		return issuingDate;
	}

	public void setIssuingDate(String issuingDate) {
		this.issuingDate = issuingDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCdistrict() {
		return cdistrict;
	}

	public void setCdistrict(String cdistrict) {
		this.cdistrict = cdistrict;
	}

	
	
}
