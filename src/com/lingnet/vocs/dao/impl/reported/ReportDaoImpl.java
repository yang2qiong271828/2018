package com.lingnet.vocs.dao.impl.reported;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.reported.ReportDao;
import com.lingnet.vocs.entity.ContactsReport;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.ProjectPreparation;
import com.lingnet.vocs.service.partner.PartnerService;


@Repository("reportDao")
public class ReportDaoImpl extends BaseDaoImpl<ProjectPreparation, String> implements
ReportDao {

    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    @Resource(name = "partnerService")
    private PartnerService partnerService;
    
	@SuppressWarnings("null")
	@Override
	public List<ProjectPreparation> getListData(Pager pager,String i,String partnerId,String status) {
		// TODO Auto-generated method stub
		String sql = "";
		sql = "SELECT * FROM project_preparation where isdelete='0'";
		if(status!="null"&&status!=null&&!status.equals("")){
        	sql=sql+"  and status='"+status+"'";	
        }else if(i=="2"){
		sql=sql+" and partnerId='"+partnerId+"'";	
		} else if(i=="3"){
			sql=sql+" and (status='1' or status='2' or status='3' or status='4' or status='5')";	
			}else if(i=="4"){
				sql=sql+"  and (status='2' or status='3' or status='4' or status='5')";	
			}
		List<ProjectPreparation> pp=(List<ProjectPreparation>) findPagerBySql(pager, sql);
        
		return pp;
	}

	@Override
	public List<ContactsReport> getContactsByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		String sql = "select * from CONTACTS_REPORT where partnerId='"+ partnerId + "'";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(ContactsReport.class);
        return query.list();
	}

    @Override
    public Pager getDemandData(Pager pager,String id) {
        String userName = LingUtil.userName();
        String a=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        String b=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String userId = user.getPartnerId();
        StringBuffer sql = new StringBuffer();
        if("true".equals(a) || "true".equals(b)){
            sql.append(" select * from (select partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from demand where bid = '"+id+"' and status <> '0'");
            sql.append(" UNION select partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from demand where partnerId = '"+userId+"' and bid = '"+id+"' ) s");
        }else{
            sql.append(" select partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from demand where bid = '"+id+"' and partnerId = '"+userId+"'");
        }
        pager = findPagerBySql(pager, sql.toString());
        return pager;
    }

    @Override
    public Pager getData(Pager pager, String status) {

    	
    	int pageSize = pager.getPageSize();
        int pageNumber = pager.getPageNumber();
        String userName = LingUtil.userName();
       // String userName = "admin";
        String a=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        String b=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
       // QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", userName));
     
        String userId = user.getPartnerId();
        String partnerId = "";
        StringBuffer sql = new StringBuffer();
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("true".equals(a) || "true".equals(b)){
        	
        	//  ff8080815ec77ff3015ecba164110045
            if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){//销售对接人
           /*    Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
                if(joint != null){
                    List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                    for(int i = 0 ;i<par.size();i++){
                        Partner partner = par.get(i);
                        partnerId += partner.getId()+"','";
                    }
                }*/
            	user.setUsername("admin");
            }
//            sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
//            sql.append(" select top "+pageNumber*pageSize+" *,s.a bb from (select p.id,p.cus_com_name,p.project_name,p.project_status,");
            sql.append(" select p.id,p.cus_com_name,p.project_name,p.project_status,");
            sql.append(" p.reform_emphasis,p.status s1,p.yijian,p.yijiann,p.introduce,p.emission_from");
            sql.append(" ,p.isdevice,p.technology,p.problem,p.environmental_requirements,p.exhaust_source,");
            sql.append(" p.progress,remark,p.project_address,p.partnerId,p.jzsj,g.status s2, ");
            sql.append(" CASE  WHEN g.status = 2 THEN 1  ");
            sql.append(" WHEN g.status = 1 THEN 2 ");
            sql.append(" WHEN g.status = 3 THEN 3 ");
            sql.append(" WHEN g.status = 0 THEN 3 ");
            sql.append(" WHEN p.status = 3 THEN 3 ");
            sql.append(" WHEN p.status = 2 THEN 4 ");
            sql.append(" WHEN p.status = 5 THEN 5 ");
            sql.append(" WHEN p.status = 1 THEN 6 ");
            sql.append(" WHEN p.status = 0 THEN 7 ");
            sql.append(" WHEN p.status = 4 THEN 8 ");
            sql.append(" ELSE 8 END ab ");
            sql.append(" from project_preparation p LEFT JOIN GRADE g on g.reportId = p.id where p.status <> '0'");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and p.partnerId in ('"+partnerId+"') ");
            }
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and s1 = '"+status+"'");
            }
//            sql.append(" order BY ab desc");
//            sql.append(" )s");
//            if(StringUtils.isNotEmpty(status)){
//                sql.append(" where s.s1 = '"+status+"'");
//            }
//            sql.append("  order BY s.a desc   ");
//            sql.append(" ) b1)t1 order by bb asc )as t2 order by bb ");
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//渠道
                sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
                sql.append(" select top "+pageNumber*pageSize+" *,s.a bb from (select p.id,p.cus_com_name,p.project_name,p.project_status,");
                sql.append(" p.reform_emphasis,p.status s1,p.yijian,p.yijiann,p.introduce,p.emission_from");
                sql.append(" ,p.isdevice,p.technology,p.problem,p.environmental_requirements,p.exhaust_source,");
                sql.append(" p.progress,remark,p.project_address,p.partnerId,p.jzsj,g.status s2, ");
                sql.append(" CASE  WHEN g.status = 2 THEN 1  ");
                sql.append(" WHEN g.status = 1 THEN 2 ");
                sql.append(" WHEN g.status = 3 THEN 3 ");
                sql.append(" WHEN g.status = 0 THEN 3 ");
                sql.append(" WHEN p.status = 3 THEN 3 ");
                sql.append(" WHEN p.status = 2 THEN 4 ");
                sql.append(" WHEN p.status = 5 THEN 5 ");
                sql.append(" WHEN p.status = 1 THEN 6 ");
                sql.append(" WHEN p.status = 0 THEN 7 ");
                sql.append(" WHEN p.status = 4 THEN 8 ");
                sql.append(" ELSE 8 END ab ");
                sql.append(" from project_preparation p LEFT JOIN GRADE g on g.reportId = p.id where p.status <> '0'");
                if(StringUtils.isNotEmpty(status)){
                    sql.append(" and s1 = '"+status+"'");
                }
//                sql.append(" order BY ab desc");
//                sql.append(" )s");
//                if(StringUtils.isNotEmpty(status)){
//                    sql.append(" where s.s1 = '"+status+"'");
//                }
//                sql.append("  order BY s.a");
//                sql.append(" ) b1)t1 order by bb asc )as t2 order by bb ");
            }else{
//                sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
//                sql.append(" select top "+pageNumber*pageSize+" *,s.a bb from (select p.id,p.cus_com_name,p.project_name,p.project_status,");
            	sql.append(" select p.id,p.cus_com_name,p.project_name,p.project_status,");
                sql.append(" p.reform_emphasis,p.status s1,p.yijian,p.yijiann,p.introduce,p.emission_from");
                sql.append(" ,p.isdevice,p.technology,p.problem,p.environmental_requirements,p.exhaust_source,");
                sql.append(" p.progress,remark,p.project_address,p.partnerId,p.jzsj,g.status s2, ");
                sql.append(" CASE  WHEN g.status = 2 THEN 1  ");
                sql.append(" WHEN g.status = 1 THEN 2 ");
                sql.append(" WHEN g.status = 3 THEN 3 ");
                sql.append(" WHEN g.status = 0 THEN 3 ");
                sql.append(" WHEN p.status = 3 THEN 3 ");
                sql.append(" WHEN p.status = 2 THEN 4 ");
                sql.append(" WHEN p.status = 5 THEN 5 ");
                sql.append(" WHEN p.status = 1 THEN 6 ");
                sql.append(" WHEN p.status = 0 THEN 7 ");
                sql.append(" WHEN p.status = 4 THEN 8 ");
                sql.append(" ELSE 8 END ab ");
                sql.append(" from project_preparation p LEFT JOIN GRADE g on g.reportId = p.id where p.partnerId in ('"+userId+"') ");
                if(StringUtils.isNotEmpty(status)){
                    sql.append(" and s1 = '"+status+"'");
                }
//                sql.append(" order BY ab desc");
//                sql.append(" )s");
//                if(StringUtils.isNotEmpty(status)){
//                    sql.append(" where s.s1 = '"+status+"'");
//                }
//                sql.append("  order BY s.a ");
//                sql.append(" ) b1)t1 order by bb asc )as t2 order by bb ");
            }
        }
        String ss = sql.toString().toUpperCase();
        int az = ss.toString().indexOf("ORDER BY");
        if(az!=-1){
            ss = ss.substring(0,az);
        }
        Integer size =  (Integer)this.getSession().createSQLQuery("SELECT COUNT(*) FROM (" +ss+") t").uniqueResult();
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        pager.setTotalCount(size);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        pager.setResult(query.list());
        return pager;
        /*SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        return query.list();*/
       /* System.out.println(sql.toString());
        return findPagerBySql(pager, sql.toString());*/
    }

    @Override
    public Pager getGjData(Pager pager, String id) {
        String userName = LingUtil.userName();
        String a=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        String b=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String userId = user.getPartnerId();
        String partnerId = "";
        StringBuffer sql = new StringBuffer();
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("true".equals(a) || "true".equals(b)){
            if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){//销售对接人
                Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
                if(joint != null){
                    List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                    for(int i = 0 ;i<par.size();i++){
                        Partner partner = par.get(i);
                        partnerId += partner.getId()+"','";
                    }
                }
            }
            sql.append(" select id,bffs,bfnr,bftime from follow where 1 = 1");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and partnerId in ('"+partnerId+"') ");
            }
            if(StringUtils.isNotEmpty(id)){
                sql.append(" and reportId  = '"+id+"'");
            }
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//渠道
                sql.append(" select id,bffs,bfnr,bftime from follow where 1 = 1");
                
            }else{
                sql.append(" select id,bffs,bfnr,bftime from follow where 1 = 1 and partnerId in ('"+userId+"') ");
            }  
            if(StringUtils.isNotEmpty(id)){
                sql.append(" and reportId  = '"+id+"'");
            }
        }
        pager = findPagerBySql(pager, sql.toString());
        return pager;
    }

	

}
