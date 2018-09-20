package com.lingnet.vocs.dao.finance;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AccountMgt;

public interface AccountMgtDao extends BaseDao<AccountMgt, String> {

    /**
     * 使用sql查询列表
     * 
     * @Title: findPagerAmBySql
     * @param m
     * @param pager
     * @param currentPartnerId 当前登录合作商的id
     * @return
     * @author zy
     * @param level 
     * @param id 
     * @since 2017年7月13日 V 1.0
     */
    Pager findPagerAmBySql(Map<String, String> m, Pager pager,String currentPartnerId, String id, Integer level,String sfbz);

    /**
     * 费用统计
     * @Title: getSumData 
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @param m 
     * @since 2017年8月10日 V 1.0
     */
    public   List<Object[]> getSumData(Map<String, String> m, String currentPartnerId);

}
