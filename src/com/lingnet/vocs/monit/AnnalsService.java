package com.lingnet.vocs.service.monit;


import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Annals;

public interface AnnalsService extends BaseService<Annals, String> {

    public  String getTemperatureData(Pager pager, String eqCode,String startdate,String enddate);

    public List<Object[]> getTemperatureChart(String eqCode, String string,String startdate,String enddate);
}
