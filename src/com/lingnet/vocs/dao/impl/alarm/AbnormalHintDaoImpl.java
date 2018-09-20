package com.lingnet.vocs.dao.impl.alarm;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.alarm.AbnormalHintDao;
import com.lingnet.vocs.entity.AbnormalHint;

@Repository("abnormalHintDao")
public class AbnormalHintDaoImpl extends BaseDaoImplInit<AbnormalHint, String>
        implements AbnormalHintDao {

    @Override
    public Pager findPagerAbHintBySql(Pager pager, Map<String, String> m,
            String id, String partnerId) {
        String sql = "";
        if (id != null && id != "") {
            sql = "select a.id,a.equipment_id,a.equipment_code,a.msg_content"
                    + ",a.alarm_date,a.operator,a.msg_status,a.partner_id"
                    + ",a.notify_person,a.equipment_type,a.createdate"
                    + " from ABNORMAL_HINT a where a.equipment_id ='" + id
                    + "'";
        } else {
            sql = "select a.id,a.equipment_id,a.equipment_code,a.msg_content"
                    + ",a.alarm_date,a.operator,a.msg_status,a.partner_id"
                    + ",a.notify_person,a.equipment_type,a.createdate"
                    + " from ABNORMAL_HINT a where 1 = 1";
        }

        if (partnerId != null && !"1".equals(partnerId)) {
            sql += " and  a.partner_id = '" + partnerId + "'";
        }

        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("equipCode"))) {
                sql += " and a.equipment_code like '%" + m.get("equipCode")
                        + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sql += " and a.alarm_date > '" + m.get("dateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sql += " and a.alarm_date < '" + m.get("dateEnd") + "'";
            }
        }
        sql += " order by a.createdate";
        Pager pager2 = findPagerBySql(pager, sql);
        return pager2;
    }
}
