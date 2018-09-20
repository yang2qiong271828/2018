package com.lingnet.vocs.service.supplier;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Supplier;
import com.lingnet.vocs.entity.SupplierCase;


/**
 * 供应商（治理厂家&监测厂家）
 * 
 * @ClassName: SewageService
 * @Description: TODO
 * @author yangqiong
 * @date 2018年4月14日   15:07:27
 *
 */
public interface SupplierService extends BaseService<Supplier, String> {

	public String getData(Pager pager, String searchData, String areaId, String key);

	public String saveOrUpdate(Supplier supplier, String json) throws Exception;

	@SuppressWarnings("rawtypes")
	public HashMap getContactsBySupplierId(String partnerId);

	public String search(String name);

	public String saveOrUpdatezljh(Supplier supplier);

	public String getAreaDatalist(String partnerId);

	@SuppressWarnings("rawtypes")
	public List getType();

	public String saveImportInfos(String endSuffix, File uploadFile) throws Exception;

	public HSSFWorkbook exporta(String name, String formdata);

	public Supplier getSupplierBySupplierId(String id);

	public Supplier findByPartnerid(String partnerid);

	

	

	
}
