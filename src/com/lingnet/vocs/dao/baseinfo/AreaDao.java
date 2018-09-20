package com.lingnet.vocs.dao.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.Area;

/**
 * 
 * @ClassName: AreaDao
 * @Description: 地区
 * @author adam
 * @date 2016-2-5 下午2:36:01
 *
 */
public interface AreaDao extends BaseDao<Area, String> {
    /**
     * 
     * @Title: getAllFullname
     * @return HashMap<String,String>
     * @author xuhp
     * @since 2016-2-23 V 1.0
     */
    public HashMap<String, String> getAllFullname(String userId);

    ArrayList<HashMap<String, String>> getChildrenArea(String pid);

	List<HashMap<String, String>> getAreaListLazy(String pid);

    public List<String[]> getChildrenList(String pid);
    
    public List getCountOrder();
}
