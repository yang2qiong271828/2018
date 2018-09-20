package com.lingnet.vocs.service.scase;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.SupplierCase;

public interface ScaseService extends BaseService<SupplierCase, String> {

	

	Object getContactsBySupplierId(String partnerId);

	String getData(Pager pager, String searchData, String areaId, String key);


	String saveOrUpdate(SupplierCase supplierCase);

	
	List<HashMap> findByClassify(String id);

	

	
	
}
