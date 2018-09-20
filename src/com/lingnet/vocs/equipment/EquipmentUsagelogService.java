package com.lingnet.vocs.service.equipment;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.EquipmentUsageLog;

/**
 * 设备使用记录
 * @ClassName: EquipmentUsagelogService 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月26日 下午3:06:05 
 *
 */
public interface EquipmentUsagelogService extends BaseService<EquipmentUsageLog, String> {
    
    /**
     * 保存修改 设备使用记录
     * @Title: saveOrUpdate 
     * @param eqUsageLog
     * @return
     * @throws Exception 
     * String 
     * @author 薛硕
     * @since 2017年6月27日 V 1.0
     */
    public  String saveOrUpdate(EquipmentUsageLog eqUsageLog) throws Exception;

    /**
     * 设备列表展示
     * @Title: getListData 
     * @param pager
     * @param partnerId
     * @param eqId
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年6月27日 V 1.0
     */
    public String getListData(Pager pager, String partnerId, String eqId);

}
