package com.lingnet.vocs.dao.impl.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.alarm.AbnormalAlarmDao;
import com.lingnet.vocs.entity.AbnormalAlarm;

@Repository("abnormalAlarmDao")
public class AbnormalAlarmDaoImpl extends
        BaseDaoImplInit<AbnormalAlarm, String> implements AbnormalAlarmDao {

    @Override
    public Pager getListData(Pager pager, String id, String partnerId) {
        Map<String, String> m = JsonUtil.parseProperties(pager.getSearchData());
        String sql = "";
        if (id != null && id != "" && StringUtils.isNotEmpty(id)) {
            sql = " select a.id,a.alarm_content,a.alarm_date,a.exception_type,a.handle_people,a.notice_people,"
                    + " a.processing_state, a.equipment_id,a.equipment_type_id,a.partner"
                    + " from abnormal_alarm a"
                    + " left join equipment b on a.equipment_id = b.id "
                    + " where equipment_id = '" + id + "'";
        } else {     
        	
        		 sql = " select a.id,a.alarm_content,a.alarm_date,a.exception_type,a.handle_people,a.notice_people,"
                         + " a.processing_state, a.equipment_id,a.equipment_type_id,a.partner "
                         + " from abnormal_alarm a"
                         + " left join equipment b on a.equipment_id = b.id "
                         + " where 1=1";
        	         
        }
        if (partnerId != null && !"1".equals(partnerId)) {
            sql += " and  a.partner = '" + partnerId + "'";
        }
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("exceptionType"))) {
                sql += " and a.exception_type like '%" + m.get("exceptionType") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("equipmentId"))) {
                sql += " and b.equipment_code like '%" + m.get("equipmentId") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("startTime"))) {
                sql += " and a.alarm_date >= '" + m.get("startTime") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("endTime"))) {
                sql += " and a.alarm_date <= '" + m.get("endTime") + "'";
            }
        }
        sql += " order by equipment_id desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }

    @Override
    public Pager getSbjgListData(Pager pager) {
        Map<String, String> m = JsonUtil.parseProperties(pager.getSearchData());
        String sql = "";
        sql = " select a.id,a.alarm_content,a.alarm_date,a.exception_type,a.handle_people,a.notice_people,"
                + " a.processing_state, a.equipment_id,a.equipment_type_id,a.partner "
                + " from abnormal_alarm a"
                + " left join equipment b on a.equipment_id = b.id "
                + " where 1=1 and  a.processing_state = '0'";
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("exceptionType"))) {
                sql += " and a.exception_type like '%" + m.get("exceptionType") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("equipmentId"))) {
                sql += " and b.equipment_code like '%" + m.get("equipmentId")  + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("startTime"))) {
                sql += " and a.alarm_date > '" + m.get("startTime") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("endTime"))) {
                sql += " and a.alarm_date < '" + m.get("endTime") + "'";
            }

        }
        sql += " order by equipment_id desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }
    
    @Override
    public Pager getSbData(Pager pager) {
        Map<String, String> m = JsonUtil.parseProperties(pager.getSearchData());
        String sql = "";
        sql = " select  a.equipment_id  from abnormal_alarm a left join equipment b on a.equipment_id = b.id "
                + " where 1=1 and  a.processing_state = '0'";
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("exceptionType"))) {
                sql += " and a.exception_type like '%" + m.get("exceptionType") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("equipmentId"))) {
                sql += " and b.equipment_code like '%" + m.get("equipmentId")  + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("startTime"))) {
                sql += " and a.alarm_date > '" + m.get("startTime") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("endTime"))) {
                sql += " and a.alarm_date < '" + m.get("endTime") + "'";
            }
        }
        sql += " group by equipment_id";
        sql += " order by equipment_id desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }
    

    @Override
    public String changeStatus(String id, String handlePeopleId) {
        String sql = "update abnormal_alarm set processing_state = '1', handle_people = '"
                + handlePeopleId + "' where id = '" + id + "' ";
        getJdbcTemplate().update(sql);
        return "success";
    }

    @Override
    public String notice(String id, String noticePeople) {
        String sql = "update abnormal_alarm set processing_state = '1', notice_people = '"
                + noticePeople + "' where id = '" + id + "' ";
        getJdbcTemplate().update(sql);
        return "success";
    }

    @SuppressWarnings("unchecked")
	@Override
    public HashMap<String, Object> getSbzb(String sn) {
        String sql = " SELECT TOP 1 sn, gpslat, gpslon FROM gps_atlas WHERE sn = '"+sn+"' ORDER BY time desc";
        SQLQuery createSQLQuery = this.getSession().createSQLQuery(sql);
        List<Object[]> list = createSQLQuery.list();
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(list.size()>0){
            map.put("lat", list.get(0)[1]);
            map.put("lon", list.get(0)[2]);
        }
        return map;
    }

}