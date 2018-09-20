package com.lingnet.vocs.service.atlas;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.GpsAtlas;

public interface AtlasService extends BaseService<GpsAtlas, String> {

    public String xxShow();
    
    public String search(String type,String name);
    
    public String searchkh(String type,String id) throws Exception;
    
    public String searchmykh(String type,String id);
    
    
    public String searchhzs(String type,String id);
    
    public String searchmyhzs(String type,String id);
    
    
    public String searchzldy(String type,String name);
    
    public String sblxList(String name,String strDate,String endDate) throws Exception;
    
    public String searchsblx(String type,String name);
    
    
    public String searchsbjg(String type,String name);
    
    
    /**
     * 通过gps设备的sn和时间获取该时间的设备移动轨迹，可以手工获取或者通过页面按钮点击获取，数据采用覆盖更新方式导入数据库
     * @Title: getGpsData 
     * @param sn//gps设备序列号--441483
     * @param date//时间 --yyyy-MM-dd
     * @return 
     * String 
     * @author duanjj
     * @since 2017年6月30日 V 1.0
     */
    public String getGpsData(String sn,String date) throws Exception;

	public String searchsbdt(Pager pager,String partnerId, String id,String sn);
	
	public String getListDataStaff(Pager pager,String type); 
 
	public String jbcsList();
	
}
