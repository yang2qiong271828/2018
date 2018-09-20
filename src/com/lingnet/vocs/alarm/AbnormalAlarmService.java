package com.lingnet.vocs.service.alarm;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AbnormalAlarm;

/**
 * @ClassName: AbnormalAlarmService
 * @Description: TODO
 * @author wanl
 * @date 2017年7月8日 上午10:19:21
 * 
 */

public interface AbnormalAlarmService extends
        BaseService<AbnormalAlarm, String> {
    /**
     * 异常信息展示
     * 
     * @Title: getListData
     * @return String
     * @author wanl
     * @param bz 
     * @since 2017年7月8日 V 1.0
     */
    public String getListData(Pager pager, String id, String partnerId, String bz);
    /**
     * 首页-异常报警信息
     * @Title: getAlarmListData 
     * @param pager
     * @return 
     * String 
     * @author xuhp
     * @since 2017-7-13 V 1.0
     */
    String getAlarmListData(Pager pager);
    
	public String getSbjgListData(Pager pager);
	
    public String getSbData(Pager pager);

}
