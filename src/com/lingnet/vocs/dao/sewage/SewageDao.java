/**
 * @PartnerDao.java
 * @com.lingnet.vocs.dao.partner
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.dao.sewage;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.entity.SewagePermit;
import com.lingnet.vocs.entity.SewageReport;
import com.lingnet.vocs.entity.SewageSituation;
import com.lingnet.vocs.entity.SewageTax;
/**
 * 
 * @ClassName: SewageDao 
 * @Description: TODO 
 * @author lyz
 * @date 2017年10月9日 下午4:43:06 
 *
 */

public interface SewageDao extends BaseDao<Sewage, String> {
	/**
	 * 
	 * @Title: getContactsByPartnerId 
	 * @param partnerId
	 * @return 
	 * List<Contacts> 
	 * @author lyz
	 * @since 2017年10月10日 V 1.0
	 */
	public List<Contacts> getContactsBySewageId(String partnerId);
	
	public List<SewageSituation> getSituationBySewageId(String partnerId);
	
	public List<SewageTax> getSewageTaxBySewageId(String partnerId,String type);

	public List<SewageReport> getSewageReportBySewageId(String partnerId);

	public SewageBusiness getBusinessBySewageId(String partnerId);
	
	public SewagePermit getPermitBySewageId(String partnerId);
	
	public Pager getDataList(Pager pagerm,String json,String areaId,String key);
	
	public List getAreaDatalist(String partnerId);
	
	public List<Object[]> getPwType();
	
	public List getType();

	

	
}
