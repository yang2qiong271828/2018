package com.lingnet.vocs.dao.workorder;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.entity.WorkOrder;

/**
 * @ClassName: WorkOrderDao
 * @Description: TODO
 * @author wanl
 * @date 2017年7月5日 下午4:48:03
 * 
 */

public interface WorkOrderDao extends BaseDao<WorkOrder, String> {

    /**
     * 工单评价保存
     * 
     * @Title: saveEvaluation
     * @param id
     * @param map
     * @return String
     * @author wanl
     * @since 2017年7月5日 V 1.0
     */
    public String saveEvaluation(String id, HashMap<String, Object> map);

	public String getMyOrderDataIndex(String string, ToolUtil toolUtil, String partnerId);

	public List getKhNames( Pager pager, String startDate, String endDate);

	public WorkOrder fingById(String id);


}
