package com.lingnet.vocs.service.workorder;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ServiceOutlay;


public interface ServiceOutlayService extends BaseService<ServiceOutlay, String>{

    public  String saveOrUpdate(ServiceOutlay serviceOutlay) throws Exception;

    public String getListData(Pager pager, String partnerId,String area);

    /**
     * 根据地区获取服务费实体
     * @Title: getByArea 
     * @param area
     * @param partnerId
     * @return 
     * ServiceOutlay 
     * @author 薛硕
     * @param type 
     * @since 2017年7月6日 V 1.0
     */
    public ServiceOutlay getByArea(String area, String type);

}
