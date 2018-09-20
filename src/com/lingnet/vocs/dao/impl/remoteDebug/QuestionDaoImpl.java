package com.lingnet.vocs.dao.impl.remoteDebug;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.remoteDebug.QuestionDao;
import com.lingnet.vocs.entity.Question;
/**
 * 问题现象
 * @ClassName: QuestionDaoImpl 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 下午5:09:05 
 *
 */
@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImplInit<Question,String > 
                              implements QuestionDao{

    @SuppressWarnings("unchecked")
    @Override
    public Pager findByDetachedCriteria(
            DetachedCriteria detachedCriteria,Pager pager) {
        detachedCriteria.setProjection(Projections.rowCount());//select count(*) from bc_staff
        List<Integer> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Integer total = list.get(0);
        pager.setTotalCount(total);//设置总数据量
        detachedCriteria.setProjection(null);//修改sql的形式为select * from ....
        //重置表和类的映射关系
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        Integer pageSize = pager.getPageSize();
        Integer pageNumber = (pager.getPageNumber()-1)*pageSize;
        pager.setResult(this.getHibernateTemplate().findByCriteria(detachedCriteria, pageNumber, pageSize));
        return pager;
        
    }

}
