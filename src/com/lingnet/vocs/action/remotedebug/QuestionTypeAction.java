package com.lingnet.vocs.action.remotedebug;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Question;
import com.lingnet.vocs.entity.QuestionType;
import com.lingnet.vocs.service.remoteDebug.QuestionTypeService;

@Controller
public class QuestionTypeAction extends BaseAction {

	private static final long serialVersionUID = 7111726885822373478L;

	private QuestionType questionType;

	private String formdata;

	@Resource(name = "questionTypeService")
	private QuestionTypeService questionTypeService;

	public String add() {
		return ADD;
	}

	public String saveOrUpdate() {
		questionType = JsonUtil.toObject(formdata, QuestionType.class);
		try {
			String result = "";
			result = questionTypeService.saveOrUpdate(questionType);
			return ajax(Status.success, result);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String edit() {
		questionType = questionTypeService.get(QuestionType.class, id);
		if (questionType == null) {
			questionType = new QuestionType();
		}
		QuestionType orc = questionTypeService.get(QuestionType.class,
				questionType.getpId());
		if (orc != null) {
			questionType.setpName(orc.getTypeName());
		}
		return ADD;
	}

	public String remove() {
		String message = "success";
		ids = StringUtils.split(ids[0], ",");
		List<Question> questions = questionTypeService.getList(Question.class,
				Restrictions.in("typeId", ids));
		if (questions.isEmpty() || questions == null) {
			questionTypeService.delete(ids);
			operate("记录问题现象", "删除", ids[0]);
		} else {
			message = "存在此类型问题，不能删除！！";
		}
		return ajax(Status.success, message);
	}

	public String getListData() {
		String json = questionTypeService.getListData(pager);
		return ajax(Status.success, JsonUtil.Encode(json));
	}

	public String list() {
		return "list";
	}

	public String tree() {
		return "tree";
	}

	public String getTreeData() {
		String typeData = "[{id:\"1\",typename:\"设备故障\",describe:\"设备故障\",parentTypeId:\"\"},{id:\"2\",typename:\"脱附报警\",describe:\"脱附报警\",parentTypeId:\"\"},{id:\"10\",typename:\"设备运行故障\",describe:\"设备运行故障\",parentTypeId:\"1\"},{id:\"20\",typename:\"脱附异常\",describe:\"脱附异常\",parentTypeId:\"2\"}]";
		return ajax(Status.success, typeData);
	}

	/****************************************************************************************/
	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public QuestionTypeService getQuestionTypeService() {
		return questionTypeService;
	}

	public void setQuestionTypeService(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
