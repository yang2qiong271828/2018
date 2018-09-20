package com.lingnet.vocs.service.customer;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.EquipmentMaintenance;

public interface EquipmentMaintenanceService extends BaseService<EquipmentMaintenance, String> {

	public String saveOrUpdate(String formdata)throws Exception;
	
	public String getListDataEm(String name,String code); 
	
	public String getListData(Pager pager,String name,String strDate,String endDate,String partnerId,String eqId);
}
