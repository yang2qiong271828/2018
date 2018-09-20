package com.lingnet.vocs.dao.impl.atlas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.atlas.AtlasDao;
import com.lingnet.vocs.entity.GpsAtlas;

@Repository("atlasDao")
public class AtlasDaoImpl extends BaseDaoImpl<GpsAtlas, String> implements AtlasDao {

    @Resource(name = "toolUtil")
    private ToolUtil tool;
    
	 @SuppressWarnings("rawtypes")
	@Override
	    public String findContractApproveFlag(String userName) {
	        String sql = "SELECT DISTINCT * from"
	                + " (SELECT r.ID,r.RESOURCENAME,r.RESOURCEURL,r.DESCRIPTION,r.PRESOURCE,r.IMGVALUE"
	                + ",r.MODULEID,r.STATE,r.sortorder" + " FROM Qx_Users u"
	                + " JOIN qx_user_role ur ON u.ID = ur.USER_ID"
	                + " JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID"
	                + " JOIN qx_resource r ON r.ID = rr.RESOURCE_ID"
	                + " WHERE u.USERNAME = '"+userName+"')"
	                + " as bm where  bm.STATE='1'"
	                + " and bm.RESOURCENAME like '%异常报警信息%' order by bm.sortorder";
	        SQLQuery query = this.getSession().createSQLQuery(sql);
	        List l = query.list();
	        if (l.size() > 0) {
	            return "true";
	        } else {
	            return "false";
	        }
	    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String searchsbdt(String partnerId, String eqId, String sn) {
        List<HashMap> mapList = new ArrayList<HashMap>();
        StringBuffer sql = new StringBuffer();
        if (eqId != null && !"".equals(eqId)) {
            HashMap map = new HashMap();
            sql.append(" SELECT a.Id,a.Imgpath,a.name,a.equipment_code,a.equipment_type,a.Model,a.Air_Volume,a.device_identification,b.gpslat, b.gpslon,b.sn  FROM EQUIPMENT a, ");
            sql.append(" ( SELECT TOP 1 sn, gpslat, gpslon FROM gps_atlas WHERE sn = '"+sn+"' ORDER BY time desc ) b");
            sql.append(" WHERE a.gps_sn = b.sn");
            sql.append(" and a.id = '"+eqId+"'");
            SQLQuery query = this.getSession().createSQLQuery(sql.toString());
            List<Object[]> list = query.list();
            if(list.size()>0){
                map.put("id", list.get(0)[0]);// 设备id
                map.put("img", list.get(0)[1]);
                map.put("qyxxmc", list.get(0)[2]);// 设备名称
                map.put("code", list.get(0)[3]);// 设备编号
                String type1 = tool.getDataDicByCodeAndVal("shebei", list.get(0)[4].toString());
                if (type1 != null) {
                    map.put("type1", type1);// 设备类型
                }
                map.put("model", list.get(0)[5]);// 设备型号
                map.put("air", list.get(0)[6]);// 风量
                map.put("device",  list.get(0)[7]);// 标识码
                map.put("spanLat", list.get(0)[8]);
                map.put("spanLng", list.get(0)[9]);
                map.put("sn", list.get(0)[10]);
                map.put("type", "2");
                mapList.add(map);
            }
            
            
        }else{
            sql.append(" SELECT Id,Imgpath,name,equipment_code,equipment_type,Model,Air_Volume,device_identification, gps_sn FROM EQUIPMENT  where 1 = 1 ");
            if (StringUtils.isNotEmpty(partnerId)) {
                sql.append(" and (owner = '" + partnerId + "' or equipment_user = '" + partnerId + "')");
            }
            SQLQuery query = this.getSession().createSQLQuery(sql.toString());
            List<Object[]> list = query.list();
            for(int i = 0 ; i<list.size();i++){
                HashMap map = new HashMap();
                map.put("id", list.get(i)[0]);// 设备id
                map.put("img", list.get(i)[1]);
                map.put("qyxxmc", list.get(i)[2]);// 设备名称
                map.put("code", list.get(i)[3]);// 设备编号
                String type1 = tool.getDataDicByCodeAndVal("shebei", list.get(i)[4].toString());
                if (type1 != null) {
                    map.put("type1", type1);// 设备类型
                }
                map.put("model", list.get(i)[5]);// 设备型号
                map.put("air", list.get(i)[6]);// 风量
                map.put("device",  list.get(i)[7]);// 标识码
                map.put("sn",  list.get(i)[8]);// sn
                map.put("type", "2");
                
                StringBuffer sql2 = new StringBuffer();
                sql2.append(" SELECT TOP 1 sn, gpslat, gpslon FROM gps_atlas WHERE sn = '"+list.get(i)[8]+"' ORDER BY time desc ");
                SQLQuery query2 = this.getSession().createSQLQuery(sql2.toString());
                List<Object[]> list2 = query2.list();
                if(list2.size()>0){
                    map.put("spanLat", list2.get(0)[1]);
                    map.put("spanLng", list2.get(0)[2]);
                }
                mapList.add(map);
            }
           
        }
        return JsonUtil.Encode(mapList);
    }

    
    
    public ToolUtil getTool() {
        return tool;
    }

    public void setTool(ToolUtil tool) {
        this.tool = tool;
    }


}
