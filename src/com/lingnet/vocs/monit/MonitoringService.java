package com.lingnet.vocs.service.monit;


import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ConstantData;

public interface MonitoringService extends BaseService<ConstantData, String> {

    /**
     * 根据盒子唯一标识查找数据库中20组数据（n个监控点）
     * @Title: findConstantData 
     * @param boxId 盒子唯一标识
     * @param uid 监控点
     * @return 
     * List<Object[]> 
     * @author duanjj
     * @since 2017年7月6日 V 1.0
     */
    public List<Object[]> findConstantData(String boxId,String uid);
    public List<Object[]> findConstantDataNew(String boxId,String uid);

    /**
     * 根据盒子唯一标识查找数据库中时间最大的一条数据
     * @Title: findLastData 
     * @param boxId
     * @param string
     * @return 
     * Object[]
     * @author duanjj
     * @since 2017年7月7日 V 1.0
     */
    public Object[] findLastData(String boxId, String string);

    /**
     * 查询分页数据，按创建时间倒叙排列，
     * @Title: findPagerData 
     * @param pager
     * @param map
     * @return 
     * List<HashMap<String,Object>> 
     * @author duanjj
     * @since 2017年7月21日 V 1.0
     */
    public List<HashMap<String, Object>> findPagerData(Pager pager,HashMap<String,Object> map);
    
    public String getDuData(String boxId);
    
    
}
