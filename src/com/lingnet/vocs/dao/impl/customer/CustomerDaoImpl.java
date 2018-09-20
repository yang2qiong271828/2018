package com.lingnet.vocs.dao.impl.customer;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.customer.CustomerDao;
import com.lingnet.vocs.entity.Customer;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.partner.PartnerService;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImplInit<Customer, String>
									implements CustomerDao{

	@Resource(name = "adminService")
	private AdminService adminService;
	@Resource(name = "adminDao")
    private AdminDao adminDao;
	@Resource(name = "partnerService")
    private PartnerService partnerService;
	@Override
	public Pager getListData(Pager pager,String name, String key,String partnerName,String areaId) {
		String sql = "";
		sql = "select a.id,a.name as name1,a.code,a.higher_agent,a.source,b.name as name2,a.address,bp.NAME provincename,bc.NAME cityname,	bd.NAME disname,con.name,con.phone"
		        + " from PARTNER a LEFT JOIN bs_category b ON a.industry = b.id LEFT JOIN B_AREA bp ON a.province = bp.id LEFT JOIN B_AREA bc ON a.city = bc.id LEFT JOIN B_AREA bd ON a.district = bd.id "
		        + " left join contacts con on a.id=con.partnerid   "
		        + "where partner_or_customer = 'C' and a.industry = b.id and a.IS_DELETED = '0' and con.maincontact='1'   ";
		if(StringUtils.isNotEmpty(areaId) && !areaId.equals("0")){
			sql += " and  (a.province='"+areaId+"' or  a.city ='"+areaId+"' or a.district='"+areaId+"' )";
		}
		if(!"".equals(name) && name != null){
			sql += " AND a.name like '%"+name+"%'";
		}
		if(!"".equals(key) && key != null){
			sql += " AND b.name like '%"+key+"%'";
		}
		if(partnerName !=null && !"".equals(partnerName)){
		    sql += " and a.higher_agent in ( select p.id from PARTNER p where p.name like '%"+partnerName+"%'  )";
		}
		sql += " ORDER BY a.createdate desc";
		 return findPagerBySql(pager, sql);
	}
	
	
	@Override
	public Pager getMyListData(Pager pager,String name, String key,String areaId) {
		QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
		String userId = user.getPartnerId();
		String sql = "";
		sql = "select a.id,a.name as name1,a.code,a.higher_agent,a.source,b.name as name2,a.address,con.name name3,con.phone "
				+ "from PARTNER a LEFT JOIN bs_category b ON a.industry = b.id LEFT JOIN B_AREA bp ON a.province = bp.id LEFT JOIN B_AREA bc ON a.city = bc.id LEFT JOIN B_AREA bd ON a.district = bd.id "
				+ " left join contacts con on a.id=con.partnerid   "
	        + "where partner_or_customer = 'C' and a.industry = b.id and a.IS_DELETED = '0'  ";
		if(StringUtils.isNotEmpty(areaId) && !areaId.equals("0")){
			sql += " and  (a.province='"+areaId+"' or  a.city ='"+areaId+"' or a.district='"+areaId+"' )";
		}
		if(!"".equals(name) && name != null){
			sql += " AND a.name like '%"+name+"%'";
		}
		if(!"".equals(key) && key != null){
			sql += " AND b.name like '%"+key+"%'";
		}
		sql += " AND a.higher_agent = '"+userId+"'";
		sql += " ORDER BY a.createdate desc";
		 return findPagerBySql(pager, sql);
	}
	
	
	@Override
	public List<Object[]> getSbList(String str,String end,String id) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String newd = sdf.format(new Date());//当前时间
		
		String sql = "select count(Datename(month,usageStart)) as a,Datename(month,usageStart) as b,Datename(year,usageStart) as c"
				+ "  from 	EQUIPMENT_USAGELOG eu  where 1 = 1  and equipment_type = '1'";
		if(id != null && !"".equals(id)){
			sql += " and eu.equipment_code in ";
			sql += " (select equipment_code from EQUIPMENT where (owner = '"+id+"' or equipment_user = '"+id+"'))";
		}
				
		if(StringUtils.isEmpty(str) && StringUtils.isEmpty(end)){
			sql += " and  Datename(YEAR, usageStart) = '"+newd+"'";
		}
		if(!StringUtils.isEmpty(str)){
			sql += " and eu.usageStart > '"+str+"'";
		}
		if(!StringUtils.isEmpty(end)){
			sql += " and eu.usageStart < '"+end+"'";
		}
		sql += "group by Datename(month,usageStart),Datename(year,usageStart)";
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		return list;
	}
	
	
	@Override
	public List<Object[]> getSbPcxxList(String year,String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sd = new SimpleDateFormat("MM"); 
		String newd = sdf.format(new Date());//当前时间
		String sql ="SELECT * FROM EQUIPMENT_USAGELOG WHERE Datename(YEAR, usageStart) = '"+year+"' AND Datename(MONTH, usageStart) = '"+month+"'";
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object getContract(String key) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select code  from CONTRACT con where company_id='"+key+"' order by sign_date desc     ");
		List<Object[]> list = this.getSession().createSQLQuery(sql.toString()).list();
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}


    @Override
    public Pager getQzKhData(Pager pager, String searchData,String area) {
        Map<String, String> map= JsonUtil.parseProperties(searchData);
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String userId = user.getPartnerId();
        StringBuilder sql = new StringBuilder();
        sql.append("  select * from (SELECT    s.id,s.code, s.pwname,s.dlwz,s.pwtype,s.produce,s.pfnd,s.fl,s.pfwz,s.need,s.starttime,s.ystime,s.governance,s.hbzlqy,s.hbzlgy,s.hbzljh,  ");
        sql.append("    b.NAME name1,                                   ");
        sql.append("    r.NAME name2,                                   ");
        sql.append("    e.NAME name3,                                   ");
        sql.append("    c.name, c.email,c.gender,c.maincontact,c.phone,c.title,c.department,s.ydistrict,s.latitude,s.longitude,s.createdate  ");
        sql.append("  FROM                                        ");
        sql.append("    sewage s                                  ");
        sql.append("  LEFT JOIN CONTACTS c ON s.id = c.partnerid and maincontact='1'  ");
        sql.append("  LEFT JOIN B_AREA b ON s.province = b.ID     ");
        sql.append("  LEFT JOIN B_AREA r ON s.city = r.ID         ");
        sql.append("  LEFT JOIN B_AREA e ON s.cdistrict = e.ID  where 1 = 1  and  (s.status is null or s.status = '1') ");
        if (!"admin".equals(user.getUsername())) {
            sql.append("  and s.userId is null ");
        }
        if(StringUtils.isNotEmpty(area)){
            sql.append("  and (s.province in ('"+area+"') or s.city in ('"+area+"') or s.cdistrict in ('"+area+"')  )  ");
        }
        if(map != null && !map.isEmpty()){
            if(StringUtils.isNotEmpty(map.get("pwname")) && !map.get("pwname").equals("null")){
                sql.append("  and  s.pwname like '%"+map.get("pwname")+"%'  ");
            }
            if(StringUtils.isNotEmpty(map.get("governance")) && !map.get("governance").equals("null")){
                sql.append("  and  s.governance = '"+map.get("governance")+"'  ");
            }
        }
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if(!"admin".equals(user.getUsername()) && !"4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){
            sql.append(" UNION ");
            sql.append("  SELECT    s.id,s.code, s.pwname,s.dlwz,s.pwtype,s.produce,s.pfnd,s.fl,s.pfwz,s.need,s.starttime,s.ystime,s.governance,s.hbzlqy,s.hbzlgy,s.hbzljh,  ");
            sql.append("    b.NAME name1,                                   ");
            sql.append("    r.NAME name2,                                   ");
            sql.append("    e.NAME name3,                                   ");
            sql.append("    c.name, c.email,c.gender,c.maincontact,c.phone,c.title,c.department,s.ydistrict,s.latitude,s.longitude,s.createdate  ");
            sql.append("  FROM                                        ");
            sql.append("    sewage s                                  ");
            sql.append("  LEFT JOIN CONTACTS c ON s.id = c.partnerid and maincontact='1'  ");
            sql.append("  LEFT JOIN B_AREA b ON s.province = b.ID     ");
            sql.append("  LEFT JOIN B_AREA r ON s.city = r.ID         ");
            sql.append("  LEFT JOIN B_AREA e ON s.cdistrict = e.ID  where s.userId = '"+userId+"' and  (s.status is null or s.status = '1')");
        }
        sql.append("   ) s order by s.createdate desc       ");
        return findPagerBySql(pager, sql.toString());
    }


    @Override
    public Pager getYxkhList(Pager pager, String searchData,String status) {
        Map<String, String> map= JsonUtil.parseProperties(searchData);
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String userId = user.getPartnerId();
        
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT    s.id,s.code, s.pwname,s.dlwz,s.pwtype,s.produce,s.pfnd,s.fl,s.pfwz,s.need,s.starttime,s.ystime,s.governance,s.hbzlqy,s.hbzlgy,s.hbzljh,  ");
        sql.append("    b.NAME name1,                                   ");
        sql.append("    r.NAME name2,                                   ");
        sql.append("    e.NAME name3,                                   ");
        sql.append("    c.name, c.email,c.gender,c.maincontact,c.phone,c.title,c.department,s.ydistrict,s.latitude,s.longitude  ");
        sql.append("  FROM                                        ");
        sql.append("    sewage s                                  ");
        sql.append("  LEFT JOIN CONTACTS c ON s.id = c.partnerid and maincontact='1'  ");
        sql.append("  LEFT JOIN B_AREA b ON s.province = b.ID     ");
        sql.append("  LEFT JOIN B_AREA r ON s.city = r.ID         ");
        sql.append("  LEFT JOIN B_AREA e ON s.cdistrict = e.ID  ");
        sql.append("  LEFT JOIN project_preparation p on p.cus_com_name_id = s.id  where 1=1 ");
        if(StringUtils.isNotEmpty(status)){
            sql.append("  and p.status = '"+status+"'  ");
        }
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        String partnerId = "";
        if(!"admin".equals(user.getUsername()) && !"4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){//判断是或否管理员
            Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
            if(joint != null){
                List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                for(int i = 0 ;i<par.size();i++){
                    Partner partner = par.get(i);
                    partnerId += partner.getId()+"','";
                }
            }
            if(!"1".equals(user.getPartnerId())){//不是华世洁的用户
                sql.append(" and p.partnerId in ('"+userId+"')");
            }else{
                sql.append(" and p.partnerId in ('"+partnerId+"')");
            }
        }
        if(map != null && !map.isEmpty()){
            if(StringUtils.isNotEmpty(map.get("pwname")) && !map.get("pwname").equals("null")){
                sql.append("  and  s.pwname like '%"+map.get("pwname")+"%'  ");
            }
            if(StringUtils.isNotEmpty(map.get("governance")) && !map.get("governance").equals("null")){
                sql.append("  and  s.governance = '"+map.get("governance")+"'  ");
            }
        }
        sql.append("   order by s.createdate desc       ");
        return findPagerBySql(pager, sql.toString());
    }

}
