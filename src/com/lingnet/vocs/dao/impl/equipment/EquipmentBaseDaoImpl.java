package com.lingnet.vocs.dao.impl.equipment;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.vocs.dao.equipment.EquipmentBaseDao;
import com.lingnet.vocs.entity.EquipmentBase;

/**
 * 设备基础信息
 * 
 * @ClassName: EquipmentBaseDaoImpl
 * @Description: TODO
 * @author wanl
 * @date 2017年6月28日 下午2:52:22
 * 
 */

@Repository("equipmentBaseDao")
public class EquipmentBaseDaoImpl extends BaseDaoImpl<EquipmentBase, String>
        implements EquipmentBaseDao {

}
