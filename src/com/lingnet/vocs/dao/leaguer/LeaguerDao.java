package com.lingnet.vocs.dao.leaguer;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Pager;

/**
 * 会员信息管理
 * 
 * @ClassName: LeaguerDao
 * @Description: TODO
 * @author wanl
 * @date 2017年7月10日 上午10:15:45
 * 
 */

public interface LeaguerDao extends BaseDao<QxUsers, String> {

    /**
     * 会员信息列表
     * 
     * @Title: getListData
     * @param pager
     * @return Pager
     * @author wanl
     * @since 2017年7月10日 V 1.0
     */
    public Pager getListData(Pager pager, String id);

}
