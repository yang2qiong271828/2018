package com.lingnet.vocs.dao.bominfo;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.Item;

/**
 * 
 * @ClassName: ItemDao 
 * @Description: 商品的dao接口
 * @author 张丽丽
 * @date 2013-10-11 上午8:21:31 
 *
 */

public interface ItemDao extends BaseDao<Item, String>{
    
    public Item getItem(String id);
    
    /**
     * 根据商品编号查询商品信息
     * @pramat  wlbh  商品编号
     * @return  item  商品实体
     */
    public Item gentbycode(String wlbh);
    
    
    public Item getbyName(String name,String itemclassId,String des1);
    

    /**
     * 根据物料名称查询物料集合
     * @param name
     * @return
     *  @author duanjj
     */
    public String finItems(String name);
    
    public String findSequence();
}

