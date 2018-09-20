package com.lingnet.vocs.action.region;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.lingnet.common.action.BaseAction;

@Namespace("/region")
@ParentPackage("region")
public class RegionAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6447368767476709998L;

	public String list() {
		return LIST;
	}
}
