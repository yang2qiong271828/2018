package com.lingnet.vocs.dao.impl.statistics;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.dao.statistics.CountDao;

/**
 * 统计
 * 
 * @ClassName: CountDaoImpl
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月4日 上午8:06:17
 *
 */
@Repository("countDao")
public class CountDaoImpl extends BaseDaoImplInit<Object, String> implements
        CountDao {

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
                sql.append(" w.district ");
            } else if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" w.district ");
            } else if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" w.city ");
            } else {
                sql.append(" w.province  ");
            }
        } else if ("4".equals(method)) {
            sql.append("  p.name ");
        }
        sql.append(" from  workorder w ");
        sql.append(" LEFT JOIN PARTNER P ON W.CUSTOMER = P.ID");
        sql.append(" WHERE W.CUSTOMER IS NOT NULL ");
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("year"))) {
                sql.append(" AND CONVERT(varchar(4),  w.createdate, 20) ='"
                        + m.get("year") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" AND w.province ='" + m.get("province") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" AND w.city ='" + m.get("city") + "'");
            }
            if (StringUtils.isNotEmpty(m.get("district"))) {
                sql.append(" AND w.district ='" + m.get("district") + "'");
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
                sql.append(" w.district ");
            } else if (StringUtils.isNotEmpty(m.get("city"))) {
                sql.append(" w.district ");
            } else if (StringUtils.isNotEmpty(m.get("province"))) {
                sql.append(" w.city ");
            } else {
                sql.append(" w.province  ");
            }
            sql.append(" ORDER BY count(1) DESC ");
        } else if ("4".equals(method)) {
            sql.append(" p.NAME ");
            sql.append(" ORDER BY count(1) DESC ");
        }
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getEqChartsData(String partnerId, String top,
            String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  ");
        if (top != null) {
            if ("1".equals(top)) {
                sql.append(" top 10  ");
            }
            if ("2".equals(top)) {
                sql.append(" top 20  ");
            }
        }
        sql.append(" count(1) 总数,w.equipment_code  ");
        sql.append(" FROM  WORKORDER w  ");
        sql.append(" WHERE W.CUSTOMER ='" + partnerId + "' and W.state not in ('0','8') " );
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("starttime"))) {
                sql.append(" AND w.createdate >='" + m.get("starttime")
                        + " 00:00:00" + "'");
            }
            if (StringUtils.isNotEmpty(m.get("endtime"))) {
                sql.append(" AND w.createdate <='" + m.get("endtime")
                        + " 23:59:59" + "'");
            }
        }
        sql.append(" GROUP BY  w.equipment_code ORDER BY count(1) DESC ");
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getEqCountData(String partnerId, String top,
            String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  ");
        if (top != null) {
            if ("1".equals(top)) {
                sql.append(" top 10  ");
            }
            if ("2".equals(top)) {
                sql.append(" top 20  ");
            }
        }
        sql.append(" w.equipment_code,w.createdate  ");
        sql.append(" FROM  WORKORDER w  ");
        sql.append(" WHERE W.CUSTOMER ='" + partnerId + "' and W.state not in ('0','8') " );
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("starttime"))) {
                sql.append(" AND w.createdate >='" + m.get("starttime")
                        + " 00:00:00" + "'");
            }
            if (StringUtils.isNotEmpty(m.get("endtime"))) {
                sql.append(" AND w.createdate <='" + m.get("endtime")
                        + " 23:59:59" + "'");
            }
        }
        sql.append(" order by w.createdate asc");
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getItemChartsData(String partnerId, String top,
            String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  ");
        if (top != null) {
            if ("1".equals(top)) {
                sql.append(" top 10  ");
            }
            if ("2".equals(top)) {
                sql.append(" top 20  ");
            }
        }
        sql.append(" count(1) 总数,I.NAME,SUM(WI.NUM)  ");
        sql.append(" FROM  WO_ITEM  WI  ");
        sql.append(" LEFT JOIN  WORKORDER W ON  W.ID = WI.WORKORDER  ");
        sql.append(" LEFT JOIN  ITEM I ON  WI.ITEM = I.ID  ");
        sql.append(" WHERE W.CUSTOMER ='" + partnerId + "'");
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("starttime"))) {
                sql.append(" AND w.createdate >='" + m.get("starttime")
                        + " 00:00:00" + "'");
            }
            if (StringUtils.isNotEmpty(m.get("endtime"))) {
                sql.append(" AND w.createdate <='" + m.get("endtime")
                        + " 23:59:59" + "'");
            }
        }
        sql.append(" GROUP BY  I.NAME ORDER BY count(1) DESC,SUM(WI.NUM) DESC ");
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getSatisfactionData(String partnerId, String top,
            String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  ");
        if (top != null) {
            if ("1".equals(top)) {
                sql.append(" top 10  ");
            }
            if ("2".equals(top)) {
                sql.append(" top 20  ");
            }
        }
        sql.append(" count(1) 总数,P.NAME  ");
        sql.append(" ,sum(case when w.score = 1 then  1 else 0 end  ) 一  ");
        sql.append(" ,sum(case when w.score = 2 then  1 else 0 end  ) 二  ");
        sql.append(" ,sum(case when w.score = 3 then  1 else 0 end  ) 三  ");
        sql.append(" ,sum(case when w.score = 4 then  1 else 0 end  ) 四  ");
        sql.append(" ,sum(case when w.score = 5 then  1 else 0 end  ) 五  ");
        sql.append(" FROM  WORKORDER  W  ");
        sql.append(" LEFT JOIN  PARTNER P  ON  P.ID = W.CUSTOMER WHERE  W.STATE = '5'   ");
        if (partnerId != null && StringUtils.isNotEmpty(partnerId)) {
            sql.append(" AND W.CUSTOMER ='" + partnerId + "'");
        }
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("starttime"))) {
                sql.append(" AND w.createdate >='" + m.get("starttime") + " 00:00:00" + "'");
            }
            if (StringUtils.isNotEmpty(m.get("endtime"))) {
                sql.append(" AND w.createdate <='" + m.get("endtime")  + " 23:59:59" + "'");
            }
        }
        sql.append(" GROUP BY  P.NAME ORDER BY COUNT (1) DESC  ");
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

   
}
