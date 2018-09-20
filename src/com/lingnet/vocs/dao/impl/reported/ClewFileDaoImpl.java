package com.lingnet.vocs.dao.impl.reported;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.vocs.dao.reported.ClewFileDao;
import com.lingnet.vocs.entity.ClewFile;


@Repository("clewFileDao")
public class ClewFileDaoImpl extends
        BaseDaoImpl<ClewFile, String> implements ClewFileDao {

	@Override
	public ClewFile findById(String id) {
		String sql = "select * FROM clew_file where threadId = ?; ";
		SQLQuery query =getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		//将结果封装到哪个对象
	    query.addEntity(ClewFile.class);
	    ClewFile clewFile = (ClewFile) query.uniqueResult();
		
		return clewFile;
	}

}
