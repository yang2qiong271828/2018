package com.lingnet.vocs.service.remoteDebug;

import java.text.ParseException;
import java.util.List;

import com.lingnet.common.entity.BaseEntity;
import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Question;

public interface QuestionService extends BaseService<Question, String> {

    public String getListData(Pager pager, String searchData);

    public String saveOrUpdate(String formdata) throws Exception;

    public String remove(String[] ids) throws Exception;

    public String getAllData(Pager pager, String key) throws ParseException;

    @SuppressWarnings("rawtypes")
	public List getTreeIds(Class<? extends BaseEntity> entityClass,String id,List list);
}
