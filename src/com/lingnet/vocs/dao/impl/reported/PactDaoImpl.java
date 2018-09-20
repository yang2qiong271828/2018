package com.lingnet.vocs.dao.impl.reported;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.components.Select;
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
import com.lingnet.vocs.dao.reported.PactDao;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Pact;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.partner.PartnerService;

@Repository("pactDao")
public class PactDaoImpl extends BaseDaoImpl<Pact, String> implements PactDao {

    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    @Resource(name = "partnerService")
    private PartnerService partnerService;
    @Override
    
    public Pager getData(Pager pager) {
        int pageSize = pager.getPageSize();
        int pageNumber = pager.getPageNumber();
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
//            sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
//            sql.append(" select top "+pageNumber*pageSize+" *,(account_receivable-ssje) ye  from (");
            sql.append(" select * from (select *,(account_receivable-ssje) ye  from (");
            sql.append(" SELECT p.id, MAX(p.code) code, MAX(p.sign_date) sign_date, MAX(p.account_receivable) account_receivable, MAX(p.bzsm) bzsm, SUM(h.ssje) ssje");
            sql.append(" ,MAX(p.company_Id) company_Id FROM pact p LEFT JOIN hkpact h on h.htId = p.id where 1 = 1");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and p.partnerId in ('"+partnerId+"') ");
            }
            sql.append(" GROUP BY p.id");
            sql.append(" )b) a ORDER BY ye desc");
//            sql.append(" ) b1)t1 order by ye asc )as t2 order by ye desc ");
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//渠道
//                sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
//                sql.append(" select top "+pageNumber*pageSize+" *,(account_receivable-ssje) ye  from (");
            	sql.append(" select * from (select *,(account_receivable-ssje) ye  from (");
                sql.append(" SELECT p.id, MAX(p.code) code, MAX(p.sign_date) sign_date, MAX(p.account_receivable) account_receivable, MAX(p.bzsm) bzsm, SUM(h.ssje) ssje");
                sql.append(" ,MAX(p.company_Id) company_Id FROM pact p LEFT JOIN hkpact h on h.htId = p.id where 1 = 1");
                sql.append(" GROUP BY p.id");
                sql.append(" )b) a ORDER BY ye desc");
//                sql.append(" ) b1)t1 order by ye asc )as t2 order by ye desc ");
            }else{
//                sql.append(" SELECT * from ( SELECT TOP "+pageSize+" * FROM ( SELECT * FROM ( ");
//                sql.append(" select top "+pageNumber*pageSize+" *,(account_receivable-ssje) ye  from (");
            	sql.append(" select * from (select *,(account_receivable-ssje) ye  from (");
                sql.append(" SELECT p.id, MAX(p.code) code, MAX(p.sign_date) sign_date, MAX(p.account_receivable) account_receivable, MAX(p.bzsm) bzsm, SUM(h.ssje) ssje");
                sql.append(" ,MAX(p.company_Id) company_Id FROM pact p LEFT JOIN hkpact h on h.htId = p.id where p.partnerId in ('"+userId+"') ");
                sql.append(" GROUP BY p.id");
                sql.append(" )b) a ORDER BY ye desc");
//                sql.append(" ) b1)t1 order by ye asc )as t2 order by ye desc ");
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
//        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
//        return query.list();
    }
    @Override
    public Double getSum(String id, String type,String date) {
        StringBuffer sql = new StringBuffer();
        if("1".equals(type)){//回款计划
            sql.append(" select sum(j.hkje) from jhpact j left join pact p on p.id = j.htId where j.date < '"+date+"' and p.id = '"+id+"' ");
        }else{//回款记录
            sql.append(" select sum(h.ssje) from hkpact h left join pact p on p.id = h.htId where h.hkDate < '"+date+"' and p.id = '"+id+"' ");
        }
        Object result = this.getSession().createSQLQuery(sql.toString()).uniqueResult();
        result = result == null ?0:result;
        return Double.valueOf(result.toString());
    }
    @Override
    public Pager getJhData(Pager pager, String id) {
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
            sql.append(" select id,hkje,kpje,stage,type,createdate,date from  jhpact where  htid = '"+id+"'");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and partnerId in ('"+partnerId+"') ");
            }
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//渠道
                sql.append(" select id,hkje,kpje,stage,type,createdate,date from  jhpact where  htid = '"+id+"'");
            }else{
                sql.append(" select id,hkje,kpje,stage,type,createdate,date from  jhpact where htid = '"+id+"' and partnerId in ('"+userId+"') ");
            }
        }
        pager = findPagerBySql(pager, sql.toString());
        return pager;
    }
    @Override
    public Pager getHkData(Pager pager, String id) {
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
            sql.append(" select id,ssje,fpje,hkdate,fpdate,createdate from  hkpact where htid = '"+id+"'");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and partnerId in ('"+partnerId+"') ");
            }
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//渠道
                sql.append(" select id,ssje,fpje,hkdate,fpdate,createdate from  hkpact where  htid = '"+id+"'");
            }else{
                sql.append(" select id,ssje,fpje,hkdate,fpdate,createdate from  hkpact where  htid = '"+id+"' and partnerId in ('"+userId+"') ");
            }
        }
        pager = findPagerBySql(pager, sql.toString());
        return pager;
    }
	@Override
	public List<Pact> getPacts(String partnerId) {
		String sql = "SELECT * FROM pact where  partnerId = ?";
	    SQLQuery query = getSession().createSQLQuery(sql);
        //设置参数
        query.setParameter(0, partnerId);
        //将结果封装到哪个对象
        query.addEntity(Pact.class);
        List<Pact> list = query.list();
		return list ;
	}

}
