package com.lingnet.vocs.dao.alarm;


import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.MsgTemplate;

public interface MsgTemplateDao extends BaseDao<MsgTemplate, String> {

    /**
     * @Title: findPagerMsgBySql
     * @param pager
     * @param m
     * @param id
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    Pager findPagerMsgBySql(Pager pager, Map<String, String> m, String id);
}
