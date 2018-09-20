package com.lingnet.vocs.service.baseinfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.DictionaryD;

public interface DataDictionaryDService extends BaseService<DictionaryD, String>{
    
    public String getAllOrderBySort(Pager pager,String id);
   
    /**
     * @author ldf
     * @description 设置默认
     * @date 2015/3/18 9:59 AM
     */
	public String setOtherDefault(DictionaryD dic);

	/**
	 * 
	 * @Title: getValueByPcode 传入父字典的code返回 name 和value值 的集合
	 * @param pcode
	 * @param validflg
	 * @return 
	 * ArrayList<HashMap<String,String>> 
	 * @author adam
	 * @since 2016-2-2 V 1.0
	 */
	ArrayList<HashMap<String, String>> getValueByPcode(String pcode,Boolean validflg);

	/**
	 * 
	 * @Title: getIdByPcode 传入code 返回 name 和 id 值 的集合
	 * @param pcode
	 * @param validflg
	 * @return 
	 * ArrayList<HashMap<String,String>> 
	 * @author adam
	 * @since 2016-2-2 V 1.0
	 */
	ArrayList<HashMap<String, String>> getIdByPcode(String pcode,Boolean validflg);

	/**
	 * 
	 * @Title: getListByCode 传入code返回DictionaryD实体的集合
	 * @param pcode
	 * @param validflg
	 * @return 
	 * ArrayList<DictionaryD> 
	 * @author adam
	 * @since 2016-2-2 V 1.0
	 */
	ArrayList<DictionaryD> getListByCode(String pcode,Boolean validflg);

	/**
	 * 
     * @Title: getAllByCodes  根据code数组获取所有的 数据字典数据
     * @param codes    code数组
     * @param validflg 是否获取有效的数据
	 * @return 
	 * Map 
	 * @author adam
	 * @since 2016-2-2 V 1.0
	 */
    @SuppressWarnings("rawtypes")
    public Map getAllByCodes(String[] codes, boolean validflg);
    
    public String getNameByCodeAndValue(String code ,String value);
    
}
