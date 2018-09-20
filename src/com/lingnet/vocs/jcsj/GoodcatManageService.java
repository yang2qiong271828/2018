/*
 * @file GoodcatManageService.java
 * 文件摘要.
 * 文件的详细说明
 * :  
 * @author 刘青勇
 * @copyright     青岛一凌网集成有限公司2013
 * @version 20120101 刘青勇  v1.0
 */
package com.lingnet.vocs.service.jcsj;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Goodcat;

/*************************************************/
/*
 * GoodcatManageService
 * 摘要说明.
 * @Description:
 *  :
 * @author 刘青勇
 * @version 2013-9-12 
 */
/*************************************************/
public interface GoodcatManageService extends BaseService<Goodcat, String> {

    public List<HashMap<String, String>> getTreeList();
    
    public List<HashMap<String, String>> getTreeData();
    public List<HashMap<String, String>> getTreeData1();
    public Goodcat getByGoodcatcode(String goodcatcode);
    
    public int isHave(Pager pager,String goodcatcode,String flag) throws Exception;

    /** 
     * @Title: 保存更新数据
     * @param id
     * @param parent 
     * @param connector 
     * @param codeheader 
     * @param goodcatname 
     * @param goodcatcode 
     * @param child 
     * @return 
     * String 
     * @author 栾胜鹏 
     * @since 2014-10-16 V 1.0  代码整理实现方法放到impl中
     */
    public String saveOrUpdate(String id, String goodcatcode, String goodcatname, String codeheader, String connector, String parent, String child)throws Exception;
    
    /**
     * 导入产品类别
     * @Title: beachSave 
     * @param fromLine
     * @param toLine
     * @param list
     * @return
     * @throws Exception 
     * String 
     * @author wsx
     * @since 2016-1-14 V 1.0
     */
    public String beachSave(String fromLine,String toLine,List<String[]> list) throws Exception;
}
