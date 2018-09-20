/**
 * @ContractService.java
 * @com.lingnet.vocs.service.contract
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.service.finance;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Contract;

/**
 * @ClassName: ContractService
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月12日 下午4:22:40
 * 
 */

public interface ContractService extends BaseService<Contract, String> {

   public  String getShowContractListData(Pager pager, String key, String partnerId);

    /**
     * @Title: getContractListData
     * @param pager
     * @param key
     * @return
     * @author zy
     * @since 2017年6月28日 V 1.0
     */
    @SuppressWarnings("rawtypes")
	HashMap getContractListData(Pager pager, String key,String id,String dhbz);

    /**
     * 合同以及附件，保存、更新
     * 
     * @Title: saveOrUpdate
     * @param contract
     * @param attachmentdata
     * @throws Exception
     * @author zy
     * @since 2017年6月30日 V 1.0
     */
    void saveOrUpdate(Contract contract, String attachmentdata)
            throws Exception;

    /**
     * 合同保存驳回意见
     * 
     * @Title: saveRejectReason
     * @param contract
     * @author zy
     * @since 2017年7月5日 V 1.0
     */
    void saveRejectReason(Contract contract) throws Exception;

    /**
     * 合同删除
     * 
     * @Title: remove
     * @param id
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年6月28日 V 1.0
     */
    String remove(String id) throws Exception;

    /**
     * 修改合同审核状态
     * 
     * @Title: changeVerifyStatus
     * @param contractId
     * @param verifyStatus
     * @return
     * @author zy
     * @throws Exception 
     * @since 2017年7月1日 V 1.0
     */
    String changeVerifyStatus(String contractId, String verifyStatus) throws Exception;
    /**
     * 到期合同提醒数据
     * @Title: getContractListData 
     * @param pager
     * @return 
     * String 
     * @author xuhp
     * @since 2017-7-11 V 1.0
     */
    String getContractListData(Pager pager);

    /**
     * 验证合同编号唯一性
     * 
     * @Title: validateCode
     * @param name
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    String validateCode(String code) throws Exception;

    /**
     * @Title: getContractByEquipment
     * @param pager
     * @param eqId
     * @return
     * @author zy
     * @since 2017年7月18日 V 1.0
     */
    String getContractByEquipment(Pager pager, String eqId, String partnerId);
    /**
     * 处理到期状态
     * @Title: dispose 
     * @param id
     * @return 
     * String 
     * @author lyz
     * @since 2017年10月13日 V 1.0
     */
    public String dispose(String id);
}
