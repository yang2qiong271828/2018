/**
 * @ContractDaoImpl.java
 * @com.lingnet.vocs.dao.impl.contract
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月12日
 */

package com.lingnet.vocs.dao.impl.finance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.finance.ContractDao;
import com.lingnet.vocs.entity.Contract;

/**
 * @ClassName: ContractDaoImpl
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月12日 下午4:37:22
 * 
 */
@Repository("contractDao")
public class ContractDaoImpl extends BaseDaoImplInit<Contract, String>
implements ContractDao {

    /**
     * 根据设备和公司查看合同
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findCotByParAndEqu(String partnerId,Map<String, String> m) {
        String sql = "select e.id as 设备id,con.contract_type as 合同类型,con.end_date as 结束时间,e.name as 设备名称"
                + " ,e.equipment_code as 设备编号,e.equipment_type as 设备类别,e.owner as 拥有者"
                + " ,e.equipment_user as 使用者 ,e.model as  设备型号 ,e.handlersId as 操作者"
                + " ,con.sign_date as 签订日期"
                + " from CONTRACT con "
                + " left join TRANSFER tr on tr.contractid = con.id "
                + " left join EQUIPMENT e on e.id = tr.equipmentId"
                + " where (e.owner = '"+partnerId+"' or e.equipment_user = '"+partnerId+"' or e.handlersId ='"+partnerId+"')  and con.verify_status = '1'";
        if(m!=null){
            if (m.get("equipmentCode")!=null) {
                String equipmentCode = m.get("equipmentCode");
                sql += " and e.equipment_code like '%"+equipmentCode+"%'";
            }
            if (m.get("beginDate")!=null) {
                sql += " and  con.endDate  >= '"+m.get("beginDate")+"' ";
            }
            if (m.get("endDate")!=null) {
                sql += " and con.endDate <= '"+m.get("endDate")+ "' ";
            } 
        }
        SQLQuery query = this.getSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getContractsBySql(Map<String, String> m) {
        String sql = "select c.id,C.code,c.partner_or_customer,c.company_id,c.contract_type,c.sign_date,c.end_date,c.pic,c.pic_phone"
                + ",c.payment_type,c.account_receivable,c.discount,c.paidup_capital,c.salesman,c.salesman_contact,c.createdate,"
                + "c.modifydate,c.verify_status"
                + " from CONTRACT c join PARTNER p on c.company_id = p.id where 1=1";
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("code"))) {
                sql += " and c.code like '%" + m.get("code") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and p.name like '%" + m.get("name") + "%'";
            }
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public Pager findPagerContractsBySql(Map<String, String> m, Pager pager,
            boolean approveFlag, String curUserId, String partnerId,String id, Integer level,String dhbz) {
    	Calendar cal = Calendar.getInstance();//创建一个实例
    	cal.add(Calendar.DATE, 10);;//给当前日期加上指定天数，这里加的是10天
    	SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
    	;
        String sql = "select c.id,c.code,c.partner_or_customer,p.name as partnerName" // 1~4
                + ",bda.name as contractTypeName,c.sign_date,c.end_date,c.pic,c.pic_phone" // 5~9
                + ",bdb.name as paymentTypeName,c.account_receivable,c.discount,c.paidup_capital"// 10~13
                + ",c.salesman,c.salesman_contact,c.createdate,c.modifydate,c.verify_status"// 14~18
                + ",c.verify_date,ua.name as verifyPersonName,ub.name as submitPersonName"// 19~21
                + ",uc.name as createPersonName,c.reject_reason,c.allival_date,c.dispose"// 22~23
                + " from CONTRACT c"
                + " left join PARTNER p on c.company_id = p.id"
                + " left join B_DATEDICTIONARY_D bda ON c.contract_type=bda.value and bda.code='contractType'"
                + " left join B_DATEDICTIONARY_D bdb ON c.payment_type=bdb.value and bdb.code='paymentType'"
                + " left join qx_users ua on c.verify_person = ua.id"
                + " left join qx_users ub on c.submit_person = ub.id"
                + " left join qx_users uc on c.create_person = uc.id";
        // 拥有合同批准权限
        if (approveFlag) {
            // 搜索合同乙方是当前登录用户的partnerId 的合同
            sql += " where ( c.sponsor='" + partnerId + "' OR c.company_id ='" + partnerId + "') ";
        } else {// 没有合同批准权限
            // 搜索出 （提交人或创建人或已被批准的合同） 且 合同乙方是当前登录用户的partnerId 的合同
            sql += " where (c.submit_person = '" + curUserId + "'"
                    + " or c.create_person ='" + curUserId + "'"
                    + " or c.verify_status = '4' ) "
                    + "and ( c.sponsor='" + partnerId + "' OR c.company_id ='" + partnerId + "') ";
        }
        if(StringUtils.isNotBlank(dhbz )){
        	sql += " and   c.end_date  between '"+mat.format(new Date())+"' and '"+mat.format(cal.getTime())+"'  AND c.verify_status='4' ";
        }
        if (null != m) {
            if (StringUtils.isNotEmpty(m.get("code"))) {
                sql += " and c.code like '%" + m.get("code") + "%'";
            }
            if (StringUtils.isNotEmpty(m.get("name"))) {
                sql += " and p.name like '%" + m.get("name") + "%'";
            }
        }
        if(0 == level){//省
            sql += " and p.province = '"+id+"'";
        }else if(1 == level){//市
            sql += " and p.city = '"+id+"'";
        }else if(2 == level){//区
            sql += " and p.district = '"+id+"'";
        }else{
        }
        sql += " order by c.createdate desc,c.end_date desc";
        return findPagerBySql(pager, sql);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findContractByEquipment(String partnerId,Map<String, String> m,String eqId) {
        String sql = "select e.id as 设备id,con.contract_type as 合同类型,con.end_date as 结束时间,e.name as 设备名称"
                + " ,e.equipment_code as 设备编号,e.equipment_type as 设备类别,e.owner as 拥有者"
                + " ,e.equipment_user as 使用者 ,e.model as  设备型号 ,e.handlersId as 操作者"
                + " ,con.sign_date as 签订日期"
                + " from CONTRACT con "
                + " left join TRANSFER tr on tr.contractid = con.id "
                + " left join EQUIPMENT e on e.id = tr.equipmentId"
                + " where  e.id =  '"+eqId+"' and  ( con.company_id='"+ partnerId +"' or con.sponsor='"+ partnerId +"')";
               // + " and con.verify_status = '3' ";
        if(m!=null){
            if (m.get("equipmentCode")!=null) {
                String equipmentCode = m.get("equipmentCode");
                sql += " and e.equipment_code like '%"+equipmentCode+"%'";
            }
            if (m.get("beginDate")!=null) {
                sql += " and  con.end_date  >= '"+m.get("beginDate")+"' ";
            }
            if (m.get("endDate")!=null) {
                sql += " and con.end_date <= '"+m.get("endDate")+ "' ";
            } 
        }
        SQLQuery query = this.getSession().createSQLQuery(sql);
        List<Object[]> list = query.list();

        return list;
    }

    @Override
    public Pager findContractByEquipment(Pager pager,String partnerId, String eqId) {
        String sql = "select c.id,c.code,c.partner_or_customer,c.company_id,c.contract_type,c.sign_date"
                + ",c.end_date,c.pic,c.pic_phone,c.payment_type,c.account_receivable,c.discount"
                + ",c.paidup_capital,c.salesman,c.salesman_contact,c.createdate,c.modifydate"
                + ",c.verify_status,c.verify_person,c.verify_date,c.submit_person,c.create_person"
                + " from CONTRACT c "
                + " left join TRANSFER tr on tr.contractid = c.id "
                + " left join EQUIPMENT e on e.id = tr.equipmentId"
                + " where  e.id =  '"+eqId+"'"
               + " and ( c.company_id='"+ partnerId +"' or c.sponsor='"+ partnerId +"') "
                       + " order by c.createdate desc";
        pager = this.findPagerBySql(pager, sql);
        
        return pager;
    }
    
    public Pager findContractByEquipment2(Pager pager,String partnerId, String eqId) {
        String sql = "select c.id,c.code,c.partner_or_customer,c.company_id,c.contract_type,c.sign_date"
                + ",c.end_date,c.pic,c.pic_phone,c.payment_type,c.account_receivable,c.discount"
                + ",c.paidup_capital,c.salesman,c.salesman_contact,c.createdate,c.modifydate"
                + ",c.verify_status,c.verify_person,c.verify_date,c.submit_person,c.create_person"
                + " from CONTRACT c "
                + " left join TRANSFER tr on tr.contractid = c.id "
                + " left join EQUIPMENT e on e.id = tr.equipmentId"
                + " where  e.id =  '"+eqId+"' and tr.is_deleted is NULL"
                + " and (  c.sponsor='"+ partnerId +"' or c.company_id='"+ partnerId +"') "
                       + " order by c.createdate desc";
        pager = this.findPagerBySql(pager, sql);
        return pager;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean findContractApproveFlag(String userName) {
        String sql = "SELECT DISTINCT * from"
                + " (SELECT r.ID,r.RESOURCENAME,r.RESOURCEURL,r.DESCRIPTION,r.PRESOURCE,r.IMGVALUE"
                + ",r.MODULEID,r.STATE,r.sortorder" + " FROM Qx_Users u"
                + " JOIN qx_user_role ur ON u.ID = ur.USER_ID"
                + " JOIN qx_role_resource rr ON ur.ROLE_ID = rr.ROLE_ID"
                + " JOIN qx_resource r ON r.ID = rr.RESOURCE_ID"
                + " WHERE u.USERNAME = '"+userName+"')"
                + " as bm where bm.MODULEID is NULL and bm.STATE='1'"
                + " and bm.RESOURCENAME like '%合同批准%' order by bm.sortorder";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        List l = query.list();
        if (l.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
