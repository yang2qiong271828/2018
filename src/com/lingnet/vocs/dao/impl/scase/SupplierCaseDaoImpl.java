package com.lingnet.vocs.dao.impl.scase;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.scase.SupplierCaseDao;
import com.lingnet.vocs.entity.SupplierCase;



/**
 * 
 * @ClassName: SupplierDaoImpl
 * @Description: TODO
 * @author lixiuyao
 * @date 2018年4月14日  14:38:07
 *
 */
@Repository("supplierCaseDao")
public class SupplierCaseDaoImpl extends BaseDaoImplInit<SupplierCase, String> implements SupplierCaseDao {

	@Override
	public Pager getDataList(Pager pager, String json, String areaId, String key) {
		Map<String, String> map = JsonUtil.parseProperties(json);
		if (map != null) {
			if (map.get("areaId") != null) {
				areaId = map.get("areaId");
			}
		}
		StringBuilder sql = new StringBuilder();
		sql.append(
				"  SELECT  	s.id,s.classify, s.main_image,s.partnerid,");
		sql.append(
				"  c.supplier_qyname");
		sql.append("  FROM                                        ");
		sql.append("  	supplier_case s                                  ");
		sql.append("  left join supplier  c on c.id=s.partnerid      ");
		sql.append("   order by s.createdate desc       ");
		
		return findPagerBySql(pager, sql.toString());
	}

	@Override
	public List<String> findByClassify(String id) {
		String sql = "SELECT classify FROM supplier_case   where partnerid = ? GROUP BY classify; ";
		SQLQuery query =getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.addScalar("classify", Hibernate.STRING);
		List<String> list = query.list();
		return list;
	}

	@Override
	public List<SupplierCase> findByPicture(String id,String obj) {
		String sql = "SELECT * FROM supplier_case WHERE partnerid = ? and classify= ? ; ";
		SQLQuery query =getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.setParameter(1, obj);
		query.addEntity(SupplierCase.class);
		List<SupplierCase> list = query.list();
		return list;
	}
    
	

	
}
