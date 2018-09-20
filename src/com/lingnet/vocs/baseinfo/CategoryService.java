package com.lingnet.vocs.service.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.Category;

/**
 * 行业类别
 * @ClassName: CategoryService 
 * @Description: TODO 
 * @author wsx
 * @date 2016-3-11 上午10:49:32 
 *
 */
public interface CategoryService extends BaseService<Category, String>{
    /**
     * 
     * @Title: selectJson  地区下拉选择的json数据
     * void 
     * @author adam
     * @since 2016-2-5 V 1.0
     */
    public String selectJson();

    /**
     * 
     * @Title: getAll 获取地区树数据
     * @return 
     * List<HashMap<String,String>> 
     * @author adam
     * @since 2016-2-16 V 1.0
     */
    public List<HashMap<String, String>> getAll();
    
    public List<HashMap<String, String>> getData();

    /**
     * 
     * @Title: saveOrUpdate 保存
     * @param area
     * @return 
     * String 
     * @author adam
     * @since 2016-2-16 V 1.0
     */
    public String saveOrUpdate(Category area);

    /**
     * 
     * @Title: getCategory 根据省市区的id数组获取 省市区全称
     * @param ids
     * @return 
     * String 
     * @author adam
     * @since 2016-2-17 V 1.0
     */
    public String getCategory(String[] ids);

    /**
     * 
     * @Title: getCategoryList 获取地区及其父地区 集合
     * @param id
     * @return 
     * String 
     * @author adam
     * @since 2016-2-22 V 1.0
     */
    public ArrayList<Category> getCategoryList(String id);

}
