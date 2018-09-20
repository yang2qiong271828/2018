package com.lingnet.vocs.action.remotedebug;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.vocs.entity.Question;
import com.lingnet.vocs.entity.QuestionType;
import com.lingnet.vocs.entity.Solution;
import com.lingnet.vocs.service.remoteDebug.SolutionService;

/**
 * 问题解决方案
 * 
 * @ClassName: SolutionAction
 * @Description: TODO
 * @author tangjw
 * @date 2017年6月2日 下午5:07:18
 * 
 */
@Controller("solutionAction")
@ParentPackage("remotedebug")
@Namespace("/remotedebug")
public class SolutionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "solutionService")
	private SolutionService solutionService;

	private String typeId;
	private String formdata;
	private String questionId;

	/**
	 * 解决方案列表
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
	 * 增加解决方案
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
	 * 编辑解决方案
	 * 
	 * @Title: edit
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String edit() {
		Solution solution = solutionService.get(Solution.class, id);
		Question question;
		QuestionType questionType;
		if (solution != null) {
			question = solutionService.get(Question.class,
					solution.getQuestionId());
			this.getRequest().setAttribute("solution", solution);
			if (question != null) {
				questionType = solutionService.get(QuestionType.class,
						question.getTypeId());
				this.getRequest().setAttribute("question", question);
				if (questionType != null) {
					this.getRequest()
							.setAttribute("questionType", questionType);
				}
			}
		}
		return ADD;
	}

	/**
	 * 查看解决方案
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
	 * 删除解决方案
	 * 
	 * @Title: remove
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String remove() {
		String message = "success";
		try {
			solutionService.remove(ids);
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}

		return ajax(Status.success, message);
	}

	/**
	 * 获取解决方案列表信息
	 * 
	 * @Title: getListData
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String getListData() {
		String data;
		if (key == null || key.equals("")) {
			data = solutionService.getListData(pager, searchData);
		} else {
			try {
				data = solutionService.getDataByTypeId(pager, key);
			} catch (Exception e) {
				data = "";
				e.printStackTrace();
			}
		}
		return ajax(Status.success, data);
	}

	/**
	 * 保存或更新解决方案
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String saveOrUpdate() {
		String message = "success";
		try {
			solutionService.saveOrUpdate(formdata);
		} catch (Exception e) {
			message = e.getMessage() + "保存失败！";
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

	/**
	 * 问题检索列表
	 * 
	 * @Title: searchList
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月7日 V 1.0
	 */
	public String searchList() {
		return "searchList";
	}

	public String showTree() {
		/*
		 * List list = new ArrayList(); Map map = new HashMap(); map.put("id",
		 * "0"); map.put("text", "客户一"); Map map1 = new HashMap();
		 * map1.put("id", "01"); map1.put("text", "设备一"); map1.put("pid", "0");
		 * Map map2 = new HashMap(); map2.put("id", "02"); map2.put("text",
		 * "设备二"); map2.put("pid", "0"); Map map3 = new HashMap();
		 * map3.put("id", "03"); map3.put("text", "设备三"); map3.put("pid", "0");
		 * Map map4 = new HashMap(); map4.put("id", "04"); map4.put("text",
		 * "设备四"); map4.put("pid", "0"); list.add(map); list.add(map1);
		 * list.add(map2); list.add(map3); list.add(map4);
		 */
		String treedata = solutionService.showTree();
		return ajax(Status.success, treedata);
	}

	public String getDataByQuestion() {
		String message = solutionService.getDataByQuestion(questionId);
		return ajax(Status.success, message);
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public SolutionService getSolutionService() {
		return solutionService;
	}

	public void setSolutionService(SolutionService solutionService) {
		this.solutionService = solutionService;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

}
