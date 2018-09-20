package com.lingnet.vocs.dao.customer;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Customer;

public interface CustomerDao extends BaseDao<Customer, String>{

	public Pager getListData(Pager pager,String name,String key, String partnerName,String areaId);

	public List<Object[]> getSbList(String str,String end,String id) throws Exception;

	public List<Object[]> getSbPcxxList(String year, String month);

	public Pager getMyListData(Pager pager, String name, String key,String areaId); 
	
	public Object  getContract(String key);

    public Pager getQzKhData(Pager pager, String searchData, String area);

    public Pager getYxkhList(Pager pager, String searchData, String status); 
}
