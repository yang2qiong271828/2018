package com.lingnet.vocs.service.reported;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Hkpact;
import com.lingnet.vocs.entity.Jhpact;
import com.lingnet.vocs.entity.Pact;

public interface PactService extends BaseService<Pact, String> {

    public String getData(Pager pager);

    public String saveOrUpdate(Pact pact, String attachmentdata) throws Exception;

    public String validateCode(String key) throws Exception;

    public String getHkData(Pager pager, String id);

    public String saveHk(Hkpact hkpact, String htId) throws Exception;

    public String saveJh(Jhpact jhpact, String htId) throws Exception;

    public String getJhData(Pager pager, String id);

    public String getJeBl(Double hkje, String type, Double proportion, String htId);


}
