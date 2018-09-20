package com.lingnet.vocs.dao.impl.standard;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.standard.DetectionDao;
import com.lingnet.vocs.entity.Detection;
@Repository("detectionDao")
public class DetectionDaoImpl extends BaseDaoImplInit<Detection, String> 
                              implements DetectionDao{

}
