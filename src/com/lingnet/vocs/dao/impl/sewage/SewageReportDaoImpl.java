package com.lingnet.vocs.dao.impl.sewage;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.sewage.SewageReportDao;
import com.lingnet.vocs.entity.SewageReport;

@Repository("sewageReportDao")
public class SewageReportDaoImpl extends  BaseDaoImplInit<SewageReport, String> implements SewageReportDao{

}
