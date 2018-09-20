package com.lingnet.vocs.dao.impl.reported;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.dao.reported.DemandDao;
import com.lingnet.vocs.entity.Demand;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.service.partner.PartnerService;

@Repository("demandDao")
public class DemandDaoImpl extends BaseDaoImpl<Demand,String> implements
        DemandDao {
   
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    @Resource(name = "partnerService")
    private PartnerService partnerService;
    @Override
    public Pager getListData(Pager pager,String status){
        String userName = LingUtil.userName();
        String a=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        String b=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
        QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", LingUtil.userName()));
        String userId = user.getPartnerId();
        String partnerId = "";
        StringBuffer sql = new StringBuffer();
        String qxRoleid = adminDao.getQxRoleid(user.getUsername());
        if("true".equals(a) || "true".equals(b)){
            if("4028816a6182bb350161830ddd590020".equals(qxRoleid)){
                Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
                if(joint != null){
                    List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
                    for(int i = 0 ;i<par.size();i++){
                        Partner partner = par.get(i);
                        partnerId += partner.getId()+"','";
                    }
                }
            }
            sql.append(" select * from ( SELECT partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from  demand where status <> '0'");
            if(!"admin".equals(user.getUsername())){
                sql.append(" and partnerId in ('"+partnerId+"') ");
            }
            sql.append(" )s");
//            sql.append(" union select partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from demand where partnerId = '"+partnerId+"') s");
            if(StringUtils.isNotEmpty(status)){
                sql.append(" where s.status = '"+status+"'");
            }
        }else{
            if("4028816a6182bb350161830c7ea7001e".equals(qxRoleid)){
                sql.append(" SELECT partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from  demand where status <> '0'");
            }else{
                sql.append(" select partnerId,id,jbxx,cus_com_name,project_name,tbsj,status,bhyj from demand where partnerId = '"+userId+"'");
            }
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and s.status = '"+status+"'");
            }
        }
        pager = findPagerBySql(pager, sql.toString());
        return pager;
    }

}
