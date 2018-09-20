package com.lingnet.vocs.action.reported;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.ContactsReport;
import com.lingnet.vocs.entity.Demand;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.ProjectPreparation;
import com.lingnet.vocs.service.reported.DemandService;
@ParentPackage("reported")
@Namespace("/reported")
public class DemandAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = -8671377924932612318L;
    
    @Resource(name = "demandService")
    private DemandService demandService;
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;
    @Resource(name = "adminDao")
    private AdminDao adminDao;
    public Partner partner;//经销商
    public Demand demand;//需求
    public ProjectPreparation pro;//报备
    public Contacts contacts;//经销商联系人
    public ContactsReport report;//联系人
    
    public String flag;//标识
    public String partnerName;//经销商名称
    public String area;//经销区域
    public String name;//经销商联系人
    public String tel;//联系电话
    public String formdata;//
    public String griddata1;//
    public String griddata2;//
    public String status;//需求状态
    public String hsjdjr;//华世洁对接人
    public String bhyj;//驳回意见
    public String jbxx;
    public String a;
    public String b;
    public String threadId;
    public String threadId1;
    public String threadId2;
    /**
     * 跳转列表页
     * @Title: list 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月19日 V 1.0
     */
    public String list(){
        String userName = LingUtil.userName();
        a=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
        b=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        return LIST;
    }
    
    /**
     * 跳转增加页
     * @Title: add 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月19日 V 1.0
     */
    public String add(){
    	//int i = (int) (Math.random() * 90);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
        
        threadId =simpleDateFormat.format(new Date())+"0";// 编码
        threadId1=simpleDateFormat.format(new Date())+"1";// 编码
        threadId2=simpleDateFormat.format(new Date())+"2";// 编码
        String username=LingUtil.userName();
        QxUsers qxuser=adminService.get(QxUsers.class,Restrictions.eq("username",username ));
        partner = this.getBeanByCriteria(Partner.class,Restrictions.eq("id",  qxuser.getPartnerId()));
        demand = new Demand();
        demand.setJbxx("1");
        return "add";
    }
    /**
     * 跳转修改页
     * @Title: edit 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String edit(){
        demand = this.getBeanById(Demand.class, id);
        threadId =demand.getThreadId();// 编码
        threadId1=demand.getThreadId1();// 编码
        threadId2=demand.getThreadId2();// 编码
        partner = this.getBeanByCriteria(Partner.class,Restrictions.eq("id",  demand.getPartnerId()));
        report = this.getBeanByCriteria(ContactsReport.class, Restrictions.eq("partnerId", id),Restrictions.eq("picMain", "1"));
        return "add";
    }
    
    
    public String remove() {
        try {
            demandService.delete(ids[0]);
            this.deleteByCriteria(ContactsReport.class, Restrictions.eq("partnerId", ids[0]));
            return ajax(Status.success,"success");
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.success,"删除失败");
        }
    }
    /**
     * 跳转查看页
     * @Title: look 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月25日 V 1.0
     */
    public String look(){
        getRequest().setAttribute("flag", "false");
        demand = this.getBeanById(Demand.class, id);
        threadId =demand.getThreadId();// 编码
        threadId1=demand.getThreadId1();// 编码
        threadId2=demand.getThreadId2();// 编码
        return "add";
    }
    
    public String  check(){
        demand = this.getBeanById(Demand.class, id);
//        if("2".equals(demand.getStatus())){
//            demand.setBhyj(demand.getHsjdjr());//状态为审核通过，展示对接人
//        }
        return  "check";
    }
    
    public String  checkk(){
        demand = this.getBeanById(Demand.class, id);
//        if("2".equals(demand.getStatus())){
//            demand.setBhyj(demand.getHsjdjr());//状态为审核通过，展示对接人
//        }
        return  "checkk";
    }
    
    
    /**
     * 需求列表展示
     * @Title: getListData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String getListData(){
        String json = demandService.getListData(pager,status);
        return ajax(Status.success, json);
    }
    /**
     * 增加页选择类别
     * @Title: getXqtb 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月23日 V 1.0
     */
    @SuppressWarnings("unchecked")
    public String getXqtb(){
        List<HashMap> result = new ArrayList<HashMap>();
        String[] text = {"考察接待","项目支持","培训支持","安装调试","技术方案","标书制作","宣传支持"};
        for(int i = 0;i < text.length; i++){
            HashMap map = new HashMap();
            map.put("id", i+1+"");
            map.put("text", text[i]);
            result.add(map);
        }
        String json = JsonUtil.toJson(result);
        return ajax(Status.success,json);
    }
    
    @SuppressWarnings("unchecked")
    public String getGylx(){
        List<HashMap> result = new ArrayList<HashMap>();
        String[] text = {"吸附回收GAC","床式RTO","催化氧化CO","转轮ZAC","储热催化氧化RCO","工业除尘","吸附回收ACF","旋转RTO","直燃炉TNY","生物床","蜂窝碳HAC","转轮一体机","其他",};
        for(int i = 0;i < text.length; i++){
            HashMap map = new HashMap();
            map.put("id", i+1+"");
            map.put("text", text[i]);
            result.add(map);
        }
        String json = JsonUtil.toJson(result);
        return ajax(Status.success,json);
    }

    
    public String ntj() {
        
        try {
            demand=getBeanById(Demand.class, id);
            demand.setBhyj(bhyj);;
            demand.setStatus("5");
            
            demandService.update(demand);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "重新提交失败");
        }
    }
    
    /**
     * 
     * @Title: getContactListData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String getContactListData() {
        if (StringUtils.isNotEmpty(id)) {
            String json = demandService.getContactListData(id);
            return ajax(Status.success, json);
        } else {
            return ajax(Status.success, NONE);
        }
    }
    
    /**
     * 需求提交
     * @Title: tj 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String tj() {
        try {
            demand=getBeanById(Demand.class, id);
            demand.setStatus("1");
            demand.setTbsj(new Date());
            demand.setBhyj("");
            demandService.update(demand);
            return ajax(Status.success, "success");
            
        } catch (Exception e) {
            return ajax(Status.success, "提交失败");
        }
    }
    
    /**
     * 保存
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String saveOrUpdate(){
        try {
            demand = JSONObject.parseObject(formdata, Demand.class);
            Map<String ,String > s = JsonUtil.parseProperties(formdata);
            String json = demandService.saveOrUpdate(demand, griddata1,griddata2,jbxx,s,threadId,threadId1,threadId2);
            return ajax(Status.success, json);
        } catch (Exception e) {
            return ajax(Status.error,"保存失败！");
        }
    }
    /**
     * 选择备案
     * @Title: select 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月24日 V 1.0
     */
    public String select(){
        return "select";
    }
    
    public String sh() {
        try {
            demand=getBeanById(Demand.class, id);
            demand.setStatus("2");
            demand.setBhyj(bhyj);
            demandService.update(demand);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
    
    public String shno() {
        try {
            demand=getBeanById(Demand.class, id);
            demand.setStatus("4");
            demand.setBhyj(bhyj);
            demandService.update(demand);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
    public String shh() {
        try {
            demand=getBeanById(Demand.class, id);
            demand.setStatus("3");
            demand.setBhyj(bhyj);
            demandService.update(demand);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
    public String shhno() {
        try {
            demand=getBeanById(Demand.class, id);
            demand.setStatus("4");
            demand.setBhyj(bhyj);;
            demandService.update(demand);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
    
    
    
    /////////////////////////////////////////////////////////
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public ProjectPreparation getPro() {
        return pro;
    }

    public void setPro(ProjectPreparation pro) {
        this.pro = pro;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public ContactsReport getReport() {
        return report;
    }

    public void setReport(ContactsReport report) {
        this.report = report;
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

    public String getGriddata1() {
        return griddata1;
    }

    public void setGriddata1(String griddata1) {
        this.griddata1 = griddata1;
    }

    public String getGriddata2() {
        return griddata2;
    }

    public void setGriddata2(String griddata2) {
        this.griddata2 = griddata2;
    }

    public String getHsjdjr() {
        return hsjdjr;
    }

    public void setHsjdjr(String hsjdjr) {
        this.hsjdjr = hsjdjr;
    }

    public String getBhyj() {
        return bhyj;
    }

    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }

    public String getJbxx() {
        return jbxx;
    }

    public void setJbxx(String jbxx) {
        this.jbxx = jbxx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getThreadId1() {
		return threadId1;
	}

	public void setThreadId1(String threadId1) {
		this.threadId1 = threadId1;
	}

	public String getThreadId2() {
		return threadId2;
	}

	public void setThreadId2(String threadId2) {
		this.threadId2 = threadId2;
	}


    

}
