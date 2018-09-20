package com.lingnet.vocs.dao.impl.sewage;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.sewage.SewagePermitDao;
import com.lingnet.vocs.entity.SewagePermit;

@Repository("sewagePermitDao")
public class SewagePermitDaoImpl extends BaseDaoImplInit<SewagePermit, String> implements SewagePermitDao{

}
