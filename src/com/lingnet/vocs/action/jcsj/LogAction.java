package com.lingnet.vocs.action.jcsj;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.service.jcsj.LogService;

/**
 * 
 * @ClassName: LogAction
 * @Description: 日志管理
 * @author 姜平豹
 * @date 2017-3-26 上午10:04:07
 * 
 */
@Controller
@ParentPackage("jcsj")
public class LogAction extends BaseAction {

	private static final long serialVersionUID = -2684708119893418263L;

	@Resource(name = "logService")
	private LogService logService;// 日志管理Service类

	private String czUser;
	private String stadate;
	private String enddate;
	private String czType;
	private String djType;
	private String ip;
	private String pcName;

	public String list() {
		return LIST;
	}

	/**
	 * 日志管理查询页
	 */
	public String search() {
		HashMap<String, String> searchMap = new HashMap<String, String>();
		// 根据查询条件查询
		searchMap.put("czUser", czUser);
		searchMap.put("stadate", stadate);
		searchMap.put("enddate", enddate);
		searchMap.put("czType", czType);
		searchMap.put("djType", djType);
		searchMap.put("ip", ip);
		return ajax(Status.success,
				JsonUtil.Encode(logService.search(pager, searchMap)));
	}

	// ////////////////////////////////////////////////////////
	public String getStadate() {
		return stadate;
	}

	public void setStadate(String stadate) {
		this.stadate = stadate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getCzUser() {
		return czUser;
	}

	public void setCzUser(String czUser) {
		this.czUser = czUser;
	}

	public String getCzType() {
		return czType;
	}

	public void setCzType(String czType) {
		this.czType = czType;
	}

	public String getDjType() {
		return djType;
	}

	public void setDjType(String djType) {
		this.djType = djType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
}
