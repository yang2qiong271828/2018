/**
 * @PartnerDaoImpl.java
 * @com.lingnet.vocs.dao.impl.partner
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月2日
 */

package com.lingnet.vocs.dao.impl.partner;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.partner.PartnerDao;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.SupplierCase;
import com.lingnet.vocs.service.partner.PartnerService;

/**
 * @ClassName: PartnerDaoImpl
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月2日 下午3:37:56
 * 
 */
@Repository("partnerDao")
@SuppressWarnings({  "unchecked" })
public class PartnerDaoImpl extends BaseDaoImplInit<Partner, String> implements
        PartnerDao {
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "partnerService")
    private PartnerService partnerService;

    @Override
	public List<Contacts> getContactsByPartnerId(String partnerId) {
        String sql = "select * from CONTACTS where partnerId='"+ partnerId + "'";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(Contacts.class);
        return query.list();
    }


	@Override
	public List<Contacts> getMainContactbyPartnerId(String partnerId) {
		String sql = "select * from contacts where partnerid ='"+partnerId+"' and maincontact = '1'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Contacts.class);
		return query.list();
	}

	@Override
    public List<Object[]> getPartnerAndCustomerBySql(Map<String, String> m,
            String partnerId, String partnerOrCustomer) {
        String sql = "select P.id,P.name,P.address,P.code,P.partner_or_customer,P.source,P.province"
                + ",P.city,P.district,P.industry,P.remark,P.higher_agent,P.joindate,P.partner_level"
                + ",P.jurisdiction_area,P.nature"
                + " from Partner P join BS_CATEGORY B on P.industry = B.id"
                + " where 1=1";
        if (StringUtils.isNotEmpty(partnerOrCustomer)) {
            sql += " and P.partner_or_customer = '" + partnerOrCustomer + "'";
        }
        if (StringUtils.isNotEmpty(partnerId)) {
            sql += " and P.higher_agent='" + partnerId + "'";
        }

		if (null != m) {
			if (StringUtils.isNotEmpty(m.get("name"))) {
				sql += " and P.name like '%" + m.get("name") + "%'";
			}
			if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry")
                            + "' or B.BASEID ='" + m.get("industry") + "')";
                }
			}
		}
        sql += " order by p.createdate desc";
        SQLQuery query = getSession().createSQLQuery(sql);
        return query.list();
    }


    @Override
    public Pager findPagerForPandCBySql(Map<String, String> m,
            String higherAgent, String partnerOrCustomer, Pager pager,String areaId) {
        /*
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,P.source,P.province"//1~7
                + ",P.city,P.district,P.industry,P.remark,P.higher_agent,P.joindate,P.partner_level" //8~14
                + ",P.jurisdiction_area,P.nature,P.createdate,P.longitude,P.latitude"                //15~19
                + " from Partner P join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " where P.is_deleted='0'";
        */
        // 2017-07-24 张羽，修改sql语句
        // 原sql第一个join没写left
    	if(m != null){
    		if(m.get("areaId") != null){
    			areaId = m.get("areaId");
    		}
    	}
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,bdd.name as sourcename"// 1~6
                + ",bp.name as provincename,bc.name as cityname,bd.name as districtname,bsc.name as industryname"// 7~10
                + ",P.remark,PP.name as highagentname,P.joindate,bdda.name as levelname,P.jurisdiction_area"// 11~15
                + ",P.nature,P.createdate,P.longitude,P.latitude,P.jurisdiction_cn,cts.name as contactsname" // 16~21
                + ",cts.phone,p.status,p.bhyj,p.djr,p.jfstatus"// 22~26
                + " from Partner P"
                + " left join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " left join B_AREA bp on P.province=bp.id"
                + " left join B_AREA bc on P.city=bc.id"
                + " left join B_AREA bd on P.district=bd.id"
                + " left join BS_CATEGORY bsc on P.industry=bsc.id"
                + " left join B_DATEDICTIONARY_D bdd on P.source=bdd.value AND bdd.code = 'companySource'"
                + " left join B_DATEDICTIONARY_D bdda on P.partner_level=bdda.value AND bdda.code='companyLevel'"
                + " left join CONTACTS cts on P.id=cts.partnerid AND cts.maincontact='1'"
                + " where P.is_deleted='0' ";
        if(StringUtils.isNotEmpty(areaId)){
        	sql += " AND (P.jurisdiction_area LIKE '%,"+areaId+"' or P.jurisdiction_area LIKE '"+areaId+",%' or P.jurisdiction_area LIKE '%,"+areaId+",%' OR P.jurisdiction_area = '"+areaId+"')";
        }
        if (StringUtils.isNotEmpty(partnerOrCustomer)) {
            sql += " and P.partner_or_customer = '" + partnerOrCustomer + "'";
        }
        if (StringUtils.isNotEmpty(higherAgent)) {
            sql += " and P.higher_agent='" + higherAgent + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and P.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("higherAgent"))) {
                sql += " and PP.name like '%" + m.get("higherAgent") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateStart"))) {
                sql += " and P.joindate >= '" + m.get("joinDateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateEnd"))) {
                sql += " and P.joindate <= '" + m.get("joinDateEnd") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry") + "' or B.BASEID ='" + m.get("industry") + "')";
                }
            }
        }
        String partnerId = "";
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){
            Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
            if(joint != null){
                List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                for(int i = 0 ;i<par.size();i++){
                    Partner partner = par.get(i);
                    partnerId += partner.getId()+"','";
                }
            }
            sql +=" and p.id in ('"+partnerId+"') ";
        }
        sql += " ORDER BY p.createdate desc";
        return findPagerBySql(pager, sql);
    }
    
    @Override
    public Pager findPagerForPandCBySql(Map<String, String> m,
            String higherAgent, String partnerOrCustomer, Pager pager,
            String area,String areaId) {
        /*
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,P.source,P.province"
                + ",P.city,P.district,P.industry,P.remark,P.higher_agent,P.joindate,P.partner_level"
                + ",P.jurisdiction_area,P.nature,P.createdate ,P.longitude,P.latitude"
                + " from Partner P join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " where P.is_deleted='0'";
        */
        // 2017-07-24 张羽
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,bdd.name as sourcename"// 1~6
                + ",bp.name as provincename,bc.name as cityname,bd.name as districtname,bsc.name as industryname"// 7~10
                + ",P.remark,PP.name as highagentname,P.joindate,bdda.name as levelname,P.jurisdiction_area"// 11~15
                + ",P.nature,P.createdate,P.longitude,P.latitude,P.jurisdiction_cn,cts.name as contactsname" // 16~21
                + ",cts.phone,p.status,p.bhyj,p.djr,p.jfstatus"// 22~26
                + " from Partner P"
                + " left join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " left join B_AREA bp on P.province=bp.id"
                + " left join B_AREA bc on P.city=bc.id"
                + " left join B_AREA bd on P.district=bd.id"
                + " left join BS_CATEGORY bsc on P.industry=bsc.id"
                + " left join B_DATEDICTIONARY_D bdd on P.source=bdd.value AND bdd.code = 'companySource'"
                + " left join B_DATEDICTIONARY_D bdda on P.partner_level=bdda.value AND bdda.code='companyLevel'"
                + " left join CONTACTS cts on P.id=cts.partnerid AND cts.maincontact='1'"
                + " where P.is_deleted='0' ";
        
        if(StringUtils.isNotEmpty(areaId)){
        	sql += " AND (P.jurisdiction_area LIKE '%,"+areaId+"' or P.jurisdiction_area LIKE '"+areaId+",%' or P.jurisdiction_area LIKE '%,"+areaId+",%' OR P.jurisdiction_area = '"+areaId+"')";
        }
        if(area!=null && !"".equals(area)){
         sql += " and P.jurisdiction_area like '%"+area+"%'";
        }
        if (StringUtils.isNotEmpty(partnerOrCustomer)) {
            sql += " and P.partner_or_customer = '" + partnerOrCustomer + "'";
        }
        if (StringUtils.isNotEmpty(higherAgent)) {
            sql += " and P.higher_agent='" + higherAgent + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and P.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("higherAgent"))) {
                sql += " and PP.name like '%" + m.get("higherAgent") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateStart"))) {
                sql += " and P.joindate > '" + m.get("joinDateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateEnd"))) {
                sql += " and P.joindate < '" + m.get("joinDateEnd") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry") + "' or B.BASEID ='" + m.get("industry") + "')";
                }
            }
        }
        String partnerId = "";
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){
            Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
            if(joint != null){
                List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                for(int i = 0 ;i<par.size();i++){
                    Partner partner = par.get(i);
                    partnerId += partner.getId()+"','";
                }
            }
            sql +=" and p.id in ('"+partnerId+"') ";
        }
        sql += " ORDER BY p.createdate desc";
        return findPagerBySql(pager, sql);
    }

    @Override
    public Pager findPagerEquipUserBySql(Map<String, String> m, Pager pager) {
        /*   
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,P.source,P.province"//1~7
                + ",P.city,P.district,P.industry,P.remark,P.higher_agent,P.joindate,P.partner_level" //8~14
                + ",P.jurisdiction_area,P.nature,P.createdate,P.longitude,P.latitude"                //15~19
                + " from Partner P join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " where P.is_deleted='0'";
        */
        // 2017-07-25 张羽
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,bdd.name as sourcename"// 1~6
                + ",bp.name as provincename,bc.name as cityname,bd.name as districtname,bsc.name as industryname"// 7~10
                + ",P.remark,PP.name as highagentname,P.joindate,bdda.name as levelname,P.jurisdiction_area"// 11~15
                + ",P.nature,P.createdate,P.longitude,P.latitude,P.jurisdiction_cn,cts.name as contactsname" // 16~21
                + ",cts.phone,p.status,p.bhyj,p.djr,p.jfstatus"// 22~26
                + " from Partner P"
                + " left join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " left join B_AREA bp on P.province=bp.id"
                + " left join B_AREA bc on P.city=bc.id"
                + " left join B_AREA bd on P.district=bd.id"
                + " left join BS_CATEGORY bsc on P.industry=bsc.id"
                + " left join B_DATEDICTIONARY_D bdd on P.source=bdd.value AND bdd.code = 'companySource'"
                + " left join B_DATEDICTIONARY_D bdda on P.partner_level=bdda.value AND bdda.code='companyLevel'"
                + " left join CONTACTS cts on P.id=cts.partnerid AND cts.maincontact='1'"
                + " where P.is_deleted='0' ";
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and P.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry") + "' or B.BASEID ='" + m.get("industry") + "')";
                }
            }
        }
        String partnerId = "";
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){
            Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
            if(joint != null){
                List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                for(int i = 0 ;i<par.size();i++){
                    Partner partner = par.get(i);
                    partnerId += partner.getId()+"','";
                }
            }
            sql +=" and p.id in ('"+partnerId+"') ";
        }
        sql += " ORDER BY p.createdate desc";
        return findPagerBySql(pager, sql);
    }

    @Override
    public Pager findPagerPartnerEquipList(Map<String, String> m, Pager pager,String currentOwnerId) {
        String sql = "select distinct(e.id),e.equipment_code,e.equipment_type,e.name,e.model" // 1~5
                + ",e.manufacturedate,e.productiondate,e.handlersid,e.owner"               // 6~9
                + ",e.equipment_user,e.working_medium,e.position,e.weight,e.temperature"   // 10~14
                + ",e.handlingload,e.carbon_type,e.carbon_spec,e.carbon_manu"              // 15~18
                + ",e.carbon_capacity,e.carbonload_date,t.contractenddate"                 // 19~21
                + " from EQUIPMENT e"
                + " left join TRANSFER t on e.id=t.equipmentid"
                + " where t.is_deleted is NULL "
                + " and (e.owner='"+currentOwnerId+"' or e.equipment_user='"+currentOwnerId+"'"
                + " or e.handlersid='" + currentOwnerId + "')";
        // 1、如果当前选择的node不是华世洁，则要从transfer记录中做限定。
        // 2、如果当前选择的node是华世洁，则应该把所有的设备都选出来。
        if (!"1".equals(currentOwnerId)) {
            sql += " and t.ownerid ='" + currentOwnerId + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("equipmentCode"))) {
                sql += " and e.equipment_code like '%"+m.get("equipmentCode")+"%'";
            }
            if (StringUtils.isNotEmpty(m.get("productionDate^s"))) {
                sql += " and e.productiondate > '"+m.get("productionDate^s")+"'";
            }
            if (StringUtils.isNotEmpty(m.get("productionDate^e"))) {
                sql += " and e.productiondate < '"+m.get("productionDate^e")+"'";
            }
        }
        sql += " order by e.equipment_type asc, e.productiondate desc";
        pager = findPagerBySql(pager, sql);
        return pager;
    }


    @Override
    public List<String> findPartnerIdsByHql(String hql) {
        return this.getHibernateTemplate().find(hql);
    }

    public Pager findPagerAllWithoutCurrentPartner(Map<String, String> m,String higherAgent, String partnerOrCustomer, Pager pager,String currentPartner) {
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,bdd.name as sourcename"// 1~6
                + ",bp.name as provincename,bc.name as cityname,bd.name as districtname,bsc.name as industryname"// 7~10
                + ",P.remark,PP.name as highagentname,P.joindate,bdda.name as levelname,P.jurisdiction_area"// 11~15
                + ",P.nature,P.createdate,P.longitude,P.latitude,P.jurisdiction_cn,cts.name as contactsname" // 16~21
                + ",cts.phone,p.status,p.bhyj,p.djr,p.jfstatus"// 22~26
                + " from Partner P"
                + " left join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " left join B_AREA bp on P.province=bp.id"
                + " left join B_AREA bc on P.city=bc.id"
                + " left join B_AREA bd on P.district=bd.id"
                + " left join BS_CATEGORY bsc on P.industry=bsc.id"
                + " left join B_DATEDICTIONARY_D bdd on P.source=bdd.value AND bdd.code = 'companySource'"
                + " left join B_DATEDICTIONARY_D bdda on P.partner_level=bdda.value AND bdda.code='companyLevel'"
                + " left join CONTACTS cts on P.id=cts.partnerid AND cts.maincontact='1'"
                + " where P.is_deleted='0' and P.id <> '"+currentPartner+"'";
        if (StringUtils.isNotEmpty(partnerOrCustomer)) {
            sql += " and P.partner_or_customer = '" + partnerOrCustomer + "'";
        }
        if (StringUtils.isNotEmpty(higherAgent)) {
            sql += " and P.higher_agent='" + higherAgent + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and P.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("higherAgent"))) {
                sql += " and PP.name like '%" + m.get("higherAgent") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateStart"))) {
                sql += " and P.joindate > '" + m.get("joinDateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateEnd"))) {
                sql += " and P.joindate < '" + m.get("joinDateEnd") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry") + "' or B.BASEID ='" + m.get("industry") + "')";
                }
            }
        }
        sql += " ORDER BY p.createdate desc";
        return findPagerBySql(pager, sql);
    }

    public Pager findPagerAllWithoutCurrentPartner(Map<String, String> m,
            String higherAgent, String partnerOrCustomer, Pager pager,
            String area, String currentPartner) {
        String sql = " select P.id,P.name,P.address,P.code,P.partner_or_customer,bdd.name as sourcename"// 1~6
                + ",bp.name as provincename,bc.name as cityname,bd.name as districtname,bsc.name as industryname"// 7~10
                + ",P.remark,PP.name as highagentname,P.joindate,bdda.name as levelname,P.jurisdiction_area"// 11~15
                + ",P.nature,P.createdate,P.longitude,P.latitude,P.jurisdiction_cn,cts.name as contactsname" // 16~21
                + ",cts.phone,p.status,p.bhyj,p.djr,p.jfstatus"// 22~26
                + " from Partner P"
                + " left join BS_CATEGORY B on P.industry = B.id"
                + " left join Partner PP on P.higher_agent = PP.id"
                + " left join B_AREA bp on P.province=bp.id"
                + " left join B_AREA bc on P.city=bc.id"
                + " left join B_AREA bd on P.district=bd.id"
                + " left join BS_CATEGORY bsc on P.industry=bsc.id"
                + " left join B_DATEDICTIONARY_D bdd on P.source=bdd.value AND bdd.code = 'companySource'"
                + " left join B_DATEDICTIONARY_D bdda on P.partner_level=bdda.value AND bdda.code='companyLevel'"
                + " left join CONTACTS cts on P.id=cts.partnerid AND cts.maincontact='1'"
                + " where P.is_deleted='0' and P.id <> '"+currentPartner+"'";

        if (area != null && !"".equals(area)) {
            sql += " and P.jurisdiction_area like '%" + area + "%'";
        }
        if (StringUtils.isNotEmpty(partnerOrCustomer)) {
            sql += " and P.partner_or_customer = '" + partnerOrCustomer + "'";
        }
        if (StringUtils.isNotEmpty(higherAgent)) {
            sql += " and P.higher_agent='" + higherAgent + "'";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and P.name like '%" + m.get("name") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("higherAgent"))) {
                sql += " and PP.name like '%" + m.get("higherAgent") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateStart"))) {
                sql += " and P.joindate > '" + m.get("joinDateStart") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("joinDateEnd"))) {
                sql += " and P.joindate < '" + m.get("joinDateEnd") + "'";
            }
            if (StringUtils.isNotEmpty(m.get("industry"))) {
                if (m.get("industry").equals("1")) {// id为1，全行业。
                } else {
                    sql += " and (B.ID ='" + m.get("industry")
                            + "' or B.BASEID ='" + m.get("industry") + "')";
                }
            }
        }
        sql += " ORDER BY p.createdate desc";
        return findPagerBySql(pager, sql);
    }


	@Override
	public Area findBySHQ(String province) {
		 String sql = "SELECT * FROM B_AREA where ID =?";
		
		 SQLQuery query = getSession().createSQLQuery(sql);
		 query.setParameter(0, province);
	     query.addEntity(Area.class);
	     Area area = (Area) query.uniqueResult();
	     
	     return area;
	    
	}

}
