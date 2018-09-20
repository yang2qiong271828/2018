package com.lingnet.vocs.service.alarm;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AbnormalHint;

@SuppressWarnings("rawtypes")
public interface AbnormalHintService extends BaseService<AbnormalHint, String> {
    String sendMsg(String formjson, int type) throws Exception;

    /**
     * 报警提示：获取列表
     * 
     * @Title: getListData
     * @param pager
     * @param equipmentId
     * @return
     * @author zy
     * @param partnerId
     * @since 2017年7月17日 V 1.0
     */
    HashMap getListData(Pager pager, String equipmentId, String partnerId);

    /**
     * 首页-报警提示信息
     * 
     * @Title: getHintListData
     * @param pager
     * @return String
     * @author xuhp
     * @since 2017-7-13 V 1.0
     */
    String getHintListData(Pager pager);
}
