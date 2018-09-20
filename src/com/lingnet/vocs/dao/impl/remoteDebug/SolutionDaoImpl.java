package com.lingnet.vocs.dao.impl.remoteDebug;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.remoteDebug.SolutionDao;
import com.lingnet.vocs.entity.Solution;
/**
 * 问题解决方案
 * @ClassName: SolutionDaoImpl 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 下午5:10:22 
 *
 */
@Repository("solutionDao")
public class SolutionDaoImpl extends BaseDaoImplInit<Solution, String>
                             implements SolutionDao {

}
