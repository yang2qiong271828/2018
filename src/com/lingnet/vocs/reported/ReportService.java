package com.lingnet.vocs.service.reported;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Follow;
import com.lingnet.vocs.entity.Grade;
import com.lingnet.vocs.entity.OperatingParameter;
import com.lingnet.vocs.entity.ProjectPreparation;
/**
 * 
 * @ClassName: ReportService 
 * @Description: TODO 
 * @author yic
 * @date 2018年1月22日 下午1:59:21 
 *
 */
public interface ReportService extends BaseService<ProjectPreparation, String> {

	public String getListData(Pager pager, String status);

	public void saveOrUpdate(ProjectPreparation projectPreparation,
			String griddata)  throws Exception;

	public Object getContactListData(String partnerId);

	public String remove(String string);

    public String getDemandData(Pager pager, String id);

    public String getGjData(Pager pager, String id);

    public String saveGj(Follow follow);

    public void saveGrade(Grade grade);

    public String sp(String id, String yijian);

    public String spno(String id, String yijian);

}
