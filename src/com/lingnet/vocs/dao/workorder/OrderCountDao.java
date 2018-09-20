package com.lingnet.vocs.dao.workorder;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.WorkOrder;

/**
 * 工单统计
 * @ClassName: OrderCountDao 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年7月10日 下午1:49:31
 *
 */
public interface OrderCountDao extends BaseDao<WorkOrder, String> {
   
    public List<Object[]> count(Map<String, String> m, String area);

}
