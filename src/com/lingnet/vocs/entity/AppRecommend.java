/**
 * @AppRecommend.java
 * @com.lingnet.mec.entity.app
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2017
 * @version V
 * @since 2017年8月2日
 */
package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/** 
 * @ClassName: AppRecommend 
 * @Description:推荐 
 * @author xuhp
 * @date 2017年8月2日 上午8:38:40 
 *  
 */
@Entity
@Table(name="APP_RECOMMEND")
public class AppRecommend extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7923405210327376210L;

	private Integer loc;//位置（1首页、2体检）
	private Integer type;//类型(1图片、2网址链接、3视频)
	private String url;//路径
	private String cid;//(不用)
	private Integer sort;//顺序
	private Integer state;//状态 0停用1启用
	private String title;//标题
	private String dataUrl;//跳转路径（不用）
	private String status;//类别（1前台，2后台）
	
	/////////////////////////////
	public Integer getLoc() {
		return loc;
	}
	public void setLoc(Integer loc) {
		this.loc = loc;
	}
	@Column(name="REC_TYPE")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="REC_URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Column(name="REC_SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name="REC_STATE")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="DATA_URL")
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	@Column(name="status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
	
}
