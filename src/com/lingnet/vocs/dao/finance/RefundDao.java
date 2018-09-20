package com.lingnet.vocs.dao.finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Refund;

public interface RefundDao extends BaseDao<Refund, String> {

    /**
     * @Title: findPagerRefundBySql
     * @param pager
     * @param m
     * @param ownerId
     * @return
     * @author zy
     * @param level 
     * @param id 
     * @since 2017年7月25日 V 1.0
     */
    Pager findPagerRefundBySql(Pager pager,Map<String, String> m,String ownerId, String id, Integer level);

    /**
     * 金额统计
     * @Title: getSummaryData 
     * @param mm
     * @param partnerId
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @since 2017年8月10日 V 1.0
     */
    List<Object[]> getSummaryData(HashMap<String, String> mm, String partnerId);

}
