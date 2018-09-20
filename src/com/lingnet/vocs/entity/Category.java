package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * BsCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true) 
@Table(name = "bs_category")
public class Category extends BaseEntity implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3933108949216734267L;
	// Fields

    private String categorycode; //行业编号
    private String name; //行业名称
    private String baseid; //父ID
    private String basename; //父节点名称
    private Integer stopflag; //是否停用(0:否;1:是)
    private Integer sort; //排序
    private Integer hotflag; //是否推荐(0:否;1:是)

    // Constructors

    /** default constructor */
    public Category() {
    }

    /** full constructor */
    public Category( String categorycode,
            String name, String baseid, String basename, Integer stopflag,
            Integer sort, Integer hotflag) {
        this.categorycode = categorycode;
        this.name = name;
        this.baseid = baseid;
        this.basename = basename;
        this.stopflag = stopflag;
        this.sort = sort;
        this.hotflag = hotflag;
    }


    @Column(name = "CATEGORY_CODE", length = 32)
    public String getCategorycode() {
        return this.categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "BASEID", length = 32)
    public String getBaseid() {
        return this.baseid;
    }

    public void setBaseid(String baseid) {
        this.baseid = baseid;
    }

    @Column(name = "BASENAME", length = 50)
    public String getBasename() {
        return this.basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    @Column(name = "STOPFLAG")
    public Integer getStopflag() {
        return this.stopflag;
    }

    public void setStopflag(Integer stopflag) {
        this.stopflag = stopflag;
    }

    @Column(name = "SORT")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "HOT_FLAG")
    public Integer getHotflag() {
        return this.hotflag;
    }

    public void setHotflag(Integer hotflag) {
        this.hotflag = hotflag;
    }

}