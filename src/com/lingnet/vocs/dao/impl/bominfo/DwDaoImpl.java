package com.lingnet.vocs.dao.impl.bominfo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.bominfo.DwDao;
import com.lingnet.vocs.entity.Jcsj_Dw;

/** 
 * 
 * @ClassName: DwDaoImpl 
 * @Description: 单位dao操作 
 * @author 姜平豹
 * @date 2013-9-11 下午2:43:40 
 *
 */
@Repository("dwDao")
public class DwDaoImpl extends BaseDaoImplInit<Jcsj_Dw, String> implements DwDao{
    //获得单位几个
    @SuppressWarnings("rawtypes")
    @Override
    public List getDws(Pager pager) {
        pager = findPager(pager, getSession().createCriteria(Jcsj_Dw.class));
        return pager.getResult();
    }

    @Override
    public Jcsj_Dw getByDH(String dwdh) {
        String Hql = "from Jcsj_Dw where dwdh = ? and is_delete = '0'"; 
        
        return uniqueResult(Hql, dwdh);
    }

  

    
    
}
