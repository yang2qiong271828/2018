package com.lingnet.vocs.service.workorder;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.OrderClass;

/**
 * 工单问题分类
 * @ClassName: OrderClassService 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月29日 上午10:37:38 
 *
 */
public interface OrderClassService  extends BaseService<OrderClass, String>{

    public String saveOrUpdate(OrderClass orderClass) throws Exception;

    public String getListData(Pager pager);

    public void remove(String[] ids) throws Exception;

    
}
