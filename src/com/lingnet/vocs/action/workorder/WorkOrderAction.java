package com.lingnet.vocs.action.workorder;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.SDKTestSendTemplateSMS;
import com.lingnet.vocs.dao.workorder.WorkOrderDao;
import com.lingnet.vocs.dao.workorder.WorkOrderLogDao;
import com.lingnet.vocs.entity.AbnormalAlarm;
import com.lingnet.vocs.entity.AreaResponsible;
import com.lingnet.vocs.entity.Attachments;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.CustomerComplaints;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.OrderClass;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.ServiceOutlay;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.entity.WorkOrderLog;
import com.lingnet.vocs.service.attachments.AttachmentsService;
import com.lingnet.vocs.service.partner.PartnerService;
import com.lingnet.vocs.service.workorder.ServiceOutlayService;
import com.lingnet.vocs.service.workorder.WorkOrderService;

/**
 * 工单
 * 
 * @ClassName: WorkOrderAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月28日 下午2:47:34
 * 
 */
@SuppressWarnings("all")
public class WorkOrderAction extends BaseAction {

	private static final long serialVersionUID = -1582926283070610396L;

	private String formdata;// form表单

	private String griddata;// grid表单

	private WorkOrder workOrder;// 工单

	@Resource(name = "workOrderService")
	private WorkOrderService workOrderService;

	private String state;// 工单状态

	private Partner partner;// 客户、供应商

	private Partner customer;// 客户、供应商

	private QxUsers qxUsers;// 用户
	private Contacts partnerUsers;// 合作商负责人

	private ArrayList<Attachments> imgList;
	private List<HashMap> followAttachs;

	private String attachmentdata;

	private WorkOrderLog workOrderLog;// 跟进记录

	private String transferOrdersId;// 转工单

	private String faultExplain;// 客户投诉转工单时故障说明

	@Resource(name = "attachmentsService")
	private AttachmentsService attachmentsService;

	@Resource(name = "partnerService")
	private PartnerService partnerService;

	private List<WorkOrderLog> woLogList;

	private String czType; // 操作类型 用于保存工单跟踪记录

	private ServiceOutlay serviceOutlay;// 区域维护费

	@Resource(name = "serviceOutlayService")
	private ServiceOutlayService soService;// 区域维护费service

	@Resource(name = "workOrderDao")
	private WorkOrderDao workOrderDao;

	@Resource(name = "workOrderLogDao")
	private WorkOrderLogDao woLogDao;//

	private String actualMaintenanceDate;

	private String score;

	private String opinion;

	private String custFeedbackId;

	private String equipmentName;

	private String partnerName;

	private AbnormalAlarm abnormalAlarm;

	private String customerName;

	private String customerAddress;

	private String customerPhone;

	private AreaResponsible areares;

	private String viewPath;
	
	private String mmp;
	
	 private String option;//柱状图;
	 
	 private String gdclqs;//首页工单处理趋势标志
	 
	 private String endDate;//结束时间
	 
	 private String startDate;//开始时间
	 
	 private String bz;
	/**
	 * 跳转工单评价页面
	 * 
	 * @Title: evaluation
	 * @return String
	 * @author wanl
	 * @since 2017年7月5日 V 1.0
	 */
	public String evaluation() {
		workOrder = workOrderService.get(WorkOrder.class, id);

		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		partner = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (partner == null) {
			partner = new Partner();
		}
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		return "evaluation";
	}

	/**
	 * 工单评价保存
	 * 
	 * @Title: saveEvaluation
	 * @return String
	 * @author wanl
	 * @since 2017年7月5日 V 1.0
	 */
	public String saveEvaluation() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("actualMaintenanceDate", actualMaintenanceDate);
		map.put("score", score);
		map.put("opinion", opinion);
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		String json = workOrderDao.saveEvaluation(id, map);
		try {
			workOrderService.saveEvaluationWorkLog("评价",
					workOrder.getWorkOrderCode(), opinion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 跳转到工单录入list页面--每个人可以看到自己录入的工单以及处理情况
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 跳转查看页面
	 * 
	 * @Title: look
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String look() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		getRequest().setAttribute("flag", "false");
		customer = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (customer == null) {
			customer = new Partner();
		}
		workOrder.setCustomerName(customer.getName());
		partner = workOrderService.get(Partner.class, workOrder.getPartner());
		if (partner == null) {
			partner = new Partner();
		}
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		areares = workOrderService.get(AreaResponsible.class,
				Restrictions.eq("name", qxUsers.getId()));
		if (areares == null) {
			areares = new AreaResponsible();
		}
		List<WorkOrderLog> list = workOrderService.getListByOrder(
				WorkOrderLog.class, Order.desc("createDate"),
				Restrictions.eq("czdj", workOrder.getWorkOrderCode()));
		if (list != null && list.size() > 0) {
			workOrderLog = list.get(0);
		}
		if (workOrderLog == null) {
			workOrderLog = new WorkOrderLog();
		}

		return "look";
	}

	/**
	 * 我的工单查看
	 * 
	 * @Title: lookMyOrder
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月26日 V 1.0
	 */
	public String lookMyOrder() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		customer = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (customer == null) {
			customer = new Partner();
		}
		workOrder.setCustomerName(customer.getName());
		partner = workOrderService.get(Partner.class, workOrder.getPartner());
		if (partner == null) {
			partner = new Partner();
		}
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		areares = workOrderService.get(AreaResponsible.class,
				Restrictions.eq("name", qxUsers.getId()));
		if (areares == null) {
			areares = new AreaResponsible();
		}
		List<WorkOrderLog> list = workOrderService.getListByOrder(
				WorkOrderLog.class, Order.desc("createDate"),
				Restrictions.eq("czdj", workOrder.getWorkOrderCode()));
		if (list != null && list.size() > 0) {
			workOrderLog = list.get(0);
		}
		if (workOrderLog == null) {
			workOrderLog = new WorkOrderLog();
		}

		return "look";
	}

	/**
	 * 待评价工单查看
	 * 
	 * @Title: lookMyOrder
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月26日 V 1.0
	 */
	public String lookCompletelist() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		customer = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (customer == null) {
			customer = new Partner();
		}
		workOrder.setCustomerName(customer.getName());
		partner = workOrderService.get(Partner.class, workOrder.getPartner());
		if (partner == null) {
			partner = new Partner();
		}
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		areares = workOrderService.get(AreaResponsible.class,
				Restrictions.eq("name", qxUsers.getId()));
		if (areares == null) {
			areares = new AreaResponsible();
		}
		List<WorkOrderLog> list = workOrderService.getListByOrder(
				WorkOrderLog.class, Order.desc("createDate"),
				Restrictions.eq("czdj", workOrder.getWorkOrderCode()));
		if (list != null && list.size() > 0) {
			workOrderLog = list.get(0);
		}
		if (workOrderLog == null) {
			workOrderLog = new WorkOrderLog();
		}

		return "look";
	}

	/**
	 * 待处理工单查看
	 * 
	 * @Title: lookMyOrder
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月26日 V 1.0
	 */
	public String lookMyGroup() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		customer = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (customer == null) {
			customer = new Partner();
		}
		workOrder.setCustomerName(customer.getName());
		partner = workOrderService.get(Partner.class, workOrder.getPartner());
		if (partner == null) {
			partner = new Partner();
		}
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		areares = workOrderService.get(AreaResponsible.class,
				Restrictions.eq("name", qxUsers.getId()));
		if (areares == null) {
			areares = new AreaResponsible();
		}
		List<WorkOrderLog> list = workOrderService.getListByOrder(
				WorkOrderLog.class, Order.desc("createDate"),
				Restrictions.eq("czdj", workOrder.getWorkOrderCode()));
		if (list != null && list.size() > 0) {
			workOrderLog = list.get(0);
		}
		if (workOrderLog == null) {
			workOrderLog = new WorkOrderLog();
		}

		return "look";
	}

	/**
	 * 跳转新增页面
	 * 
	 * @Title: add
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String add() {
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		workOrder.setWorkOrderType("0");
		return "add";
	}

	/**
	 * 客户投诉跳转工单添加页面
	 * 
	 * @Title: turnAdd
	 * @return String
	 * @author wanl
	 * @throws UnsupportedEncodingException
	 * @since 2017年7月4日 V 1.0
	 */
	public String turnAdd() throws UnsupportedEncodingException {
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		CustomerComplaints customerComplaints = this.getBeanById(
				CustomerComplaints.class, transferOrdersId);
		String partnerId = customerComplaints.getComplaintsName();

		Contacts contacts = workOrderService.get(Contacts.class,
				Restrictions.eq("partnerId", partnerId));
		String picName = contacts.getPicName();

		Partner partner = this.getBeanById(Partner.class, partnerId);
		String salesman = partner.getSalesman();
		String salesmanContact = partner.getSalesmanContact();

		faultExplain = URLDecoder.decode(faultExplain, "UTF-8");
		customerName = URLDecoder.decode(customerName, "UTF-8");
		customerAddress = URLDecoder.decode(customerAddress, "UTF-8");

		workOrder.setCustomer(partnerId);
		workOrder.setTransferOrdersId(transferOrdersId);
		workOrder.setFaultExplain(faultExplain);
		workOrder.setCustomerName(customerName);
		workOrder.setCustomerAddress(customerAddress);
		workOrder.setCustomerPhone(customerPhone);
		workOrder.setContacts(picName);
		workOrder.setSales(salesman);
		workOrder.setSalesPhone(salesmanContact);
		workOrder.setWorkOrderType("2");
		return "turn";
	}

	/**
	 * 异常报警转工单
	 * 
	 * @Title: exceptionTurnAdd
	 * @return String
	 * @author wanl
	 * @throws UnsupportedEncodingException
	 * @since 2017年7月13日 V 1.0
	 */
	public String exceptionTurnAdd() throws UnsupportedEncodingException {
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		abnormalAlarm = this.getBeanById(AbnormalAlarm.class, transferOrdersId);
		String partnerId = abnormalAlarm.getPartner();
		String exceptionTypeId = abnormalAlarm.getExceptionType();

		OrderClass orderClass = this.getBeanById(OrderClass.class,
				exceptionTypeId);
		String exceptionTypeName = orderClass.getId();

		Contacts contacts = workOrderService.get(Contacts.class,
				Restrictions.eq("partnerId", partnerId));
		String picName = contacts.getPicName();
		String picPhone = contacts.getPicPhone();

		Partner partner = this.getBeanById(Partner.class, partnerId);
		String cus = partner.getName();
		String add = partner.getAddress();
		String salesman = partner.getSalesman();
		String salesmanContact = partner.getSalesmanContact();

		String equipmentId = abnormalAlarm.getEquipmentId();
		Equipment equipment = this.getBeanById(Equipment.class, equipmentId);
		String equipmentCode = equipment.getEquipmentCode();

		faultExplain = URLDecoder.decode(faultExplain, "UTF-8");

		workOrder.setTransferOrdersId(transferOrdersId);
		workOrder.setFaultExplain(faultExplain);
		workOrder.setFaultType(exceptionTypeName);
		workOrder.setEquipmentCode(equipmentCode);
		workOrder.setCustomerName(cus);
		workOrder.setCustomer(partnerId);
		workOrder.setCustomerAddress(add);
		workOrder.setContacts(picName);
		workOrder.setCustomerPhone(picPhone);
		workOrder.setSales(salesman);
		workOrder.setSalesPhone(salesmanContact);
		workOrder.setWorkOrderType("3");
		return "exception";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @Title: edit
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String edit() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder.getCustomer() != null) {
			workOrder.setCustomerName(partnerService.get(Partner.class,
					workOrder.getCustomer()).getName());
		}
		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "add";
	}

	/**
	 * 修改保存
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月28日 V 1.0
	 */
	public String saveOrUpdate() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		try {
			String result = "";
			result = workOrderService.saveOrUpdate(workOrder);
			workOrderService.saveWorkLog(workOrder.getCzType(),
					workOrder.getWorkOrderCode(), "", workOrder.getPartner());
			if (attachmentdata != null && !"[]".equals(attachmentdata)) {
				attachmentsService.saveOrUpdateAttachments(attachmentdata,
						workOrder.getId(), "WORKORDER");
			}
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 列表展示获取list
	 * 
	 * @Title: getListData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月28日 V 1.0
	 */
	public String getListData() {
		String json = workOrderService.getListData(pager, state, id,mmp);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 发布
	 * 
	 * @Title: publish
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String publish() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		workOrder.setState("1");
		String result = "";
		try {
			result = workOrderService.saveOrUpdate(workOrder);
			workOrderService.saveWorkLog("发布工单", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/*
	 * 指派合作商
	 */
	public String hzs() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder.getCustomer() != null) {
			workOrder.setCustomerName(partnerService.get(Partner.class,
					workOrder.getCustomer()).getName());
		}
		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		;
		partner = workOrderService.get(Partner.class,
				partnerService.get(Partner.class, workOrder.getCustomer())
						.getHigherAgent());
		if (partner == null) {
			partner = new Partner();
		}

		return "hzs";
	}

	/**
	 * 指派负责人
	 * 
	 * @Title: fzr
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String fzr() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder.getCustomer() != null) {
			workOrder.setCustomerName(partnerService.get(Partner.class,
					workOrder.getCustomer()).getName());
		}
		if (workOrder.getPartner() != null) {
			workOrder.setPartnerName(partnerService.get(Partner.class,
					workOrder.getPartner()).getName());
		}

		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}

		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "fzr";
	}

	/**
	 * 跳转到区域负责人回退页面
	 * 
	 * @Title: fzrBack
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月13日 V 1.0
	 */
	public String fzrBack() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "fzrback";
	}

	/**
	 * 指派内部负责人
	 * 
	 * @Title: nbyg
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月10日 V 1.0
	 */
	public String nbyg() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		if (workOrder.getCustomer() != null) {
			workOrder.setCustomerName(partnerService.get(Partner.class,
					workOrder.getCustomer()).getName());
		}
		if (workOrder.getPartner() != null) {
			Partner partner2 = partnerService.get(Partner.class,
					workOrder.getPartner());
			workOrder
					.setPartnerName(partner2 == null ? "" : partner2.getName());
		}
		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		partner = workOrderService.get(Partner.class, "1");
		return "nbyg";
	}

	/**
	 * 指派负责人 保存
	 * 
	 * @Title: saveFzr
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String saveFzr() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		try {
			String result = "";
			result = workOrderService.saveFzr(workOrder);
			workOrderService.saveWorkLog("指派负责人", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 废弃
	 * 
	 * @Title: discard
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String discard() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		workOrder.setState("8");
		String result = "";
		try {
			result = workOrderService.saveOrUpdate(workOrder);
			workOrderService.saveWorkLog("废弃工单", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 删除工单
	 * 
	 * @Title: remove
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String remove() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		workOrderService.remove(id);
		workOrderService
				.saveWorkLog("删除", workOrder.getWorkOrderCode(), "", "");
		return ajax(Status.success, "success");
	}

	/**
	 * 跳转到合作商的工单
	 * 
	 * @Title: mygroup
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月29日 V 1.0
	 */
	public String mygroup() {
		return "mygroup";
	}

	/**
	 * 合作商的工单列表展示
	 * 
	 * @Title: getGroupData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String getGroupData() {
		String json = "";
		String partnerId = this.getSession("partnerId").toString();
		json = workOrderService.getGroupData(pager, partnerId, state,mmp);
		return ajax(Status.success, json);
	}

	/**
	 * 工单选择页
	 * 
	 * @Title: showWorkOrder
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String showWorkOrder() {
		return "show";
	}

	/**
	 * 跳转到合作商回退页面
	 * 
	 * @Title: hzsBack
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月13日 V 1.0
	 */
	public String hzsBack() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "hzsback";
	}

	/**
	 * 合作商退回
	 * 
	 * @Title: goBack
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String partnerGoBack() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		workOrder.setState("9");
		workOrder.setPartner("");
		String result = "";
		try {
			result = workOrderService.saveOrUpdate(workOrder);
			workOrderService.saveWorkLog("合作商退回", workOrder.getWorkOrderCode(),
					workOrder.getHfjg(), "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 我的工单
	 * 
	 * @Title: getMyOrderData
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String getMyOrderData() {
		String json = "";
		String partnerId = this.getSession("partnerId").toString();
		json = workOrderService.getMyOrderData(pager, partnerId);
		return ajax(Status.success, json);
	}

	/**
	 * 接单
	 * 
	 * @Title: receive
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String receive() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		String result = "";
		try {
			result = workOrderService.receive(workOrder);
			workOrderService.saveWorkLog("接单", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 我的工单 退回 维护人员的退回
	 * 
	 * @Title: myGoBack
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月30日 V 1.0
	 */
	public String myGoBack() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		String result = "";
		try {
			result = workOrderService.myGoBack(workOrder);
			workOrderService.saveWorkLog("区域负责人退回",
					workOrder.getWorkOrderCode(), workOrder.getHfjg(), "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 跳转到我的工单
	 */
	public String myorder() {
		return "myorder";
	}

	/**
	 * 跳转到转派页面
	 * 
	 * @Title: goOther
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月1日 V 1.0
	 */
	public String goOther() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "goother";
	}

	/**
	 * 申请转派保存
	 * 
	 * @Title: applyGoOther
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月20日 V 1.0
	 */
	public String applyGoOther() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		String result;
		try {
			result = workOrderService.applyGoOther(workOrder);
			workOrderService.saveWorkLog("申请转派", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}

	}

	/**
	 * 保存转派
	 * 
	 * @Title: saveGoOther
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月1日 V 1.0
	 */
	public String saveGoOther() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		String result = "";
		try {
			result = workOrderService.saveGoOther(workOrder);
			workOrderService.saveWorkLog("通过转派申请",
					workOrder.getWorkOrderCode(), "", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 退回转派
	 * 
	 * @Title: backGoOther
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String backGoOther() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		workOrder.setState("10");
		try {
			String result = "";
			result = workOrderService.backGoOther(workOrder);
			workOrderService.saveWorkLog("退回转派申请",
					workOrder.getWorkOrderCode(), "", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 结单
	 * 
	 * @Title: statement
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月1日 V 1.0
	 */
	public String statement() {
		String partnerId = this.getSession("partnerId").toString();
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		imgList = attachmentsService.getAttachmentListByEntityId(workOrder
				.getId());
		getRequest().setAttribute("flag", "false");
		customer = workOrderService.get(Partner.class, workOrder.getCustomer());
		if (customer == null) {
			customer = new Partner();
		}
		partner = workOrderService.get(Partner.class, workOrder.getPartner());
		if (partner == null) {
			partner = new Partner();
		}

		List<Contacts> list1 = workOrderService.getListByOrder(Contacts.class,
				Order.asc("picName"),
				Restrictions.eq("partnerId", workOrder.getPartner()),
				Restrictions.eq("picMain", "0"));
		if (list1 != null && list1.size() > 0) {
			partnerUsers = list1.get(0);
		} else {
			list1 = workOrderService.getList(Contacts.class,
					Restrictions.eq("partnerId", workOrder.getPartner()));
			if (list1 != null && list1.size() > 0) {
				partnerUsers = list1.get(0);
			}
		}
		if (partnerUsers == null) {
			partnerUsers = new Contacts();
		}

		workOrder.setCustomerName(customer.getName());
		qxUsers = workOrderService.get(QxUsers.class,
				workOrder.getReplayPerson());
		if (qxUsers == null) {
			qxUsers = new QxUsers();
		}
		areares = workOrderService.get(AreaResponsible.class,
				Restrictions.eq("name", qxUsers.getId()));
		String area = workOrder.getDistrict();
		if (area != null) {
			serviceOutlay = soService.getByArea(area, workOrder.getFaultType());// 区
		}

		if (serviceOutlay != null) {
			serviceOutlay = soService.getByArea(workOrder.getCity(),
					workOrder.getFaultType());// 市
		}
		if (serviceOutlay != null) {
			serviceOutlay = soService.getByArea(workOrder.getProvince(),
					workOrder.getFaultType());// 省
		}
		if (serviceOutlay != null) {
			serviceOutlay = soService.getByArea("0", workOrder.getFaultType());// 中国
		}
		if (areares == null) {
			areares = new AreaResponsible();
		}
		return "statement";
	}

	/**
	 * 保存结单信息
	 * 
	 * @Title: saveStatement
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String saveStatement() {
		workOrder = JsonUtil.toObject(formdata, WorkOrder.class);
		WorkOrder order = workOrderService.get(WorkOrder.class,
				workOrder.getId());

		partner = workOrderService.get(Partner.class, order.getCustomer());
		String partnername = partner.getName();
		String code = order.getWorkOrderCode();

		String result = "";
		try {
			result = workOrderService.saveStatement(workOrder, griddata);
			workOrderService.saveWorkLog("结单", order.getWorkOrderCode(),
					workOrder.getHfjg(), "");

			// 获取request
			HttpServletRequest request = super.getRequest();
			// 拼接url
			/*
			 * String basePath = request.getScheme() + "://" +
			 * request.getServerName() + ":" + request.getServerPort() +
			 * request.getContextPath() +
			 * "/workorder/work_order!evaluation.action?id=" +
			 * workOrder.getId();
			 */
			Properties p = new Properties();
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("filepath.properties");// 获取配置文件
			p.load(in);
			in.close();
			String rootPath = p.getProperty("massage_path");

			String basePath = rootPath
					+ "/workorder/work_order!evaluation.action?id="
					+ workOrder.getId();

			// 转为数组形式
			String[] strs = new String[] { partnername, code, basePath };
			String phone = order.getCustomerPhone();
			// String phoneList = "12345678910";
			// 调用发送短信方法
			SDKTestSendTemplateSMS.sendMsg(phone, "190082", strs,
					"8a216da85d158d1b015d2bc8e2270839");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 我回复过的工单
	 * 
	 * @Title: getReplayData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String getReplayData() {
		String partnerId = this.getSession("partnerId").toString();
		String json = workOrderService.getReplayData(pager, partnerId, state);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	/**
	 * 客户的工单
	 * 
	 * @Title: completelist
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String completelist() {
		return "completelist";
	}

	/**
	 * 客户的工单获取数据
	 * 
	 * @Title: getCompleteData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String getCompleteData() {
		String partnerId = this.getSession("partnerId").toString();
		String json = workOrderService.getCompleteData(pager, partnerId);
		return ajax(Status.success, json);
	}
	
	//待审批订单
	public String dsporder() {
		return "dsporder";
	}
	
	//待审批列表
	public String getDsporderData() {
		String partnerId = this.getSession("partnerId").toString();
		List<WorkOrder> list = workOrderService.getReplayDataAll();
		String json = workOrderService.getDsporderData(pager);
		return ajax(Status.success, json);
	}
	//审批订单
		public String sptg() {
			WorkOrder workorder = workOrderService.findById(id);
			workorder.setScore("5");
			String json = null;
			try {
				json = workOrderService.Upd(workorder);
				return ajax(Status.success);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
	
	/**
	 * 激活
	 * 
	 * @Title: activation
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月3日 V 1.0
	 */
	public String activation() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		workOrder.setState("10");
		try {
			String result = "";
			result = workOrderService.saveOrUpdate(workOrder);
			workOrderService.saveWorkLog("激活工单", workOrder.getWorkOrderCode(),
					"", "");
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String getItemByWorkOrder() {
		String result = workOrderService.getItemByWorkOrder(id);
		return ajax(Status.success, result);
	}

	/**
	 * 跳转到工单跟踪记录展示页
	 * 
	 * @Title: follow
	 * @return String
	 * @author 薛硕
	 * @throws ParseException
	 * @since 2017年7月6日 V 1.0
	 */
	public String follow() throws ParseException {
		workOrder = this.getBeanById(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "follow";
	}

	/**
	 * 待处理工单查看跟踪记录
	 * 
	 * @Title: follow
	 * @return String
	 * @author 薛硕
	 * @throws ParseException
	 * @since 2017年7月6日 V 1.0
	 */
	public String followMyGroup() throws ParseException {
		workOrder = this.getBeanById(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		return "follow";
	}

	public String confirm() {
		return "confirm";
	}

	/**
	 * 跳转到我的回复过的工单页面
	 */
	public String myreplay() {
		return "myreplay";
	}

	public String addfollow() {
		workOrder = workOrderService.get(WorkOrder.class, id);
		if (workOrder != null) {
			this.getRequest().setAttribute("workOrder", workOrder);
		}
		return "addfollow";
	}

	public String saveFollow() {
		String message = "success";
		try {
			workOrderService.saveFollow(formdata, griddata, attachmentdata);
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return ajax(Status.success, message);
	}

	public String getFollowData() {
		workOrder = this.getBeanById(WorkOrder.class, id);
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		/*
		 * Partner er =getBeanById(Partner.class,workOrder.getCustomer());
		 * String usrename = ""; if(er != null){ usrename = er.getName(); }
		 */
		followAttachs = new ArrayList<HashMap>();
		// followAttachs =
		// workOrderService.getFollowMap(workOrder.getWorkOrderCode());
		woLogList = workOrderService.getFollowByWorkOrder(workOrder
				.getWorkOrderCode());
		if (!woLogList.isEmpty()) {
			try {
				for (int i = 0; i < woLogList.size(); i++) {
					WorkOrderLog wolog = woLogList.get(i);
					QxUsers qxUser = workOrderService.get(QxUsers.class,
							wolog.getCzUser());
					List<Attachments> listattach = attachmentsService.getList(
							Attachments.class,
							Restrictions.eq("entityId", wolog.getId()));
					// Attachments attach =
					// attachmentsService.get(Attachments.class,
					// Restrictions.eq("entityId", wolog.getId()));
					HashMap<String, Object> map = new HashMap();

					if (listattach != null && !listattach.isEmpty()) {
						for (int k = 0; k < listattach.size(); k++) {
							Attachments attach = listattach.get(k);
							map.put("filename" + k, attach.getName());
							map.put("filepath" + k, attach.getPath());
						}
					}
					map.put("attachnum", listattach.size());
					Partner er = getBeanById(Partner.class, wolog.getFzorhz());
					String usrename = "";
					if (er != null) {
						usrename = er.getName();
					}
					if (qxUser != null) {
						if (er != null) {
						}
						if (wolog.getCzType().equals("指派合作商")) {
							map.put("czName",
									qxUser.getName() + qxUser.getUsername()
											+ wolog.getCzType() + usrename);
						} else if (wolog.getCzType().equals("指派负责人")) {
							map.put("czName",
									qxUser.getName() + qxUser.getUsername()
											+ wolog.getCzType() + usrename);
						} else {
							map.put("czName",
									qxUser.getName() + qxUser.getUsername()
											+ wolog.getCzType());
						}
					}
					map.put("czDate", wolog.getCzDate());
					map.put("czdj", wolog.getCzdj());
					// map.put("czType", wolog.getCzType());
					map.put("hfnr", wolog.getHfnr());
					followAttachs.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ajax(Status.success, JsonUtil.Encode(followAttachs));
	}

	public String picView() {
		imgList = attachmentsService.getAttachmentListByEntityId(id);
		getRequest().setAttribute("flag", "false");
		return "pic_view";
	}
	
	public String showPic() {

		return "show_pic";
	}

	public String viewPic() {
		return "view_pic";
	}
	
	public String getMyOrderDataIndex(){
		String partnerId = this.getSession("partnerId").toString();
		return ajax(Status.success, workOrderService.getMyOrderDataIndex(partnerId));
	}

	
	/**
	 * 工单柱状图
	 * @Title: getChartsData 
	 * @return 
	 * String 
	 * @author lyz
	 * @since 2017年12月14日 V 1.0
	 */
	public String getChartsData(){
		//List<Object[]> areaList = areaService.getCountOrder();
		List<Sewage> list = null;
		EnhancedOption op = new EnhancedOption();
		/*op.title().text("统计图表");*/
		op.tooltip().trigger(Trigger.axis);
		op.toolbox().show(true);
		if(StringUtils.isNotEmpty(bz) && bz.equals("1")){
			op.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar).show(true),
			Tool.restore, Tool.saveAsImage);
		}
		op.calculable(true);
		List res1 = new ArrayList();
		List res2 = new ArrayList();
		List res3 = new ArrayList();
		CategoryAxis categoryAxis = new CategoryAxis();
		List<Object[]> listNames = workOrderService.getKhNames(pager,startDate,endDate);
		for(int i=0;i<listNames.size();i++){
			if(i<5){
				if(listNames.get(i)[1]!=null&&listNames.get(i)[1].toString().length()>9){
					res3.add(listNames.get(i)[1].toString().substring(0,9)+"..");
				}else{
					res1.add(listNames.get(i)[1]);
				}
				
				res1.add(listNames.get(i)[2]);
			}
		}
		categoryAxis.name("客户").data(res3.toArray()).axisLabel()
				.rotate(20);
		op.xAxis(categoryAxis);
		op.yAxis(new ValueAxis().name("工单数量"));
		Bar bar = new Bar();
		bar.name("工单数量").stack("工单数量").symbol(Symbol.circle)
				.data(res1.toArray()).itemStyle().normal().color("#00FFFF");
		op.series(bar);
		bar = new Bar();
		option = GsonUtil.format(op);
		return ajax(Status.success, JsonUtil.toJson(option));
	}
	/**
	 * 跳转页面
	 * @Title: listcharts 
	 * @return 
	 * String 
	 * @author lyz
	 * @since 2017年12月14日 V 1.0
	 */
	public String listcharts(){
		return "listcharts";
	}
	/**
	 * 查表格数据
	 * @Title: getZsListData 
	 * @return 
	 * String 
	 * @author lyz
	 * @since 2017年12月14日 V 1.0
	 */
	public String getZsListData(){
		List<Object[]> listNames = workOrderService.getKhNames(pager,startDate,endDate);
		List list = new ArrayList();
		for(Object[] obj:listNames){
			HashMap map =new HashMap();
			map.put("cust",obj[1]);
			map.put("all", obj[2]);
			list.add(map);
		} 
		HashMap hashMap = new HashMap();
		hashMap.put("data", list);
		hashMap.put("total", list.size());
		return ajax(Status.success,JsonUtil.Encode(hashMap));
	}
	/****************************************************************************************************/
	public String com() {
		return "com";
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public WorkOrderService getWorkOrderService() {
		return workOrderService;
	}

	public void setWorkOrderService(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public QxUsers getQxUsers() {
		return qxUsers;
	}

	public void setQxUsers(QxUsers qxUsers) {
		this.qxUsers = qxUsers;
	}

	public String getGriddata() {
		return griddata;
	}

	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}

	public ArrayList<Attachments> getImgList() {
		return imgList;
	}

	public void setImgList(ArrayList<Attachments> imgList) {
		this.imgList = imgList;
	}

	public String getAttachmentdata() {
		return attachmentdata;
	}

	public void setAttachmentdata(String attachmentdata) {
		this.attachmentdata = attachmentdata;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	public WorkOrderLog getWorkOrderLog() {
		return workOrderLog;
	}

	public void setWorkOrderLog(WorkOrderLog workOrderLog) {
		this.workOrderLog = workOrderLog;
	}

	public String getTransferOrdersId() {
		return transferOrdersId;
	}

	public void setTransferOrdersId(String transferOrdersId) {
		this.transferOrdersId = transferOrdersId;
	}

	public String getFaultExplain() {
		return faultExplain;
	}

	public void setFaultExplain(String faultExplain) {
		this.faultExplain = faultExplain;
	}

	public Partner getCustomer() {
		return customer;
	}

	public void setCustomer(Partner customer) {
		this.customer = customer;
	}

	public List<WorkOrderLog> getWoLogList() {
		return woLogList;
	}

	public void setWoLogList(List<WorkOrderLog> woLogList) {
		this.woLogList = woLogList;
	}

	public String getCzType() {
		return czType;
	}

	public void setCzType(String czType) {
		this.czType = czType;
	}

	public AttachmentsService getAttachmentsService() {
		return attachmentsService;
	}

	public void setAttachmentsService(AttachmentsService attachmentsService) {
		this.attachmentsService = attachmentsService;
	}

	public ServiceOutlay getServiceOutlay() {
		return serviceOutlay;
	}

	public void setServiceOutlay(ServiceOutlay serviceOutlay) {
		this.serviceOutlay = serviceOutlay;
	}

	public ServiceOutlayService getSoService() {
		return soService;
	}

	public void setSoService(ServiceOutlayService soService) {
		this.soService = soService;
	}

	public WorkOrderDao getWorkOrderDao() {
		return workOrderDao;
	}

	public void setWorkOrderDao(WorkOrderDao workOrderDao) {
		this.workOrderDao = workOrderDao;
	}

	public String getActualMaintenanceDate() {
		return actualMaintenanceDate;
	}

	public void setActualMaintenanceDate(String actualMaintenanceDate) {
		this.actualMaintenanceDate = actualMaintenanceDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCustFeedbackId() {
		return custFeedbackId;
	}

	public void setCustFeedbackId(String custFeedbackId) {
		this.custFeedbackId = custFeedbackId;
	}

	public Contacts getPartnerUsers() {
		return partnerUsers;
	}

	public void setPartnerUsers(Contacts partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public List<HashMap> getFollowAttachs() {
		return followAttachs;
	}

	public void setFollowAttachs(List<HashMap> followAttachs) {
		this.followAttachs = followAttachs;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public AbnormalAlarm getAbnormalAlarm() {
		return abnormalAlarm;
	}

	public void setAbnormalAlarm(AbnormalAlarm abnormalAlarm) {
		this.abnormalAlarm = abnormalAlarm;
	}

	public AreaResponsible getAreares() {
		return areares;
	}

	public void setAreares(AreaResponsible areares) {
		this.areares = areares;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getMmp() {
		return mmp;
	}

	public void setMmp(String mmp) {
		this.mmp = mmp;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getGdclqs() {
		return gdclqs;
	}

	public void setGdclqs(String gdclqs) {
		this.gdclqs = gdclqs;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
