package com.lingnet.vocs.dao.impl.customer;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.customer.CustomerComplaintsDao;
import com.lingnet.vocs.entity.CustomerComplaints;

/**
 * @ClassName: CustomerComplaintsDaoImpl
 * @Description: TODO
 * @author wanl
 * @date 2017年6月29日 下午6:23:01
 * 
 */
@Repository("customerComplaintsDao")
public class CustomerComplaintsDaoImpl extends
        BaseDaoImplInit<CustomerComplaints, String> implements
        CustomerComplaintsDao {

    @Resource(name = "adminService")
    private AdminService adminService;

    // 所有客户投诉列表
    @Override
    public Pager getListData(Pager pager, String partnerId,String khtszt) {
        String sql = "";
        if ("1".equals(partnerId)) {
            sql = "select a.id,a.complaintsName,b.address,a.complaintsPhone,a.complaintsDate,"
                    + "a.complaintContent,a.stateOfComplaint,a.processResult,a.enterThePerson,c.work_order_code "
                    + "from (customer_complaints a left join partner b on a.complaintsName = b.id)"
                    + "left join WORKORDER c on a.id = c.transfer_orders_id where 1 = 1 ";
        } else {
            sql = "select a.id,a.complaintsName,b.address,a.complaintsPhone,a.complaintsDate,"
                    + "a.complaintContent,a.stateOfComplaint,a.processResult,a.enterThePerson,c.work_order_code "
                    + "from (customer_complaints a left join partner b on a.complaintsName = b.id)"
                    + "left join WORKORDER c on a.id = c.transfer_orders_id where a.complaintsName = '"
                    + partnerId + "'";
        }
        if(StringUtils.isNotEmpty(khtszt)){
        	sql += "  and a.stateOfComplaint='0'    ";
        }
        sql += " order by a.complaintsDate desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }

    // // 点击查询所有客户列表
    // @SuppressWarnings({ "unchecked" })
    // @Override
    // public List<Object[]> getCustomerListData(String name) {
    //
    // String sql = "select name,address from partner where 1 = 1";
    //
    // if (!"".equals(name) && name != null) {
    // sql += " and name like '%" + name + "%'";
    // }
    //
    // SQLQuery query = this.getSession().createSQLQuery(sql.toString());
    // List<Object[]> list = query.list();
    // return list;
    // }

    // 点击查询所有客户列表
    @Override
    public Pager
            getCustomerListData(Pager pager, String name, String clientType) {

        String sql = "select name,address,id,code,industry,nature,higher_agent from partner where 1 = 1";

        if (!"".equals(name) && name != null) {
            sql += " and name like '%" + name + "%'";
        }
        if (!"".equals(clientType) && clientType != null) {
            sql += " and partner_or_customer = '" + clientType + "'";
        }
        sql += " order by name desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }

    // 根据选中的客户名称查询客户详细信息
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String getClientData(HashMap<String, Object> map) {
        Map result = new HashMap();

        String complaintsName = (String) map.get("complaintsName");

        String sql = "select a.address,a.code,b.name,b.phone from partner a left join CONTACTS b ON "
                + "a.id = b.partnerid WHERE a.id ='" + complaintsName + "'";

        Map<String, Object> map2 = getJdbcTemplate().queryForMap(sql);

        result.put("data", map2);
        result.put("totalCount", map2.size());

        return JsonUtil.Encode(result);
    }

    // 根据选择的客户名查询该客户所有的投诉信息
    @Override
    public Pager getPersonalComplaintsListData(HashMap<String, Object> map,
            Pager pager) {

        String complaintsName = (String) map.get("complaintsName");

        String sql = "select a.complaintsName,a.complaintsDate,a.complaintContent,a.stateOfComplaint,a.processResult,b.address,a.complaintsPhone "
                + "from customer_complaints a left join partner b on a.complaintsName = b.id where a.complaintsName = '"
                + complaintsName + "'";
        sql += " order by a.complaintsDate desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }

    // 客户投诉结单
    @Override
    public String changeStatus(String id) {
        String sql = "update customer_complaints set stateOfComplaint = '1' where id = '"
                + id + "' ";
        getJdbcTemplate().update(sql);
        return "success";
    }

}
