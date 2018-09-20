package com.lingnet.vocs.dao.impl.workorder;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.workorder.WorkOrderLogDao;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.entity.WorkOrderLog;

/**
 * 工单
 * @ClassName: WorkOrderDao 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月28日 下午2:25:06 
 *
 */
@Repository("workOrderLogDao")
public class WorkOrderLogDaoImpl extends BaseDaoImplInit<WorkOrderLog, String> 
implements WorkOrderLogDao
{

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findFollowMap(String workOrderCode) {
        String sql = "select w.czdate, w.czdj, w.cztype, a.name, a.path from wo_log w join attachments a on w.id=a.entity_id where a.deleted=0 and w.czdj='"+workOrderCode+"' order by w.czdate";
        SQLQuery query=this.getSession().createSQLQuery(sql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> findCzdjs(String sql) {
        SQLQuery query=this.getSession().createSQLQuery(sql);
        return query.list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkOrder> findCz() {
    	
    	StringBuilder sqls= new StringBuilder();
    	String sql="SELECT  w.id,w.work_order_title,w.work_order_code,w.work_order_type,w.fault_type,w.expect_date,w.work_order_level,w.equipment_code,w.resource,w.createperson, p.name as customer,w.customer_phone,w.customer_cell_phone,w.partner,w.partner_cell_phone,w.replay_phone,w.replay_cell_phone,w.sales,w.sales_phone,w.specific_req,w.fault_explain,w.upload_file,w.work_order_follow,w.createDate,w.modifyDate,w.state,w.customer_address,w.check_date,cause,w.rec_item_charges,w.rea_item_charges,w.rec_mainter_charges,w.rea_mainter_charges," + 
    			"w.receive,w.transfer_orders_id,w.actual_maintenanceDate,w.score,w.opinion,w.contacts,w.confirm,w.confirm_date,w.province,w.city,w.district,w.partner_phone,w.replay_person   FROM WORKORDER  w left join PARTNER p  on  w.customer=p.id   where w.score>'3'   order by w.modifyDate desc ";
        SQLQuery query=this.getSession().createSQLQuery(sql);
        query.addEntity(WorkOrder.class);
        List<WorkOrder> list = query.list();
        return list;
    }

	@Override
	public List<WorkOrder> findNotp(Pager pager) {
		StringBuilder sqls= new StringBuilder();
    	String sql="SELECT  w.id,w.work_order_title,w.work_order_code,w.work_order_type,w.fault_type,w.expect_date,w.work_order_level,w.equipment_code,w.resource,w.createperson, p.name as customer,w.customer_phone,w.customer_cell_phone,w.partner,w.partner_cell_phone,w.replay_phone,w.replay_cell_phone,w.sales,w.sales_phone,w.specific_req,w.fault_explain,w.upload_file,w.work_order_follow,w.createDate,w.modifyDate,w.state,w.customer_address,w.check_date,cause,w.rec_item_charges,w.rea_item_charges,w.rec_mainter_charges,w.rea_mainter_charges," + 
    			"w.receive,w.transfer_orders_id,w.actual_maintenanceDate,w.score,w.opinion,w.contacts,w.confirm,w.confirm_date,w.province,w.city,w.district,w.partner_phone,w.replay_person   FROM WORKORDER  w left join PARTNER p  on  w.customer=p.id   where w.score<'4'   order by w.modifyDate desc ";
        SQLQuery query=this.getSession().createSQLQuery(sql);
        query.addEntity(WorkOrder.class);
        List<WorkOrder> list = query.list();
        return list;
	}


}
