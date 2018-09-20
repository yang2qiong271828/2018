/**
 * @PartnerDao.java
 * @com.lingnet.vocs.dao.partner
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.dao.partner;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Partner;

/**
 * @ClassName: PartnerDao
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月2日 下午3:37:19
 * 
 */

public interface PartnerDao extends BaseDao<Partner, String> {

	/**
	 * 根据合作商Id 获取联系人列表
	 * 
	 * @Title: getContactsByPartnerId
	 * @param partnerId
	 * @return
	 * @author zy
	 * @since 2017年6月26日 V 1.0
	 */
	List<Contacts> getContactsByPartnerId(String partnerId);

	/**
	 * 根据合作商Id 获取主联系人
	 * 
	 * @Title: getMainContactbyPartnerId
	 * @param partnerId
	 * @return
	 * @author zy
	 * @since 2017年6月26日 V 1.0
	 */
    List<Contacts> getMainContactbyPartnerId(String partnerId);
    
    /**
     * 根据查询条件，上级归属，合作商/客户类型，查询
     * 
     * @Title: getPartnerAndCustomerBySql
     * @param map
     *            ：查询条件
     * @param higherAgent
     *            ：若为null，则查询全部；若不为空，则查询当前higherAgent下属所有
     * @param partnerOrCustomer
     *            ：若为null，则查询全部合作商、客户
     * @return
     * @author zy
     * @since 2017年6月30日 V 1.0
     */
    List<Object[]> getPartnerAndCustomerBySql(Map<String, String> map, String higherAgent, String partnerOrCustomer);

    /**
     * higherAgent为null——全部合作商
     * 否则                                       ——当前合作商的下级
     * 
     * @Title: findPagerForPandCBySql
     * @param map
     *            ：查询条件
     * @param higherAgent
     *            ：若为null，则查询全部；若不为空，则查询当前higherAgent下属所有
     * @param partnerOrCustomer
     *            ：若为null，则查询全部合作商、客户
     * @param pager
     *            ：带分页
     * @return
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    Pager findPagerForPandCBySql(Map<String, String> map, String higherAgent,
            String partnerOrCustomer, Pager pager,String areaId);
    
    Pager findPagerForPandCBySql(Map<String, String> map, String higherAgent,
            String partner, Pager pager, String area,String areaId);

    // Pager findPagerHsj(Map<String, String> map, Pager pager);

    Pager findPagerEquipUserBySql(Map<String, String> m, Pager pager);

    /**
     * @Title: findPagerPartnerEquipList
     * @param m
     * @param pager
     * @param currentOwnerId
     * @return
     * @author zy
     * @since 2017年7月18日 V 1.0
     */
    Pager findPagerPartnerEquipList(Map<String, String> m, Pager pager,String currentOwnerId);

    /**
     * 使用hql语句查询id集合
     * @Title: findPartnerIdsByHql 
     * @param hql
     * @return 
     * List<String> 
     * @author TANGJW
     * @since 2017年7月19日 V 1.0
     */
    public List<String> findPartnerIdsByHql(String hql);

	Area findBySHQ(String province);
}
