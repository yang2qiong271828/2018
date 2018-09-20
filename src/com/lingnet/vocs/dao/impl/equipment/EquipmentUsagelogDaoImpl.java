package com.lingnet.vocs.dao.impl.equipment;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.equipment.EquipmentUsagelogDao;
import com.lingnet.vocs.entity.EquipmentUsageLog;

@Repository("equipmentUsagelogDao")
public class EquipmentUsagelogDaoImpl extends BaseDaoImplInit<EquipmentUsageLog, String>
        implements EquipmentUsagelogDao {

}
