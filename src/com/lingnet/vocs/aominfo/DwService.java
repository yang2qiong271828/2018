package com.lingnet.vocs.service.aominfo;
import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Jcsj_Dw;
/** 
 * 
 * @ClassName: DwService 
 * @Description: TODO 
 * @author 姜平豹
 * @date 2013-9-11 下午2:38:17 
 *
 */
@SuppressWarnings("rawtypes")
public interface DwService extends BaseService<Jcsj_Dw,String> {
    
    public HashMap getDws(Pager pager);
    
    public Jcsj_Dw getOne(String id);
    
    //public String saveOrUpdate(List<Map<String, String>> gridMap) throws Exception;
    public String saveOrUpdate(String formdata) throws Exception;

    /** 
     * @Title: 返回给页面单位json，作为combobox的选项 
     * @return 
     * String 
     * @author 栾胜鹏 整理代码将实现方法放到impl中  
     * @since 2014-10-16 V 1.0
     */
    public String dw();
    
}
