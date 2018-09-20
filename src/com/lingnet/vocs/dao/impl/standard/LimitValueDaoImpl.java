package com.lingnet.vocs.dao.impl.standard;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.standard.LimitValueDao;
import com.lingnet.vocs.entity.LimitValue;
@Repository("limitValueDao")
public class LimitValueDaoImpl extends BaseDaoImplInit<LimitValue, String> 
                                implements LimitValueDao{

}
