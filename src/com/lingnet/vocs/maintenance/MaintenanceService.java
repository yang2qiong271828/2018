package com.lingnet.vocs.service.maintenance;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.RegularMaintenance;

@SuppressWarnings("rawtypes")
public interface MaintenanceService extends BaseService<RegularMaintenance, String>{
	void saveOrUpdate(RegularMaintenance   regularMaintenance) throws Exception;
}
