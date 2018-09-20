package com.lingnet.vocs.dao.statistics;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;

public interface ServiceBillDao extends BaseDao<Object, String>{

    public  List<Object[]> getChartsData(String method,String key);
    
    public  Pager getGridData(String method,String key, Pager pager);
}
