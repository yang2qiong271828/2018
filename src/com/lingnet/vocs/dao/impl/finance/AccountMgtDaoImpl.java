package com.lingnet.vocs.dao.impl.finance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.finance.AccountMgtDao;
import com.lingnet.vocs.entity.AccountMgt;

@Repository("accountMgtDao")
public class AccountMgtDaoImpl extends BaseDaoImplInit<AccountMgt, String>
        implements AccountMgtDao {

    @Override
    public Pager findPagerAmBySql(Map<String, String> m, Pager pager,String currentPartnerId,String id ,Integer level,String sfbz) {
        String sql = " select a.id,a.code,p.name as partnerName,a.finance_date" // 1~4
                + ",a.service_receivable,a.service_discount,a.service_received" // 5~7
                + ",a.work_order_code,u.name as recorderName,a.partner_or_customer" // 8~10
                + ",a.material_receivable,a.material_discount,a.material_received"// 11~13
                + ",a.verify_status,a.invoice_no"                                 // 14~15
                + " from ACCOUNT_MGT a"
                + " left join PARTNER p on a.partner_id=p.id"
                + " left join qx_users u on a.recorder=u.id";
        if ("1".equals(currentPartnerId)) {
            sql += " where 1=1 ";
        } else {
            sql += " where a.owner_id = '" + currentPartnerId + "'";
        }
        if(StringUtils.isNotEmpty(sfbz)){
        	sql += " and verify_status='0' ";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("companyName"))) {
                sql += " and p.name like '%" + m.get("companyName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sql += " and a.finance_date >= '" + m.get("dateStart") + " 00:00:00'";
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sql += " and a.finance_date <= '" + m.get("dateEnd") + " 23:59:59'";
            }
            if (StringUtils.isNotEmpty(m.get("workOrderCode"))) {
                sql += " and a.work_order_code like '%" + m.get("workOrderCode") + "%'";
            }
        }
        if(0 == level){//省
            sql += " and p.province = '"+id+"'";
        }else if(1 == level){//市
            sql += " and p.city = '"+id+"'";
        }else if(2 == level){//区
            sql += " and p.district = '"+id+"'";
        }else{
        }
        sql += " order by a.createdate desc";
        pager = findPagerBySql(pager, sql);
        return pager;
    }
    
    @SuppressWarnings({ "unchecked" })
    @Override
    public List<Object[]> getSumData(Map<String, String> m, String currentPartnerId) {
        String sql = "SELECT sum(a.service_receivable) as 应收服务费,sum(a.service_discount) as 减免服务费,sum(a.service_received) as 实收服务费  "
                + " ,sum(a.material_receivable) as 应收物料费,sum(a.material_discount) as 减免物料费,sum(a.material_received) as 实收物料费  "
                + " from ACCOUNT_MGT a"
                + " left join PARTNER p on a.partner_id=p.id"
                + " left join qx_users u on a.recorder=u.id";
        if ("1".equals(currentPartnerId)) {
            sql += " where a.verify_status = '1' ";
        } else {
            sql += " where a.verify_status ='1' and a.owner_id = '" + currentPartnerId + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("companyName"))) {
                sql += " and p.name like '%" + m.get("companyName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sql += " and a.finance_date >= '" + m.get("dateStart") + " 00:00:00'";
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sql += " and a.finance_date <= '" + m.get("dateEnd") + " 23:59:59'";
            }
            if (StringUtils.isNotEmpty(m.get("workOrderCode"))) {
                sql += " and a.work_order_code like '%" + m.get("workOrderCode") + "%'";
            }
        }
        Query query = getSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }
    
}
