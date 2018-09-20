package com.lingnet.vocs.dao.impl.statistics;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.statistics.ServiceChargeDao;

@Repository("serviceChargeDao")
public class ServiceChargeDaoImpl extends BaseDaoImplInit<Object, String>
        implements ServiceChargeDao {

    @SuppressWarnings("unchecked")
    @Override
    // 结单的时候，可以创建两条名称一样的物料记录
    public List<Object[]> getChartsData(String key) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        String content = m.get("content");// 统计内容
        String caliber = m.get("caliber");// 统计口径
        String engineer = m.get("engineer");// 人员
        StringBuffer sb = new StringBuffer();
        if (null != m) {
            // 统计内容
            if ("materialOnly".equals(content)) {
                // 统计按照item_code降序
                // 1、使用pivot函数；2、定义sql变量，因此原sql中的条件的值，要加“双单引号”
                // 在前置条件中已经限定item.is_delete=0
                sb.append(" DECLARE @sql_str VARCHAR(8000)");
                sb.append(" DECLARE @sql_col VARCHAR(8000)");
                sb.append(" SELECT @sql_col = ISNULL(@sql_col + ',','')");
                sb.append(" + QUOTENAME([item_code]) FROM [ITEM]");
                sb.append(" WHERE [is_delete]=0 GROUP BY [item_code] ORDER BY [item_code] DESC");
                sb.append(" SET @sql_str = '");
                sb.append(" select * from (");
                sb.append("select it.item_code,");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20) 年份,");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)) 季度,");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1) 月份,");
                } else if ("datePeriod".equals(caliber)) {

                }
                sb.append(" sum( wi.num * wi.price ) as amount");
                sb.append(" from workorder wo");
                sb.append(" inner join wo_item wi on wo.id = wi.workorder");
                sb.append(" inner join item it on wi.item = it.id");
                sb.append(" where (wo.state=5 or wo.state=6)");
                // ----------------- 限定条件------------------
                if (StringUtils.isNotEmpty(engineer)) {
                    sb.append(" and wo.id in ( select  wa.work_order  from WORKORDER_AREARESP wa where wa.area_responsible = ''" + engineer + "'' ) ");
                }
                if (null != m.get("year")) {
                    sb.append(" and CONVERT(varchar(4), wo.createdate, 20) = "+m.get("year")+" ");
                }
                if (null != m.get("dateStart")) {
                    sb.append(" and wo.createdate >= ''" + m.get("dateStart") + " 00:00:00''");
                }
                if (null != m.get("dateEnd")) {
                    sb.append(" and wo.createdate <= ''" + m.get("dateEnd") + " 23:59:59''");
                }
                if (null != m.get("companyname")) {
                    sb.append(" and wo.partner = ''" + m.get("companyname") + "''");
                }
                // ----------------- 限定条件------------------
                sb.append(" group by");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20),");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)),");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1),");
                } else if ("datePeriod".equals(caliber)) {

                }
                sb.append(" it.item_code");
                sb.append(") bm");
                sb.append(" pivot(max(bm.amount) for bm.item_code in ('+@sql_col+')) a'");
                sb.append("EXEC (@sql_str)");
            } else if ("serviceMaterial".equals(content)) {
                sb.append("select");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20) 年份,");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)) 季度,");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1) 月份,");
                } else if ("datePeriod".equals(caliber)) {
                }
                sb.append(" sum (wo.rec_item_charges) as materialReceivable,");
                sb.append(" sum (wo.rea_item_charges) as materialReceived,");
                sb.append(" sum (wo.rec_mainter_charges) as serviceReceivable,");
                sb.append(" sum (wo.rea_mainter_charges) as serviceReceived");
                sb.append(" from workorder wo");
                sb.append(" where (wo.state='5' or wo.state='6')");
                // ----------------- 限定条件------------------
                if (StringUtils.isNotEmpty(engineer)) {
                    sb.append(" and wo.replay_person ='" + engineer + "'");
                }
                if (null != m.get("year")) {
                    sb.append(" and CONVERT(varchar(4), wo.createdate, 20) = "+m.get("year")+" ");
                }
                if (null != m.get("dateStart")) {
                    sb.append(" and wo.createdate >= '" + m.get("dateStart") + " 00:00:00'");
                }
                if (null != m.get("dateEnd")) {
                    sb.append(" and wo.createdate <= '" + m.get("dateEnd") + " 23:59:59'");
                }
                if (null != m.get("companyname")) {
                    sb.append(" and wo.customer = '" + m.get("companyname") + "'");
                }
                // ----------------- 限定条件------------------
                if ("year".equals(caliber)) {
                    sb.append(" group by CONVERT(varchar(4), wo.createdate, 20) ");
                } else if ("season".equals(caliber)) {
                    sb.append(" group by CONVERT(INT,DATENAME(quarter, wo.createdate)) ");
                } else if ("month".equals(caliber)) {
                    sb.append(" group by CONVERT(varchar(2), wo.createdate, 1) ");
                } else if ("datePeriod".equals(caliber)) {
                }
            }
        }
        SQLQuery query = this.getSession().createSQLQuery(sb.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public Pager getGridData(String key, Pager pager) {
        Map<String, String> m = JsonUtil.parseProperties(key);
        String content = m.get("content");// 统计内容
        String caliber = m.get("caliber");// 统计口径
        String engineer = m.get("engineer");// 人员
        StringBuffer sb = new StringBuffer();
        if (null != m) {
            if ("materialOnly".equals(content)) {
                sb.append("select it.item_code,");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20) 年份,");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)) 季度,");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1) 月份,");
                } else if ("datePeriod".equals(caliber)) {

                }
                sb.append("  sum( wi.num * wi.price )  as amount");
                sb.append(" from workorder wo");
                sb.append(" inner join wo_item wi on wo.id = wi.workorder");
                sb.append(" inner join item it on wi.item = it.id");
                sb.append(" where (wo.state=5 or wo.state=6)");
                sb.append(" and it.is_delete='0'");
                // ----------------- 限定条件------------------
                if (StringUtils.isNotEmpty(engineer)) {
                    sb.append(" and wo.id in ( select  wa.work_order  from WORKORDER_AREARESP wa where wa.area_responsible = '" + engineer + "' ) ");
                }
                if (null != m.get("year")) {
                    sb.append(" and CONVERT(varchar(4), wo.createdate, 20) = "
                            + m.get("year") + " ");
                }
                if (null != m.get("dateStart")) {
                    sb.append(" and wo.createdate >= '" + m.get("dateStart") + " 00:00:00'");
                }
                if (null != m.get("dateEnd")) {
                    sb.append(" and wo.createdate <= '" + m.get("dateEnd") + " 23:59:59'");
                }
                if (null != m.get("companyname")) { 
                    sb.append(" and wo.partner = '" + m.get("companyname") + "'");
                }
                // ----------------- 限定条件------------------
                sb.append(" group by");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20),");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)),");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1),");
                } else if ("datePeriod".equals(caliber)) {
                }
                sb.append(" it.item_code");
                if ("year".equals(caliber)) {
                    sb.append(" order by  CONVERT(varchar(4), wo.createdate, 20) ");
                }
            } else if ("serviceMaterial".equals(content)) {
                sb.append("select");
                if ("year".equals(caliber)) {
                    sb.append(" CONVERT(varchar(4), wo.createdate, 20) 年份,");
                } else if ("season".equals(caliber)) {
                    sb.append(" CONVERT(INT,DATENAME(quarter, wo.createdate)) 季度,");
                } else if ("month".equals(caliber)) {
                    sb.append(" CONVERT(varchar(2), wo.createdate, 1) 月份,");
                } else if ("datePeriod".equals(caliber)) {
                }
                sb.append(" sum (wo.rec_item_charges) as materialReceivable,");
                sb.append(" sum (wo.rea_item_charges) as materialReceived,");
                sb.append(" sum (wo.rec_mainter_charges) as serviceReceivable,");
                sb.append(" sum (wo.rea_mainter_charges) as serviceReceived");
                sb.append(" from workorder wo");
                sb.append(" where (wo.state='5' or wo.state='6')");
                // ----------------- 限定条件------------------
                if (StringUtils.isNotEmpty(engineer)) {
                    sb.append(" and wo.replay_person ='" + engineer + "'");
                }
                if (null != m.get("year")) {
                    sb.append(" and CONVERT(varchar(4), wo.createdate, 20) = "+m.get("year")+" ");
                }
                if (null != m.get("dateStart")) {
                    sb.append(" and wo.createdate > '" + m.get("dateStart") + " 00:00:00'");
                }
                if (null != m.get("dateEnd")) {
                    sb.append(" and wo.createdate < '" + m.get("dateEnd") + " 23:59:59'");
                }
                if (null != m.get("companyname")) {
                    sb.append(" and wo.customer = '" + m.get("companyname") + "'");
                }
                // ----------------- 限定条件------------------
                if ("year".equals(caliber)) {
                    sb.append(" group by CONVERT(varchar(4), wo.createdate, 20)  order by CONVERT(varchar(4), wo.createdate, 20) ");
                } else if ("season".equals(caliber)) {
                    sb.append(" group by CONVERT(INT,DATENAME(quarter, wo.createdate)) ");
                } else if ("month".equals(caliber)) {
                    sb.append(" group by CONVERT(varchar(2), wo.createdate, 1) ");
                } else if ("datePeriod".equals(caliber)) {
                }
            }
        }
        pager = findPagerBySql(pager, sb.toString());
        return pager;
    }

}
