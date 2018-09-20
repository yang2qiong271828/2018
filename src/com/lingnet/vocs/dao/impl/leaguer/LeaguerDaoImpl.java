package com.lingnet.vocs.dao.impl.leaguer;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.leaguer.LeaguerDao;

/**
 * 会员信息管理
 * 
 * @ClassName: LeaguerDaoImpl
 * @Description: TODO
 * @author wanl
 * @date 2017年7月10日 上午10:16:23
 * 
 */

@Repository("leaguerDao")
public class LeaguerDaoImpl extends BaseDaoImplInit<QxUsers, String> implements
        LeaguerDao {

    @Override
    public Pager getListData(Pager pager, String id) {
        Map<String, String> m = JsonUtil.parseProperties(pager.getSearchData());

        String sql = "";

        sql = " select q.id,q.username,q.code,q.industry,q.address,q.contact_person,"
                + " q.contact_number,q.equipment_type,q.logindate "
                + " ,a.name 省,b.name 市 ,c.name 区,q.reset 是否重置密码"
                + " from  qx_users q"
                + " left join b_area a on q.province = a.id "
                + " left join b_area b on q.city = b.id "
                + " left join b_area c on q.town = c.id "
                + " where 1 = 1 ";

        if (id != null) {
            sql += " and q.partner_id = '" + id + "'";
        }

        if (m != null) {
            if (StringUtils.isNotEmpty(m.get("memberName"))) {
                sql += " and q.username like '%" + m.get("memberName") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("code"))) {
                sql += " and q.code like '%" + m.get("code") + "%'";
            }
        }

        sql += " order by q.logindate desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }
}
