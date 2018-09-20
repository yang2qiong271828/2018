package com.lingnet.vocs.dao.impl.sewage;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.sewage.SewageTaxDao;
import com.lingnet.vocs.entity.SewageTax;

@Repository("sewageTaxDao")
public class SewageTaxDaoImpl extends  BaseDaoImplInit<SewageTax, String> implements SewageTaxDao{

}
