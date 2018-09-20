package com.lingnet.vocs.dao.impl.finance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.finance.ContractChargeDao;
import com.lingnet.vocs.entity.ContractCharge;

@Repository("contractChargeDao")
public class ContractChargeDaoImpl extends BaseDaoImplInit<ContractCharge, String> implements
        ContractChargeDao {

    @Override
    public Pager findPagerContractChargeBySql(Map<String, String> m,Pager pager,String partnerId, String id, Integer level,String htsf) {
        String sql = "select c.id,c.code,c.partner_or_customer,c.company_id"            // 1~4
                + ",c.account_receivable,c.discount,c.paidup_capital,c.verify_status"   // 5~8
                + ",u.name as uname,c.finance_date,c.createdate,p.name as pname"        // 9~12
                + ",c.contract_code,c.invoice_no"                                       // 13~14
                + " from CONTRACT_CHARGE c left join Partner p"
                + " on c.company_id=p.id"
                + " left join qx_users u on c.recorder = u.id"
                + " where c.sponsor = '" + partnerId + "'";
        if(StringUtils.isNotEmpty(htsf)){
        	sql += "  and c.verify_status='0'  ";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("companyName"))) {
                sql += " and p.name like '%" + m.get("companyName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sql += " and c.finance_date >= '" + m.get("dateStart") + " 00:00:00' ";
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sql += " and c.finance_date <= '" + m.get("dateEnd") + " 23:59:59'";
            }
            if (StringUtils.isNotEmpty(m.get("contractCode"))) {
                sql += " and c.contract_code like '%" + m.get("contractCode") + "%'";
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
        sql += " order by c.createdate desc";
        pager = findPagerBySql(pager, sql);
        return pager;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<Object[]> getSumData(Map<String, String> m,String  partnerId) {
        String sql = "SELECT sum(c.account_receivable) as 应收费,sum(c.discount) as 减免费,sum(c.paidup_capital) as 实收费  "
                + " FROM CONTRACT_CHARGE c "
                + " left join Partner p on c.company_id=p.id" 
                + " left join qx_users u on c.recorder = u.id"
                + " where c.sponsor = '" + partnerId + "' and c.verify_status ='1' " ;
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("companyName"))) {
                sql += " and p.name like '%" + m.get("companyName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("dateStart"))) {
                sql += " and c.finance_date > '" + m.get("dateStart") +  " 00:00:00'";
            }
            if (StringUtils.isNotEmpty(m.get("dateEnd"))) {
                sql += " and c.finance_date <= '" + m.get("dateEnd") +  " 23:59:59'";
            }
            if (StringUtils.isNotEmpty(m.get("contractCode"))) {
                sql += " and c.contract_code like '%" + m.get("contractCode") + "%'";
            }
        }
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
}
