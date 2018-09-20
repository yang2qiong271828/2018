package com.lingnet.vocs.entity;

// default package

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 企业文件表
 */
@Entity
@Table(name = "clew_file")
public class ClewFile extends BaseEntity implements Serializable {

	// Fields


	/**
	 * 
	 */
	private static final long serialVersionUID = 8095842032573837874L;
	private String filename;
	private String description;
	private String filepath;
	private String threadId;
	private String caseId;//上传人的ID
	private String state;//什么阶段的资料
	private String wjstatus;//文件类型
    private String wjxs;//文件形式
    private String jdstatus;//阶段   ----1,处置2，移交3，办理4，办结
	
	// Constructors

	/** default constructor */
	public ClewFile() {
	}

	

	// Property accessors

	@Column(name = "filename", length = 100)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	

	@Column(name = "description")
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name = "threadId",length = 32)
    public String getThreadId() {
        return threadId;
    }



    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }



    @Column(name = "caseId",length = 32)
	public String getCaseId() {
		return caseId;
	}



	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}



	@Column(name = "state",length = 2)
	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "wjstatus", length = 200)
    public String getWjstatus() {
        return wjstatus;
    }
    public void setWjstatus(String wjstatus) {
        this.wjstatus = wjstatus;
    }
    @Column(name = "wjxs", length = 200)
    public String getWjxs() {
        return wjxs;
    }
    public void setWjxs(String wjxs) {
        this.wjxs = wjxs;
    }


    @Column(name = "jdstatus", length = 32)
    public String getJdstatus() {
        return jdstatus;
    }



    public void setJdstatus(String jdstatus) {
        this.jdstatus = jdstatus;
    }
	
    


	
    

}