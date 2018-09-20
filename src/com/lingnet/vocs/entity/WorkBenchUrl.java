/**
 * 
 */
package com.lingnet.vocs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 工作台路径
 */
@Entity
@Table(name="JC_WORK_BENCH_URL")
public class WorkBenchUrl extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -4388051559602538047L;
	private String title;//标题
	private String dataName;//跳转标题
	private String pageURL;//显示路径
    private String dataURL;//跳转路径
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="DATA_NAME")
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	@Column(name="PAGE_URL")
	public String getPageURL() {
		return pageURL;
	}
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}
	@Column(name="DATA_URL")
	public String getDataURL() {
		return dataURL;
	}
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}
    
    
}
