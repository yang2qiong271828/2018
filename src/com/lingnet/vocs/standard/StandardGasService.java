package com.lingnet.vocs.service.standard;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.StandardGas;

public interface StandardGasService extends BaseService<StandardGas,String> {

    public String saveOrUpdate(StandardGas standardGas) throws Exception;

}
