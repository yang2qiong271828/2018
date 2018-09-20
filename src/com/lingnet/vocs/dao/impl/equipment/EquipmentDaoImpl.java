package com.lingnet.vocs.dao.impl.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.equipment.EquipmentDao;
import com.lingnet.vocs.entity.Equipment;

/**
 * @ClassName: EquipmentDaoImpl
 * @Description: TODO
 * @author wanl
 * @date 2017年6月29日 下午6:22:32
 * 
 */

@Repository("equipmentDao")
public class EquipmentDaoImpl extends BaseDaoImplInit<Equipment, String>
        implements EquipmentDao {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String getAirVolumeByEquipment(HashMap<String, Object> map) {

        Map result = new HashMap();

        String airVolume = (String) map.get("airVolume");

        String equipmentType = (String) map.get("equipmentType");

        String sql = " select name,model,working_medium,adsorption_capacity,"
                + " position,weight,temperature,handlingLoad,carbon_type,"
                + " carbon_spec,carbon_manu,carbon_capacity,eqLength,eqWidth,"
                + " eqHeight,export_concentration,pressure_difference,temperature_limit,"
                + " CONVERT(varchar(10), manufactureDate, 120) as manufactureDate,"
                + " CONVERT(varchar(10), productionDate, 120) as productionDate,"
                + " CONVERT(varchar(10), carbonLoad_date, 120) as carbonLoad_date"
                + " from equipment_base where air_volume = '" + airVolume
                + "' and equipment_type = '" + equipmentType + "'";

        Map<String, Object> map2 = getJdbcTemplate().queryForMap(sql);
        result.put("data", map2);

        return JsonUtil.Encode(result);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String getAirVolume(HashMap<String, Object> map) {

        Map result = new HashMap();

        String equipmentType = (String) map.get("equipmentType");

        String sql = "select air_volume from equipment_base where equipment_type = '"
                + equipmentType + "'";

        SQLQuery query = this.getSession().createSQLQuery(sql);
        List<Object> list = query.list();

        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < list.size(); i++) {

            HashMap map2 = new HashMap();

            String s = (String) list.get(i);

            map2.put("equipment", s);

            arrayList.add(map2);
        }

        result.put("data", arrayList);
        result.put("totalCount", arrayList.size());

        return JsonUtil.Encode(result);

    }

    @Override
    public Pager getEqByconData(Pager pager, String partnerId) {
        String sql = " (owner = '"
                + partnerId
                + "' or equipment_user = '"
                + partnerId
                + "' or handlersId ='"
                + partnerId
                + "')"
                + " and  id in ( select equipmentId from TRANSFER  where contractId in"
                + " ( select id from contract where verify_status = '4') ) ";
        pager = super.findPager(pager, Restrictions.sqlRestriction(sql));
        return pager;
    }

    @Override
    public Pager findPagerEquipment(Map<String, String> m, Pager pager,
            String partnerId) {
        String sql = "select e.id,e.equipment_code,e.equipment_type,e.name,e.model,e.manufactureDate"// 1~6
                + ",e.productionDate,e.owner,e.equipment_user,e.working_medium,e.position"// 7~11
                + ",e.weight,e.temperature,e.handlingLoad,e.carbon_type,e.carbon_spec,e.carbon_manu"// 12~17
                + ",e.carbon_capacity,e.carbonLoad_date,e.createdate,e.modifydate,e.eqLength"// 18~22
                + ",e.eqWidth,e.eqHeight,e.handlersId,e.air_volume,e.export_concentration"// 23~27
                + ",e.pressure_difference,e.device_identification,e.temperature_limit"// 28,29,30
                + ",e.adsorption_capacity,e.imgpath,e.gps_sn"// 31,32,33
                + " from EQUIPMENT e where e.owner = '" + partnerId + "'";
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("equipmentCode"))) {
                sql += " and e.equipment_code like '%" + m.get("equipmentCode")
                        + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("prodateStart"))) {
                sql += " and e.productionDate > '" + m.get("prodateStart")
                        + "'";
            }
            if (StringUtils.isNotEmpty(m.get("prodateEnd"))) {
                sql += " and e.productionDate < '" + m.get("prodateEnd") + "'";
            }
        }
        sql += " order by e.createdate desc";
        pager = findPagerBySql(pager, sql);
        return pager;
    }

    @Override
    public String savePlc(String id, HashMap<String, Object> map) {

        String plcIdentificationCode = (String) map
                .get("plcIdentificationCode");

        String sql = "update equipment set plc_identification_code = '"
                + plcIdentificationCode + "'where id = '" + id + "'";

        getJdbcTemplate().update(sql);
        return "success";
    }
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getcd(Pager pager,String partnerId, String areaId,Map<String, String> m) {
		if(m != null){
			if(m.get("areaId") != null){
				areaId = m.get("areaId"); 
			}
		}
		StringBuilder sql= new StringBuilder();
		sql.append("  SELECT  DISTINCT e.id,e.name,e.model,e.equipment_code,e.equipment_user,e.handlersId,e.owner,e.equipment_type  ");
		sql.append("  FROM                                                           ");
		sql.append("  	EQUIPMENT e                                                  ");
		sql.append("  LEFT JOIN PARTNER p ON e.owner = p.id  LEFT JOIN TRANSFER tr ON tr.equipmentId = e.id LEFT JOIN CONTRACT con ON con.id = tr.contractId                    ");
		sql.append("  WHERE                    ");
		sql.append("  	(                                                            ");
		sql.append("  		owner = '"+partnerId+"'    ");
		sql.append("  		OR equipment_user = '"+partnerId+"'    ");
		sql.append("  	)                                                            ");
		if(StringUtils.isNotEmpty(areaId) && !areaId.equals("0")){
			sql.append("  and  (p.province='"+areaId+"' or p.city='"+areaId+"' or p.district='"+areaId+"') ");
		}
		if(m != null){
			 if (m.get("equipmentCode")!=null) {
	                String equipmentCode = m.get("equipmentCode");
	                sql.append( " and e.equipment_code like '%"+equipmentCode+"%'" );
	            }
			 if (m.get("beginDate")!=null) {
	               sql.append( " and  con.end_date  >= '"+m.get("beginDate")+"' " );
	            }
	            if (m.get("endDate")!=null) {
	              sql.append(" and con.end_date <= '"+m.get("endDate")+ "' ");
	            } 
		}
		return (List<Object[]>) findPagerBySql(pager, sql.toString()).getResult();
	}
}
