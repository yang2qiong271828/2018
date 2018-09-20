package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "vocs_change")
public class VocsChange extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891205038706868720L;

//    private static final long serialVersionUID = 1L;
//    private String vocsId;//vocs治理单元id
//    private String vocsName;//vocs治理单元编号
//    private String partnerId;//合作商id
//    private String partnerName;//合作商名称
//    public String getVocsId() {
//        return vocsId;
//    }
//    public void setVocsId(String vocsId) {
//        this.vocsId = vocsId;
//    }
//    public String getVocsName() {
//        return vocsName;
//    }
//    public void setVocsName(String vocsName) {
//        this.vocsName = vocsName;
//    }
//    public String getPartnerId() {
//        return partnerId;
//    }
//    public void setPartnerId(String partnerId) {
//        this.partnerId = partnerId;
//    }
//    public String getPartnerName() {
//        return partnerName;
//    }
//    public void setPartnerName(String partnerName) {
//        this.partnerName = partnerName;
//    }

}
