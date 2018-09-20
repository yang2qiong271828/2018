package com.lingnet.vocs.dao.workorder;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.entity.WorkOrderLog;

/**
 * 工单
 * @ClassName: WorkOrderDao 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月28日 下午2:25:06 
 *
 */
public interface WorkOrderLogDao extends BaseDao<WorkOrderLog, String> {

    /**
     * 
     * @Title: findFollowMap 
     * @return 
     * List<Object[]> 
     * @author TANGJW
     * @since 2017年7月12日 V 1.0
     */
    public List<Object[]> findFollowMap(String workOrderCode);

    public List<Object> findCzdjs(String sql);

	List<WorkOrder> findCz();

	public List<WorkOrder> findNotp(Pager pager);


}
