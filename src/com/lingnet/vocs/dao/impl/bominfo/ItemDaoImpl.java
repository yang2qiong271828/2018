package com.lingnet.vocs.dao.impl.bominfo; 
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.dao.bominfo.DwDao;
import com.lingnet.vocs.dao.bominfo.ItemDao;
import com.lingnet.vocs.dao.jcsj.GoodcatManageDao;
import com.lingnet.vocs.entity.Goodcat;
import com.lingnet.vocs.entity.Item;
import com.lingnet.vocs.entity.Jcsj_Dw;

/**
 * 
 * @ClassName: ItemDaoImpl 
 * @Description: 商品的dao接口实现类  
 * @author 张丽丽
 * @date 2017-10-11 上午8:19:31 
 *
 */

@Repository("itemDao")
public class ItemDaoImpl extends BaseDaoImplInit<Item, String> implements ItemDao{
    
    @SuppressWarnings("rawtypes")
    @Resource(name = "baseService")
    private BaseService baseService;
    
    
    @Resource(name = "goodcatManageDao")
    private GoodcatManageDao goodcatManageDao;
    
    @Resource(name="dwDao")
    private DwDao dwDao;
    
//    @Resource(name = "locStkLocDao")
//    private LocStkLocDao locStkLocDao;
    
    private Goodcat goodcat;//商品种类及编码设置
    private Jcsj_Dw dw;//商品种类及编码设置
    
    private static int seq = 0;
    private static long currentTime = 0;
    
    /**
     * 根据id查询某一条商品数据
     */
    @Override
    public Item getItem(String id) {
        String HQL="FROM Item as item WHERE item.id=?";
        return uniqueResult(HQL,id);
    }
    
    /**
     * 根据商品编号查询商品信息
     * @pramat  wlbh  商品编号
     * @return  item  商品实体
     */
    @Override
    public Item gentbycode(String wlbh){ 
        Item Item=null; 
        if(wlbh!=null&&!("".equals(wlbh))){ 
            Criteria criteria = getSession().createCriteria(Item.class);   
            criteria.add(Restrictions.eq("itemCode", wlbh));  
            criteria.add(Restrictions.eq("is_delete", "0"));
            Item=(Item)criteria.uniqueResult();
        }
        return Item;
    }

   

    @Override
    public Item getbyName(String name, String itemclassId, String des1) {
        String Hql = "from item where name = ? and itemclassId = ? and des1 = ? and is_delete = '0'"; 
        
        return uniqueResult(Hql, name, itemclassId, des1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String finItems(String name) {
        String sql = "select item_code from item where name like '%"+name+"%'";
        SQLQuery query=this.getSession().createSQLQuery(sql.toString());
        List<Object>  list = query.list();
        String itemCode = "";
        for(int i=0;i<list.size();i++){
            itemCode = list.get(i).toString()+",";
        }
        if(!"".equals(itemCode)){
            itemCode = itemCode.substring(0, itemCode.length()-1);
        }
        return itemCode;
    }
    /**
     * 商品编码生成公共方法
     * @Title: getCode 
     * @param goodcat
     * @param hashmap
     * @return 
     * String 
     * @author mazm
     * @throws Exception 
     * @since 2016-7-29 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String getCode(Goodcat goodcat, HashMap hashmap) throws Exception {
        String Codeheader = null;
        String Goodcatname = null;
        String itemcode = null;
        Codeheader = goodcat.getCodeheader();
        Goodcatname = goodcat.getGoodcatname();
            if (hashmap.size()>0&&hashmap.get(Goodcatname)!=null) {
               System.out.println(hashmap.get(Goodcatname).toString());
                String aaa =hashmap.get(Goodcatname).toString();
                aaa = aaa.substring(Codeheader.length() + 1);
                Long bb = Long.valueOf(aaa) + 1;
                String cc = bb + "";
                StringBuffer sb = null;
                for (int z = cc.length(); z < 8; z++) {
                    sb = new StringBuffer();
                    sb.append("0").append(cc);// 左(前)补0
                    // sb.append(cc).append("0");//右(后)补0
                    cc = sb.toString();
                }
                itemcode = Codeheader+"-"+cc;
                hashmap.put(Goodcatname,itemcode);
            } else {
                StringBuilder sqls = new StringBuilder();
                int code=Codeheader.length() + 9;
                sqls.append("  SELECT max(item_code) FROM item WHERE item_code LIKE '%"
                        + Codeheader
                        + "-%'  AND LENGTH(ITEM_CODE)="
                        + code + "  ");
                SQLQuery query = this.getSession().createSQLQuery(
                        sqls.toString());
                List<Object> aa = query.list();
                try{
                if (aa.size() > 0) {
                    if (aa.get(0) != null) {
                        String s = aa.get(0).toString();
                        s = s.substring(Codeheader.length() + 1);
                        
                        Long bb = Long.valueOf(s) + 1;
                        
                        String cc = bb + "";
                        int strLen = cc.length();
                        int stra = s.length();
                        StringBuffer sb = null;
                        for (int z = strLen; z < 8; z++) {
                            sb = new StringBuffer();
                            sb.append("0").append(cc);// 左(前)补0
                            // sb.append(cc).append("0");//右(后)补0
                            cc = sb.toString();
                        }
                        itemcode=Codeheader+"-"+cc;
                        hashmap.put(Goodcatname, itemcode);    
                    }else{
                        itemcode=Codeheader+"-00000001";
                        hashmap.put(Goodcatname, itemcode);
                    }
                }
                }catch (Exception e) {
                    // TODO: handle exception
                    throw new Exception("上传表格中有未加商品编码商品，商品编码超出系统自动生成范围，请自行编码再上传！");
                }
            }
        return itemcode;
    }
    @Override
    public String findSequence() {
      String sql=   "SELECT NEXT VALUE FOR ItemCode;";
       Object result = this.getSession().createSQLQuery(sql).uniqueResult();
        return result.toString();
    }
}
