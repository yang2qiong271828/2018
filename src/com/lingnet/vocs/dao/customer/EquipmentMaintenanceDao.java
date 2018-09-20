package com.lingnet.vocs.dao.customer;

import java.text.ParseException;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.EquipmentMaintenance;

public interface EquipmentMaintenanceDao extends
		BaseDao<EquipmentMaintenance, String> {

	public List<Object[]> getListDataEm(String name , String code);
	
	
	public Pager getListData(Pager pager,String name , String strDate, String endDate,String partnerId,String eqId) throws ParseException;

}
