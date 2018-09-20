package com.lingnet.vocs.service.workorder;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.entity.WorkOrderLog;

/**
 * 工单
 * 
 * @ClassName: WorkOrderService
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月28日 下午2:25:21
 *
 */
public interface WorkOrderService extends BaseService<WorkOrder, String> {

    /**
     * 保存 修改
     * @Title: saveOrUpdate 
     * @param workOrder
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String saveOrUpdate(WorkOrder workOrder) throws Exception;

    /**
     * 工单录入列表获取数据
     * @Title: getListData 
     * @param pager
     * @param state
     * @return 
     * String 
     * @author 薛硕
     * @param id 
     * @param mmp 
     * @since 2017年7月6日 V 1.0
     */
    public String getListData(Pager pager, String state, String id, String mmp);

    /**
     * 删除
     * @Title: remove 
     * @param id 
     * void 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public void remove(String id);

    /**
     * 合作商的工单
     * @Title: getGroupData 
     * @param pager
     * @param partnerId
     * @param state
     * @return 
     * String 
     * @author 薛硕
     * @param mmp 
     * @since 2017年7月6日 V 1.0
     */
    public String getGroupData(Pager pager, String partnerId, String state, String mmp);

    /**
     * 我的工单
     * @Title: getMyOrderData 
     * @param pager
     * @param partnerId
     * @return 
     * String 
     * @author 薛硕
     * @param mmp 
     * @since 2017年7月6日 V 1.0
     */
    public String getMyOrderData(Pager pager, String partnerId);

    /**
     * 工单 - 区域负责人 中间表 保存
     * @Title: saveWoar 
     * @param workOrder
     * @param state 
     * void 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public void saveWoar(WorkOrder workOrder, String state);

    /**
     * 我的工单转派申请
     * @Title: myGoBack 
     * @param workOrder
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String myGoBack(WorkOrder workOrder) throws Exception;

    /**
     * 保存转派
     * @Title: saveGoOther 
     * @param workOrder
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String saveGoOther(WorkOrder workOrder) throws Exception;

    /**
     *  保存接单
     * @Title: receive 
     * @param workOrder
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String receive(WorkOrder workOrder) throws Exception;

    /**
     * 保存结单信息
     * @Title: saveStatement 
     * @param workOrder
     * @param griddata
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String saveStatement(WorkOrder workOrder, String griddata)
            throws Exception;

    /**
     * 客户的工单获取数据
     * @Title: getCompleteData 
     * @param pager
     * @param partnerId
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String getCompleteData(Pager pager, String partnerId);

    /**
     * 根据工单获取物料
     * @Title: getItemByWorkOrder 
     * @param id
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public String getItemByWorkOrder(String id);

    /**
     * 
     * @Title: saveWorkLog 
     * @param czType 操作类型  czdj 错做单据  hfjg 回复结果
     * void 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public void saveWorkLog(String czType, String czdj, String hfjg, String hh);

    /**
     * 根据工单获取跟踪记录
     * @Title: getFollowByWorkOrder 
     * @param id
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年7月6日 V 1.0
     */
    public List<WorkOrderLog> getFollowByWorkOrder(String code);
    /**
     * 首页-我的工单
     * @Title: getMyOrderListData 
     * @param pager
     * @return 
     * String 
     * @author xuhp
     * @since 2017-7-13 V 1.0
     */
    String getMyOrderListData(Pager pager);
    /**
     * 首页-待指派工单（运维）
     * @Title: getOrderListData 
     * @param pager
     * @return 
     * String 
     * @author xuhp
     * @since 2017-7-13 V 1.0
     */
    String getOrderListData(Pager pager);
    /**
     * 首页-待指派工单（区域）
     * @Title: getOrderGroupListData 
     * @param pager
     * @return 
     * String 
     * @author xuhp
     * @since 2017-7-13 V 1.0
     */
    String getOrderGroupListData(Pager pager);
    /**
     * 跟进记录
     * @Title: saveFollow 
     * @param formdata 
     * void 
     * @author TANGJW
     * @param griddata 
     * @since 2017年7月12日 V 1.0
     */
    public void saveFollow(String formdata, String griddata,String attachmentdata) throws Exception;

    /**
     * 获取跟踪记录及图片信息
     * @Title: getFollowMap 
     * @param workOrderCode
     * @return 
     * List<Map> 
     * @author TANGJW
     * @since 2017年7月12日 V 1.0
     */
    @SuppressWarnings("rawtypes")
	public List<HashMap> getFollowMap(String workOrderCode);
    
    /**
     * 根据工单更新物料
     * @Title: updateItem 
     * @param workorder
     * @param griddata
     * @throws Exception 
     * void 
     * @author TANGJW
     * @since 2017年7月14日 V 1.0
     */
    public void updateItem(WorkOrder workorder,String griddata ) throws Exception;

    public String applyGoOther(WorkOrder workOrder) throws Exception;

    public String saveFzr(WorkOrder workOrder) throws Exception;

    public String backGoOther(WorkOrder workOrder) throws Exception;

    public String getReplayData(Pager pager, String partnerId, String state);

    public  void saveEvaluationWorkLog(String czType, String czdj, String hfjg);

	public String getMyOrderDataIndex(String partnerId);

	public List getKhNames(Pager pager, String startDate, String endDate);


	List<WorkOrder> getReplayDataAll();

	public String getDsporderData(Pager pager);

	public WorkOrder findById(String id);

	public String Upd(WorkOrder workorder);
}
