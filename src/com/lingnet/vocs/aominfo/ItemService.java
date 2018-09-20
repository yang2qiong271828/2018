package com.lingnet.vocs.service.aominfo;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Item;
/**
 * 
 * @ClassName: ItemService 
 * @Description: 商品的业务类接口 
 * @author 张丽丽
 * @date 2013-10-11 上午8:18:29 
 *
 */

public interface ItemService extends BaseService<Item, String>{
    
    public  Item getItem(String id);
    
    /**
     * 根据商品编号查询商品信息
     * @pramat  wlbh  商品编号
     * @return  item  商品实体
     */
    public Item gentbycode(String wlbh);
    
    /**
     * 
     * @Title: beachSave 批量上传商品清单保存方法
     * @param list
     * @return
     * @throws Exception 
     * String 
     * @author 张丽丽
     * @since 2014-4-22 V 1.0
     */
    public String beachSave(String fromLine,String toLine,List<String[]> list) throws Exception;
    public String beachSave2(String fromLine,String toLine,List<String[]> list) throws Exception;

    /** 
     * @Title: 保存更新商品信息 
     * @param data
     * @param header 
     * @return 
     * String 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-16 V 1.0
     */
    public String saveOrUpdate(String data, String header) throws Exception;

    /** 
     * @Title: 获得弹窗页面商品信息内容
     * @param pager
     * @param custId
     * @return 
     * String 
     * @author 栾胜鹏  modify by 刘殿飞   2015-5-22 下午3:17
     * @since 2014-10-16 V 1.0 代码整理实现方法放到impl中 
     */
    public String treeShow(Pager pager, String custId);
    /** 
     * @Title: 获得list页面商品信息内容
     * @param pager
     * @param custId
     * @return 
     * String 
     * @author 栾胜鹏
     * @since 2014-10-16 V 1.0 代码整理实现方法放到impl中 
     */
    public String maintreeshow(Pager pager, String custId);
    public String maintreeshow2(Pager pager, String custId,String location);
    public String treeShow2(Pager pager, String custId);

    /**
     * 根据物料名称查询物料编号集合
     * @param name
     * @return
     * @author duanjj
     */
    public String findItems(String name);

    public String getdelete(String id) throws Exception;

}
