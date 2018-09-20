package com.lingnet.vocs.action.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Category;
import com.lingnet.vocs.service.baseinfo.CategoryService;

/**
 * 行业类别
 * @ClassName: CategoryAction 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月22日 下午4:03:37 
 *
 */
public class CategoryAction extends BaseAction {

	private static final long serialVersionUID = -739213555316086962L;
	
	@Resource(name = "categoryService")
    private CategoryService categoryService;
	private Category category;// 行业对象

	/**
	 * 展示页面
	 * @Title: list 
	 * @return 
	 * String 
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String list() {
		return "list";
	}

	
	/**
	 * 获取行业树数据
	 * @Title: getdata 
	 * @return 
	 * String 
	 * @author wsx
	 * @since 2016-3-11 V 1.0
	 */
    public String getdata() {
        List<HashMap<String, String>> data = categoryService.getAll();
        String json = JsonUtil.Encode(data);
        return ajax(Status.success, json);
    }
    
    /**
     * 获取非停用行业树数据
     * @Title: getData 
     * @return 
     * String 
     * @author 薛硕
     * @since 2017年6月22日 V 1.0
     */
    public String getData(){
        List<HashMap<String, String>> data = categoryService.getData();
        String json = JsonUtil.Encode(data);
        return ajax(Status.success, json);
    }
	

   /**
    * 删除
    * @Title: delete 
    * @return 
    * String 
    * @author 薛硕
    * @since 2017年6月22日 V 1.0
    */
    public String delete() {
        Category category = categoryService.get(Category.class,id);
        List<Category> list = categoryService.getList(Category.class,Restrictions.eq("baseid", category.getId()));
        if (list.size() > 0) {
            return ajax(Status.error, category.getName() + "--存在下级行业，请先删除下级行业！");
        }
        categoryService.delete(id);
        return ajax(Status.success, "success");
    }

   /**
    * 保存
    * @Title: saveOrUpdata 
    * @return 
    * String 
    * @author 薛硕
    * @since 2017年6月22日 V 1.0
    */
    public String saveOrUpdata()
    {
        if(category ==null)
        {
            return ajax(Status.success, "请重新操作！");
        }
        String message=categoryService.saveOrUpdate(category);
        return ajax(Status.success, message);
    }
    
    /**
     * 行业数据
     * @Title: selectJson 
     * @return 
     * String 
     * @author wsx
     * @since 2016-3-11 V 1.0
     */
    public String selectJson(){
        return ajax(Status.success,categoryService.selectJson());
    }
    
   /**
    *  行业树
    * @Title: getCategoryTreeData 
    * @return 
    * String 
    * @author 薛硕
    * @since 2017年6月22日 V 1.0
    */
    public String getCategoryTreeData(){
        String json = "";
        json = JsonUtil.Encode(categoryService.getAll());
        return ajax(Status.success, json);
    }
    
    /**
     * 行业树  制造业
     * @Title: getCategoryData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年2月8日 V 1.0
     */
    public String getCategoryData(){//制造业
        List<Category> cat = this.getBeanListByCriteria(Category.class, Restrictions.eq("baseid", "f39ccfb953a69f670153a6aa120e0005"));
        List<HashMap<String, String>> listMap = new ArrayList<HashMap<String,String>>();
        for(Category c : cat){
            HashMap map = new HashMap();
            map.put("id", c.getId());
            map.put("name", c.getName());
            map.put("pid", c.getBaseid());
            listMap.add(map);
        }
        String json = JsonUtil.Encode(listMap);
        return ajax(Status.success, json);
    }
//========================set/get ==========================//
    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

}
