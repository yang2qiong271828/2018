package com.lingnet.vocs.dao.impl.finance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.finance.AccountFlowDao;
import com.lingnet.vocs.entity.AccountFlow;

@Repository("accountFlowDao")
public class AccountFlowDaoImpl extends BaseDaoImplInit<AccountFlow, String>
        implements AccountFlowDao {

    @Override
    public Pager findPagerAfBySql(Map<String, String> m, Pager pager,String areaId, String ownerId) {
        String sql = "select a.id,a.code,u.name as uName,a.finance_date"            //1~4
                + ",a.account_receivable,a.discount,a.paidup_capital,a.partner_id"  //5~8
                + ",a.workorder_code,a.contract_code,bp.name as provinceName"       //9~11
                + ",bc.name as cityName,bd.name as districtName,a.createdate"       //12~14
                + ",a.type,p.name as partnerName,p.partner_or_customer"             //15~17
                + ",pp.name as ownerName"                                           //18
                + " from ACCOUNT_FLOW a"
                + " left join PARTNER p on a.partner_id=p.id"
                + " left join PARTNER pp on a.owner_id=pp.id"
                + " left join B_AREA bp on a.province=bp.id"
                + " left join B_AREA bc on a.city=bc.id"
                + " left join B_AREA bd on a.district=bd.id"
                + " left join qx_users u on a.recorder=u.id";
        // 如果是华世洁，则看到全部；否则只能看到自己的
        if ("1".equals(ownerId)) {
            sql += " where 1=1";
        } else {
            sql += " where a.owner_id ='" + ownerId + "'";
        }
        // 当有地区id传入时，且id不是0时，对特定地区进行搜索
        if (StringUtils.isNotEmpty(areaId) && !"0".equals(areaId)) {
            sql += " and (a.province ='" + areaId + "' or a.city ='" + areaId
                    + "'" + " or a.district ='" + areaId + "')";
        }
        if(m!=null){
            if (StringUtils.isNotEmpty(m.get("partnerOrCustomer"))) {
                sql += " and p.partner_or_customer = '" + m.get("partnerOrCustomer") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("partnerName"))) {
                sql += " and p.name like '%" + m.get("partnerName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("financeDateStart"))) {
                sql += " and a.finance_date >= '" + m.get("financeDateStart")+" 00:00:00'";
            }
            if (StringUtils.isNotEmpty(m.get("financeDateEnd"))) {
                sql += " and a.finance_date <= '" + m.get("financeDateEnd")+" 23:59:59'";
            }
        }
        sql += " order by a.createdate desc";
        pager = findPagerBySql(pager, sql);
        return pager;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<Object[]> getSummaryData(Map<String, String> m,String areaId,String ownerId) {
        String sql = "SELECT sum(a.account_receivable) as s1,sum(a.discount) as s2,sum(a.paidup_capital) as s3 FROM ACCOUNT_FLOW a"
        + " left join PARTNER p on a.partner_id=p.id"
        + " left join PARTNER pp on a.owner_id=pp.id"
        + " left join B_AREA bp on a.province=bp.id"
        + " left join B_AREA bc on a.city=bc.id"
        + " left join B_AREA bd on a.district=bd.id"
        + " left join qx_users u on a.recorder=u.id";
        if ("1".equals(ownerId)) {
            sql += " where 1=1";
        } else {
            sql += " where a.owner_id ='" + ownerId + "'";
        }
        // 当有地区id传入时，且id不是0时，对特定地区进行搜索
        if (StringUtils.isNotEmpty(areaId) && !"0".equals(areaId)) {
            sql += " and (a.province ='" + areaId + "' or a.city ='" + areaId
                    + "'" + " or a.district ='" + areaId + "')";
        }
        if(m!=null){
            if (StringUtils.isNotEmpty(m.get("partnerOrCustomer"))) {
                sql += " and p.partner_or_customer = '" + m.get("partnerOrCustomer") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("partnerName"))) {
                sql += " and p.name like '%" + m.get("partnerName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("financeDateStart"))) {
                sql += " and a.finance_date >= '" + m.get("financeDateStart")+" 00:00:00'";
            }
            if (StringUtils.isNotEmpty(m.get("financeDateEnd"))) {
                sql += " and a.finance_date <= '" + m.get("financeDateEnd")+" 23:59:59'";
            }
        }
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
}
