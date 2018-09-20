package com.lingnet.vocs.dao.impl.monit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.monit.MonitoringDao;
import com.lingnet.vocs.entity.ConstantData;
import com.lingnet.vocs.entity.Equipment;

@Repository("monitoringDao")
public class MonitoringDaoImpl extends BaseDaoImplInit<ConstantData, String>
        implements MonitoringDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findConstantData(String boxId, String uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sql = new StringBuffer();
        Calendar  calendar= Calendar.getInstance();
        String tablename = "constant_data_";
        int month = calendar.get(Calendar.MONTH)+1;
        if(month<10){
            tablename = tablename + calendar.get(Calendar.YEAR)+"0"+(calendar.get(Calendar.MONTH)+1);
        }else{
            tablename = tablename + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)+1);
        }
        sql.append(" SELECT distinct TOP 5 ");
        //sql.append(" Fbox_uid,max(value) value,CONVERT(varchar(16), time_stamp, 120) time_stamp ");
        sql.append(" Fbox_uid,value,CONVERT(varchar(16), time_stamp, 120) time_stamp ,flag  ");
        sql.append(" FROM V_Constant  ");
        sql.append(" where fbox_uid='" + boxId + "' ");
        sql.append(" and  CONVERT(varchar(50), time_stamp, 120)<'"+sdf.format(new Date())+"' ");
        sql.append(" and name='" + uid + "' ");//正式数据使用，测试数据注释，方便返回数据
        //sql.append(" group by CONVERT(varchar(16), time_stamp, 120),Fbox_uid ");
        sql.append(" order by time_stamp desc ");
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.toString());
        // 进行数据查询
        List<Object[]> resultList = sqlQuery.list();
        return resultList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findConstantDataNew(String boxId, String uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sql = new StringBuffer();
        Calendar  calendar= Calendar.getInstance();
        String tablename = "constant_data_";
        int month = calendar.get(Calendar.MONTH)+1;
        if(month<10){
            tablename = tablename + calendar.get(Calendar.YEAR)+"0"+(calendar.get(Calendar.MONTH)+1);
        }else{
            tablename = tablename + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)+1);
        }
        sql.append(" SELECT distinct TOP 5 ");
        //sql.append(" Fbox_uid,max(value),CONVERT(varchar(16), time_stamp, 120) time_stamp ,flag  ");
        sql.append(" Fbox_uid,value,CONVERT(varchar(16), time_stamp, 120) time_stamp ,flag  ");
        sql.append(" FROM V_Constant  ");
        sql.append(" where fbox_uid='" + boxId + "' ");
        sql.append(" and  CONVERT(varchar(16), time_stamp, 120)<'"+sdf.format(new Date())+"' ");
        sql.append(" and name='" + uid + "' ");//正式数据使用，测试数据注释，方便返回数据
        //sql.append(" group by CONVERT(varchar(16), time_stamp, 120),Fbox_uid ,flag ");
        sql.append(" order by time_stamp desc ");
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.toString());
        // 进行数据查询
        List<Object[]> resultList = sqlQuery.list();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findLastData(String boxId, String uid) {
        StringBuffer sql = new StringBuffer();
        Calendar  calendar= Calendar.getInstance();
        String tablename = "constant_data_";
        int month = calendar.get(Calendar.MONTH)+1;
        if(month<10){
            tablename = tablename + calendar.get(Calendar.YEAR)+"0"+(calendar.get(Calendar.MONTH)+1);
        }else{
            tablename = tablename + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)+1);
        }
        /*sql.append(" SELECT TOP 1 ");
        sql.append(" name,value,CONVERT(varchar(100), time_stamp, 24),quality,flag ");
        sql.append(" FROM "+tablename+"  ");
        sql.append(" where fbox_uid='" + boxId + "' ");
        sql.append(" and uid='" + uid + "' ");
        sql.append(" order by time_stamp desc ");*/
        sql.append(" SELECT TOP 1 ");
        sql.append(" name,value,CONVERT(varchar(100), time_stamp, 24),quality,flag ");
        sql.append(" FROM V_Constant  ");
        sql.append(" where fbox_uid='" + boxId + "' ");
        sql.append(" and name='" + uid + "' order by time_stamp desc");//正式系统使用
       // sql.append(" order by newid()");//测试数据，保证有数据返回
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.toString());
        // 进行数据查询
        List<Object[]> resultList = sqlQuery.list();
        if(resultList!=null&&resultList.size()>0){
            return resultList.get(0);
        }else{
            return null;
        }
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HashMap<String, Object>> findPagerData(Pager pager,
            HashMap<String, Object> map) {
        StringBuffer sqlBuffer = new StringBuffer();
        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
        //计算分页开始条数
        Integer start = pager.getPageNumber()*pager.getPageSize();
        sqlBuffer.append(" select top "+pager.getPageSize()+" v.id,Fbox_uid,time_stamp,value,quality,e.air_volume,e.name from V_Constant v ");
        sqlBuffer.append(" left join EQUIPMENT e on e.equipment_code = v.fbox_uid ");
        sqlBuffer.append("  where 1=1  ");
        if(map.get("equipmentCode")!=null&&!"".equals(map.get("equipmentCode").toString())){
            sqlBuffer.append("  and v.fbox_uid='"+map.get("equipmentCode")+"' ");
        }
        if(map.get("owner")!=null&&!"".equals(map.get("owner").toString())){
            sqlBuffer.append("  and e.owner='"+map.get("owner")+"'");
        }
        if(map.get("start")!=null&&!"".equals(map.get("start").toString())){
            sqlBuffer.append("  and v.time_stamp>= '"+map.get("start")+"'");
        }
        if(map.get("end")!=null&&!"".equals(map.get("end").toString())){
            sqlBuffer.append("  and v.time_stamp<= '"+map.get("end")+"'");
        }
        if(map.get("navVal")!=null&&!"".equals(map.get("navVal").toString())){
            sqlBuffer.append("  and v.name= '"+map.get("navVal")+"'");
        }
        sqlBuffer.append(" and v.id in( ");
        sqlBuffer.append(" SELECT top "+pager.getPageSize()+" ID FROM ( ");
        sqlBuffer.append(" SELECT top "+start+" ID, createdate from V_Constant order by createdate desc ");
        sqlBuffer.append(" ) w order by w.createdate asc  ) ");
        sqlBuffer.append(" order by v.createdate desc ");
        
        //获取总记录数
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(id) from V_Constant v where 1=1");
        if(map.get("start")!=null&&!"".equals(map.get("start").toString())){
            sqlCount.append("  and v.time_stamp>= '"+map.get("start")+"'");
        }
        if(map.get("end")!=null&&!"".equals(map.get("end").toString())){
            sqlCount.append("  and v.time_stamp<= '"+map.get("end")+"'");
        }
        if(map.get("navVal")!=null&&!"".equals(map.get("navVal").toString())){
            sqlCount.append("  and v.name= '"+map.get("navVal")+"'");
        }
        if(map.get("equipmentCode")!=null&&!"".equals(map.get("equipmentCode").toString())){
            sqlCount.append("  and v.fbox_uid='"+map.get("equipmentCode")+"' ");
        }
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sqlCount.toString());
        Integer total = Integer.parseInt(sqlQuery.list().get(0).toString());
        pager.setTotalCount(total);
        SQLQuery sqlQuery1 = this.getSession().createSQLQuery(sqlBuffer.toString());
        List<Object[]> obs = sqlQuery1.list();
        SimpleDateFormat from = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        if(obs!=null&&obs.size()>0){
            //循环数据
            for(Object[] ob:obs){
                HashMap<String, Object> mp = new HashMap<String, Object>();
                mp.put("id", ob[0]);
                mp.put("equipmentCode", ob[1]);
                mp.put("timeStamp", from.format(ob[2]));
                mp.put("content", ob[3]);
                mp.put("quality", ob[4]);
                mp.put("standard", ob[5]);
                mp.put("name", ob[6]);
                
                mapList.add(mp);
            }
        }
        return mapList;
    }

	@Override
	public String getDuData(String boxId) {
		Equipment ment = (Equipment) this.getSession().createCriteria(Equipment.class).add(Restrictions.eq("equipmentCode", boxId)).uniqueResult();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT                                                  ");
		sql.append(" 	*                                                    ");
		sql.append(" FROM                                                    ");
		sql.append(" 	V_constant con,                            ");
		sql.append(" 	(                                                    ");
		sql.append(" 		SELECT                                           ");
		sql.append(" 			max(time_stamp) date,                        ");
		sql.append(" 			NAME                                         ");
		sql.append(" 		FROM                                             ");
		sql.append(" 			V_constant                         ");
		sql.append(" 		WHERE                                            ");
		sql.append(" 			Fbox_uid IN (                                ");
		sql.append(" 				SELECT                                   ");
		sql.append(" 					plc_identification_code              ");
		sql.append(" 				FROM                                     ");
		sql.append(" 					EQUIPMENT                            ");
		sql.append(" 				WHERE                                    ");
		sql.append(" 					plc_identification_code IS NOT NULL  ");
		sql.append(" 			)                                            ");
		sql.append(" 		GROUP BY                                         ");
		sql.append(" 			NAME                                         ");
		sql.append(" 	) t                                                  ");
		sql.append(" WHERE                                                   ");
		sql.append(" 	t. NAME = con. NAME                                  ");
		sql.append(" AND t.date = con.time_stamp                             ");
		sql.append(" AND con.Fbox_uid = '"+ment.getPlcIdentificationCode()+"' ");
		sql.append("  order by t.name asc ");
		return JsonUtil.Encode(this.getSession().createSQLQuery(sql.toString()).list());
	}

}
