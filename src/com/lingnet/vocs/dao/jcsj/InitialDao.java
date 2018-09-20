/**
 * @IntialDao.java
 * @com.lingnet.lneterp.dao.jcsj
 * @Description：
 * 
 * @author 栾胜鹏 
 * @copyright  2014
 * @version V
 * @since 2014-12-3
 */
package com.lingnet.vocs.dao.jcsj;

import java.util.List;

/** 
 * @ClassName: IntialDao 
 * @Description: TODO 
 * @author 栾胜鹏
 * @date 2014-12-3 上午11:07:21 
 *  
 */

public interface InitialDao {

    /** 执行sql文件中的sql语句
     * @Title: excute 
     * @param list
     * @return 
     * String 
     * @author 栾胜鹏
     * @param dataPro 
     * @since 2014-12-3 V 1.0
     */
    String excute(List<String> list, String dataPro)throws Exception;

}
