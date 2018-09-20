package com.lingnet.vocs.service.workorder;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.OrderQuestion;

public interface OrderQuestionService extends BaseService<OrderQuestion, String>{

    public  String saveOrUpdate(OrderQuestion orderQuestion) throws Exception;

    public String getListData(Pager pager);

}
