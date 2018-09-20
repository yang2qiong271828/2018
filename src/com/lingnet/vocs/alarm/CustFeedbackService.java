package com.lingnet.vocs.service.alarm;


import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.CustFeedback;

public interface CustFeedbackService extends BaseService<CustFeedback, String> {
    /**
     * @Title: getListData
     * @param pager
     * @param key
     * @return
     * @author zy
     * @since 2017年7月5日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    HashMap getListData(Pager pager, String key);

    /**
     * 删除
     * 
     * @Title: remove
     * @param id
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月6日 V 1.0
     */
    String remove(String id) throws Exception;
    /**
     * @Title: saveOrUpdate
     * @param custFeedback
     * @throws Exception
     * @author zy
     * @since 2017年7月5日 V 1.0
     */
    void saveOrUpdate(CustFeedback custFeedback) throws Exception;

    /**
     * 工单提交
     * 
     * @Title: submitWorkOrder
     * @param id
     * @author zy
     * @since 2017年7月6日 V 1.0
     */
    void submitWorkOrder(String id) throws Exception;

}
