package com.lingnet.vocs.service.remoteDebug;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.QuestionType;

public interface QuestionTypeService  extends BaseService<QuestionType, String>{

    public String saveOrUpdate(QuestionType questionType) throws Exception;

    public String getListData(Pager pager);

    @SuppressWarnings("rawtypes")
	public List getTreeIds(String value,List list);
}
