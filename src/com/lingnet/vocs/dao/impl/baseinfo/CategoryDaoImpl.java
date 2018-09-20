package com.lingnet.vocs.dao.impl.baseinfo;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.baseinfo.CategoryDao;
import com.lingnet.vocs.entity.Category;

/**
 * 行业类别
 * @ClassName: CategoryDaoImpl 
 * @Description: TODO 
 * @author wsx
 * @date 2016-3-11 上午10:45:51 
 *
 */
@Repository("categoryDao")
public class CategoryDaoImpl  extends BaseDaoImplInit<Category, String> implements CategoryDao{

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<HashMap<String, Object>> findCategory(
//			HashMap<Object, Object> map) {
//		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT id,name,CATEGORY_CODE,HOT_FLAG from bs_category ");
//		sql.append(" where 1=1 and STOPFLAG=0 ");
//		if (map.get("id") != null && !"".equals(map.get("id"))) {
//			sql.append(" and BASEID='" + map.get("id") + "' ");
//		}
//		if (map.get("flg") != null && !"".equals(map.get("flg"))) {
//			sql.append(" and HOT_FLAG=1 ");
//			sql.append(" and id in (select CATEGORY_ID from qz_circle) ");
//		}
//		
//		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
//		List<Object[]> beans = query.list();
//		for (Object[] ob : beans) {
//			String[] bean = LingUtil.objectToString(ob);
//			HashMap<String, Object> mp = new HashMap<String, Object>();
//			mp.put("id", bean[0]);
//			mp.put("name", bean[1]);
//			mp.put("categorycode", bean[2]);
//			mp.put("hotflag", bean[3]);
//			dataList.add(mp);
//		}
//		return dataList;
//		
//	}
	
}
