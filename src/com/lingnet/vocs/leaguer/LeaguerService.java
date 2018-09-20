package com.lingnet.vocs.service.leaguer;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Pager;

/**
 * 会员信息管理
 * 
 * @ClassName: LeaguerService
 * @Description: TODO
 * @author wanl
 * @date 2017年7月10日 上午10:15:09
 * 
 */

public interface LeaguerService extends BaseService<QxUsers, String> {

    /**
     * 会员信息列表查询
     * 
     * @Title: getListData
     * @param pager
     * @return String
     * @author wanl
     * @since 2017年7月10日 V 1.0
     */
    public String getListData(Pager pager, String id);

    /**
     * 会员信息编辑保存
     * 
     * @Title: memberEditor
     * @return String
     * @author wanl
     * @since 2017年7月10日 V 1.0
     */
    public String saveOrUpdate(QxUsers qxUsers) throws Exception;

}
