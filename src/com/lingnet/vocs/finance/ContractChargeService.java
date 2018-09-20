package com.lingnet.vocs.service.finance;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ContractCharge;

public interface ContractChargeService extends BaseService<ContractCharge, String> {

    void saveOrUpdate(ContractCharge contractCharge) throws Exception;

    @SuppressWarnings("rawtypes")
	HashMap getListData(Pager pager,String id,String htsf);
    
    String changeVerifyStatus(String amId, String verifyStatus) throws Exception;
}
