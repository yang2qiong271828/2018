package com.lingnet.vocs.service.jcsj;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: LogService 
 * @Description: 日志管理 
 * @author 姜平豹
 * @date 2014-3-26 上午9:34:06 
 *
 */
public interface LogService extends BaseService<QxLog, String> {
    @SuppressWarnings("rawtypes")
    public HashMap search(Pager pager, HashMap<String, String> searchMap);
    
    public String operate(String djType,String czType,String czdj);
}
