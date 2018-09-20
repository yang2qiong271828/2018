package com.lingnet.vocs.dao.statistics;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;

public interface TaskScoreDao extends BaseDao<Object, String> {
    /**
     * @Title: getChartsData
     * @param key
     * @return
     * @author zy
     * @since 2017年7月27日 V 1.0
     */
    List<Object[]> getChartsData(String key);

    /**
     * @Title: getGridData
     * @param key
     * @return
     * @author zy
     * @since 2017年7月27日 V 1.0
     */
    Pager getGridData(String key, Pager pager);
}
