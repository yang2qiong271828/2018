/**
 * @EquipmentDao.java
 * @com.lingnet.vocs.dao.equipment
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.dao.equipment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Equipment;

/**
 * @ClassName: EquipmentDao
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月2日 下午6:33:25
 * 
 */

public interface EquipmentDao extends BaseDao<Equipment, String> {

    /**
     * @Title: getAirVolumeByEquipment
     * @param map
     * @return String
     * @author wanl
     * @since 2017年6月29日 V 1.0
     */
    public String getAirVolumeByEquipment(HashMap<String, Object> map);

    /**
     * @Title: getAirVolume
     * @param map
     * @return String
     * @author wanl
     * @since 2017年6月29日 V 1.0
     */
    public String getAirVolume(HashMap<String, Object> map);

    /**
     * @Title: savePlc
     * @param id
     * @return String
     * @author wanl
     * @since 2017年7月7日 V 1.0
     */
    public String savePlc(String id, HashMap<String, Object> map);

    public Pager getEqByconData(Pager pager, String partnerId);

    Pager findPagerEquipment(Map<String, String> m, Pager pager,
            String partnerId);
    
    public List<Object[]> getcd(Pager pager,String partnerId,String areaId,Map<String, String> m);
}
