package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

/**
 * 短信模板
 * 
 * @ClassName: MsgTemplate
 * @Description: TODO
 * @author zy
 * @date 2017年7月7日 上午8:28:51
 * 
 */
@Entity
@Table(name = "MSG_TEMPLATE")
public class MsgTemplate extends BaseEntity {
    public String name;// 模板名称
    public String tmplContent;// 模板内容
    public String isEnabled;// 是否启用
    private String partnerId;// 适用的合作商
    private String partnerName;// 合作商名称
    private String phoneList;// 手机号列表
    private String type;// 故障类型
    private String receiverIds;// 接收者
    private String receiverNames;// 接收者名称

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tmpl_content")
    public String getTmplContent() {
        return tmplContent;
    }

    public void setTmplContent(String tmplContent) {
        this.tmplContent = tmplContent;
    }

    @Column(name = "is_enabled")
    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Column(name = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Column(name = "phone_list")
    public String getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(String phoneList) {
        this.phoneList = phoneList;
    }

    @Transient
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Column(name = "receiver_ids")
    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getReceiverNames() {
        return receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

}
