package com.lingnet.vocs.dao.baseinfo;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.Dictionary;
/**
 * @ClassName: DataDictionaryDao 
 * @Description: TODO 
 * @author 刘殿飞
 * @date 2014-12-18 下午2：25 
 *
 */
public interface DataDictionaryDao  extends BaseDao<Dictionary, String>{
    
    /**
     * 
     * @Title: getAllOrderBySort 数据字典树list
     * @return 
     * @author 刘殿飞
     * @since  V 1.0
     */
    public List<Dictionary> getAllOrderBySort();

    /**
     * 
     * @Title: getAllByCodes  根据code数组获取所有的 数据字典数据
     * @param codes    code数组
     * @param validflg 是否获取有效的数据
     * @return 
     * Map<String,Dictionary> 
     * @author adam
     * @since 2016-2-3 V 1.0
     */
    public Map<String, Dictionary> getAllByCodes(String[] codes, boolean validflg);
    
    @SuppressWarnings("rawtypes")
	Map getMapByCode(String[] codes);
    
    @SuppressWarnings("rawtypes")
	Map getReverseMapByCode(String[] codes);
}
