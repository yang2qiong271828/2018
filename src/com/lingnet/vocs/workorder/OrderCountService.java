package com.lingnet.vocs.service.workorder;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.WorkOrder;

/**
 * 工单统计
 * 
 * @ClassName: OrderCountService
 * @Description: TODO
 * @author 薛硕
 * @date 2017年7月10日 下午1:49:31
 *
 */
public interface OrderCountService extends BaseService<WorkOrder, String> {

    public String getListData(Pager pager, String id);


}
