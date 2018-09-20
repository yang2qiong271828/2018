package com.lingnet.vocs.dao.impl.maintenancet;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.maintenancet.MaintenancetDao;

import com.lingnet.vocs.entity.RegularMaintenance;

@Repository("maintenancetDao")
public class MaintenancetDaoImpl extends BaseDaoImplInit<RegularMaintenance, String>
implements  MaintenancetDao {

}
