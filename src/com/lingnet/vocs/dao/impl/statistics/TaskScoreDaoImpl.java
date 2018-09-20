package com.lingnet.vocs.dao.impl.statistics;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.statistics.TaskScoreDao;

@Repository("taskScoreDao")
public class TaskScoreDaoImpl extends BaseDaoImplInit<Object, String> implements
        TaskScoreDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getChartsData(String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        String caliber = m.get("caliber");
        String content = m.get("content");
        StringBuffer sb = new StringBuffer();
        if (null != m) {
            // 统计口径
            if ("employee".equals(caliber)) {
                sb.append("select qu.name as employee");
            } else if ("company".equals(caliber)) {
                sb.append("select p.name as partnerName");
            }
            // 统计内容
            if ("avgScore".equals(content)) {
                sb.append(",avg(cast(wo.score as numeric(10,2))) as avgScore");
            } else if ("taskCount".equals(m.get("content"))) {
                sb.append(",count(qu.name) as taskCount");
            } else {
                sb.append(",avg(cast(wo.score as numeric(10,2))) as avgScore");
                sb.append(",count(qu.name) as taskCount");
            }
            sb.append(" from workorder wo");
            sb.append(" inner join qx_users qu on wo.replay_person = qu.id");
            sb.append(" inner join partner p on wo.customer = p.id");//qx_user -- partner
            sb.append(" inner join orderclass oc on oc.id = wo.fault_type");
            sb.append(" where (wo.state='5' or wo.state='6') ");
            // ----------------- 限定条件
            // 1、故障类型
            if (StringUtils.isNotEmpty(m.get("faultType"))) {
                sb.append(" and oc.id='" + m.get("faultType") + "'");
            }
            // 2、时间区间
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sb.append(" and wo.check_date >= '" + m.get("dateStart") + " 00:00:00 '");
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sb.append(" and wo.check_date <= '" + m.get("dateEnd") + " 23:59:59'");
            }
            // 3、所属公司
            if (StringUtils.isNotEmpty(m.get("company"))) {
                sb.append(" and p.id = '" + m.get("company") + "'");
            }
            // ----------------- 限定条件
            // 统计口径
            if ("employee".equals(caliber)) {
                sb.append(" group by qu.name order by qu.name asc");
            } else if ("company".equals(caliber)) {
                sb.append(" group by p.name order by p.name asc");
            }
        }
        SQLQuery query = this.getSession().createSQLQuery(sb.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public Pager getGridData(String key, Pager pager) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        String caliber = m.get("caliber");
        String content = m.get("content");
        StringBuffer sb = new StringBuffer();
        if (null != m) {
            // 统计口径
            if ("employee".equals(caliber)) {
                sb.append("select qu.name as employee");
            } else if ("company".equals(caliber)) {
                sb.append("select p.name as partnerName");
            }
            // 统计内容
            sb.append(",avg(cast(wo.score as numeric(10,2))) as avgScore");
            sb.append(",count(qu.name) as taskCount");

            sb.append(" from workorder wo");
            sb.append(" inner join qx_users qu on wo.replay_person = qu.id");
            sb.append(" inner join partner p on wo.customer = p.id");
            sb.append(" inner join orderclass oc on oc.id = wo.fault_type");
            sb.append(" where (wo.state='5' or wo.state='6') ");
            // ----------------- 限定条件
            // 1、故障类型
            if (StringUtils.isNotEmpty(m.get("faultType"))) {
                sb.append(" and oc.id='" + m.get("faultType") + "'");
            }
            // 2、时间区间
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sb.append(" and wo.check_date >= '" + m.get("dateStart") + " 00:00:00'");
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sb.append(" and wo.check_date <= '" + m.get("dateEnd") + " 23:59:59'");
            }
            // 3、所属公司
            if (StringUtils.isNotEmpty(m.get("company"))) {
                sb.append(" and p.id = '" + m.get("company") + "'");
            }
            // ----------------- 限定条件
            // 统计口径
            if ("employee".equals(caliber)) {
                sb.append(" group by qu.name order by qu.name asc");
            } else if ("company".equals(caliber)) {
                sb.append(" group by p.name order by p.name asc");
            }
        }
        pager = findPagerBySql(pager, sb.toString());
        return pager;
    }

}
