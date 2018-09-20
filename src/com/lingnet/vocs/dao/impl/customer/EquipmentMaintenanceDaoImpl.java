package com.lingnet.vocs.dao.impl.customer;

import java.text.ParseException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.customer.EquipmentMaintenanceDao;
import com.lingnet.vocs.entity.EquipmentMaintenance;

@Repository("equipmentMaintenanceDao")
public class EquipmentMaintenanceDaoImpl extends
		BaseDaoImplInit<EquipmentMaintenance, String> implements
		EquipmentMaintenanceDao {

	@SuppressWarnings("unchecked")
    @Override
	public List<Object[]> getListDataEm(String name, String code) {
		String sql = "select id,equipment_code,equipment_type,name,model from EQUIPMENT where 1=1";
		if(name != null && !"".equals(name) && name != "null"){
			sql += " and name like '%"+name+"%'";
		}
		if(code != null && !"".equals(code) && code != "null"){
			sql += "  and equipment_code = '"+code+"'";
		}
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public Pager getListData(Pager pager,String name, String strDate,String endDate,String partnerId,String eqId) throws ParseException {
		String sql = "select id,equipment_code,maintainance_content,maintainance_person,maintainance_date from equipment_maintenance where 1=1 and IS_DELETED = '0'";
		if(name != null && !"".equals(name) && name != "null"){
			sql += " and equipment_code like '%"+name+"%'";
		}
		if(partnerId != null && !"".equals(partnerId) && partnerId != "null"){
			sql += " and (partner_id = '"+partnerId+"' or owner = '"+partnerId+"')";
		}
		if(eqId != null && !"".equals(eqId) && eqId != "null"){
			sql += " and equipment_id = '"+eqId+"'";
		}
		if(strDate != null && !"".equals(strDate)){
			sql += " and maintainance_date >= '"+strDate+" 00:00:00'";
		}
		if(endDate != null && !"".equals(endDate)){
			sql += " and maintainance_date <= '"+endDate+" 23:59:59'"; 				
		}
		sql += " ORDER BY createdate desc";
		return findPagerBySql(pager, sql);
	}

}
