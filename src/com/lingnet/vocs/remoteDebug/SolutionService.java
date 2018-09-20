package com.lingnet.vocs.service.remoteDebug;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Solution;

public interface SolutionService extends BaseService<Solution, String> {

    public String showTree();

    public String getListData(Pager pager, String searchData);

    public String getDataByTypeId(Pager pager, String typeId) throws Exception;

    public void saveOrUpdate(String formdata) throws Exception;

    public void remove(String[] ids) throws Exception;

    public String getDataByQuestion(String questionId);

}
