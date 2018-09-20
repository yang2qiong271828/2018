package com.lingnet.vocs.service.statistics;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;

public interface ServiceChargeService extends BaseService<Object, String> {
    List<Object[]> getChartsData(String key);

    String getGridData(String key, Pager pager, String engineerCn);
}
