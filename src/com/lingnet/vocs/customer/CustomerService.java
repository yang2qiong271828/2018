package com.lingnet.vocs.service.customer;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Customer;

public interface CustomerService extends BaseService<Customer, String> {
	
	/**
     * 保存或编辑VOCS客户
     * @Title: saveOrUpdate 
     * @parm
     * String girdData 前台子表表格数据
     * String id 前台主表id
     * @return 
     * String   
     * @author zmf
	 * @throws Exception 
     * @since 2016-8-12 V 1.0
     */
	public String saveOrUpdate(String formData,String girdData) throws Exception;

	
	public String getListData(Pager pager,String name,String key, String partnerName,String areaId);
	
	public String getSbList(String str,String end,String id) throws Exception;

	public String getSbPcxxList(Pager pager,String str,String end, String id,String year,String month);

	public String getMyListData(Pager pager, String name, String key,String areaId);


	public String saveUpdate(String formdata, String griddata) throws Exception;


	public String getCustomerTree(String partnerId,String key,String areaId);


	public String getListCustomer(Pager pager, String name, String key);


    public String getQzKhData(Pager pager, String searchData);


    public String getYxkhList(Pager pager, String searchData, String status);
}
