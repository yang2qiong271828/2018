package com.lingnet.vocs.dao.baseinfo;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.DictionaryD;
/**
 * @ClassName: DataDictionaryDao 
 * @Description: TODO 
 * @author 刘殿飞
 * @date 2014-12-18 下午2：25 
 *
 */
public interface DataDictionaryDDao  extends BaseDao<DictionaryD, String>{
    
    
    Pager getAllOrderBySort(Pager pager,String id);
    
    String getName(String code,String value);

    @SuppressWarnings("rawtypes")
    Map getAllByCodes(String[] codes, boolean validflg);
    
}
