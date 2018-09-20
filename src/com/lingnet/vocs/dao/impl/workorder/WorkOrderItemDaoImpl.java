package com.lingnet.vocs.dao.impl.workorder;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.workorder.WorkOrderItemDao;
import com.lingnet.vocs.entity.WorkOrderItem;

/**
 * 工单
 * @ClassName: WorkOrderDao 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月28日 下午2:25:06 
 *
 */
@Repository("workOrderItemDao")
public class WorkOrderItemDaoImpl extends BaseDaoImplInit<WorkOrderItem, String> 
implements WorkOrderItemDao
{


}
