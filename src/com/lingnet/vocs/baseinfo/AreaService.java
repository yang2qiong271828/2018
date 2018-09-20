package com.lingnet.vocs.service.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.Area;

/**
 * 
 * @ClassName: AreaService
 * @Description: 地区
 * @author adam
 * @date 2016-2-5 下午2:37:35
 *
 */
public interface AreaService extends BaseService<Area, String> {
    /**
     * 
     * @Title: selectJson 地区下拉选择的json数据 void
     * @author adam
     * @since 2016-2-5 V 1.0
     */
    public String selectJson();

    /**
     * 
     * @Title: getAll 获取地区树数据
     * @return List<HashMap<String,String>>
     * @author adam
     * @param partnerId 
     * @since 2016-2-16 V 1.0
     */
    public List<HashMap<String, String>> getAll(String partnerId,String pid,String key,String id);

    /**
     * 
     * @Title: saveOrUpdate 保存
     * @param area
     * @return String
     * @author adam
     * @since 2016-2-16 V 1.0
     */
    public String saveOrUpdate(Area area);

    /**
     * 
     * @Title: getArea 根据省市区的id数组获取 省市区全称
     * @param ids
     * @return String
     * @author adam
     * @since 2016-2-17 V 1.0
     */
    public String getArea(String[] ids);

    /**
     * 
     * @Title: getAreaList 获取地区及其父地区 集合
     * @param id
     * @return String
     * @author adam
     * @since 2016-2-22 V 1.0
     */
    public ArrayList<Area> getAreaList(String id);

	/**
	 * 根据父id 获取Chindren Area List
	 * 
	 * @Title: getChildrenArea
	 * @param pid
	 * @return
	 * @author zy
	 * @since 2017年6月22日 V 1.0
	 */
	ArrayList<HashMap<String, String>> getChildrenArea(String pid);

	/**
	 * 获取地区列表，懒加载
	 * 
	 * @Title: getAreaListLay
	 * @param pid
	 * @return
	 * @author zy
	 * @since 2017年6月22日 V 1.0
	 */
	List<HashMap<String, String>> getAreaListLazy(String pid);

    public List<HashMap<String, String>> getParentAll(String pid);

    public List<HashMap<String, String>> getAll();
    
    @SuppressWarnings("rawtypes")
	public List getCountOrder();

    public ArrayList areaChildren(List<Area> areaList, String string);
}
