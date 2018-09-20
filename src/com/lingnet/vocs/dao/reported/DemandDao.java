package com.lingnet.vocs.dao.reported;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Demand;

public interface DemandDao extends BaseDao<Demand,String> {

    public Pager getListData(Pager pager, String status);
}
