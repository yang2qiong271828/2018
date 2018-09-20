package com.lingnet.vocs.service.workorder;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.AreaResponsible;

/**
 * 区域负责人
 * @ClassName: AreaResponsibleService 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月28日 下午6:09:03 
 *
 */
public interface AreaResponsibleService  extends BaseService<AreaResponsible, String>{

    public String saveOrUpdate(AreaResponsible areaRes) throws Exception;

    public String getListData(Pager pager, String partnerId,String area);

    
}
