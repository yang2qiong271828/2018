package com.lingnet.vocs.dao.finance;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ContractCharge;

public interface ContractChargeDao extends BaseDao<ContractCharge, String> {

    /**
     * @Title: findPagerContractChargeBySql
     * @param m
     * @param pager
     * @param partnerId
     * @return
     * @author zy
     * @param level 
     * @param id 
     * @since 2017年7月21日 V 1.0
     */
    Pager findPagerContractChargeBySql(Map<String, String> m, Pager pager,String partnerId, String id, Integer level,String htsf);
    
    /**
     * 费用统计
     * @Title: getSumData 
     * @return 
     * List<Object[]> 
     * @author 薛硕
     * @param m 
     * @since 2017年8月10日 V 1.0
     */
    public  List<Object[]> getSumData(Map<String, String> m, String partnerId);

}
