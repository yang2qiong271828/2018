/*
 * @file GoodcatManageDaoImpl.java
 * 文件摘要.
 * 文件的详细说明
 * :  
 * @author 刘青勇
 * @copyright     青岛一凌网集成有限公司2013
 * @version 20120101 刘青勇  v1.0
 */
package com.lingnet.vocs.dao.impl.jcsj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.jcsj.GoodcatManageDao;
import com.lingnet.vocs.entity.Goodcat;
import com.lingnet.vocs.entity.Item;

/*************************************************/
/*
 * GoodcatManageDaoImpl
 * 摘要说明.
 * @Description:
 *  :
 * @author 刘青勇
 * @version 2013-9-12 
 */
/*************************************************/
@Repository("goodcatManageDao")
public class GoodcatManageDaoImpl extends  BaseDaoImplInit<Goodcat, String> implements GoodcatManageDao {

    private static int seq = 0;
    private static long currentTime = 0;
    
    /**
     * 商品类型代码查询
     */
    @Override
    public Goodcat getByGoodcatcode(String goodcatcode){
        
        String Hql = "from Goodcat as goodcat where goodcat.goodcatcode = ? and  goodcat.is_delete = '0' "; 
       
        Goodcat goodcat=uniqueResult(Hql, goodcatcode);
        return goodcat;
    }
    
    
    /**
     * 分页查询
     */
    @Override
    public Pager findPagerByCode(Pager pager,String pgoodcatcode,String flag) throws Exception {
        //声明Criteria
        Criteria criteria=getSession().createCriteria(Item.class);
        //Criteria执行查询
        criteria.add(Restrictions.eq("is_delete", "0"));
        if("goodcat".equals(flag)){
           criteria.add(Restrictions.eq("itemclassId",(pgoodcatcode.trim())));
        }else{
           criteria.add(Restrictions.eq("unit",(pgoodcatcode.trim())));
        }
        pager = findPager(pager,criteria);
        return pager;
    }


    @Override
    public Goodcat getByGoodcatName(String goodcatName) {
        String Hql = "from Goodcat as goodcat where goodcat.goodcatname = ? and is_delete = '0'"; 
        
        return uniqueResult(Hql, goodcatName);
    }


    @Override
    public String beachAdd(String fromLine, String toLine, List<String[]> list)
            throws Exception {
        SimpleDateFormat sdf= new SimpleDateFormat("yyMMddHHmmss");
        Date datem=new Date();
        
        String sql = "insert into jcsj_wllx(id,createdate,modifydate,BMQZ,LJF,WLLXDM,WLLXMC,P_WLLXDM,IS_DELETE)"
                                    +"VALUES(?,?,?,?,?,?,?,?,?)";
        
        this.getSession().connection().setAutoCommit(false);
        PreparedStatement prest = this .getSession().connection().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        Long isLocked = 0l;
        String allowValue="true";
        for (int i = 0; i < list.size(); i++) {
            
            String[] str = new String[5];
            String[] values = list.get(i);
            if(values.length>5){
                throw new Exception("请选择正确的产品类别导入文件！");
            }
            for(int q=0;q<values.length;q++){
                str[q]=values[q];
            }
            for(int k=values.length;k<4;k++){
                str[k]="";
            }
            
            //得到唯一ID
            currentTime = System.currentTimeMillis();
            long t1 = System.currentTimeMillis();
            if (Math.abs(Math.abs(t1) - Math.abs(currentTime)) < 1) {
                if ((seq + 1) >= 100) {
                    seq = 0;
                }
                seq++;
            }
            
            StringBuilder id = new StringBuilder();
            id.append(t1);
            id.append(seq);
            prest.setString(1, id.toString());
           
           
            //创建时间，修改时间
            prest.setDate(2, new java.sql.Date(datem.getTime()));
            prest.setDate(3, new java.sql.Date(datem.getTime()));

            
            int a= Integer.parseInt(fromLine);
            for (int j = 0; j <str.length; j++) {
                if(j==0){
                    if(str[0]!=null && !"".equals(str[0])){
                        if(str[0].length()>50){
                            return "导入数据：第"+(i+a)+"行，</br>商品种类代号大于50字符！导入失败！";
                        }
                       //begain zrl  2016-7-4  筛选商品种类代号重复
                        StringBuilder xdm=new StringBuilder()
                        .append(" SELECT q.BMQZ,q.WLLXDM,q.WLLXMC ")
                        .append(" FROM JCSJ_WLLX q ")
                        .append(" WHERE WLLXDM = '"+str[0]+" ' and is_delete ='0' ")
                        ;
                        SQLQuery query=this.getSession().createSQLQuery(xdm.toString());
                        List resultxdm=query.list();
                        if(null == resultxdm || resultxdm.size() ==0){
                            prest.setString(6, str[0]);//商品种类代号
                        }
                        else{
                            return "导入数据：第"+(i+a)+"行，</br>商品种类代号与之前重复！</br>商品种类代号不能重复，导入失败！";
                        }
                      //end zrl  2016-7-4  筛选商品种类代号重复
                    }else{
                        return "导入数据：第"+(i+a)+"行，</br>商品种类代号为空！</br>商品种类代号不能为空，导入失败！";
                    }
                }
                if(j==1){
                    if(str[1].length()>50){
                        return "导入数据：第"+(i+a)+"行，</br>商品种类名称大于50字符！导入失败！";
                    }
                    if(str[1]!=null && !"".equals(str[1])){
                       //begain mzm 2016/7/16 原因  商品代号，名称，前缀，各不能与数据库相同！
                      
                           Goodcat goodcatna=this.get(Restrictions.eq("goodcatname", str[1].toString()),Restrictions.eq("is_delete", "0"));
                        if(goodcatna!=null && Goodcat.DELETE != goodcatna.getIs_delete()){
                            throw new Exception("导入数据：第"+(i+a)+"行，</br>商品种类名称与之前重复！</br>商品种类名称不能重复，导入失败！"); 
                        }
                       
                        
                      //end mzm 2016/7/16 原因  商品代号，名称，前缀，各不能与数据库相同！
                        prest.setString(7, str[1]);//商品种类名称
                    }else{
                        return "导入数据：第"+(i+a)+"行，</br>商品种类名称为空！</br>商品种类名称不能为空，导入失败！";
                    }
                }
                
                if(j==2){
                    if(str[2]!=null && !"".equals(str[2])){
                      //begain mzm 2016/7/16 原因  商品代号，名称，前缀，各不能与数据库相同！
                        Goodcat codehead=this.get(Restrictions.eq("codeheader", str[2].toString()),Restrictions.eq("is_delete", "0"));
                        if(codehead!=null && Goodcat.DELETE != codehead.getIs_delete()){
                            throw new Exception("导入数据：第"+(i+a)+"行，</br>编码前缀与之前重复！</br>编码前缀不能重复，导入失败！"); 
                        }
                      //end mzm 2016/7/16 原因  商品代号，名称，前缀，各不能与数据库相同！
                        prest.setString(4, str[2]);//编码前缀
                    }else{
                        return "导入数据：第"+(i+a)+"行，</br>编码前缀为空！</br>编码前缀不能为空，导入失败！";
                    }
                }
                if(j==3){
                    if("-".equals(str[3])){
                        prest.setInt(5, 1);
                    } else {
                        prest.setInt(5, 0);
                    }
                    
                }
                //begain mzm 2016/7/25  商品父类导入
                if(j==4){
                    if(str[4]!=null && !"".equals(str[4])){
                        Goodcat goodcatna=this.get(Restrictions.eq("goodcatname", str[4].toString()));
                        if(goodcatna!=null && Goodcat.DELETE != goodcatna.getIs_delete()){
                            prest.setString(8, str[4]);
                        }else{
                            throw new Exception("导入数据：第"+(i+a)+"行，</br>没有该商品种类名称！</br>请核对父类名称！导入失败！"); 
                        }
                       
                    } else {
                        prest.setString(8, "all");
                    }
                    
                }
               //end mzm 2016/7/25  商品父类导入
            }
            
            //未删除状态
            prest.setString(9, isLocked.toString());
            
            prest.addBatch();
            
       
        }
        try{
            prest.executeBatch();
        }catch (Exception e){
            throw new Exception("上传格式不正确");
        }
        this.getSession().connection().commit();
        
        return "success";
    }
}
