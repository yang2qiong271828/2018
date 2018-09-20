package com.lingnet.vocs.dao.customer;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.CustomerComplaints;

/**
 * @ClassName: CustomerComplaintsDao
 * @Description: TODO
 * @author wanl
 * @date 2017年6月29日 下午6:23:18
 * 
 */

public interface CustomerComplaintsDao extends
        BaseDao<CustomerComplaints, String> {

    /**
     * @Title: getListData
     * @return List<Object[]>
     * @author wanl
     * @param khtszt 
     * @since 2017年6月30日 V 1.0
     */
    public Pager getListData(Pager pager, String partnerId, String khtszt);

    /**
     * 添加客户投诉信息时查询客户列表
     * 
     * @Title: getCustomerListData
     * @return String
     * @author wanl
     * @since 2017年6月30日 V 1.0
     */
    // public List<Object[]> getCustomerListData(String name);

    /**
     * 选择客户时根据选中的客户名自动查询客户信息
     * 
     * @Title: getClientData
     * @param map
     * @return String
     * @author wanl
     * @since 2017年6月30日 V 1.0
     */
    public String getClientData(HashMap<String, Object> map);

    /**
     * 同一客户的所有投诉信息查询方法
     * 
     * @Title: getPersonalComplaintsListData
     * @param map
     * @return String
     * @author wanl
     * @since 2017年7月3日 V 1.0
     */
    public Pager getPersonalComplaintsListData(HashMap<String, Object> map,
            Pager pager);

    /**
     * 结单,更改状态为已处理
     * 
     * @Title: changeStatus void
     * @author wanl
     * @since 2017年7月3日 V 1.0
     */

    public String changeStatus(String id);

    /**
     * @Title: getCustomerListData
     * @param pager
     * @param name
     * @return Pager
     * @author wanl
     * @param clientType
     * @since 2017年7月4日 V 1.0
     */
    public Pager
            getCustomerListData(Pager pager, String name, String clientType);
}
