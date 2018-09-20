package com.lingnet.vocs.dao.impl.equipment;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.equipment.EquipmentUseDao;
import com.lingnet.vocs.entity.Machine;
import com.lingnet.vocs.entity.SupplierCase;

@Repository("equipmentUseDao")
public class EquipmentUseDaoImpl extends BaseDaoImplInit<Machine, String>
implements EquipmentUseDao{

    @Override
    public List<Object[]> getList(String sbmc, String gylx, String sbcs, String id,
            String start, String count,String isrm) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select top "+count+" machine.id,equipmentCode,equipmentName,equipmentName,equipmentpicture,");
        //sql.append(" equipmentfirm,description,point,price,cslxr,csphone,trade,num,describe,partnerId,isrm from machine ");
       
        sql.append("  supplier_qyname as equipmentfirm,description,point,price,cslxr,csphone,trade,num,describe,partnerId,isrm from machine ");
       //修改sql,供应商数据从自己添加的操作
        sql.append(" left  join supplier s on s.id=machine.supplierid  ");
        
        sql.append(" where machine.id not in (select top (("+start+"-1)*"+count+")id from machine) "); 
        sql.append(" and status = '0' ");
        if (StringUtils.isNotEmpty(id) && !id.equals("null")) {
            sql.append("  and id='" + id + "'  ");
        }
        if (StringUtils.isNotEmpty(gylx) && !gylx.equals("null")) {
            sql.append("  and equipmentType = '"  + gylx + "'");
        }
        if (StringUtils.isNotEmpty(sbcs) && !sbcs.equals("null")) {
            sql.append("  and supplier_qyname = '" + sbcs + "'");
        }
        if (StringUtils.isNotEmpty(sbmc) && !sbmc.equals("null")) {
            sql.append("  and (equipmentName like '%" + sbmc + "%' or equipmentfirm like  '%" + sbmc + "%')");
        }
        if(StringUtils.isNotEmpty(isrm)){
            sql.append(" and isrm = '0' ");
        }
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

	@Override
	public Pager getData(Pager pager) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  m.id,m.equipmentCode,m.equipmentName,m.equipmentpicture,s.supplier_qyname as equipmentfirm,m.equipmentType,m.description,m.point,m.price,m.cslxr,m.csphone,m.trade,m.num,m.describe,m.partnerId,m.isrm,m.supplierid,m.status from machine m ");
	    sql.append(" LEFT join supplier s on s.id=m.supplierid  ");
		return findPagerBySql(pager, sql.toString());
	}

	@Override
	public List<Machine> findBySupplierId(String id) {
			String sql = "SELECT * FROM machine WHERE supplierid=?; ";
			SQLQuery query =getSession().createSQLQuery(sql);
			query.setParameter(0, id);
			query.addEntity(Machine.class);
			List<Machine> list = query.list();
			return list;
		
	}

}
