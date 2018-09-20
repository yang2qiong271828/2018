package com.lingnet.vocs.dao.bominfo;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Jcsj_Dw;

/** 
 * 
 * @ClassName: DwDao 
 * @Description: TODO 
 * @author 姜平豹
 * @date 2013-9-11 下午2:42:45 
 *
 */

public interface DwDao  extends BaseDao<Jcsj_Dw,String> {
    @SuppressWarnings("rawtypes")
    public List getDws(Pager pager);
    
    public Jcsj_Dw getByDH(String dwdh);
}
