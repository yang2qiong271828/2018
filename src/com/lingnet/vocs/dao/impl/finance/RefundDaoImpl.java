package com.lingnet.vocs.dao.impl.finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.finance.RefundDao;
import com.lingnet.vocs.entity.Refund;

@Repository("refundDao")
public class RefundDaoImpl extends BaseDaoImplInit<Refund, String> implements
        RefundDao {

    @Override
    public Pager findPagerRefundBySql(Pager pager, Map<String, String> m,String ownerId, String id, Integer level) {
        String sql = "select r.id,r.code,r.work_order_code,r.contract_code"    //1~4
                + ",r.amount,r.refund_date,r.partner_id,r.contact,r.phone"     //5~9
                + ",r.partner_or_customer,r.verify_status,p.name"              //10~12
                + " from REFUND r left join PARTNER p"
                + " on r.partner_id=p.id";
        if ("1".equals(ownerId)) {
            sql += " where 1=1";
        } else {
            sql += " where r.owner_id ='" + ownerId + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("woCode"))) {
                sql += " and r.work_order_code like '%" + m.get("woCode") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("coCode"))) {
                sql += " and r.contract_code like '%" + m.get("coCode") + "%'";
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
        sql += " order by r.createdate desc";
        return findPagerBySql(pager, sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getSummaryData(HashMap<String, String> m,
            String partnerId) {
            String sql = "SELECT sum(r.amount) as 退款金额 "
                    + " FROM REFUND r ";
            if ("1".equals(partnerId)) {
                sql += " where r.verify_status='1' ";
            } else {
                sql += " where r.verify_status='1'  and r.owner_id ='" + partnerId + "'";
            }
            if (null != m) {
                if (StringUtils.isNotEmpty(m.get("woCode"))) {
                    sql += " and r.work_order_code like '%" + m.get("woCode") + "%'";
                }
                if (StringUtils.isNotEmpty(m.get("coCode"))) {
                    sql += " and r.contract_code like '%" + m.get("coCode") + "%'";
                }
            }
            Query query = getSession().createSQLQuery(sql);
            return query.list();
    }
}
