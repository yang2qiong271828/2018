package com.lingnet.vocs.dao.impl.baseinfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.baseinfo.DataDictionaryDDao;
import com.lingnet.vocs.entity.DictionaryD;
/**
 * 
 * @ClassName: DataDictionaryDDaoImpl (数据字典子表dao实现类)
 * @Description: TODO 
 * @author 刘殿飞
 * @date 2017-12-19 下午6:41 
 *
 */
@Repository("dataDictionaryDDao")
public class DataDictionaryDDaoImpl  extends BaseDaoImplInit<DictionaryD, String> implements DataDictionaryDDao{
  
    @Override
    public Pager getAllOrderBySort(Pager pager,String id) {
        String hql="from DictionaryD where parent_id='"+id+"' order by orderNumber asc";
        Session session=this.getSession();
        Query query =session.createQuery(hql);
        pager.setTotalCount(query.list().size());
		query.setFirstResult((pager.getPageNumber()-1)*pager.getPageSize());
		query.setMaxResults(pager.getPageSize());
		pager.setResult(query.list());
        return pager;
    }
    
    public String getName(String code,String value){
    	String sql="select * from b_datedictionary_d where code=? and value=?";
    	SQLQuery query = this.getSession().createSQLQuery(sql).addEntity(DictionaryD.class);
    	query.setParameter(0, code);
    	query.setParameter(1, value);
    	
    	return ((DictionaryD)query.list().get(0)).getName();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map getAllByCodes(String[] codes, boolean validflg) {
        List<DictionaryD> dList = new ArrayList<DictionaryD>();
        Map codeMap = new HashMap();
        if(validflg) {
            dList = this.getListByOrder(Order.asc("orderNumber") ,Restrictions.eq("isValidflg", "1"));
        }else{
            dList = this.getListByOrder(Order.asc("orderNumber"));
        }
        for(String code : codes){
            List<DictionaryD> list =new ArrayList<DictionaryD>();
            for(DictionaryD d : dList){
                if(code.equals(d.getCode())){
                    list.add(d);
                }
            }
            codeMap.put(code,list);
        }
        return codeMap;
    }
}
