package com.lingnet.vocs.dao.alarm;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AbnormalHint;

public interface AbnormalHintDao extends BaseDao<AbnormalHint, String> {
    /**
     * @Title: findPagerAbHintBySql
     * @param pager
     * @param m
     * @param id
     * @return
     * @author zy
     * @param partnerId
     * @since 2017年7月17日 V 1.0
     */
    Pager findPagerAbHintBySql(Pager pager, Map<String, String> m, String id,
            String partnerId);
}
