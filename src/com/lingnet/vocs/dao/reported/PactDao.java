package com.lingnet.vocs.dao.reported;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Pact;

public interface PactDao extends BaseDao<Pact, String> {

    public Pager getData(Pager pager);

    public Double getSum(String id, String type, String date);

    public Pager getJhData(Pager pager, String id);

    public Pager getHkData(Pager pager, String id);

	public List<Pact> getPacts(String partnerId);

    
}
