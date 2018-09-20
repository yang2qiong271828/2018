package com.lingnet.vocs.dao.finance;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AccountFlow;

public interface AccountFlowDao extends BaseDao<AccountFlow, String> {

    /**
     * 根据sql获取账务流水列表数据
     * 
     * @Title: findPagerAfBySql
     * @param m
     * @param pager
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    Pager findPagerAfBySql(Map<String, String> m, Pager pager, String areaId,
            String ownerId);

    /**
     * 金额统计
     * @Title: getSummaryData 
     * @param m
     * @param areaId
     * @param ownerId
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @since 2017年8月10日 V 1.0
     */
    List<Object[]> getSummaryData(Map<String, String> m,String areaId,String ownerId);
}
