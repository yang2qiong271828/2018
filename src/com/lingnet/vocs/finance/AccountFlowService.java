package com.lingnet.vocs.service.finance;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AccountFlow;

@SuppressWarnings("rawtypes")
public interface AccountFlowService extends BaseService<AccountFlow, String> {
    /**
     * 账务流水：获取列表数据
     * 
     * @Title: getListData
     * @param pager
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    HashMap getListData(Pager pager, String areaId);

    /**
     * 生成账务流水编号
     * 
     * @Title: generateAfCode
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    String generateAfCode();
}
