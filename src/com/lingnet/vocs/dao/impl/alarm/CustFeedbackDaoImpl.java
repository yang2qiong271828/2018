package com.lingnet.vocs.dao.impl.alarm;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.alarm.CustFeedbackDao;
import com.lingnet.vocs.entity.CustFeedback;

@Repository("custFeedbackDao")
public class CustFeedbackDaoImpl extends BaseDaoImplInit<CustFeedback, String>
        implements CustFeedbackDao {

    @Override
    public Pager findPagerCustFeedback(Map<String, String> m, Pager pager) {
        String sql = "select c.id,c.equipment_id,e.equipment_code,c.name as cname,c.type,c.contact"// 1~6
                + ",c.phone,c.detail,c.fault_date,c.submited,p.name as pname,c.createdate,c.state" // 7~13
                + " from CUST_FEEDBACK c"
                + " left join EQUIPMENT e on c.equipment_id = e.id"
                + " left join PARTNER p on c.equipment_user_id = p.id"
                + " where 1=1";
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("partnerName"))) {
                sql += " and p.name like '%" + m.get("partnerName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("equipmentCode"))) {
                sql += " and e.equipment_code like '%" + m.get("equipmentCode")
                        + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("faultDateStart"))) {
                sql += " and c.fault_date >= '" + m.get("faultDateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("faultDateEnd"))) {
                sql += " and c.fault_date <= '" + m.get("faultDateEnd") + "'";
            }
        }
        sql += " ORDER BY c.createdate desc";
        return findPagerBySql(pager, sql);
    }
}
