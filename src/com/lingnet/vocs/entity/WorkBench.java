package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "JC_WORK_BENCH")
public class WorkBench extends BaseEntity implements java.io.Serializable  { 
    private static final long serialVersionUID = 1L;
    private String title;//标题
    private String content;//内容(用来显示在页面上)
    private String contentSave;//内容（用于显示在ueditor编辑）
    private String pageURL;//显示路径
    private String dataURL;//跳转路径
    private String memo;//备注
    private Integer state;   //0：关闭状态     1：开启状态  2:草稿 3:审核通过
    private String partnerOrCustomer;//约束 P合作商C客户H华世洁        S系统（系统工作台）
    private Integer group;//分组
    private Integer height;//高
    private Integer width;//宽
    private Integer sort;//排序
    private String dataName;//跳转标题
    private String location;//位置（系统工作台）  0：置顶 1：尾部
    private String type;//类型 1系统（系统工作台）    0普通（工作台）
    private String urlId;//workbenchurl id （工作台）
    private String urlTitle;//workbenchurl title （工作台）
    private String lx;//类型 0法律法规 1vocs治理技术知识
    private String head;//总标题 
    public WorkBench() {
        super();
    }
    @Column(name = "TITLE")
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "PAGE_URL")
    public String getPageURL() {
        return pageURL;
    }
    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }
    @Column(name = "DATA_URL")
    public String getDataURL() {
        return dataURL;
    }
    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }
    @Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Column(name = "STATE")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    @Column(name = "PARTNER_OR_CUSTOMER")
	public String getPartnerOrCustomer() {
		return partnerOrCustomer;
	}
	public void setPartnerOrCustomer(String partnerOrCustomer) {
		this.partnerOrCustomer = partnerOrCustomer;
	}
	@Column(name = "BENCH_GROUP")
	public Integer getGroup() {
		return group;
	}
	public void setGroup(Integer group) {
		this.group = group;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	@Column(name="BENCH_SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name="DATA_NAME")
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	@Column(name="BENCH_LOC")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="BENCH_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="CONTENT_SAVE")
	public String getContentSave() {
		return contentSave;
	}
	public void setContentSave(String contentSave) {
		this.contentSave = contentSave;
	}
	@Column(name="URL_ID")
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	@Column(name="URL_TITLE")
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	@Column(name="LX")
    public String getLx() {
        return lx;
    }
    public void setLx(String lx) {
        this.lx = lx;
    }
    @Column(name = "HEAD")
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
}
