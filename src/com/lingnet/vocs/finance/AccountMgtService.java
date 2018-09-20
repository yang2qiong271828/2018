package com.lingnet.vocs.service.finance;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AccountMgt;

public interface AccountMgtService extends BaseService<AccountMgt, String> {
    /**
     * 列表数据
     * 
     * @Title: getListData
     * @param pager
     * @param key
     * @return
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    @SuppressWarnings("rawtypes")
	HashMap getListData(Pager pager, String key,String id,String sfbz);

    /**
     * 保存或更新
     * 
     * @Title: saveOrUpdate
     * @param accountMgt
     * @throws Exception
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    void saveOrUpdate(AccountMgt accountMgt) throws Exception;

    /**
     * @Title: remove
     * @param id
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月17日 V 1.0
     */
    String remove(String id) throws Exception;

    /**
     * @Title: changeVerifyStatus
     * @param amId
     * @param verifyStatus
     * @return
     * @author zy
     * @since 2017年7月20日 V 1.0
     */
    String changeVerifyStatus(String amId, String verifyStatus)throws Exception;

    /**
     * @Title: validateCode
     * @param code
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月20日 V 1.0
     */
    String validateCode(String code) throws Exception;
}
