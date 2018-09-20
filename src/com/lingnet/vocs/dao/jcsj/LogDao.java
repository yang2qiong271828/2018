package com.lingnet.vocs.dao.jcsj;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: LogDao 
 * @Description: 日志管理dao 
 * @author 姜平豹
 * @date 2014-3-26 上午10:07:02 
 *
 */
public interface LogDao extends BaseDao<QxLog,String> {
	@SuppressWarnings("rawtypes")
    public List<HashMap> search(Pager pager, HashMap<String, String> searchMap);
	/**
	 * 
	 * @Title: Operate 
	 * @param djType单据类型
	 * @param czType操作类型：增加，修改，删除，审核，查看
	 * @return 
	 * String 
	 * @author 姜平豹
	 * @since 2014-3-26 V 1.0
	 */
	public String Operate(String djType,String czType);
}
