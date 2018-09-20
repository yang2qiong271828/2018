package com.lingnet.vocs.action.alarm;


import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.MsgTemplate;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.alarm.MsgTemplateService;

/**
 * 短信模板
 * 
 * @ClassName: MsgTemplateAction
 * @Description: TODO
 * @author zy
 * @date 2017年7月7日 上午8:42:20
 * 
 */

public class MsgTemplateAction extends BaseAction {
    private static final long serialVersionUID = 27399829946389237L;
    @Resource(name = "msgTemplateService")
    private MsgTemplateService msgTemplateService;
    private MsgTemplate msgTemplate;
    private String formdata;

    /**
     * 短信模板列表页
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
     * 列表页数据
     * 
     * @Title: getListData
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public String getListData() {
        if(id==null ){
            id = this.getSession("partnerId").toString();
        }
        HashMap m = msgTemplateService.getListData(pager, id);
        String s = JsonUtil.Encode(m);
        return ajax(Status.success, s);
    }

    /**
     * 短信模板添加页
     * 
     * @Title: add
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String add(){
        return ADD;
    }

    /**
     * 短信模板编辑页
     * 
     * @Title: edit
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String edit(){
        msgTemplate = msgTemplateService.get(MsgTemplate.class, id);
        Partner p = msgTemplateService.get(Partner.class,
                msgTemplate.getPartnerId());
        msgTemplate.setPartnerName(p.getName());
        String receiverNames = "";
        if (StringUtils.isNotEmpty(msgTemplate.getReceiverIds())) {
            for (String s : msgTemplate.getReceiverIds().split(",")) {
                QxUsers u = msgTemplateService.get(QxUsers.class,
                        Restrictions.eq("id", s));
                receiverNames += u.getName() + ",";
            }
        }
        msgTemplate.setReceiverNames(receiverNames);
        return ADD;
    }

    /**
     * 短信模板查看页
     * 
     * @Title: look
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String look() {
        getRequest().setAttribute("flag", "false");
        msgTemplate = msgTemplateService.get(MsgTemplate.class, id);
        Partner p = msgTemplateService.get(Partner.class,
                msgTemplate.getPartnerId());
        msgTemplate.setPartnerName(p.getName());
        String receiverNames = "";
        if (StringUtils.isNotEmpty(msgTemplate.getReceiverIds())) {
            for (String s : msgTemplate.getReceiverIds().split(",")) {
                QxUsers u = msgTemplateService.get(QxUsers.class,
                        Restrictions.eq("id", s));
                receiverNames += u.getName() + ",";
            }
        }
        msgTemplate.setReceiverNames(receiverNames);
        return ADD;
    }

    /**
     * 短信模板保存
     * 
     * @Title: saveOrUpdate
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            msgTemplate = JsonUtil.toObject(formdata, MsgTemplate.class);
            msgTemplateService.saveOrUpdate(msgTemplate);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
                    "text/html;charset=UTF-8");
        }
    }

    /**
     * 短信模板删除
     * 
     * @Title: remove
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String remove(){
        try {
            msgTemplateService.remove(ids[0]);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.error, "error");
        }
    }

    /**
     * 短信模板 修改停用/启用
     * 
     * @Title: changeIsEnabled
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String changeIsEnabled() {
        try {
            msgTemplateService.changeIsEnabled(id, key);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
                    "text/html;charset=UTF-8");
        }
    }

    /**
     * 短信模板 发送短信页面
     * 
     * @Title: msg
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String msg() {
        return "msg";
    }

    /**
     * 发送短信
     * 
     * @Title: sendMsg
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    public String sendMsg() {
        String result = "";
        try {
            result = msgTemplateService.sendMsg(formdata, 0);
        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        return ajax(Status.success, result);
    }

    /************************************************************* get set *************************************************************/
    public MsgTemplate getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(MsgTemplate msgTemplate) {
        this.msgTemplate = msgTemplate;
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

}
