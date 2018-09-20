package com.lingnet.vocs.service.reported;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Demand;

public interface DemandService extends BaseService<Demand, String> {

    public String getListData(Pager pager, String status);

    public String saveOrUpdate(Demand demand, String griddata, String griddata2, String jbxx, Map<String, String> s,String threadId,String threadId1,String threadId2) throws Exception;

    public String getContactListData(String id);

}
