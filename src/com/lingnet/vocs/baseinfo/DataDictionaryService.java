package com.lingnet.vocs.service.baseinfo;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.Dictionary;
import com.lingnet.vocs.entity.DictionaryD;
/**
 * 数据字典service
 * @ClassName: DataDictionaryService 
 * @Description: TODO 
 * @author 刘殿飞
 * @date 2014-12-15 下午2:42:49 
 *
 */
public interface DataDictionaryService extends BaseService<Dictionary, String>{
    
    
    /**
     * 
     * @Title: getAllOrderBySort 
     * @return 
     * List<Dictionary> 
     * @author fanxw
     * @since 2014-12-15 V 1.0 modify by 刘殿飞
     */
    public List<Dictionary> getAllOrderBySort();

    /**
     * 
     * @Title: getSearchDataByCode 根据Code数组 获取列表页的搜索筛选数据
     * @param codes Code数组 
     * @return 
     * String 
     * @author adam
     * @since 2016-1-13 V 1.0
     */
    public String getSearchDataByCode(String[] codes);

    /**
     * 
     * @Title: getSearchDataByCode  根据Code数组 获取列表页的搜索筛选数据
     * @param codes  Code数组 
     * @param codesName 
     * @param codesId
     * @return 
     * String 
     * @author adam
     * @since 2016-1-28 V 1.0
     */
    public String getSearchDataByCode(String[] codes, String[] codesName,String[] codesId);
    
    /**
     * 
     * @Title: getFormatterDataByCode 根据Code数组数据字典数据 用于jqGrid列表中的字段格式化
     * @param codes
     * @return 
     * String 
     * @author adam
     * @since 2016-1-19 V 1.0
     */
    public String getFormatterDataByCode(String[] codes);

    /**
     * 
     * @Title: getFormatterDataByCode 根据Code数组数据字典数据 用于jqGrid列表中的字段格式化
     * @param codes
     * @param hasOther 是否有其他选项
     * @return 
     * String 
     * @author adam
     * @since 2016-1-21 V 1.0
     */
    String getFormatterDataByCode(String[] codes, Boolean hasOther);

    /**
     * 
     * @Title: getDictionary 根据code和value查询数据字典子表
     * @param code
     * @param value
     * @return 
     * DictionaryD 
     * @author adam
     * @since 2016-2-17 V 1.0
     */
    public DictionaryD getDictionary(String code , String value );

	
}
