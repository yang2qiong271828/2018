package com.lingnet.vocs.action.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
/**
 * 检测标准
 * @ClassName: DetectionAction 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 下午5:07:53 
 *
 */
@Controller("detection")
@ParentPackage("standard")
@Namespace("/standard")
public class DetectionAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    /**
     * 设备检测标准
     * @Title: list 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    public String list(){
        return LIST;
    }
    
    /**
     * 增加检测标准
     * @Title: add 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    public String add(){
        return ADD;
    }
    
    /**
     * 编辑检测标准
     * @Title: edit 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    public String edit(){
        return ADD;
    }
    
    /**
     * 查看检测标准
     * @Title: look 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月5日 V 1.0
     */
    public String look(){
        return ADD;
    }
    
    /**
     * 删除检测标准
     * @Title: remove 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    public String remove(){
        return ajax(Status.success,"success");
    }
    
    /**
     * 棄用
     * 获取检测标准
     * @Title: getListData 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getListData(){
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("id", "1");
        map.put("place", "青岛");
        map.put("standard", "国标");
        map.put("customer", "客户1");
//        map.put("address", "青岛");
        map.put("param1", "3.6");
        /*map.put("param2", "1.2");
        map.put("param3", "5.7");
        map.put("param4", "7.5");*/
        map.put("param5", "2.4");
/*        map.put("status", 1);*/
        map.put("param6", "11.7");
        Map map1 = new HashMap();
        map1.put("id", "2");
        map1.put("place", "青岛");
        map1.put("standard", "省标");
        map1.put("customer", "客户2");
//        map.put("address", "青岛");*/
//        map1.put("param1", "5.9");
        map1.put("param2", "7.7");
        map1.put("param3", "6.4");
//        map1.put("param4", "8.8");
        map1.put("param5", "9.9");
/*        map.put("status", 1);*/
//        map1.put("param6", "13.8");
        list.add(map);
        list.add(map1);
        return ajax(Status.success,JsonUtil.Encode(list));
    }
    
    /**
     * 保存或更新检测标准
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月3日 V 1.0
     */
    public String saveOrUpdate(){
        return ajax(Status.success,"success");
    }
    
    /**
     * 地区列表页
     * @Title: placeList 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月7日 V 1.0
     */
    public String placeList(){
        return "placeList";
    }
    
    /**
     * 客户列表页
     * @Title: customerList 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月7日 V 1.0
     */
    public String customerList(){
        return "customerList";
    }
    
    /**
     * 获取地区数据
     * @Title: getPlaceDate 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月7日 V 1.0
     */
    public String getPlaceDate(){
        return ajax(Status.success,"success");
    }
    
    /**
     * 棄用
     * 获取污染物
     * @Title: getPollutant 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月8日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPollutant(){
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("text", "一氧化碳");
        Map map1 = new HashMap();
        map1.put("text", "二氧化碳");
        Map map2 = new HashMap();
        map2.put("text", "二氧化硫");
        Map map3 = new HashMap();
        map3.put("text", "染料尘");
        Map map4 = new HashMap();
        map4.put("text", "石英粉尘");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return ajax(Status.success,JsonUtil.Encode(list));
    }
    
    /**
     * 标准限值列表
     * @Title: limitValueList 
     * @return 
     * String 
     * @author TANGJW
     * @since 2017年6月9日 V 1.0
     */
    public String limitValueList(){
        return "limitValuelist";
    }
    
    
    
}
