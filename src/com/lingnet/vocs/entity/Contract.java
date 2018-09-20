
/**
 * @Contract.java
 * @com.lingnet.vocs.entity
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;


/**
 * @ClassName: Contract
 * @Description: TODO
 * @author zy
 * @date 2017年7月20日 下午6:36:20
 * 
 */

@Entity
@Table(name = "CONTRACT")
public class Contract extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 2620084848404706587L;

    //合同编号
    private String code;
    // 甲方类型 合作商或者客户
    private String partnerOrCustomer;
    // 乙方公司 Id
    private String companyId;
    // 甲方公司 Name
    private String companyName;
    //合同类型  1试用 2 购买 3 租赁
    private String contractType;
    //签订日期
    private Date signDate;
    //合同到期时间
    private Date endDate;
    //负责人 ID
    private String pic;
    //负责人联系方式
    private String picPhone;
    //付款方式
    private String paymentType;
    //应收金额
    private Double accountReceivable;
    //减免金额
    private Double discount;
    //实收金额
    private Double paidupCapital;
    //销售人员 id
    private String salesman;
    //销售人员 联系方式
    private String salesmanContact;
    // 审核状态
    private String verifyStatus;
    // 审核人员
    private String verifyPerson;
    // 审核日期
    private Date verifyDate;
    // 合同创建人
    private String createPerson;
    // 合同提交人
    private String submitPerson;
    // 驳回意见
    private String rejectReason;
    // 驳回人
    private String rejectPerson;
    // 甲方公司Id
    private String sponsor;
    /********** 临时字段 ***********/
    //签合同人所在公司 名称
    private String fullName;
    //销售人员名称 名称
    private String salesmanId;
    //负责人 名称
    private String picId;
    // 合同类型
    private String contractTypeName;
    // 甲方类别
    private String porc;
    // 付费方式
    private String payType;
    
    private String states;
  
    private String verifyName;
  
    private Date allivalDate;
    
    private String dispose;//处理到期日期的状态(0 未处理 1 已处理)
    /*********************************************************************/
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "partner_or_customer")
    public String getPartnerOrCustomer() {
        return partnerOrCustomer;
    }
    public void setPartnerOrCustomer(String partnerOrCustomer) {
        this.partnerOrCustomer = partnerOrCustomer;
    }

    @Column(name = "company_id")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "contract_type")
    public String getContractType() {
        return contractType;
    }
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Column(name = "sign_date")
    public Date getSignDate() {
        return signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "pic")
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    @Column(name = "pic_phone")
    public String getPicPhone() {
        return picPhone;
    }
    public void setPicPhone(String picPhone) {
        this.picPhone = picPhone;
    }

    @Column(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Column(name = "account_receivable")
    public Double getAccountReceivable() {
        return accountReceivable;
    }

    public void setAccountReceivable(Double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Column(name = "paidup_capital")
    public Double getPaidupCapital() {
        return paidupCapital;
    }

    public void setPaidupCapital(Double paidupCapital) {
        this.paidupCapital = paidupCapital;
    }

    @Column(name = "salesman")
    public String getSalesman() {
        return salesman;
    }
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    @Column(name = "salesman_contact")
    public String getSalesmanContact() {
        return salesmanContact;
    }
    public void setSalesmanContact(String salesmanContact) {
        this.salesmanContact = salesmanContact;
    }
    
    @Transient
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Transient 
    public String getSalesmanId() {
        return salesmanId;
    }
    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }
    @Transient 
    public String getPicId() {
        return picId;
    }
    public void setPicId(String picId) {
        this.picId = picId;
    }

    @Column(name = "verify_status")
    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
    @Transient 
    public String getContractTypeName() {
        return contractTypeName;
    }
    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }
    @Transient 
    public String getPorc() {
        return porc;
    }
    public void setPorc(String porc) {
        this.porc = porc;
    }
    @Transient 
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    @Transient 
    public String getStates() {
        return states;
    }
    public void setStates(String states) {
        this.states = states;
    }
    
   @Column(name = "verify_person")
    public String getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson;
    }

    @Column(name = "verify_date")
    public Date getVerifyDate() {
        return verifyDate;
    }
    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    @Column(name = "create_person")
    public String getCreatePerson() {
        return createPerson;
    }
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Column(name = "submit_person")
    public String getSubmitPerson() {
        return submitPerson;
    }
    public void setSubmitPerson(String submitPerson) {
        this.submitPerson = submitPerson;
    }

    @Column(name = "sponsor")
    public String getSponsor() {
        return sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    @Column(name = "reject_reason")
    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Column(name = "reject_person")
    public String getRejectPerson() {
        return rejectPerson;
    }

    public void setRejectPerson(String rejectPerson) {
        this.rejectPerson = rejectPerson;
    }
    @Transient
    public String getVerifyName() {
        return verifyName;
    }
    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    @Transient
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Column(name = "allival_date",columnDefinition="DATE")
	public Date getAllivalDate() {
		return allivalDate;
	}
	public void setAllivalDate(Date allivalDate) {
		this.allivalDate = allivalDate;
	}
	@Column(name = "dispose" )
	public String getDispose() {
		return dispose;
	}
	public void setDispose(String dispose) {
		this.dispose = dispose;
	}
	
 
	
}
