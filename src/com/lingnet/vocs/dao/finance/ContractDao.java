/**
 * @ContractDao.java
 * @com.lingnet.vocs.dao.contract
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.dao.finance;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Contract;

/**
 * @ClassName: ContractDao
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月12日 下午4:36:25
 * 
 */

public interface ContractDao extends BaseDao<Contract, String> {

    public  List<Object[]> findCotByParAndEqu(String partnerId, Map<String, String> m);

    /**
     * @Title: getContractsBySql
     * @param map
     * @return
     * @author zy
     * @since 2017年7月13日 V 1.0
     */
    List<Object[]> getContractsBySql(Map<String, String> map);

    /**
     * @Title: findPagerContractsBySql
     * @param map
     * @param pager
     * @param approveFlag
     * @param userId
     * @param partnerId
     * @return
     * @author zy
     * @param integer 
     * @since 2017年7月13日 V 1.0
     */
    Pager findPagerContractsBySql(Map<String, String> map, Pager pager,
            boolean approveFlag, String userId, String partnerId,String id, Integer level,String dhbz);
    
    List<Object[]> findContractByEquipment(String partnerId,
            Map<String, String> m, String eqId);

    public Pager findContractByEquipment(Pager pager,String partnerId, String eqId);
    
    /**
     * @Title: findContractByEquipment2
     * @param pager
     * @param partnerId
     *            ——合同的甲方，companyId字段
     * @param eqId
     * @return
     * @author zy
     * @since 2017年7月21日 V 1.0
     */
    Pager findContractByEquipment2(Pager pager,String partnerId, String eqId);

    /**
     * @Title: findContractApproveFlag
     * @param userName
     * @return
     * @author zy
     * @since 2017年7月21日 V 1.0
     */
    boolean findContractApproveFlag(String userName);

}
