package com.lingnet.vocs.action.bominfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.vocs.entity.Goodcat;
import com.lingnet.vocs.entity.Item;
import com.lingnet.vocs.entity.Jcsj_Dw;
import com.lingnet.vocs.service.aominfo.DwService;
import com.lingnet.vocs.service.aominfo.ItemService;
/**
 * 物料
 * @ClassName: ItemAction 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月22日 下午4:02:46 
 *
 */
@Controller
@ParentPackage("bominfo")
public class ItemAction extends BaseAction {
    
    private static final long serialVersionUID = 7477437003520871974L;
    
    @Resource(name = "itemService")
    private ItemService itemService;
    
    @Resource(name = "dwService")
    private DwService dwService;
    
    @Resource(name = "lingUtil")
    private LingUtil lingUtil;
    
    private Item item; // 商品实体类
    private String data; // 保存和修改数据时用到的参数
    private String json; // json对象
    private String id; // 商品id
    private String itemCode; // 商品编号
    private Goodcat goodcatType; //商品种类
   // private JcsjCk jcsjCkreceive; //商品默认收货仓库
    private Jcsj_Dw jcsjDwstore; //商品库存单位
    private Jcsj_Dw poUnit; //商品采购单位
//    private JcsjCk jcsjCkwork; //商品默认生产仓区
//    private JcsjCk jcsjCkgood; //默认合格品生产仓区
//    private JcsjCk jcsjCkbad; //商品坏品生产仓区
    private Goodcat goodcat;//商品种类及编码设置
    private Goodcat goodcat1;//商品种类及编码设置
    private String custId;//客户id
    private String header;//商品种类前缀
    private String show;//展现标识
    private String LocId;//仓库id
    private String location;//货架位

    /**
     * 跳转到商品显示页面
     */
    public String list() {
        return LIST;
    }

    /**
     * 跳转到商品新增页面
     */
    public String add() {
        return "add";
    }

    public String item(){
        return "item";
    }
    public String item2() throws UnsupportedEncodingException{
        //begin modify by yc 2017-07-04 库存数量调整添加时删掉货架商品选择页显示为空
        if(location.equals("")){
            location="undefined";
        }
      //end modify by yc 2017-07-04 库存数量调整添加时删掉货架商品选择页显示为空
        location=URLDecoder.decode(location,"utf-8");
        String d=LocId;
        LocId=d;
        return "item2";
    }
    /**
     * 库存单位/采购单位/包装箱体积参数.长度单位/重量单位访问单位设置列表（锁定的）
     */
    public String getdataone() {
        return ajax(Status.success, JsonUtil.Encode(getBeanListByCriteria(Jcsj_Dw.class, Restrictions.eq("is_delete", "0"))));
    }
    
    /**
     * 库存单位/采购单位/包装箱体积参数.长度单位/重量单位访问单位设置列表（所有的）
     */
    public String getdataones() {
        return ajax(Status.success, JsonUtil.Encode(dwService.getAllList(Jcsj_Dw.class)));
    }
    /**
     * 保存更新商品信息
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-16
     */
    public String saveOrUpdate() throws Exception {
        try {
            return ajax(Status.success, itemService.saveOrUpdate(data,header));
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
    }

    /**
     * 跳转到商品编辑页面
     */
    public String edit() {
        item = itemService.getItem(id);
        Goodcat goodcat = itemService.get(Goodcat.class,Restrictions.eq("goodcatcode", item.getItemclassId()) );
        item.setItemclass(goodcat==null?"":goodcat.getGoodcatname());
        if (item == null) {
            return ajax(Status.error, "该单据已经被删除，请关闭页面。");
        } else {
            try {
               // goodcatType = lingUtil.getByGoodcatcode(item.getItemclassId());
              //  jcsjCkreceive = lingUtil.getCkById(item.getDefaultPurLocId());
            } catch (Exception e) {
                return ajax(Status.error, "服务器繁忙,请稍后再试!");
            }
            return "add";
        }
        
    }

    /**
     * 跳转到商品信息查看页面
     */
    public String look(){
        item =  itemService.getItem(id);
        Goodcat goodcat = itemService.get(Goodcat.class,Restrictions.eq("goodcatcode", item.getItemclassId()) );
        item.setItemclass(goodcat==null?"":goodcat.getGoodcatname());
        if (item == null) {
            return ajax(Status.error, "该单据已经被删除，请关闭页面。");
        } else {
            try {
               // goodcatType = lingUtil.getByGoodcatcode(item.getItemclassId());
                //jcsjCkreceive = lingUtil.getCkById(item.getDefaultPurLocId());
            } catch (Exception e) {
                return ajax(Status.error, "服务器繁忙,请稍后再试!");
            }
            return "add";
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String findbycode() throws Exception{
        Item itemc=getBeanByCriteria(Item.class, Restrictions.eq("itemCode", itemCode));
        HashMap map = new HashMap();
        if(itemc!=null){
        map.put("itemCode", itemc.getItemCode());
        map.put("name", itemc.getName());
        map.put("des1",itemc.getDes1());
        if(itemc.getUnit()!=null){
        map.put("unit", lingUtil.getUnit(itemc.getUnit()));
        }
        
//        LocStkLoc stk = null;
//        if (!"".equals(location) && location != null){
//            stk = itemService.get(LocStkLoc.class, Restrictions.eq("locId", LocId),Restrictions.eq("itemCode", itemCode),Restrictions.eq("location", location));
//        } else {
//            stk = itemService.get(LocStkLoc.class, Restrictions.eq("locId", LocId),Restrictions.eq("itemCode", itemCode));
//        }
//        
//        if(stk != null){
//            map.put("qty", stk.getQty());
//        } else {
//            map.put("qty", 0);
//        }
        
        return ajax(Status.success,JsonUtil.Encode(map));
        }else{
            return ajax(Status.success,"error");
        }
    }
    /**
     * 获得弹窗商品信息内容
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-16
     */
    public String treeshow() throws Exception{
        return ajax(Status.success,itemService.treeShow(pager,custId));
    }
    /**
     * 获得list页面商品信息内容
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-16
     * modify by 刘殿飞    2017-5-22 下午3:15
     */
    public String maintreeshow() throws Exception{
        if(pager.getSearchBy() != null){
            pager.setSearchBy(pager.getSearchBy()+",is_delete");
            pager.setKeyword(pager.getKeyword()+",0");
        } else {
            pager.setSearchBy("is_delete");
            pager.setKeyword("0");
        }
        return ajax(Status.success,itemService.maintreeshow(pager,custId));
    }
    
    public String maintreeshow2() throws Exception{
        
        String[] arr=pager.getKeyword().split(",");
        //解决中文乱码问题
        location=URLDecoder.decode(arr[4],"utf-8");
  //      location = new String(location.getBytes("ISO-8859-1"), "UTF-8");
        if(pager.getSearchBy() != null){
            pager.setSearchBy("itemCode,name,des1,des2"+",is_delete");
            pager.setKeyword(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+",0");
        } else {
            pager.setSearchBy("is_delete");
            pager.setKeyword("0");
        }
        return ajax(Status.success,itemService.maintreeshow2(pager,LocId,location));
    }

    
    /**
     * 获得list页面商品信息内容
     * lfp  2017-05-16
     */
    public String treeshow2() throws Exception{
        if(pager.getSearchBy() != null){
            pager.setSearchBy(pager.getSearchBy()+",is_delete");
            pager.setKeyword(pager.getKeyword()+",0");
        } else {
            pager.setSearchBy("is_delete");
            pager.setKeyword("0");
        }
        return ajax(Status.success,itemService.treeShow2(pager,LocId));
    }
    /**
     * 跳转到商品删除页面
     * @throws Exception 
     */
    public String delete() throws Exception {
            try {  
                return ajax(Status.success, itemService.getdelete(id));   
            } catch (Exception e) {
                return ajax(Status.error,e.getMessage());   
        }
    }
    
    /**
     * 商品财务分类添加页面
     * @since  1.0 
     */
    public String fiwlcwlx() {
        return "fiwlcwlx";
    }

    /* 属性的getter/setter方法 */
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Jcsj_Dw getJcsjDwstore() {
        return jcsjDwstore;
    }

    public void setJcsjDwstore(Jcsj_Dw jcsjDwstore) {
        this.jcsjDwstore = jcsjDwstore;
    }

    public Goodcat getGoodcatType() {
        return goodcatType;
    }

    public void setGoodcatType(Goodcat goodcatType) {
        this.goodcatType = goodcatType;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Jcsj_Dw getPoUnit() {
        return poUnit;
    }

    public void setPoUnit(Jcsj_Dw poUnit) {
        this.poUnit = poUnit;
    }

    public Goodcat getGoodcat() {
        return goodcat;
    }

    public void setGoodcat(Goodcat goodcat) {
        this.goodcat = goodcat;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Goodcat getGoodcat1() {
        return goodcat1;
    }

    public void setGoodcat1(Goodcat goodcat1) {
        this.goodcat1 = goodcat1;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLocId() {
        return LocId;
    }

    public void setLocId(String locId) {
        LocId = locId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
