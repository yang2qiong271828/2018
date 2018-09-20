package com.lingnet.vocs.service.statistics;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;

public interface ServiceBillService extends BaseService<Object, String>{

    public List<Object[]> getChartsData(String method, String key);

    public String getGridData(Pager pager,String method, String key);
}
