package com.lingnet.vocs.dao.impl.supplier;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.supplier.SupplierDao;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.entity.Supplier;


/**
 * 
 * @ClassName: SupplierDaoImpl
 * @Description: TODO
 * @author yangqiong
 * @date 2018年4月14日  14:38:07
 *
 */
@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDaoImplInit<Supplier, String> implements SupplierDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Contacts> getContactsBySupplierId(String partnerId) {
		String sql = "select * from CONTACTS where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Contacts.class);
		return query.list();
	}

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
				"  SELECT  	s.id, s.supplier_qyname, s.supplier_type, s.supplier_desc, s.supplier_shhxydm, "
				+ "s.supplier_zzjgdm, s.suppliier_zcnumber, s.supplier_openstatus, s.supplier_qyzclx, s.supplier_newdate, "
				+ "s.supplier_openlimitdate, s.supplier_issuedate, s.supplier_fddbr, s.supplier_qyzczb, s.supplier_qydjjg, "
				+ "s.supplier_qyaddress, s.supplier_businessscope, s.supplier_province, s.supplier_city, s.supplier_xzdistrict, "
				+ "s.supplier_industrialdistrict,  ");
		sql.append("  	b.NAME name1,                                   ");
		sql.append("  	r.NAME name2,                                   ");
		sql.append("  	e.NAME name3,                                   ");
		sql.append(
				"  	c.name, c.email,c.gender,c.maincontact,c.phone,c.title,c.department,s.latitude,s.longitude  ");
		sql.append("  FROM                                        ");
		sql.append("  	supplier s                                  ");
		sql.append("  LEFT JOIN CONTACTS c ON s.id = c.partnerid and maincontact='1'  ");
		sql.append("  LEFT JOIN B_AREA b ON s.supplier_province = b.ID     ");
		sql.append("  LEFT JOIN B_AREA r ON s.supplier_city = r.ID         ");
		sql.append("  LEFT JOIN B_AREA e ON s.supplier_xzdistrict = e.ID  where 1=1  ");
		if (StringUtils.isNotEmpty(areaId) && !areaId.equals("0") && !areaId.equals("null")) {
			sql.append("  and (s.supplier_province='" + areaId + "' or s.supplier_city='" + areaId + "' or s.supplier_xzdistrict='" + areaId
					+ "'  )  ");
		}
		sql.append("   order by s.createdate desc       ");
		
		return findPagerBySql(pager, sql.toString());
	}

	@Override
	public List getAreaDatalist(String partnerId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"  SELECT s.supplier_id, s.supplier_qyname FROM	supplier  s LEFT JOIN B_AREA b ON s.supplier_province = b.id LEFT JOIN B_AREA r ON s.supplier_city = r.id LEFT JOIN B_AREA a ON s.supplier_xzdistrict = a.ID where 1=1   ");
		if (partnerId != null && !partnerId.equals("0")) {
			sql.append(" and s.supplier_province ='" + partnerId + "' or s.supplier_city ='" + partnerId + "' or s.supplier_xzdistrict='"
					+ partnerId + "' ");
		}
		sql.append("  order by s.governance desc  ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public List getType() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"    SELECT supplier_type from supplier where supplier_type is not null and supplier_type <> '' GROUP BY supplier_type  order  by count(supplier_type) DESC         ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public Supplier findBySupplierId(String id) {
		String sql = "select * from supplier where id='" + id + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Supplier.class);
		List<Supplier> list = query.list();
		if (list.size() > 0) {
			return (Supplier) list.get(0);
		};
		
		return null;
	}

	@Override
	public Supplier findByPartnerid(String partnerid) {
		String sql = "select * from supplier where id='" + partnerid + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Supplier.class);
		List<Supplier> list = query.list();
		if (list.size() > 0) {
			return (Supplier) list.get(0);
		};
		
		return null;
	}
}
