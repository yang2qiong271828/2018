package com.lingnet.vocs.service.reported;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ClewFile;

public interface ClewFileService extends BaseService<ClewFile, String> {
    /**
     * 显示列表信息
     * @return
     */
	public String listData(Pager pager, String threadId);

	
	/**
	 * 保存和修改方法
	 * @param jcDlqyglCompanyInfo
	 * @return
	 */
	public String saveOrUpdate(ClewFile jcDlqyglCompanyFile);


	public String findById(String id);


	public ClewFile findByThreadId(String threadId);


	
	
		
}
