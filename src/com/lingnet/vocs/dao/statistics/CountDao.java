package com.lingnet.vocs.dao.statistics;

import java.util.List;

import com.lingnet.common.dao.BaseDao;

/**
 * 统计
 * @ClassName: CountDao 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年7月4日 上午8:06:17
 *
 */
public interface CountDao extends BaseDao<Object, String> {
   
    /**
     * 工单统计
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
     * 设备维修统计
     * @Title: getEqChartsData 
     * @param eqList
     * @param top
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @param key 
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getEqChartsData(String partnerId,String top, String key);

    /**
     * 物料统计
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
     * 客户满意度
     * @Title: getSatisfactionData 
     * @param partnerId
     * @param top
     * @param key
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @since 2017年8月1日 V 1.0
     */
    public List<Object[]> getSatisfactionData(String partnerId, String top,
            String key);

    public List<Object[]> getEqCountData(String partnerId, String top,
            String key);

}
