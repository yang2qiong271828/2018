package com.lingnet.vocs.dao.impl.statistics;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.statistics.ServiceBillDao;

@Repository("serviceBillDao")
public class ServiceBillDaoImpl extends BaseDaoImplInit<Object, String>
implements ServiceBillDao
{
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getChartsData(String method, String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) 总数 ,");
        sql.append(" sum(case when w.state in ('5','6') then 1 else 0 end) 完成数,");
        sql.append(" sum(case when w.state not in ('5','6') then 1 else 0 end) 未完成数,");
        sql.append(" sum(case when w.state in ('5','6') then  w.rea_item_charges else 0 end ) 物料费,");
        sql.append(" sum(case when w.state in ('5','6') then  w.rea_mainter_charges else 0 end  ) 服务费,");
        if ("0".equals(method)) {
            sql.append(" CONVERT(varchar(4),  w.createdate, 20) 年份 ");
        } else if ("1".equals(method)) {
            sql.append(" CONVERT(INT,DATENAME(quarter,w.createdate)) 季度 ");
        } else if ("2".equals(method)) {
            sql.append(" CONVERT(varchar(2),  w.createdate, 1) 月份 ");
        } else if ("3".equals(method)) {
            if (StringUtils.isNotEmpty(m.get("district"))) {
                sql.append(" p.district ");
            } else if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" p.district ");
            } else if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" p.city ");
            } else {
                sql.append(" p.province  ");
            }
        } else if ("4".equals(method)) {
            sql.append("  p.name ");
        }else if ("5".equals(method)) {
            sql.append(" CONVERT(varchar(11),  w.createdate, 20) ");
        }
        sql.append("  ,sum(case when w.state in ('5','6') then  w.rea_item_charges else 0 end ) + sum(case when w.state in ('5','6') then  w.rea_mainter_charges else 0 end  )  物料费服务费");
        sql.append(" from  workorder w ");
        sql.append(" LEFT JOIN PARTNER P ON W.CUSTOMER = P.ID");
        sql.append(" WHERE W.CUSTOMER IS NOT NULL AND W.state in ('5','6') ");
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("year"))) {
                sql.append(" AND CONVERT(varchar(4),  w.createdate, 20) ='"+ m.get("year") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" AND p.province ='" + m.get("province") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" AND p.city ='" + m.get("city") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("district"))) {
                sql.append(" AND p.district ='" + m.get("district") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("starttime"))) {
                sql.append(" AND w.createdate >='" + m.get("starttime")+" 00:00:00" + "'");
            }
            if (StringUtils.isNotEmpty(m.get("endtime"))) {
                sql.append(" AND w.createdate <='" + m.get("endtime")+" 23:59:59" + "'");
            }
        }
        sql.append(" GROUP BY  ");
        if ("0".equals(method)) {
            sql.append(" CONVERT(varchar(4),  w.createdate, 20)");
        } else if ("1".equals(method)) {
            sql.append(" CONVERT(INT,DATENAME(quarter,w.createdate))");
        } else if ("2".equals(method)) {
            sql.append(" CONVERT(varchar(2),  w.createdate, 1) ");
        } else if ("3".equals(method)) {
            if (StringUtils.isNotEmpty(m.get("district"))) {
                sql.append(" p.district ");
            } else if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" p.district ");
            } else if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" p.city ");
            } else {
                sql.append(" p.province  ");
            }
            sql.append(" ORDER BY sum(case when w.state in ('5','6') then  w.rea_item_charges else 0 end ) + sum(case when w.state in ('5','6') then  w.rea_mainter_charges else 0 end  ) DESC ");
        } else if ("4".equals(method)) {
            sql.append(" p.NAME ");
            sql.append(" ORDER BY sum(case when w.state in ('5','6') then  w.rea_item_charges else 0 end ) + sum(case when w.state in ('5','6') then  w.rea_mainter_charges else 0 end  ) DESC ");
        }else if ("5".equals(method)) {
            sql.append("  CONVERT(varchar(11),  w.createdate, 20) ");
        }
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }
    
    @Override
    public Pager getGridData(String method,String key, Pager pager) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sb = new StringBuffer();
        if (null != m) {
            // 统计口径
            if ("employee".equals(m.get("caliber"))) {
                sb.append("select qu.name as employee");
            } else if ("depart".equals(m.get("caliber"))) {
                sb.append("select qd.name as departName");
            } else if ("company".equals(m.get("caliber"))) {
                sb.append("select p.name as partnerName");
            }
            // 统计内容
            sb.append(",avg(cast(wo.score as numeric(10,2))) as avgScore");
            sb.append(",count(qu.name) as taskCount");

            sb.append(" from workorder wo");
            sb.append(" inner join qx_users qu on wo.replay_person = qu.id");
            if ("employee".equals(m.get("caliber"))) {
                //
            } else if ("depart".equals(m.get("caliber"))) {
                sb.append(" inner join qx_department qd on qu.depid = qd.id");
            } else if ("company".equals(m.get("caliber"))) {
                sb.append(" inner join partner p on qu.partner_id = p.id");
            }
            sb.append(" inner join orderclass oc on oc.id = wo.fault_type");
            sb.append(" where wo.score is not null");
            // ----------------- 限定条件
            // 1、故障类型
            if (StringUtils.isNotEmpty(m.get("faultType"))) {
                sb.append(" and oc.id='" + m.get("faultType") + "'");
            }
            // 2、时间日期
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sb.append(" and wo.check_date > '" + m.get("dateStart") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sb.append(" and wo.check_date < '" + m.get("dateEnd") + "'");
            }
            // ----------------- 限定条件
            // 统计口径
            if ("employee".equals(m.get("caliber"))) {
                sb.append(" group by qu.name order by qu.name asc");
            } else if ("depart".equals(m.get("caliber"))) {
                sb.append(" group by qd.name order by qd.name asc");
            } else if ("company".equals(m.get("caliber"))) {
                sb.append(" group by p.name order by p.name asc");
            }
        }
        pager = findPagerBySql(pager, sb.toString());
        return pager;
    }
}
