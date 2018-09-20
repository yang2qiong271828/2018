package com.lingnet.vocs.action.remotedebug;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Question;
import com.lingnet.vocs.entity.QuestionType;
import com.lingnet.vocs.service.remoteDebug.QuestionService;

/**
 * 问题现象
 * 
 * @ClassName: QuestionAction
 * @Description: TODO
 * @author tangjw
 * @date 2017年6月2日 下午4:59:43
 * 
 */
@Controller("questionAction")
@ParentPackage("remotedebug")
@Namespace("/remotedebug")
public class QuestionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "questionService")
	private QuestionService questionService;

	private String formdata;
	private String griddata;

	/**
	 * 问题现象列表
	 * 
	 * @Title: list
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 增加问题现象
	 * 
	 * @Title: add
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 编辑问题现象
	 * 
	 * @Title: edit
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String edit() {
		Question question = this.getBeanById(Question.class, id);
		if (question != null) {
			this.getRequest().setAttribute("question", question);
		}
		return ADD;
	}

	/**
	 * 查看问题现象
	 * 
	 * @Title: look
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月5日 V 1.0
	 */
	public String look() {
		return "show";
	}

	/**
	 * 删除问题现象
	 * 
	 * @Title: remove
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String remove() {
		String message = "success";
		try {
			message = questionService.remove(ids);
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}

		return ajax(Status.success, message);
	}

	/**
	 * 获取问题现象列表信息
	 * 
	 * @Title: getListData
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String getListData() {
		String data = questionService.getListData(pager, searchData);

		return ajax(Status.success, data);
	}

	/**
	 * 保存或更新问题现象
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String saveOrUpdate() {
		String message = "success";
		try {
			message = questionService.saveOrUpdate(formdata);
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return ajax(Status.success, message);
	}

	/**
	 * 选择问题
	 * 
	 * @Title: item
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月5日 V 1.0
	 */
	public String item() {
		return "item";
	}

	public String downLoad() throws IOException {
		System.out.println(id);
		this.download("/docs/数据库设计.xlsx");
		return NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getQuestionType() {
		List<QuestionType> types = this.getAllList(QuestionType.class);
		List list = new ArrayList();
		for (QuestionType type : types) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", type.getId());
			map.put("name", type.getTypeName());
			map.put("pid", type.getpId());
			list.add(map);
		}
		String data = JsonUtil.Encode(list);
		return ajax(Status.success, data);
	}

	public String equList() {
		return "equList";
	}

	public String getAllData() {
		String message;
		try {
			message = questionService.getAllData(pager, key);
		} catch (ParseException e) {
			message = "";
			e.printStackTrace();
		}
		return ajax(Status.success, message);
	}

	// /////////////////////////////////////////
	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
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

}
