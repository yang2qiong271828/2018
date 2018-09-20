package com.lingnet.vocs.dao.equipment;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Machine;

public interface EquipmentUseDao extends BaseDao<Machine, String>{

    
    public List<Object[]> getList(String sbmc,String gylx,String sbcs,String id,String start,String count,String isrm);

	public Pager getData(Pager pager);

	public List<Machine> findBySupplierId(String id);
}
