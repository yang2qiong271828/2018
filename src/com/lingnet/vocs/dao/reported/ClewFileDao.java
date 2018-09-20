package com.lingnet.vocs.dao.reported;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.ClewFile;

public interface ClewFileDao extends BaseDao<ClewFile, String> {

	ClewFile findById(String id);

}
