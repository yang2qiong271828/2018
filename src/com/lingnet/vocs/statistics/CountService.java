package com.lingnet.vocs.service.statistics;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;

/**
 * 统计
 * 
 * @ClassName: CountService
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月4日 上午8:06:17
 *
 */
public interface CountService extends BaseService<Object, String> {

    /**
     * 工单统计图表
     * @Title: getChartsData 
     * @param method
     * @param key
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getChartsData(String method, String key);

    /**
     * 工单统计grid
     * @Title: getGridData 
     * @param method
     * @param key
     * @param pager
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public String getGridData(String method, String key,Pager pager);

    /**
     * 设备维修统计图表
     * @Title: getEqChartsData 
     * @param partnerId
     * @param top
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @param key 
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getEqChartsData(String partnerId, String top, String key);

    /**
     * 设备维修统计grid
     * @Title: getEqGridData 
     * @param partnerId
     * @param top
     * @param pager
     * @return 
     * String 
     * @author 薛硕
     * @param key 
     * @since 2017年8月1日 V 1.0
     */
    public String getEqGridData(String partnerId, String top, Pager pager, String key);

    /**
     * 物料统计图表
     * @Title: getItemChartsData 
     * @param partnerId
     * @param top
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @param key 
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getItemChartsData(String partnerId, String top, String key);

    /**
     * 物料统计grid
     * @Title: getItemGridData 
     * @param partnerId
     * @param top
     * @param pager
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public String getItemGridData(String partnerId, String top, Pager pager,String key);

    /**
     * 客户满意度图表
     * @Title: getSatisfactionChartsData 
     * @param partnerId
     * @param top
     * @param key
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getSatisfactionChartsData(String partnerId,
            String top, String key);

    /**
     * 客户满意度grid
     * @Title: getSatisfactionGridData 
     * @param partnerId
     * @param top
     * @param pager
     * @param key
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public String getSatisfactionGridData(String partnerId, String top,
            Pager pager, String key);

}
