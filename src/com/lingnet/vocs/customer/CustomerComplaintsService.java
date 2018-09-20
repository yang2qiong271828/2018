package com.lingnet.vocs.service.customer;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.CustomerComplaints;

/**
 * @ClassName: CustomerComplaintsService
 * @Description: TODO
 * @author wanl
 * @date 2017年6月30日 上午10:50:02
 * 
 */

public interface CustomerComplaintsService extends
        BaseService<CustomerComplaints, String> {

    /**
     * 客户投诉添加修改方法
     * 
     * @Title: saveOrUpdate
     * @return
     * @throws Exception
     *             String
     * @author wanl
     * @since 2017年7月1日 V 1.0
     */
    public String saveOrUpdate(CustomerComplaints customerComplaints)
            throws Exception;

    /**
     * 客户投诉列表查询
     * 
     * @Title: getListData
     * @return String
     * @author wanl
     * @param khtszt 
     * @since 2017年7月1日 V 1.0
     */
    public String getListData(Pager pager, String partnerId, String khtszt);

    /**
     * 同一客户的所有投诉信息查询方法
     * 
     * @Title: getPersonalComplaintsListData
     * @return String
     * @author wanl
     * @since 2017年7月3日 V 1.0
     */
    public String getPersonalComplaintsListData(HashMap<String, Object> map,
            Pager pager);

    /**
     * @Title: getCustomerListData
     * @param name
     * @param pager
     * @return String
     * @author wanl
     * @since 2017年7月3日 V 1.0
     */
    public String getCustomerListData(String clientType, String name,
            Pager pager);

    public void remove(String[] ids);
}
