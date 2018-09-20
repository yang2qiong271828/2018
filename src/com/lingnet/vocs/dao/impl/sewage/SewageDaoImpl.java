package com.lingnet.vocs.dao.impl.sewage;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.sewage.SewageDao;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.entity.SewageBusiness;
import com.lingnet.vocs.entity.SewagePermit;
import com.lingnet.vocs.entity.SewageReport;
import com.lingnet.vocs.entity.SewageSituation;
import com.lingnet.vocs.entity.SewageTax;

/**
 * 
 * @ClassName: SewageDaoImpl
 * @Description: TODO
 * @author lyz
 * @date 2017年10月9日 下午4:43:45
 *
 */
@Repository("sewageDao")
public class SewageDaoImpl extends BaseDaoImplInit<Sewage, String> implements SewageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Contacts> getContactsBySewageId(String partnerId) {
		String sql = "select * from CONTACTS where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Contacts.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SewageSituation> getSituationBySewageId(String partnerId) {
		String sql = "select * from sewage_situation where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(SewageSituation.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SewageTax> getSewageTaxBySewageId(String partnerId, String type) {
		String sql = "select * from sewage_tax where partnerId='" + partnerId + "' and type='" + type + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(SewageTax.class);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SewageReport> getSewageReportBySewageId(String partnerId) {
		String sql = "select * from sewage_report where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(SewageReport.class);
		return query.list();
	}


	@Override
	public SewageBusiness getBusinessBySewageId(String partnerId) {
		String sql = "select * from sewage_business where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(SewageBusiness.class);
		List<SewageBusiness> list = query.list();
		if (list.size() > 0) {
			return (SewageBusiness) list.get(0);
		}
		return null;
	}
	

	@Override
	public SewagePermit getPermitBySewageId(String partnerId) {
		String sql = "select * from sewage_permit where partnerId='" + partnerId + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(SewagePermit.class);
		List<SewagePermit> list = query.list();
		if (list.size() > 0) {
			return (SewagePermit) list.get(0);
		}
		return null;
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
				"  SELECT  	s.id,s.code, s.pwname,s.dlwz,s.pwtype,s.produce,s.pfnd,s.fl,s.pfwz,s.need,s.starttime,s.ystime,s.governance,s.hbzlqy,s.hbzlgy,s.hbzljh,  ");
		sql.append("  	b.NAME name1,                                   ");
		sql.append("  	r.NAME name2,                                   ");
		sql.append("  	e.NAME name3,                                   ");
		sql.append(
				"  	c.name, c.email,c.gender,c.maincontact,c.phone,c.title,c.department,s.ydistrict,s.latitude,s.longitude  ");
		sql.append("  FROM                                        ");
		sql.append("  	sewage s                                  ");
		sql.append("  LEFT JOIN CONTACTS c ON s.id = c.partnerid and maincontact='1'  ");
		sql.append("  LEFT JOIN B_AREA b ON s.province = b.ID     ");
		sql.append("  LEFT JOIN B_AREA r ON s.city = r.ID         ");
		sql.append("  LEFT JOIN B_AREA e ON s.cdistrict = e.ID  where 1=1  ");
		if (StringUtils.isNotEmpty(areaId) && !areaId.equals("0") && !areaId.equals("null")) {
			sql.append("  and (s.province='" + areaId + "' or s.city='" + areaId + "' or s.cdistrict='" + areaId
					+ "'  )  ");
		}
		if (map != null && !map.isEmpty()) {
			if (StringUtils.isNotEmpty(map.get("pwname")) && !map.get("pwname").equals("null")) {
				sql.append("  and  s.pwname like '%" + map.get("pwname") + "%'  ");
			}
			if (StringUtils.isNotEmpty(map.get("governance")) && !map.get("governance").equals("null")) {
				sql.append("  and  s.governance = '" + map.get("governance") + "'  ");
			}
		}
		if (StringUtils.isNotEmpty(key) && !key.equals("null")) {
			sql.append("  and  s.pwname like '%" + key + "%'  ");
		}
		sql.append("   order by s.createdate desc       ");
		return findPagerBySql(pager, sql.toString());
	}

	@Override
	public List getAreaDatalist(String partnerId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"  SELECT s.id,s.pwname,s.governance FROM	sewage  s LEFT JOIN B_AREA b ON s.province = b.id LEFT JOIN B_AREA r ON s.city = r.id LEFT JOIN B_AREA a ON s.cdistrict = a.ID where 1=1   ");
		if (partnerId != null && !partnerId.equals("0")) {
			sql.append(" and s.province ='" + partnerId + "' or s.city ='" + partnerId + "' or s.cdistrict='"
					+ partnerId + "' ");
		}
		sql.append("  order by s.governance desc  ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}

	/**
	 * 获取排污类型
	 */
	@Override
	public List<Object[]> getPwType() {
		StringBuilder sql = new StringBuilder();
		sql.append("  select pwtype from sewage   ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public List getType() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"    SELECT pwtype from sewage where pwtype is not null and pwtype <> '' GROUP BY pwtype  order  by count(pwtype) DESC         ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}
	
	
	
	
	
}
