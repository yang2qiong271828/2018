package com.lingnet.vocs.dao.atlas;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.vocs.entity.GpsAtlas;

public interface AtlasDao extends BaseDao<GpsAtlas, String> {

	public String findContractApproveFlag(String username);

	public String searchsbdt(String partnerId, String eqId,String sn);
}
