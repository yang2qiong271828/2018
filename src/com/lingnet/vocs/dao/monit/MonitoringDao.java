package com.lingnet.vocs.dao.monit;


import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ConstantData;

public interface MonitoringDao extends BaseDao<ConstantData, String> {

    public List<Object[]> findConstantData(String boxId, String uid);
    
    public List<Object[]> findConstantDataNew(String boxId, String uid);

    public Object[] findLastData(String boxId, String uid);

    /**
     * 根据查询条件获取数据
     * @Title: findPagerData 
     * @param pager
     * @param map
     * @return 
     * List<HashMap<String,Object>> 
     * @author duanjj
     * @since 2017年7月21日 V 1.0
     */
    public List<HashMap<String, Object>> findPagerData(Pager pager,
            HashMap<String, Object> map);
    
    
    public String getDuData(String boxId);
    
}
