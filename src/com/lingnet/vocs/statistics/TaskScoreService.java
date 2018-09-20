package com.lingnet.vocs.service.statistics;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;

/**
 * @ClassName: TaskScoreService
 * @Description: TODO
 * @author zy
 * @date 2017年7月27日 下午3:21:36
 * 
 */

public interface TaskScoreService extends BaseService<Object, String> {
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
    String getGridData(String key, Pager pager, String companyCn);
}
