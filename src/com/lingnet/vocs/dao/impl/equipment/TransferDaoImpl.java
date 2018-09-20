package com.lingnet.vocs.dao.impl.equipment;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.equipment.TransferDao;
import com.lingnet.vocs.entity.Transfer;

/**
 * 设备转移记录
 * @ClassName: TransferDaoImpl 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月21日 下午6:07:49 
 *
 */
@Repository("transferDao")
public class TransferDaoImpl extends BaseDaoImplInit<Transfer, String>
        implements TransferDao {

}
