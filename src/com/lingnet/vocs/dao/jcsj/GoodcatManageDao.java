/*
 * @file GoodcatManageDao.java
 * 文件摘要.
 * 文件的详细说明
 * :  
 * @author 刘青勇
 * @copyright     青岛一凌网集成有限公司2013
 * @version 20120101 刘青勇  v1.0
 */
package com.lingnet.vocs.dao.jcsj;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Goodcat;

/*************************************************/
/*
 * GoodcatManageDao
 * 摘要说明.
 * @Description:
 *  :
 * @author 刘青勇
 * @version 2013-9-12 
 */
/*************************************************/
public interface GoodcatManageDao extends BaseDao<Goodcat, String> {

    public Goodcat getByGoodcatcode(String goodcatcode);
    
    public Goodcat getByGoodcatName(String goodcatName);
    
    public Pager findPagerByCode(Pager pager,String goodcatcode,String flag) throws Exception ;
    
    /**
     * 导入
     * @Title: beachAdd 
     * @param fromLine
     * @param toLine
     * @param list
     * @return
     * @throws Exception 
     * String 
     * @author wsx
     * @since 2016-1-14 V 1.0
     */
    public String beachAdd(String fromLine,String toLine,List<String[]> list) throws Exception;
}
