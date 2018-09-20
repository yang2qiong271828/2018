package com.lingnet.vocs.action.workorder;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.OrderQuestion;
import com.lingnet.vocs.service.workorder.OrderQuestionService;

/**
 * 问题库
 * 
 * @ClassName: OrderQuestion
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月6日 下午4:31:23
 * 
 */
public class OrderQuestionAction extends BaseAction {

	private static final long serialVersionUID = 4835673874793704929L;

	private OrderQuestion orderQuestion;

	@Resource(name = "orderQuestionService")
	private OrderQuestionService orderQuestionService;

	private String formdata;// form表单

	/**
	 * list展示页
	 * 
	 * @Title: list
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String list() {
		return "list";
	}

	/**
	 * 列表页获取数据
	 * 
	 * @Title: getListData
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String getListData() {
		String json = "";
		json = orderQuestionService.getListData(pager);
		return ajax(Status.success, json);
	}

	/**
	 * 新增
	 * 
	 * @Title: add
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String saveOrUpdate() {
		orderQuestion = JsonUtil.toObject(formdata, OrderQuestion.class);
		try {
			String result = "";
			result = orderQuestionService.saveOrUpdate(orderQuestion);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	/**
	 * 修改
	 * 
	 * @Title: edit
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String edit() {
		orderQuestion = orderQuestionService.get(OrderQuestion.class, id);
		if (orderQuestion == null) {
			orderQuestion = new OrderQuestion();
		}
		return ADD;
	}

	/**
	 * 查看
	 * 
	 * @Title: look
	 * @return String
	 * @author 薛硕
	 * @since 2017年7月7日 V 1.0
	 */
	public String look() {
		orderQuestion = orderQuestionService.get(OrderQuestion.class, id);
		if (orderQuestion == null) {
			orderQuestion = new OrderQuestion();
		}
		return "look";
	}

	public OrderQuestion getOrderQuestion() {
		return orderQuestion;
	}

	public void setOrderQuestion(OrderQuestion orderQuestion) {
		this.orderQuestion = orderQuestion;
	}

	public OrderQuestionService getOrderQuestionService() {
		return orderQuestionService;
	}

	public void setOrderQuestionService(
			OrderQuestionService orderQuestionService) {
		this.orderQuestionService = orderQuestionService;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
