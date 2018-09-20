package com.lingnet.vocs.dao.statistics;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;

public interface ServiceChargeDao extends BaseDao<Object, String> {
    List<Object[]> getChartsData(String key);

    Pager getGridData(String key, Pager pager);

}
