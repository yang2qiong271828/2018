package com.lingnet.vocs.action.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.SDKTestSendTemplateSMS;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.alarm.AbnormalAlarmDao;
import com.lingnet.vocs.entity.AbnormalAlarm;
import com.lingnet.vocs.entity.Equipment;
import com.lingnet.vocs.entity.MsgTemplate;
import com.lingnet.vocs.entity.OrderClass;
import com.lingnet.vocs.service.alarm.AbnormalAlarmService;
import com.lingnet.vocs.service.alarm.MsgTemplateService;

/**
 * 异常报警信息
 * 
 * @ClassName: AbnormalAlarmAction
 * @Description: TODO
 * @author wanl
 * @date 2017年7月12日 上午10:09:14
 * 
 */

public class AbnormalAlarmAction extends BaseAction {
    private static final long serialVersionUID = 27399829946389237L;

    @Resource(name = "abnormalAlarmService")
    private AbnormalAlarmService abnormalAlarmService;
    @Resource(name = "abnormalAlarmDao")
    private AbnormalAlarmDao abnormalAlarmDao;
    @Resource(name = "msgTemplateService")
    private MsgTemplateService msgTemplateService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;

    private AbnormalAlarm abnormalAlarm;

    private String name;

    private String partnerId;

    private String phone;
    
    private String bz;//首页传过来值

    /**
     * 进入主页面 默认显示图标信息
     * */
    public String list() {

        return "list";
    }

    /**
     * 加载异常报警列表
     * 
     * @Title: getListData
     * @return String
     * @author wanl
     * @since 2017年7月12日 V 1.0
     */
    public String getListData() {
        if (partnerId == null) {
            partnerId = this.getSession("partnerId").toString();
        }
        return ajax(Status.success,
                abnormalAlarmService.getListData(pager, id, partnerId,bz));
    }

    /**
     * 异常简单处理
     * 
     * @Title: changeStatus
     * @return String
     * @author wanl
     * @since 2017年7月12日 V 1.0
     */
    public String changeStatus() {
        String handlePeopleId = toolUtil.getUserId();
        String status = abnormalAlarmDao.changeStatus(id, handlePeopleId);
        abnormalAlarm = abnormalAlarmService.get(AbnormalAlarm.class, id);
        Equipment equipment = abnormalAlarmService.get(Equipment.class,
                abnormalAlarm.getEquipmentId());
        operate("异常报警", "简单处理", equipment.getEquipmentCode());
        return ajax(Status.success, status);
    }

    /**
     * 将异常信息以短信的形式通知维修人员
     * 
     * @Title: notice
     * @return String
     * @author wanl
     * @since 2017年7月12日 V 1.0
     */
    public String notice() {

        AbnormalAlarm abnormalAlarm = abnormalAlarmDao.get(id);

        // 拼接短信模板
        Equipment equipment = abnormalAlarmService.get(Equipment.class,
                Restrictions.eq("id", abnormalAlarm.getEquipmentId()));
        String equipmentName = equipment.getEquipmentCode();

        String alarmContent = abnormalAlarm.getAlarmContent();
        Date alarmDate = abnormalAlarm.getAlarmDate();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = time.format(alarmDate);
        OrderClass orderClass = abnormalAlarmService.get(OrderClass.class,
                Restrictions.eq("id", abnormalAlarm.getExceptionType()));
        String exceptionType = orderClass.getTypeName();

        String template = ",您的设备" + equipmentName + "出现" + alarmContent + "报警,"
                + "报警时间为" + format + ",异常类型为" + exceptionType;
        String[] strs = new String[] { template };

        // 根据三个字段值确定一条短信模板数据
        MsgTemplate msgTemplate = msgTemplateService.get(MsgTemplate.class,
                Restrictions.eq("type", abnormalAlarm.getExceptionType()),
                Restrictions.eq("partnerId", abnormalAlarm.getPartner()),
                Restrictions.eq("isEnabled", "1"));
        if(msgTemplate==null){
            return ajax(Status.error, "请维护短信模板！");
        }
        String[] arr = msgTemplate.getReceiverIds().split(",");
        String name = "";
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                QxUsers qxUsers = this.getBeanById(QxUsers.class,
                        arr[i].toString());
                name += qxUsers == null ? "" : qxUsers.getName() + ",";
            }
        }
        name = name.substring(0, name.length() - 1);

        String result = abnormalAlarmDao.notice(id, name);

        // 获取通知人员
        String phoneList = msgTemplate.getPhoneList();
        String[] phoneArr = phoneList.split(",");
        if (phoneArr != null) {
            for (int i = 0; i < phoneArr.length; i++) {
                // 调用发送短信方法
                SDKTestSendTemplateSMS.sendMsg(phoneArr[i].toString(),
                        "190077", strs, "8a216da85d158d1b015d2bc8e2270839");
            }
        }
        operate("异常报警", "通知处理", equipment.getEquipmentCode());
        return ajax(Status.success, JsonUtil.Encode(result));

    }

    /************************************************************* get set *************************************************************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public AbnormalAlarmService getAbnormalAlarmService() {
        return abnormalAlarmService;
    }

    public void setAbnormalAlarmService(
            AbnormalAlarmService abnormalAlarmService) {
        this.abnormalAlarmService = abnormalAlarmService;
    }

    public AbnormalAlarmDao getAbnormalAlarmDao() {
        return abnormalAlarmDao;
    }

    public void setAbnormalAlarmDao(AbnormalAlarmDao abnormalAlarmDao) {
        this.abnormalAlarmDao = abnormalAlarmDao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AbnormalAlarm getAbnormalAlarm() {
        return abnormalAlarm;
    }

    public void setAbnormalAlarm(AbnormalAlarm abnormalAlarm) {
        this.abnormalAlarm = abnormalAlarm;
    }

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
