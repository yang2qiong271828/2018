/**
 * @PartnerService.java
 * @com.lingnet.vocs.service.partner
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年12月2日
 */

package com.lingnet.vocs.service.partner;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Partner;

/**
 * @ClassName: PartnerService
 * @Description: TODO
 * @author zhangyu
 * @date 2017年12月2日 下午3:38:55
 * 
 */

@SuppressWarnings("rawtypes")
public interface HbjService extends BaseService<Partner, String> {
	/**
	 * 保存或更新
	 * 
	 * @Title: saveOrUpdate
	 * @param partner
	 * @param contactsJson
	 * @throws Exception
	 * @author zy
	 * @since 2017年12月21日 V 1.0
	 */
	void saveOrUpdate(Partner partner, String contactsJson) throws Exception;

	/** 合作商列表
	 * @Title: getPartnerListData 
	 * @return 
	 * HashMap 
	 * @author zhangyu
	 * @since 2017年12月20日 V 1.0
	 */
	HashMap getMyPartnerListData(Pager pager, String key,String areaId);

    /**
     * @Title: getPartnerListData
     * @param pager
     * @param partnerId
     * @return
     * @author zy
     * @since 2017年7月24日 V 1.0
     */
    HashMap getPartnerListData(Pager pager, String partnerId,String areaId);

    /** 
     * 合作商联系人列表
     * @Title: getPicListData 
     * @param partnerId
     * @return 
     * HashMap 
     * @author zhangyu
     * @since 2017年12月20日 V 1.0
     */
    HashMap getContactListData(String partnerId);

	/**
	 * 输入合作商名称，立即验证唯一性
	 * 
	 * @Title: validateName
	 * @param name
	 * @return
	 * @author zy
	 * @since 2017年12月23日 V 1.0
	 */
	String validateName(String name) throws Exception;

	/**
	 * 删除
	 * 
	 * @Title: remove
	 * @param id
	 * @return
	 * @author zy
	 * @since 2017年12月23日 V 1.0
	 */
	String remove(String id) throws Exception;


	/**
	 * 获取合作商表的数据集合--所有
	 * @Title: partnerData 
	 * @param searchData
	 * @return 
	 * List<HashMap> 
	 * @author duanjj
	 * @since 2017年12月23日 V 1.0
	 */
    public List<HashMap<String, String>> partnerData(String searchData);

	/**
	 * 生成合作商Code
	 * 
	 * @Title: generateCode
	 * @param areaCode
	 * @return
	 * @author zy
	 * @since 2017年12月24日 V 1.0
	 */
	String generateCode(String areaCode);

    /**
     * @Title: getAllPartnerAndCustomer
     * @param pager
     * @param key
     *            ： 传入合作商/客户类型
     * @return
     * @author zy
     * @since 2017年12月30日 V 1.0
     */
    HashMap getPandC4FirstParty(Pager pager, String partnerId, String key);

    /**
     * 获取合作商拥有设备列表
     * 
     * @Title: getPartnerEquipList
     * @param pager
     * @param partnerId
     * @return
     * @author zy
     * @since 2017年7月6日 V 1.0
     */
    HashMap getPartnerEquipList(Pager pager, String partnerId);

    /**
     * 设备使用者页面 获取列表数据
     * 
     * @Title: getEquipUserList
     * @param pager
     * @return
     * @author zy
     * @since 2017年7月14日 V 1.0
     */
    HashMap getEquipUserList(Pager pager);

    /**
     * @Title: saveOrUpdateContacts
     * @param partnerId
     * @param partnerCode
     * @param pOrC
     * @param contactsJson
     * @author zy
     * @since 2017年8月2日 V 1.0
     */
    void saveOrUpdateContacts(String partnerId, String partnerCode,
            String pOrC, String contactsJson);

    /**
     * @Title: validateHigherAgent
     * @param id
     * @param chosenId
     * @return
     * @author zy
     * @since 2017年8月2日 V 1.0
     */
    String validateHigherAgent(String id, String chosenId);
}
