package com.lingnet.vocs.dao.reported;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.ContactsReport;
import com.lingnet.vocs.entity.Demand;
import com.lingnet.vocs.entity.ProjectPreparation;

public interface ReportDao extends BaseDao<ProjectPreparation, String> {

	public List<ProjectPreparation> getListData(Pager pager, String i, String partnerId, String status);

	public List<ContactsReport> getContactsByPartnerId(String partnerId);

    public Pager getDemandData(Pager pager,String id);

    public Pager getData(Pager pager, String status);

    public Pager getGjData(Pager pager, String id);



}
