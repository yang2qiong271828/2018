package com.lingnet.vocs.service.vocsmanage;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.VocsChange;

public interface VocsChangeService extends BaseService<VocsChange, String> {

    
    @SuppressWarnings("rawtypes")
	HashMap getListData(Pager pager);

}
