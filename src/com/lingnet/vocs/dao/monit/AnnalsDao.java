package com.lingnet.vocs.dao.monit;


import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Annals;

public interface AnnalsDao extends BaseDao<Annals, String> {

  public   Pager getListData(Pager pager, String eqCode,String name,String startdate,String enddate);

public List<Object[]> getTemperatureChart(String eqCode, String uid,String startdate,String enddate);
}
