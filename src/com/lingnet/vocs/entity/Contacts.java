/**
 * @Contacts.java
 * @com.lingnet.vocs.entity
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月19日
 */

package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * @ClassName: Contacts
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月19日 上午10:12:12
 * 
 */
@Entity
@Table(name = "CONTACTS")
public class Contacts extends BaseEntity {

    // 姓名
    private String picName;
    // 性别
    private String picGender;
    // 职务
    private String picTitle;
    // 部门
    private String picDepartment;
    // 手机
    private String picPhone;
    // 邮箱
    private String picEmail;
    // 主联系人标识
    private String picMain;
    // 隶属合作商id
    private String partnerId;


    /**
     * @return the name
     */
    @Column(name = "name")
    public String getPicName() {
        return picName;
    }

    /**
     * @param name
     *            the name to set
     */

    public void setPicName(String picName) {
        this.picName = picName;
    }

    /**
     * @return the gender
     */
    @Column(name = "gender")
    public String getPicGender() {
        return picGender;
    }

    /**
     * @param gender
     *            the gender to set
     */

    public void setPicGender(String picGender) {
        this.picGender = picGender;
    }

    /**
     * @return the title
     */
    @Column(name = "title")
    public String getPicTitle() {
        return picTitle;
    }

    /**
     * @param title
     *            the title to set
     */

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    /**
     * @return the department
     */
    @Column(name = "department")
    public String getPicDepartment() {
        return picDepartment;
    }

    /**
     * @param department
     *            the department to set
     */

    public void setPicDepartment(String picDepartment) {
        this.picDepartment = picDepartment;
    }

    /**
     * @return the phone
     */
    @Column(name = "phone")
    public String getPicPhone() {
        return picPhone;
    }

    /**
     * @param phone
     *            the phone to set
     */

    public void setPicPhone(String picPhone) {
        this.picPhone = picPhone;
    }

    /**
     * @return the email
     */
    @Column(name = "email")
    public String getPicEmail() {
        return picEmail;
    }

    /**
     * @param email
     *            the email to set
     */

    public void setPicEmail(String picEmail) {
        this.picEmail = picEmail;
    }

    /**
     * @return the mainContact
     */
    @Column(name = "mainContact")
    public String getPicMain() {
        return picMain;
    }

    /**
     * @param mainContact
     *            the mainContact to set
     */

    public void setPicMain(String picMain) {
        this.picMain = picMain;
    }

    /**
     * @return the partnerId
     */
    @Column(name = "partnerId")
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId
     *            the partnerId to set
     */

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
}
