package com.lingnet.vocs.dao.impl.workorder;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.workorder.OrderCountDao;
import com.lingnet.vocs.entity.WorkOrder;

/**
 * 工单统计
 * 
 * @ClassName: OrderCountItemDaoImpl
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月10日 下午1:49:31
 *
 */
@Repository("orderCountDao")
public class OrderCountDaoImpl extends BaseDaoImplInit<WorkOrder, String>
        implements OrderCountDao {

    @Override
    public List<Object[]> count(Map<String, String> m, String area) {
        String sql = " select state,count(state) from WorkOrder  where 1=1 ";
        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("workOrderLevel"))) {
                sql += " and work_order_level = '" + m.get("workOrderLevel")
                        + "'";
            }
            if (StringUtils.isNotEmpty(m.get("state"))) {
                sql += " and state = '" + m.get("state") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("faultType"))) {
                sql += " and fault_type = '" + m.get("faultType") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("workOrderCode"))) {
                sql += " and work_order_code = '" + m.get("workOrderCode")
                        + "'";
            }
            if (StringUtils.isNotEmpty(m.get("startTime"))) {
                sql += " and check_date >= '" + m.get("startTime") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("endTime"))) {
                sql += " and check_date <= '" + m.get("endTime") + "'";
            }
        }
        if (area != null && !"".equals(area)) {
            sql += " and customer in ( select id from partner where (province = '"
                    + area
                    + "' or city = '"
                    + area
                    + "' or district = '"
                    + area + "') )";
        }
        sql += " group by state";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> list = query.list();
        return list;
    }

}
