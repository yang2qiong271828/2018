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

package com.lingnet.vocs.dao.scase;



import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.SupplierCase;

/**
 * 
 * @ClassName: SupplierDao 
 * @Description: TODO 
 * @author lixiuyao
 * @date 2018年4月14日  14:32:19
 *
 */

public interface SupplierCaseDao extends BaseDao<SupplierCase, String> {

	Pager getDataList(Pager pager, String searchData, String areaId, String key);

	List<String> findByClassify(String id);

	List<SupplierCase> findByPicture(String id, String obj);


	

	
}
