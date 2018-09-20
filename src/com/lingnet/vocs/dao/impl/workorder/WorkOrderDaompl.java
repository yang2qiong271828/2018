package com.lingnet.vocs.dao.impl.workorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.workorder.WorkOrderDao;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.entity.WorkOrderAreaResp;

/**
 * @ClassName: WorkOrderDaompl
 * @Description: TODO
 * @author wanl
 * @date 2017年7月5日 下午4:47:48
 * 
 */

@Repository("workOrderDao")
public class WorkOrderDaompl extends BaseDaoImplInit<WorkOrder, String>
        implements WorkOrderDao {

    @Override
    public String saveEvaluation(String id, HashMap<String, Object> map) {

        String actualMaintenanceDate = (String) map
                .get("actualMaintenanceDate");
        String score = (String) map.get("score");
        String opinion = (String) map.get("opinion");

        String sql = "update workorder set actual_maintenanceDate = '"
                + actualMaintenanceDate + "', score = '" + score
                + "', opinion ='" + opinion + "' ,state = '5' where id = '" + id + "'";

        getJdbcTemplate().update(sql);
        return "success";
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getMyOrderDataIndex(String userName,ToolUtil toolUtil,String partnerId) {
		StringBuilder sql =new StringBuilder();
		HashMap map = new HashMap();
		  sql.append("SELECT DISTINCT *  FROM   ");
		  sql.append("  (SELECT r.PRESOURCE ,r.Resourcename ,r.Sortorder ,r.Description ,r.Moduleid ,r.Id ,r.State ,r.CreateDate,r.Resourceurl  ");
		  sql.append("   FROM Qx_Users u ");
		  sql.append("  JOIN qx_user_role ur ON u.ID = ur.USER_ID   ");
		  sql.append("   JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID  ");
		  sql.append("   JOIN qx_resource r ON r.ID = rr.RESOURCE_ID ");
		  sql.append("   WHERE  u.USERNAME = '"+userName+"' and RESOURCEURL = '/workorder/work_order!myorder.action' ) AS bm ");
		  sql.append("   WHERE bm.STATE = '1'  ORDER BY bm.sortorder           ");    
		  List<Object[]>  list = this.getSession().createSQLQuery(sql.toString()).list();
		  String[] arr = { "0", "1" };
		  List<WorkOrderAreaResp> woarList = null;
		  Criteria criteria = getSession().createCriteria(WorkOrderAreaResp.class);
		  if(list.size()>0){
			  woarList =  criteria.add(Restrictions.eq("areaResponsible", toolUtil.getUserId())).add(Restrictions.in("state", arr)).list();
			  if (woarList != null && woarList.size() > 0) {
				  StringBuilder sql3 =new StringBuilder();
				  for (int i = 0; i < woarList.size(); i++) {
						String[] stateArr = { "10", "7", "3" };
						sql3.append("select id,work_order_title from WorkOrder where id = '"+woarList.get(i).getWorkOrder()+"' and state in ('10','7','3')  ");
						map.put("total", this.getSession().createSQLQuery(sql3.toString()).list().size());
				  }
			  }else{
				  map.put("total", "0");
			  }
			  for(Object[] obj:list){
				  map.put("url", obj[8]);
				}
			  return JsonUtil.Encode(map);
		  }else{
			  StringBuilder sql1 =new StringBuilder();
			  sql1.append("SELECT DISTINCT *  FROM   ");
			  sql1.append("  (SELECT r.PRESOURCE ,r.Resourcename ,r.Sortorder ,r.Description ,r.Moduleid ,r.Id ,r.State ,r.CreateDate,r.Resourceurl  ");
			  sql1.append("   FROM Qx_Users u ");
			  sql1.append("  JOIN qx_user_role ur ON u.ID = ur.USER_ID   ");
			  sql1.append("   JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID  ");
			  sql1.append("   JOIN qx_resource r ON r.ID = rr.RESOURCE_ID ");
			  sql1.append("   WHERE  u.USERNAME = '"+userName+"' and RESOURCEURL = '/workorder/work_order!list.action' ) AS bm ");
			  sql1.append("   WHERE bm.STATE = '1'  ORDER BY bm.sortorder           ");   
			  List<Object[]> listlr = this.getSession().createSQLQuery(sql1.toString()).list();
			  if(listlr.size()>0){//是否有工单录入展现功能
				  StringBuilder sql11 =new StringBuilder();
				  sql11.append("SELECT DISTINCT *  FROM   ");
				  sql11.append("  (SELECT r.PRESOURCE ,r.Resourcename ,r.Sortorder ,r.Description ,r.Moduleid ,r.Id ,r.State ,r.CreateDate,r.Resourceurl  ");
				  sql11.append("   FROM Qx_Users u ");
				  sql11.append("  JOIN qx_user_role ur ON u.ID = ur.USER_ID   ");
				  sql11.append("   JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID  ");
				  sql11.append("   JOIN qx_resource r ON r.ID = rr.RESOURCE_ID ");
				  sql11.append("   WHERE  u.USERNAME = '"+userName+"' and RESOURCEURL = '/workorder/work_order!hzs.action' ) AS bm ");
				  sql11.append("   WHERE bm.STATE = '1'  ORDER BY bm.sortorder           ");  
				  List<Object[]> objzp  = this.getSession().createSQLQuery(sql11.toString()).list();
				 if(objzp.size()>0){//是否有指派合作商
					 //查表
					 Criteria criteriaw = this.getSession().createCriteria(WorkOrder.class);
					 String[] aabb= { "1" , "9" } ;
					 map.put("total", criteriaw.add(Restrictions.in("state", aabb)).list().size());
					 for(Object[] obj:listlr){
						  map.put("url", obj[8]);
					}
					return JsonUtil.Encode(map);
				 }else{
					 StringBuilder sql22 =new StringBuilder();
					 sql22.append("SELECT DISTINCT *  FROM   ");
					 sql22.append("  (SELECT r.PRESOURCE ,r.Resourcename ,r.Sortorder ,r.Description ,r.Moduleid ,r.Id ,r.State ,r.CreateDate,r.Resourceurl  ");
					 sql22.append("   FROM Qx_Users u ");
					 sql22.append("  JOIN qx_user_role ur ON u.ID = ur.USER_ID   ");
					 sql22.append("   JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID  ");
					 sql22.append("   JOIN qx_resource r ON r.ID = rr.RESOURCE_ID ");
					 sql22.append("   WHERE  u.USERNAME = '"+userName+"' and RESOURCEURL = '/workorder/work_order!mygroup.action' ) AS bm ");
					 sql22.append("   WHERE bm.STATE = '1'  ORDER BY bm.sortorder           ");  
					  List<Object[]> objzp22  = this.getSession().createSQLQuery(sql22.toString()).list();
					  if(objzp22.size()>0){
						  Criteria criteriad = this.getSession().createCriteria(WorkOrder.class);
						  String[] bb = {"2","9"};
							 map.put("total", criteriad.add(Restrictions.in("state", bb)).add(Restrictions.eq("partner", partnerId)).list().size());
							 for(Object[] obj:objzp22){
								  map.put("url", obj[8]);
							}
							return JsonUtil.Encode(map);
					  }
				 }
				  
			  }
		  }
		  map.put("total", "0");
		return  JsonUtil.Encode(map);
	}

	@Override
	public List getKhNames(Pager pager,String startDate,String endDate) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT MAX (p.name) nameP,COUNT (w.customer) zong FROM  ");
		sql.append("  (select * from WORKORDER where 1=1 ");
		if(StringUtils.isNotEmpty(startDate)){
			sql.append(" and  createDate >= convert(datetime, '"+startDate+"')  ");
		}
		if(StringUtils.isNotEmpty(endDate)){
			sql.append(" and createDate <= convert(datetime, '"+endDate+"')  ");
		}
		sql.append("  ) w  ");
		sql.append(" LEFT JOIN PARTNER p ON w.customer = p.id        ");
		sql.append(" where p.name is not NULL and p.name <> '' AND   ");
		sql.append(" w.work_order_level <> '0'                       ");
		sql.append(" GROUP BY                                        ");
		sql.append(" 	w.customer                                   ");
		sql.append(" ORDER BY                                        ");
		sql.append(" 	COUNT (w.customer) DESC                     ");
		return findPagerBySql(pager, sql.toString()).getResult();
	}

	@Override
	public WorkOrder fingById(String id) {
		String sql = "SELECT  w.id,w.work_order_title,w.work_order_code,w.work_order_type,w.fault_type,w.expect_date,w.work_order_level,w.equipment_code,w.resource,w.createperson, p.name as customer,w.customer_phone,w.customer_cell_phone,w.partner,w.partner_cell_phone,w.replay_phone,w.replay_cell_phone,w.sales,w.sales_phone,w.specific_req,w.fault_explain,w.upload_file,w.work_order_follow,w.createDate,w.modifyDate,w.state,w.customer_address,w.check_date,cause,w.rec_item_charges,w.rea_item_charges,w.rec_mainter_charges,w.rea_mainter_charges,w.receive,w.transfer_orders_id,w.actual_maintenanceDate,w.score,w.opinion,w.contacts,w.confirm,w.confirm_date,w.province,w.city,w.district,w.partner_phone,w.replay_person   FROM WORKORDER  w left join PARTNER p  on  w.customer=p.id   where w.id=? ";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.addEntity(WorkOrder.class);
		WorkOrder workOrder = (WorkOrder) query.uniqueResult();
		return workOrder;
	}
}
