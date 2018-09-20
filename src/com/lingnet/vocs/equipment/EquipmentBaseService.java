package com.lingnet.vocs.service.equipment;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.EquipmentBase;

public interface EquipmentBaseService extends
        BaseService<EquipmentBase, String> {

    /**
     * 设备基础信息编辑保存
     * 
     * @Title: saveOrUpdate
     * @param equipment
     * @return
     * @throws Exception
     *             String
     * @author wanl
     * @since 2017年6月28日 V 1.0
     */
    public String saveOrUpdate(EquipmentBase equipmentBase) throws Exception;

    /**
     * 设备基础信息列表展示
     * 
     * @Title: getListData
     * @param pager
     * @param key
     * @param partnerId
     * @return String
     * @author wanl
     * @since 2017年6月28日 V 1.0
     */
    public String getListData(Pager pager, String partnerId);

    /**
     * 设备基础信息删除
     * 
     * @Title: remove
     * @param inspectId
     * @return
     * @throws Exception
     *             String
     * @author wanl
     * @since 2017年6月28日 V 1.0
     */
    public String remove(String equipmentBaseId) throws Exception;

}
