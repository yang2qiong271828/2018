package com.lingnet.vocs.service.finance;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Refund;

public interface RefundService extends BaseService<Refund, String> {

    /**
     * @Title: saveOrUpdate
     * @param refund
     * @author zy
     * @since 2017年7月21日 V 1.0
     */
    void saveOrUpdate(Refund refund);

    /**
     * @Title: getListData
     * @param pager
     * @return
     * @author zy
     * @since 2017年7月21日 V 1.0
     */
    @SuppressWarnings("rawtypes")
	HashMap getListData(Pager pager,String id);
    
    /**
     * @Title: changeVerifyStatus
     * @param amId
     * @param verifyStatus
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月21日 V 1.0
     */
    String changeVerifyStatus(String id, String verifyStatus) throws Exception;
}
