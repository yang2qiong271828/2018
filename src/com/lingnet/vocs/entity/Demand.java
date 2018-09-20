package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/** 
 * @ClassName: Demand 
 * @Description: TODO 
 * @author lizk
 * @date 2018年1月23日 下午5:45:14 
 *  
 */

@Entity
@Table(name = "demand")
public class Demand extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 520661021336005207L;
    
    private String jbxx;//需求选择1.考察接待,2.项目支持,3.培训支持,4.安装调试,5.技术方案,6.标书制作,7.宣传支持,8.投诉处理"
    private String bid;//报备id
    private String status;//审核状态
    private Date tbsj;//提报时间
    private String bhyj;//驳回意见
    private String partnerId;//操作人id
    private String name;//经销商名称
    private String phone;//经销商电话
    private String picName;
    private String picPhone;
    private String picEmail;
    private String picTitle;
    //////////////////经销商客户考察接待申请单/////////////////////////
    private Date jhddsj;//计划到达时间
    private Date jhfhsj;//计划返回时间
    private String mddkcnr;//计划考察目的地及考察内容
    private Date kcsj;//考察时间
    private String kcrs;//考察人数
    private String zysxbz;//考察注意事项备注
    private String ptry;//陪同人员（经销商、总公司领导、子公司经理、办事处）
    private String jdap;//接待方式及接待内容安排（食宿及交通、所需礼品）
    private String jdzysx;//接待人员注意事项
    private String hsjdjr;//华世洁 接待对接人 
    
    private String cusComName; //客户公司名称
    private String projectName; //项目名称
    private String comAddress;  //公司地址
    private String projectAddress; //项目地址
    private String zfcompany;//总包/分包公司
    private String zfcompanyAddress; //总包/分包公司地址
    private String introduce; //客户及项目简介
    private String projectStatus;//项目状态(0环评阶段 1设计阶段 2运行阶段)
    private String emissionFrom;//排放形式（1集中 2分散）
    private String isdevice;//有无处理装置(0有 1无)
    private String technology;//处理装置采用何种工艺
    private String problem;//目前处理装置存在问题
    private String reformEmphasis;//改造侧重点(0环保效益 1经济效益 2其他)
    private String environmentalRequirements;//环保要求
    private String exhaustSource;//废气产生工艺来源
    private String progress;//客户及项目商务维护进展
    private String remark;//其他需要说明的事项
    private String xmfzr; //项目负责人
    private String xmlxdh;//联系电话
    private String email;//电子邮件
    /////////////////////经销商技术支持申请表 /////////////////////////
    private String xsjswt;//销售/技术问题反馈及销售/技术支持需求
    private String xsdjr;//销售/技术人员对接人
    //////////////////////技术方案/标书制作申请//////////////////////
    private String zzzysx;// 制作注意事项（价格、内容要求等）
    private Date zzsj;//制作开始时间
    private Date zzsje;//制作结束时间
    private String hsjzzr;//华世洁制作人员
    //////////////////投诉处理单/////////////////////////////////
    private String cpmc;//产品名称
    private String cpgg;//产品规格
    private String fhsl;//发货数量
    private String fhph;//发货批号
    private String jxsfktsyj;//经销商反馈投诉意见
    private String khfzr;//客户负责人
    private String zw;//职位
    private String lxdh;//联系电话
    private String dzyx;//电子邮箱
    private String khjxmjj;//客户及项目简介
    private String khfktsnr;//客户反馈投诉内容
    private String hsjtsfk;//华世洁投诉反馈 
    ////////////////////培训报名表/////////////////////////////////
    private String ggkcmc;//公告课程名称
    private Date pxsj;//培训时间
    private String pxrysl;//培训人员数量
    private String bzsm;//备注说明
    ////////////////////安装调试申请/////////////////////////////////
    private String xqxz;//需求选择 1.安装,2.调试
    ////////////////////宣传支持申请//////////////////////////////////
    private String hzmc;//会展名称
    private Date hzsj;//会展时间
    private String zbf;//主办方
    private String lxr;//联系人
    private String lxfs;//联系方式
    private String czxs;//参展形式 1.展位、2.演讲、3.赞助、4.主办
    private String fyys;//费用预算
    private String czsm;//参展说明
    private String ryzc;//人员支持
    private String wzzc;//物资支持
    private String qtzc;//其他支持
    private String zcsm;//支持说明
    private String hsjyj;//华世洁审批（公司签章）意见
    private String threadId;
    private String threadId1;
    private String threadId2;
    
    
    @Column(name = "bid")
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }@Column(name = "jhddsj")
    public Date getJhddsj() {
        return jhddsj;
    }
    public void setJhddsj(Date jhddsj) {
        this.jhddsj = jhddsj;
    }
    @Column(name = "jhfhsj")
    public Date getJhfhsj() {
        return jhfhsj;
    }
    public void setJhfhsj(Date jhfhsj) {
        this.jhfhsj = jhfhsj;
    }
    @Column(name = "mddkcnr")
    public String getMddkcnr() {
        return mddkcnr;
    }
    public void setMddkcnr(String mddkcnr) {
        this.mddkcnr = mddkcnr;
    }
    @Column(name = "kcsj")
    public Date getKcsj() {
        return kcsj;
    }
    public void setKcsj(Date kcsj) {
        this.kcsj = kcsj;
    }
    @Column(name = "kcrs")
    public String getKcrs() {
        return kcrs;
    }
    public void setKcrs(String kcrs) {
        this.kcrs = kcrs;
    }
    @Column(name = "zysxbz")
    public String getZysxbz() {
        return zysxbz;
    }
    public void setZysxbz(String zysxbz) {
        this.zysxbz = zysxbz;
    }
    @Column(name = "ptry")
    public String getPtry() {
        return ptry;
    }
    public void setPtry(String ptry) {
        this.ptry = ptry;
    }
    @Column(name = "jdap")
    public String getJdap() {
        return jdap;
    }
    public void setJdap(String jdap) {
        this.jdap = jdap;
    }
    @Column(name = "jdzysx")
    public String getJdzysx() {
        return jdzysx;
    }
    public void setJdzysx(String jdzysx) {
        this.jdzysx = jdzysx;
    }
    @Column(name = "hsjdjr")
    public String getHsjdjr() {
        return hsjdjr;
    }
    public void setHsjdjr(String hsjdjr) {
        this.hsjdjr = hsjdjr;
    }
    @Column(name = "xsjswt")
    public String getXsjswt() {
        return xsjswt;
    }
    public void setXsjswt(String xsjswt) {
        this.xsjswt = xsjswt;
    }
    @Column(name = "xsdjr")
    public String getXsdjr() {
        return xsdjr;
    }
    public void setXsdjr(String xsdjr) {
        this.xsdjr = xsdjr;
    }
    @Column(name = "zzzysx")
    public String getZzzysx() {
        return zzzysx;
    }
    public void setZzzysx(String zzzysx) {
        this.zzzysx = zzzysx;
    }
    @Column(name = "hsjzzr")
    public String getHsjzzr() {
        return hsjzzr;
    }
    public void setHsjzzr(String hsjzzr) {
        this.hsjzzr = hsjzzr;
    }
    @Column(name = "cpmc")
    public String getCpmc() {
        return cpmc;
    }
    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }
    @Column(name = "cpgg")
    public String getCpgg() {
        return cpgg;
    }
    public void setCpgg(String cpgg) {
        this.cpgg = cpgg;
    }
    @Column(name = "fhsl")
    public String getFhsl() {
        return fhsl;
    }
    public void setFhsl(String fhsl) {
        this.fhsl = fhsl;
    }
    @Column(name = "fhph")
    public String getFhph() {
        return fhph;
    }
    public void setFhph(String fhph) {
        this.fhph = fhph;
    }
    @Column(name = "jxsfktsyj")
    public String getJxsfktsyj() {
        return jxsfktsyj;
    }
    public void setJxsfktsyj(String jxsfktsyj) {
        this.jxsfktsyj = jxsfktsyj;
    }
    @Column(name = "khfzr")
    public String getKhfzr() {
        return khfzr;
    }
    public void setKhfzr(String khfzr) {
        this.khfzr = khfzr;
    }
    @Column(name = "zw")
    public String getZw() {
        return zw;
    }
    public void setZw(String zw) {
        this.zw = zw;
    }
    @Column(name = "lxdh")
    public String getLxdh() {
        return lxdh;
    }
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
    @Column(name = "dzyx")
    public String getDzyx() {
        return dzyx;
    }
    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }
    @Column(name = "khjxmjj")
    public String getKhjxmjj() {
        return khjxmjj;
    }
    public void setKhjxmjj(String khjxmjj) {
        this.khjxmjj = khjxmjj;
    }
    @Column(name = "khfktsnr")
    public String getKhfktsnr() {
        return khfktsnr;
    }
    public void setKhfktsnr(String khfktsnr) {
        this.khfktsnr = khfktsnr;
    }
    @Column(name = "hsjtsfk")
    public String getHsjtsfk() {
        return hsjtsfk;
    }
    public void setHsjtsfk(String hsjtsfk) {
        this.hsjtsfk = hsjtsfk;
    }
    @Column(name = "ggkcmc")
    public String getGgkcmc() {
        return ggkcmc;
    }
    public void setGgkcmc(String ggkcmc) {
        this.ggkcmc = ggkcmc;
    }
    @Column(name = "pxrysl")
    public String getPxrysl() {
        return pxrysl;
    }
    public void setPxrysl(String pxrysl) {
        this.pxrysl = pxrysl;
    }
    @Column(name = "bzsm")
    public String getBzsm() {
        return bzsm;
    }
    public void setBzsm(String bzsm) {
        this.bzsm = bzsm;
    }
    @Column(name = "xqxz")
    public String getXqxz() {
        return xqxz;
    }
    public void setXqxz(String xqxz) {
        this.xqxz = xqxz;
    }
    @Column(name = "hzmc")
    public String getHzmc() {
        return hzmc;
    }
    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }
    @Column(name = "hzsj")
    public Date getHzsj() {
        return hzsj;
    }
    public void setHzsj(Date hzsj) {
        this.hzsj = hzsj;
    }
    @Column(name = "zbf")
    public String getZbf() {
        return zbf;
    }
    public void setZbf(String zbf) {
        this.zbf = zbf;
    }
    @Column(name = "lxr")
    public String getLxr() {
        return lxr;
    }
    public void setLxr(String lxr) {
        this.lxr = lxr;
    }
    @Column(name = "lxfs")
    public String getLxfs() {
        return lxfs;
    }
    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }
    @Column(name = "czxs")
    public String getCzxs() {
        return czxs;
    }
    public void setCzxs(String czxs) {
        this.czxs = czxs;
    }
    @Column(name = "fyys")
    public String getFyys() {
        return fyys;
    }
    public void setFyys(String fyys) {
        this.fyys = fyys;
    }
    @Column(name = "czsm")
    public String getCzsm() {
        return czsm;
    }
    public void setCzsm(String czsm) {
        this.czsm = czsm;
    }
    @Column(name = "ryzc")
    public String getRyzc() {
        return ryzc;
    }
    public void setRyzc(String ryzc) {
        this.ryzc = ryzc;
    }
    @Column(name = "wzzc")
    public String getWzzc() {
        return wzzc;
    }
    public void setWzzc(String wzzc) {
        this.wzzc = wzzc;
    }
    @Column(name = "qtzc")
    public String getQtzc() {
        return qtzc;
    }
    public void setQtzc(String qtzc) {
        this.qtzc = qtzc;
    }
    @Column(name = "zcsm")
    public String getZcsm() {
        return zcsm;
    }
    public void setZcsm(String zcsm) {
        this.zcsm = zcsm;
    }
    @Column(name = "hsjyj")
    public String getHsjyj() {
        return hsjyj;
    }
    public void setHsjyj(String hsjyj) {
        this.hsjyj = hsjyj;
    }
    @Column(name = "pxsj")
    public Date getPxsj() {
        return pxsj;
    }
    public void setPxsj(Date pxsj) {
        this.pxsj = pxsj;
    }
    @Column(name = "cus_com_name")
    public String getCusComName() {
        return cusComName;
    }
    public void setCusComName(String cusComName) {
        this.cusComName = cusComName;
    }
    
     @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
     @Column(name = "com_address")
    public String getComAddress() {
        return comAddress;
    }
    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }
    
     @Column(name = "project_address")
    public String getProjectAddress() {
        return projectAddress;
    }
    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }
    
     @Column(name = "zfcompany")
    public String getZfcompany() {
        return zfcompany;
    }
    public void setZfcompany(String zfcompany) {
        this.zfcompany = zfcompany;
    }
    
     @Column(name = "zfcompany_address")
    public String getZfcompanyAddress() {
        return zfcompanyAddress;
    }
    public void setZfcompanyAddress(String zfcompanyAddress) {
        this.zfcompanyAddress = zfcompanyAddress;
    }
    
     @Column(name = "introduce")
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
     @Column(name = "project_status")
    public String getProjectStatus() {
        return projectStatus;
    }
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
    
     @Column(name = "emission_from")
    public String getEmissionFrom() {
        return emissionFrom;
    }
    public void setEmissionFrom(String emissionFrom) {
        this.emissionFrom = emissionFrom;
    }
    
     @Column(name = "isdevice")
    public String getIsdevice() {
        return isdevice;
    }
    public void setIsdevice(String isdevice) {
        this.isdevice = isdevice;
    }
    
     @Column(name = "technology")
    public String getTechnology() {
        return technology;
    }
    public void setTechnology(String technology) {
        this.technology = technology;
    }
    
     @Column(name = "problem")
    public String getProblem() {
        return problem;
    }
    public void setProblem(String problem) {
        this.problem = problem;
    }
    
     @Column(name = "reform_emphasis")
    public String getReformEmphasis() {
        return reformEmphasis;
    }
    public void setReformEmphasis(String reformEmphasis) {
        this.reformEmphasis = reformEmphasis;
    }
    
     @Column(name = "environmental_requirements")
    public String getEnvironmentalRequirements() {
        return environmentalRequirements;
    }
    public void setEnvironmentalRequirements(String environmentalRequirements) {
        this.environmentalRequirements = environmentalRequirements;
    }
    
     @Column(name = "exhaust_source")
    public String getExhaustSource() {
        return exhaustSource;
    }
    public void setExhaustSource(String exhaustSource) {
        this.exhaustSource = exhaustSource;
    }
    
     @Column(name = "progress")
    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    
     @Column(name = "remark")
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name = "xmfzr")
    public String getXmfzr() {
        return xmfzr;
    }
    public void setXmfzr(String xmfzr) {
        this.xmfzr = xmfzr;
    }
    
    @Column(name = "xmlxdh")
    public String getXmlxdh() {
        return xmlxdh;
    }
    public void setXmlxdh(String xmlxdh) {
        this.xmlxdh = xmlxdh;
    }
    
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "zzsj")
    public Date getZzsj() {
        return zzsj;
    }
    public void setZzsj(Date zzsj) {
        this.zzsj = zzsj;
    }
    @Column(name = "jbxx")
    public String getJbxx() {
        return jbxx;
    }
    public void setJbxx(String jbxx) {
        this.jbxx = jbxx;
    }
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "tbsj")
    public Date getTbsj() {
        return tbsj;
    }
    public void setTbsj(Date tbsj) {
        this.tbsj = tbsj;
    }
    @Column(name = "bhyj")
    public String getBhyj() {
        return bhyj;
    }
    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getZzsje() {
        return zzsje;
    }
    public void setZzsje(Date zzsje) {
        this.zzsje = zzsje;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public String getPicPhone() {
        return picPhone;
    }
    public void setPicPhone(String picPhone) {
        this.picPhone = picPhone;
    }
    public String getPicEmail() {
        return picEmail;
    }
    public void setPicEmail(String picEmail) {
        this.picEmail = picEmail;
    }
    public String getPicTitle() {
        return picTitle;
    }
    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	public String getThreadId1() {
		return threadId1;
	}
	public void setThreadId1(String threadId1) {
		this.threadId1 = threadId1;
	}
	public String getThreadId2() {
		return threadId2;
	}
	public void setThreadId2(String threadId2) {
		this.threadId2 = threadId2;
	}
}
