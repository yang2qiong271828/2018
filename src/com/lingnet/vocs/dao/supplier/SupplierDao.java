/**
 * @SupplierDao.java
 * @com.lingnet.vocs.dao.supplier
 * @Description：
 * 
 * @author yangqiong
 * @copyright  2018
 * @version V
 * @since 2018年4月14日
 */

package com.lingnet.vocs.dao.supplier;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Supplier;

/**
 * 
 * @ClassName: SupplierDao 
 * @Description: TODO 
 * @author yangqiong
 * @date 2018年4月14日  14:32:19
 *
 */

public interface SupplierDao extends BaseDao<Supplier, String> {

	public List<Contacts> getContactsBySupplierId(String partnerId);
	
	public Pager getDataList(Pager pagerm,String json,String areaId,String key);
	
	public List getAreaDatalist(String partnerId);
	
	public List getType();

	public Supplier findBySupplierId(String id);

	public Supplier findByPartnerid(String partnerid);
}
