/**
 * @IntialDaoImpl.java
 * @com.lingnet.lneterp.dao.impl.jcsj
 * @Description：
 * 
 * @author 栾胜鹏 
 * @copyright  2014
 * @version V
 * @since 2014-12-3
 */
package com.lingnet.vocs.dao.impl.jcsj;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.jcsj.InitialDao;

/** 
 *
 * @ClassName: IntialDaoImpl 
 * @Description: TODO 
 * @author 栾胜鹏
 * @date 2014-12-3 上午11:07:57 
 *  
 */
@Repository("initialDao")
public class InitialDaoImpl extends BaseDaoImplInit<String, String> implements InitialDao {

    /**
     * 执行sql文件中的sql语句,根据数据库类型判断sql语句
     */
    @Override
    public String excute(List<String> list,String dataPro) throws Exception {
        if(dataPro.equals("oracle")){
            for(String sql : list){
                JdbcTemplate jt=getJdbcTemplate();
                
                jt.execute(sql); 
            }
        }else if(dataPro.equalsIgnoreCase("mysql")){
            for(String sql : list){
                try {
                    JdbcTemplate jt=getJdbcTemplate();
                    jt.execute(sql+";"); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } 
        }
        return "success"; 
    }

}
