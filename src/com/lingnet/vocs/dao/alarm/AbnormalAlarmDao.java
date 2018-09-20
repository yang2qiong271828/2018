package com.lingnet.vocs.dao.alarm;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AbnormalAlarm;

public interface AbnormalAlarmDao extends BaseDao<AbnormalAlarm, String> {
    /**
     * 异常信息展示
     * 
     * @Title: getListData
     * @param pager
     * @param id
     * @return String
     * @author wanl
     * @param bz 
     * @since 2017年7月8日 V 1.0
     */
    public Pager getListData(Pager pager, String id, String partnerId);

    /**
     * 处理设备报警信息使其变为已处理
     * 
     * @Title: changeStatus
     * @param id
     * @return String
     * @author wanl
     * @since 2017年7月8日 V 1.0
     */
    public String changeStatus(String id, String handlePeopleId);

    /**
     * 处理完成后填入处理人
     * 
     * @Title: notice
     * @return String
     * @author wanl
     * @since 2017年7月12日 V 1.0
     */
    public String notice(String id, String noticePeople);

    /**
     * 设备列表页展示
     * @Title: getSbjgListData 
     * @param pager
     * @return 
     * Pager 
     * @author lizk
     * @since 2017年11月15日 V 1.0
     */
	public Pager getSbjgListData(Pager pager);

	/**
	 * 异常设备初始加载
	 * @Title: getSbData 
	 * @param pager
	 * @return 
	 * Pager 
	 * @author lizk
	 * @since 2017年11月15日 V 1.0
	 */
    public Pager getSbData(Pager pager);
    
    /**
     * 获取设备坐标
     * @Title: getSbzb 
     * @param sn
     * @return 
     * HashMap<String,String> 
     * @author lizk
     * @since 2017年11月15日 V 1.0
     */
    public HashMap<String, Object> getSbzb(String sn);

}
