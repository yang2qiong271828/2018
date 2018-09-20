package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: ProjectPreparation 
 * @Description: TODO 
 * @author yic
 * @date 2018年1月19日 下午4:18:58 
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true) 
@Table(name = "project_preparation")
public class ProjectPreparation extends BaseEntity implements java.io.Serializable{

	/**
	 * @ProjectPreparation.java
	 * @author yic 
	 * @date 2018年1月19日 下午4:19:33
	 */
	private static final long serialVersionUID = 4577846646574644745L;
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
	private String lxdh;//联系电话
	private String email;//电子邮件
	private String isdelete;//是否删除 0未删除 1已删除
	private String status;//0草稿 1已提交 2正在审核 3审核通过 4审核不通过 5重新提交
	private String partnerId;//经销商id
	private String yijian;
	private String yijiann;
	private String cusComNameId;//客户id
	
	//2.8新增
	private String xmys;//项目预算
	private String xmzq;//项目周期
	private String hylb;//行业类别
	private String ejhy;//二级行业
	private Date jzsj;//截止时间   修改成为审核时间。
	
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
	
	@Column(name = "lxdh")
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "isdelete")
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name = "partnerId")
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	@Column(name = "yijian")
	public String getYijian() {
		return yijian;
	}
	public void setYijian(String yijian) {
		this.yijian = yijian;
	}
	
	@Column(name = "yijiann")
	public String getYijiann() {
		return yijiann;
	}
	public void setYijiann(String yijiann) {
		this.yijiann = yijiann;
	}
	@Column(name = "cus_com_name_id")
    public String getCusComNameId() {
        return cusComNameId;
    }
    public void setCusComNameId(String cusComNameId) {
        this.cusComNameId = cusComNameId;
    }
    @Column(name = "xmys")
    public String getXmys() {
        return xmys;
    }
    public void setXmys(String xmys) {
        this.xmys = xmys;
    }
    @Column(name = "xmzq")
    public String getXmzq() {
        return xmzq;
    }
    public void setXmzq(String xmzq) {
        this.xmzq = xmzq;
    }
    @Column(name = "hylb")
    public String getHylb() {
        return hylb;
    }
    public void setHylb(String hylb) {
        this.hylb = hylb;
    }
    @Column(name = "ejhy")
    public String getEjhy() {
        return ejhy;
    }
    public void setEjhy(String ejhy) {
        this.ejhy = ejhy;
    }
    @Column(name = "jzsj")
    public Date getJzsj() {
        return jzsj;
    }
    public void setJzsj(Date jzsj) {
        this.jzsj = jzsj;
    }
    
    
}
