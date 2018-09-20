package com.lingnet.vocs.dao.impl.alarm;


import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.alarm.MsgTemplateDao;
import com.lingnet.vocs.entity.MsgTemplate;

@Repository("msgTemplateDao")
public class MsgTemplateDaoImpl extends BaseDaoImplInit<MsgTemplate, String>
implements MsgTemplateDao{

    @Override
    public Pager findPagerMsgBySql(Pager pager,Map<String, String> m, String id) {
        String sql = "select m.id,m.name,m.tmpl_content,m.partner_id,m.is_enabled"// 1~5
                + ",m.createdate,m.phone_list,m.receiver_ids,m.type,p.higher_agent"// 6~10
                + " from MSG_TEMPLATE m left join PARTNER p"
                + " on m.partner_id = p.id"
                + " where m.partner_id ='"+id+"'";
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and m.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("isEnabled"))) {
                sql += " and m.is_enabled = '" + m.get("isEnabled") + "'";
            }
        }
        sql += " order by m.createdate desc";
        Pager pager2 = findPagerBySql(pager, sql);
        return pager2;
    }
}
