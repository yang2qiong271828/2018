package com.lingnet.vocs.service.equipment;

import java.util.ArrayList;
import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.Transfer;

/**
 * ' 设备
 * 
 * @ClassName: EquipmentService
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月21日 上午10:47:10
 *
 */
public interface EquipmentService extends BaseService<Equipment, String> {

    /**
     * 保存
     * 
     * @Title: saveOrUpdate
     * @param equipment
     * @return
     * @throws Exception
     *             String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String saveOrUpdate(Equipment equipment) throws Exception;

    /**
     * 设备列表展示
     * 
     * @Title: getListData
     * @param pager
     * @param key
     * @return String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String getListData(Pager pager, String key, String partnerId);

    /**
     * 删除
     * 
     * @Title: remove
     * @param inspectId
     * @return
     * @throws Exception
     *             String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String remove(String inspectId) throws Exception;

    /**
     * 保存转移记录
     * 
     * @Title: saveTransfer
     * @param Transfer
     * @return
     * @throws Exception
     *             String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String saveTransfer(Transfer Transfer) throws Exception;

    /**
     * 获取转移记录
     * 
     * @Title: getTarOwnData
     * @param pager
     * @param equipmentId
     * @return String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String getTarOwnData(Pager pager, String equipmentId);

    /**
     * 我的设备列表展示
     * 
     * @Title: getMyEquipmentListData
     * @param pager
     * @param partnerId
     * @return String
     * @author 薛硕
     * @since 2017年6月26日 V 1.0
     */
    public String getMyEquipmentListData(Pager pager, String partnerId,String areaId);

    /**
     * 人员树
     * 
     * @Title: getTreeData
     * @param partnerId
     * @return String
     * @author 薛硕
     * @param id
     * @param areaId 
     * @param isEq
     * @since 2017年6月26日 V 1.0
     */

    public String getTreeData(String partnerId, String id, String areaId);

    public ArrayList<Object> getEqTreeData(String partnerId, String eqType);

    /**
     * @Title: saveTakeback
     * @param oldOwner
     *            ：原归属者
     * @param equipmentId
     *            ： 设备id
     * @param contractId
     *            ： 合同id
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年6月28日 V 1.0
     */
    String saveTakeBack(String equipmentId, String oldOwner,String contractId) throws Exception;

    /**
     * @Title: getAirVolumeByEquipment
     * @param map
     * @return String
     * @author wanl
     * @since 2017年6月29日 V 1.0
     */
    public String getAirVolumeByEquipment(HashMap<String, Object> map);

    /**
     * @Title: getAirVolume
     * @param map
     * @return String
     * @author wanl
     * @since 2017年6月29日 V 1.0
     */
    public String getAirVolume(HashMap<String, Object> map);

    public String getContractByEquipment(Pager pager, String eqId);

    public String getEqByconData(Pager pager, String partnerId, String userId);

    int getEqNumberByPartnerId(String id);

    /**
     * 首页设备到期提醒
     * 
     * @Title: getEquipmentListData
     * @param pager
     * @return String
     * @author xuhp
     * @since 2017-7-12 V 1.0
     */
    String getEquipmentListData(Pager pager);
    /**
     *转回操作者
     * @Title: backcz 
     * @param id
     * @return 
     * String 
     * @author lyz
     * @since 2017年10月13日 V 1.0
     */
    public String backcz(String id);
    /**
     *转回所属人
     * @Title: backcz 
     * @param id
     * @return 
     * String 
     * @author lyz
     * @since 2017年10月13日 V 1.0
     */
    public String backss(String id);

	public String saveOrUpdatePz(HashMap<String, String> map);
}
