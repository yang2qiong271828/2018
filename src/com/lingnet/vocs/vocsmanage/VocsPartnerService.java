package com.lingnet.vocs.service.vocsmanage;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.VocsPartner;

public interface VocsPartnerService extends BaseService<VocsPartner, String> {

    
    @SuppressWarnings("rawtypes")
	HashMap getListData(Pager pager);

}
