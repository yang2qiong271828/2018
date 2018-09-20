package com.lingnet.vocs.dao.impl.remoteDebug;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.remoteDebug.RemoteDebugDao;
import com.lingnet.vocs.entity.OperatingParameter;
/**
 * 远程调试
 * @ClassName: RemoteDebugDaoImpl 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 下午5:09:35 
 *
 */
@Repository("remoteDebugDao")
public class RemoteDebugDaoImpl extends BaseDaoImplInit<OperatingParameter, String> 
                                implements RemoteDebugDao{

}
