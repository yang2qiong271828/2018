package com.lingnet.vocs.service.sewage;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.entity.SewagePermit;

/**
 * 排污企业排污情况汇总
 * 
 * @ClassName: SewageService
 * @Description: TODO
 * @author lyz
 * @date 2017年10月9日 下午2:47:53
 *
 */
public interface SewageService extends BaseService<Sewage, String> {

	public String getData(Pager pager, String searchData, String areaId, String key);

	public String saveOrUpdate(Sewage sewage, String json, String business, String situation, String permit,
			String sewageTax,String sewageReprot) throws Exception;

	@SuppressWarnings("rawtypes")
	public HashMap getContactsBySewageId(String partnerId);

	public SewageBusiness getBusinessBySewageId(String partnerId);

	public SewagePermit getPermitBySewageId(String partnerId);

	public HashMap getSituationBySewageId(String partnerId);

	public HashMap getSewageTaxBySewageId(String partnerId, String type);
	
	public HashMap getSewageReportBySewageId(String partnerId);


	public String search(String name);

	public String saveOrUpdatezljh(Sewage sewage);

	public String getAreaDatalist(String partnerId);

	public String getPwType();

	@SuppressWarnings("rawtypes")
	public List getType();

	public String saveImportInfos(String endSuffix, File uploadFile) throws Exception;

	public HSSFWorkbook exporta(String name, String formdata);

}
