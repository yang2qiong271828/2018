package com.lingnet.vocs.dao.alarm;


import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.CustFeedback;

public interface CustFeedbackDao extends BaseDao<CustFeedback, String> {
    /**
     * @Title: findPagerCustFeedback
     * @param map
     * @param pager
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    Pager findPagerCustFeedback(Map<String, String> map, Pager pager);
}
