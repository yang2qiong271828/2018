package com.lingnet.vocs.action.reported;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.lingnet.vocs.dao.reported.ReportDao;
import com.lingnet.vocs.entity.ContactsReport;
import com.lingnet.vocs.entity.Demand;
import com.lingnet.vocs.entity.Follow;
import com.lingnet.vocs.entity.Grade;
import com.lingnet.vocs.entity.Joint;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.ProjectPreparation;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.service.partner.PartnerService;
import com.lingnet.vocs.service.reported.ReportService;
import com.lingnet.vocs.service.sewage.SewageService;
@ParentPackage("reported")
@Namespace("/reported")
public class ReportAction extends BaseAction {

	@Resource(name = "reportService")
	private ReportService reportService;// 客户
	
	@Resource(name = "reportDao")
	private ReportDao reportDao;
	
	@Resource(name = "toolUtil")
	private ToolUtil toolUtil;
	@Resource(name = "adminDao")
    private AdminDao adminDao;
	@Resource(name = "adminService")
	private AdminService adminService;
	@Resource(name = "sewageService")
    private SewageService sewageService;
	
	@Resource(name = "partnerService")
	private PartnerService partnerService;// 合作商
	
	private ProjectPreparation projectPreparation;//报备表
	private Sewage sewage;//排污企业表
	private Demand demand;//需求表
	public Follow follow;//跟进表
	public Grade grade;//评分表
	
	private String formdata;// 表单数据
	private String griddata;// 联系人列表
	private String partnerId;
	private String zs;
	private String yijian;
	private String yijiann;
	private String status;
	private String a;
	private String b;
	private String name;
    public String threadId;
    public String threadId1;
    public String threadId2;
    public String flag;
    
	
	/**
	 * @ReportAction.java
	 * @author yic 
	 * @date 2018年1月19日 上午10:14:35
	 */
	private static final long serialVersionUID = 1863912332804589064L;
    /**
     * 
     * @Title: list 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String list(){
         String userName = LingUtil.userName();
    	 a=adminDao.findContractApproveFlag(userName,"/reported/report!check.action");
    	 b=adminDao.findContractApproveFlag(userName,"/reported/report!checkk.action");
    	 
	     return LIST;
    }
    /**
     * 
     * @Title: add 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String add(){
    
	  return ADD;
    }
    /**
     * 
     * @Title: edit 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String edit(){
      
      projectPreparation=getBeanById(ProjectPreparation.class, id);
     // System.out.println(id);
   //   System.out.println(projectPreparation);
    /*  if(projectPreparation==null) {
    	  sewage = getBeanById(Sewage.class, id);
    	  projectPreparation.setCusComName(sewage.getPwname());
      }*/
  	  return ADD;
      }
    
    /**
     * 
     * @Title: look 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String look(){
    	
    	zs="1";
    	getRequest().setAttribute("flag", "false");
    	projectPreparation = reportService.get(ProjectPreparation.class, id);
    	return "add";
    }
    /**
     * 查看详情页
     * @Title: show 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String show(){
        getRequest().setAttribute("flag", "false");
        projectPreparation = reportService.get(ProjectPreparation.class, id);
        sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", projectPreparation.getCusComNameId()));
        demand = new Demand();
        demand = this.getBeanByCriteria(Demand.class, Restrictions.eq("bid", id));
//        follow = new Follow();
//        follow = this.getBeanByCriteria(Follow.class, Restrictions.eq("reprotId", id));
        return "show";
    }
    /**
     * 详情中报备页
     * @Title: report 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String report(){
        getRequest().setAttribute("flag", "false");
        projectPreparation = reportService.get(ProjectPreparation.class, id);
        return "report";
    }
    /**
     * 详情中需求列表页
     * @Title: demandList 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String  demandList(){
        String userName = LingUtil.userName();
        a=adminDao.findContractApproveFlag(userName,"/reported/demand!check.action");
        b=adminDao.findContractApproveFlag(userName,"/reported/demand!checkk.action");
        return "demand_list";
    }
    /**
     * 列表展示数据
     * @Title: getDemandData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String getDemandData(){
        String json = reportService.getDemandData(pager,id);
        return ajax(Status.success, json);
    }
    
    
    /**
     * 打分增加页
     * @Title: editgrade 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月16日 V 1.0
     */
    public String editGrade(){
        projectPreparation=getBeanById(ProjectPreparation.class, id);
        grade = this.getBeanByCriteria(Grade.class, Restrictions.eq("reportId", id));
        if(grade == null){
            grade = new Grade();
            grade.setJs(0);; //技术 
            grade.setJfpw(0);; //甲方评委
            grade.setPfxz(0);;//评分细则
            grade.setGxnx(0);;//关系内线 
            grade.setTzys(0);;//投资预算
            grade.setZz(0);;//资质
            grade.setAlyj(0);;//案例业绩
            grade.setFs(0);;//分包方案/招标方式
        }
        return "addgrade";
    }
    
    public String Grade(){
        projectPreparation=getBeanById(ProjectPreparation.class, id);
        grade = this.getBeanByCriteria(Grade.class, Restrictions.eq("reportId", id));
        if(grade == null){
            grade = new Grade();
            grade.setJs(0);; //技术 
            grade.setJfpw(0);; //甲方评委
            grade.setPfxz(0);;//评分细则
            grade.setGxnx(0);;//关系内线 
            grade.setTzys(0);;//投资预算
            grade.setZz(0);;//资质
            grade.setAlyj(0);;//案例业绩
            grade.setFs(0);;//分包方案/招标方式
        }
        return "grade";
    }
    
    public String saveGrade(){
        try {
            grade = JSONObject.parseObject(formdata, Grade.class);
            reportService.saveGrade(grade);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.error,"保存失败");
        }
    }
    
    
    
    /**
     * 跟进记录列表
     * @Title: gj
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String gj(){
        return "gj";
    }
    public String gjlist(){
        return "gjlist";
    }
    
    public String getGjData(){
        String json = reportService.getGjData(pager,id);
        return ajax(Status.success, json);
    }
    
    public String addgj(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
        threadId =simpleDateFormat.format(new Date());// 编码
        return "addgj";
    }
    
    public String editgj(){
        follow = this.getBeanByCriteria(Follow.class, Restrictions.eq("id", id));
        if (null == follow) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
            threadId =simpleDateFormat.format(new Date());// 编码
        }else{
            if(StringUtils.isEmpty(follow.getThreadId())){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
                threadId =simpleDateFormat.format(new Date());// 编码
            }else{
                threadId = follow.getThreadId();
            }
        }
        return "addgj";
    }
    
    public String lookgj(){
        follow = this.getBeanByCriteria(Follow.class, Restrictions.eq("id", id));
        if (null == follow) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 单号由开头+年月日时分秒合成
            threadId =simpleDateFormat.format(new Date());// 编码
        }else{
            threadId = follow.getThreadId();
        }
        flag = "false";
        getRequest().setAttribute("flag", "false");
        return "addgj";
    }
    
    
    public String saveGj(){
        try {
            follow = JSONObject.parseObject(formdata, Follow.class);
            reportService.saveGj(follow);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
                    "text/html;charset=UTF-8");
        }
    }
    
    /**
     * 
     * @Title: remove 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String removegj() {
        
        try {
//            reportDao.delete(ids[0]);
            this.deleteByCriteria(Follow.class, Restrictions.eq("id", ids[0]));
            return ajax(Status.success, "success");
        } catch (Exception e) {
            return ajax(Status.success, "删除失败");
        }
    }
    
    /**
     * 需求详情展示页
     * @Title: demand 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String demand(){
        getRequest().setAttribute("flag", "false");
        demand = new Demand();
        demand = this.getBeanByCriteria(Demand.class, Restrictions.eq("id", id));
        threadId =demand.getThreadId();// 编码
        threadId1=demand.getThreadId1();// 编码
        threadId2=demand.getThreadId2();// 编码
        return "demand";
    }
    /**
     * 详情客户页
     * @Title: sewage 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月5日 V 1.0
     */
    public String sewage(){
        getRequest().setAttribute("flag", "false");
        sewage = new Sewage();
        sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", id));
        return "sewage";
    }
    

    /**
     * 
     * @Title: getListData 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
    public String getListData(){
      //报备状态为审核的。达到截止时间状态没有需求替换为重新提报
        List<ProjectPreparation> preparation = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("status", "3"));
        if(preparation.size() > 0){
            Date date = new Date();
            for(int i = 0;i<preparation.size();i++){
                ProjectPreparation p = preparation.get(i);
                List<Demand> be = this.getBeanListByCriteria(Demand.class,Restrictions.eq("bid", p.getId()));
                Date jzsj = p.getJzsj();
                Calendar c = Calendar.getInstance();
                c.setTime(jzsj);
                c.add(Calendar.MONTH, 6);//获取审核时间半年后的时间
                
                if(c.getTime().getTime() < date.getTime()){
                    if(be.size() == 0){
                        p.setStatus("5");
                        reportService.save(p);
                    }
                }
            }
        }
        
    	String json = reportService.getListData(pager,status);
		return ajax(Status.success, json);
    }
    
    /**
     * 
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author yic
     * @since 2018年1月22日 V 1.0
     */
	public String saveOrUpdate() {
		try {
			projectPreparation = JSONObject.parseObject(formdata, ProjectPreparation.class);
			reportService.saveOrUpdate(projectPreparation, griddata);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
					"text/html;charset=UTF-8");
		}
	}
	/**
	 * 
	 * @Title: remove 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月22日 V 1.0
	 */
	public String remove() {
		
		try {
//			reportService.remove(ids[0]);
		    reportService.delete(ids[0]);
		    this.deleteByCriteria(ContactsReport.class, Restrictions.eq("partnerId", ids[0]));
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "删除失败");
		}
	}
	
	/**
	 * 对客户名称进行查重
	 * @Title: lookkhmc 
	 * @return 
	 * String 
	 * @author lizk
	 * @since 2018年2月2日 V 1.0
	 */
	public String lookkhmc(){
	    List<ProjectPreparation> br = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComName", name)
	            ,Restrictions.or(Restrictions.eq("status", "3"), Restrictions.eq("status", "2")));
        String msg = "";
        if(br.size() > 0){
            msg = "该客户已存在";
        }else{
            msg ="";
        }
        return ajax(Status.success, msg);
	}
	public String select(){
	    return "select";
	}
	/**
	 * 
	 * @Title: tj 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月22日 V 1.0
	 */
	public String tj() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			
			projectPreparation.setStatus("1");
			projectPreparation.setYijian("");
			projectPreparation.setJzsj(null);
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "提交失败");
		}
	}
	/**
	 * 
	 * @Title: ntj 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月25日 V 1.0
	 */
	public String ntj() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
            for(ProjectPreparation l : lp){
                if("3".equals(l.getStatus())){
                    return ajax(Status.success, "改客户已报备成功，请勿重复操作！");
                }
            }
			projectPreparation.setYijian(yijian);
			projectPreparation.setStatus("5");
			projectPreparation.setJzsj(null);
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "提交失败");
		}
	}
	/**
	 * 
	 * @Title: nntj 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月25日 V 1.0
	 */
	public String nntj() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
            for(ProjectPreparation l : lp){
                if("3".equals(l.getStatus())){
                    return ajax(Status.success, "改客户已报备成功，请勿重复操作！");
                }
            }
			projectPreparation.setYijian(yijian);
			projectPreparation.setStatus("5");
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "重新提交失败");
		}
	}
	public String check(){
		projectPreparation=getBeanById(ProjectPreparation.class, id);
		
		return "check";
	}
	public String verify(){
		projectPreparation=getBeanById(ProjectPreparation.class, id);
	    String userName = LingUtil.userName();
	    QxUsers user = adminService.get(QxUsers.class,Restrictions.eq("username", userName));
        Joint joint = partnerService.get(Joint.class,Restrictions.eq("userId", user.getId()));
        if(joint != null){
            List<Partner> par = partnerService.getList(Partner.class, Restrictions.eq("djr", joint.getId()));//根据对接人id  对接潜在客户 ，获取其负责区域
        	List<Partner>  list = new ArrayList<Partner>();
        	for(int i = 0 ;i<par.size();i++){
        		Partner  partner1 = par.get(i);
                list.add(partner1);
              }
        	for (Partner partner2 : list) {	
        		if ((projectPreparation.getPartnerId()).equals(partner2.getId())) {
        			return ajax(Status.success, "success");
        		}
        	}
        }
		return ajax(Status.success, "该经销商不在您的对接范围内不允许审核。");
	}
	
	
	public String checkk(){
		projectPreparation=getBeanById(ProjectPreparation.class, id);
		
		return "checkk";
	}
	
	public String checksp(){
        projectPreparation=getBeanById(ProjectPreparation.class, id);
        grade = this.getBeanByCriteria(Grade.class, Restrictions.eq("reportId", id));
        return "checksp";
    }
	
	
	public String sp() {
        try {
            return ajax(Status.success, reportService.sp(id,yijian));
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
	
	public String spno() {
        try {
            return ajax(Status.success, reportService.spno(id,yijian));
        } catch (Exception e) {
            return ajax(Status.success, "审核失败");
        }
    }
	
	/**
	 * 
	 * @Title: sh 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月22日 V 1.0
	 */
	public String sh() {

		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
			for(ProjectPreparation l : lp){
			    if("3".equals(l.getStatus())){
			        return ajax(Status.success, "该客户已报备成功，无法重复报备！");
			    }
			}
			projectPreparation.setStatus("2");
			projectPreparation.setYijian(yijian);
			projectPreparation.setJzsj(new Date());
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "审核失败");
		}
	}
	
	public String shno() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
            for(ProjectPreparation l : lp){
                if("3".equals(l.getStatus())){
                    return ajax(Status.success, "改客户已报备成功，请勿重复操作！");
                }
            }
			projectPreparation.setStatus("4");
			projectPreparation.setYijian(yijian);
			projectPreparation.setJzsj(new Date());
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "审核失败");
		}
	}
	public String shh() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			Sewage sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", projectPreparation.getCusComNameId()));
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
            for(ProjectPreparation l : lp){
                if("3".equals(l.getStatus())){
                    return ajax(Status.success, "改客户已报备成功，请勿重复操作！");
                }
            }
			sewage.setStatus("2");//意向客户
			sewageService.save(sewage);
			projectPreparation.setStatus("3");
			projectPreparation.setYijian(yijian);
			projectPreparation.setJzsj(new Date());
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "审核失败");
		}
	}
	public String shhno() {
		
		try {
			projectPreparation=getBeanById(ProjectPreparation.class, id);
			List<ProjectPreparation> lp = this.getBeanListByCriteria(ProjectPreparation.class, Restrictions.eq("cusComNameId", projectPreparation.getCusComNameId()));
            for(ProjectPreparation l : lp){
                if("3".equals(l.getStatus())){
                    return ajax(Status.success, "改客户已报备成功，请勿重复操作！");
                }
            }
			projectPreparation.setStatus("4");
			projectPreparation.setYijian(yijian);
			projectPreparation.setJzsj(new Date());
			reportService.update(projectPreparation);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			return ajax(Status.success, "审核失败");
		}
	}
	/**
	 * 
	 * @Title: getContactListData 
	 * @return 
	 * String 
	 * @author yic
	 * @since 2018年1月22日 V 1.0
	 */
	public String getContactListData() {
		if (StringUtils.isNotEmpty(partnerId)) {
			String json= JsonUtil.Encode(reportService.getContactListData(partnerId));
			return ajax(Status.success, json);
		} else {
			/*ArrayList arrlist = new ArrayList();
			HashMap map = new HashMap();
			map.put("id", "");
			map.put("picName", "");
			map.put("picGender", "");
			map.put("picTitle", "");
			map.put("picDepartment", "");
			map.put("picPhone", "");
			map.put("picEmail", "");
			map.put("picMain", "");
			arrlist.add(map);
			
			HashMap mapResult = new HashMap();
			mapResult.put("data", arrlist);
			mapResult.put("total", arrlist.size());*/
			return ajax(Status.success, NONE);
		}
	}
	
    public String lookgz(){
    	return "lookgz";
    }
	public ProjectPreparation getProjectPreparation() {
		return projectPreparation;
	}
	public void setProjectPreparation(ProjectPreparation projectPreparation) {
		this.projectPreparation = projectPreparation;
	}
	public String getFormdata() {
		return formdata;
	}
	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
	public String getGriddata() {
		return griddata;
	}
	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public String getYijian() {
		return yijian;
	}
	public void setYijian(String yijian) {
		this.yijian = yijian;
	}
	public String getYijiann() {
		return yijiann;
	}
	public void setYijiann(String yijiann) {
		this.yijiann = yijiann;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Sewage getSewage() {
        return sewage;
    }
    public void setSewage(Sewage sewage) {
        this.sewage = sewage;
    }
    public Demand getDemand() {
        return demand;
    }
    public void setDemand(Demand demand) {
        this.demand = demand;
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
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Follow getFollow() {
        return follow;
    }
    public void setFollow(Follow follow) {
        this.follow = follow;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
