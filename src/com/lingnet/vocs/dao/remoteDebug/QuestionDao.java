package com.lingnet.vocs.dao.remoteDebug;


import org.hibernate.criterion.DetachedCriteria;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Question;

public interface QuestionDao extends BaseDao<Question, String> {
public Pager findByDetachedCriteria(DetachedCriteria detachedCriteria,Pager pager);
}
