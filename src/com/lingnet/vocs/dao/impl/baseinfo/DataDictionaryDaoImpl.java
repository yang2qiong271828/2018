package com.lingnet.vocs.dao.impl.baseinfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.baseinfo.DataDictionaryDDao;
import com.lingnet.vocs.dao.baseinfo.DataDictionaryDao;
import com.lingnet.vocs.entity.Dictionary;
import com.lingnet.vocs.entity.DictionaryD;
/**
 * 
 * @ClassName: DataDictionaryDaoImpl 
 * @Description: TODO 
 * @author 刘殿飞
 * @date 2017-1-10 下午3:47:42 
 *
 */
@Repository("dataDictionaryDao")
public class DataDictionaryDaoImpl  extends BaseDaoImplInit<Dictionary, String> implements DataDictionaryDao{
    @Resource(name="dataDictionaryDDao")
    private DataDictionaryDDao dataDDao;
	/**
     * 
     * @Title: getAllOrderBySort 数据字典树list
     * @return 
     * @author 刘殿飞
     * @since  V 1.0
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    public List<Dictionary> getAllOrderBySort() {
    	// Dictionary对应于实体类   code是实体类编码字段
        String hql="from Dictionary  order by orderNumber asc";
        Session session=this.getSession();
        Query query =session.createQuery(hql);
        // 获取数据字典实体list
        List<Dictionary> list=query.list();
        return list;
    }

    /**
     * 根据code数组获取所有的 数据字典数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Dictionary> getAllByCodes(String[] codes,boolean validflg) {
        List<Dictionary> dList = new ArrayList<Dictionary>();
        Map codeMap = new HashMap();
        if(validflg) {
            dList = this.getList(Restrictions.eq("isValidflg", "1"));
        }else{
            dList = this.getAllList();
        }
        for(String code : codes){
            for(Dictionary d : dList){
                if(code.equals(d.getCode())){
                    codeMap.put(code,d);
                    break;
                }
            }
        }
        return codeMap;
    }
    
    /**
     * 根据Code数组数据字典数据 用于jqGrid列表中的字段格式化
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Map getMapByCode(String[] codes) {
        Map<String,Dictionary> dataMap =getAllByCodes(codes,true);
        Map dataDMap=dataDDao.getAllByCodes(codes, true);
        HashMap map=new HashMap();
        for(String code:codes){
            Dictionary dictionary=dataMap.get(code);
            if(dictionary!=null){
                HashMap dMap=new HashMap();
                List<DictionaryD> dList = (List<DictionaryD>) dataDMap.get(code);
                dMap.put("-1","-");
                for(DictionaryD dicD : dList){
                    dMap.put(dicD.getValue(),dicD.getName());
                }
                map.put(code, dMap);
            }
        }
        return map;
    }    
    
    /**
     * 根据Code数组数据字典数据 用于导入时按名称获取值
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Map getReverseMapByCode(String[] codes){
    	Map<String,Dictionary> dataMap =getAllByCodes(codes,true);
        Map dataDMap=dataDDao.getAllByCodes(codes, true);
        HashMap map=new HashMap();
        for(String code:codes){
            Dictionary dictionary=dataMap.get(code);
            if(dictionary!=null){
                HashMap dMap=new HashMap();
                List<DictionaryD> dList = (List<DictionaryD>) dataDMap.get(code);
                for(DictionaryD dicD : dList){
                    dMap.put(dicD.getName(),dicD.getValue());
                }
                map.put(code, dMap);
            }
        }
        return map;
    }
}
