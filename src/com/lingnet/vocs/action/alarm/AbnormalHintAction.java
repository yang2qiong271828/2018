package com.lingnet.vocs.action.alarm;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AbnormalHint;
import com.lingnet.vocs.service.alarm.AbnormalHintService;

/**
 * 报警提示信息管理
 * 
 * @ClassName: AbnormalHintAction
 * @Description: TODO
 * @author zy
 * @date 2017年7月11日 上午8:52:39
 * 
 */

public class AbnormalHintAction extends BaseAction {
    private static final long serialVersionUID = 27399829946389237L;
    @Resource(name = "abnormalHintService")
    private AbnormalHintService abnormalHintService;
    private AbnormalHint abnormalHint;
    private String partnerId;

    /**
     * 报警提示信息列表页
     * 
     * @Title: list
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String list() {
        return LIST;
    }

    /**
     * 报警提示信息获取列表
     * 
     * @Title: getListData
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public String getListData() {
        if (partnerId == null) {
            partnerId = this.getSession("partnerId").toString();
        }
        HashMap m = abnormalHintService.getListData(pager, id, partnerId);
        return ajax(Status.success, JsonUtil.Encode(m));
    }

    public String look() {
        return ADD;
    }

    /************************************************************* get set *************************************************************/

    public AbnormalHint getAbnormalHint() {
        return abnormalHint;
    }

    public void setAbnormalHint(AbnormalHint abnormalHint) {
        this.abnormalHint = abnormalHint;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

}
