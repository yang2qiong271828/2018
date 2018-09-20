package com.lingnet.vocs.action.reported;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.From;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Attachments;
import com.lingnet.vocs.entity.Hkpact;
import com.lingnet.vocs.entity.Jhpact;
import com.lingnet.vocs.entity.Pact;
import com.lingnet.vocs.entity.Partner;
import com.lingnet.vocs.entity.Sewage;
import com.lingnet.vocs.service.attachments.AttachmentsService;
import com.lingnet.vocs.service.reported.DemandService;
import com.lingnet.vocs.service.reported.PactService;
@ParentPackage("reported")
@Namespace("/reported")
public class PactAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = -8671377924932612318L;
    
    @Resource(name = "demandService")
    private DemandService demandService;
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "pactService")
    private PactService pactService;
    @Resource(name = "attachmentsService")
    private AttachmentsService attachmentsService;
    public Partner partner;//经销商
    public Pact pact;//合同录入
    public Hkpact hkpact;//回款记录
    public Jhpact jhpact;//回款计划
    
    
    public String formdata;//
    public String griddata;//
    public String threadId;
    public String companyName;//公司名称
    public String htId;//合同id
    public Double proportion;//比例
    public String type;//类型
    public Double hkje;//回款金额
    public Double zhkje;//总回款金额
    public Double yghkje;//剩余回款金额
    public Double yjhkje;//已回款金额
    public Double ykpje;//已开票金额
    private ArrayList<Attachments> imgList;
    private String attachmentdata;// 页面上附件列表
    /**
     * 跳转列表页
     * @Title: list 
     * @return 
     * String 
     * @author lizk
     * @since 2018年1月19日 V 1.0
     */
    public String list(){
        return LIST;
    }
    
    /**
     * 合同列表展示
     * @Title: getData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String getData(){
        return ajax(Status.success,pactService.getData(pager));
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
        pact = new Pact();
        pact.setContractType("2");//默认购买合同
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
        pact = this.getBeanByCriteria(Pact.class, Restrictions.eq("id", id));
        imgList = attachmentsService.getAttachmentListByEntityId(id);
        Sewage sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", pact.getCompanyId()));
        if(sewage != null){
            companyName = sewage.getPwname();
        }
        return "add";
    }
    
    /**
     * 删除合同
     * @Title: remove 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String remove() {
        try {
            List<Hkpact> hk = this.getBeanListByCriteria(Hkpact.class, Restrictions.eq("htId", ids[0]));//回款
            List<Jhpact> jh = this.getBeanListByCriteria(Jhpact.class, Restrictions.eq("htId", ids[0]));//计划
            if(hk.size() > 0){
                throw new Exception("该合同存在回款记录！");
            }
            if(jh.size() > 0){
                throw new Exception("该合同存在回款计划！");
            }
            pactService.delete(ids[0]);
            attachmentsService.removeAttachmentsByEntityId(ids[0]);
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
        pact = this.getBeanByCriteria(Pact.class, Restrictions.eq("id", id));
        imgList = attachmentsService.getAttachmentListByEntityId(id);
        Sewage sewage = this.getBeanByCriteria(Sewage.class, Restrictions.eq("id", pact.getCompanyId()));
        if(sewage != null){
            companyName = sewage.getPwname();
        }
        getRequest().setAttribute("flag", "false");
        return "add";
    }
    
    
    /**
     * 验证唯一性
     * 
     * @Title: validateCode
     * @return
     * @author zy
     * @since 2017年7月20日 V 1.0
     */
    public String validateCode() {
        try {
            String s = pactService.validateCode(key);
            return ajax(Status.success, s);
        } catch (Exception e) {
            return ajax(e.toString().substring(e.toString().indexOf(":") + 1),
                    "text/html;charset=UTF-8");
        }
    }
    
    /**
     * 选择成单客户
     * @Title: cdkh 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String cdkh(){
        return "cdkh";
    }
    
    /**
     * 保存合同
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String saveOrUpdate(){
        try {
            pact = JSONObject.parseObject(formdata, Pact.class);
            String json = pactService.saveOrUpdate(pact,attachmentdata);
            return ajax(Status.success, json);
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }
    ///////////////////上    合同////////////下  回款////////////////////////
    /**
     * 跳转回款记录列表
     * @Title: hklist 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String hklist(){
        return "hklist";
    }
    /**
     * 回款添加页
     * @Title: addhk 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月8日 V 1.0
     */
    public String addhk(){
        Pact pact = this.getBeanByCriteria(Pact.class, Restrictions.eq("id", htId));
        zhkje = pact.getAccountReceivable();
        yjhkje = 0.0;
        ykpje = 0.0;
        List<Hkpact> list = this.getBeanListByCriteria(Hkpact.class, Restrictions.eq("htId", htId));
        for(int i = 0; i < list.size(); i++){
            Hkpact h = list.get(i);
            yjhkje = yjhkje + h.getSsje();
            ykpje = ykpje + h.getFpje();
        }
        yghkje = zhkje - yjhkje;
        return "addhk";
    }
    
    public String edithk(){
        hkpact = this.getBeanByCriteria(Hkpact.class, Restrictions.eq("id", id));
        return "addhk";
    }
    
    public String lookhk(){
        hkpact = this.getBeanByCriteria(Hkpact.class, Restrictions.eq("id", id));
        getRequest().setAttribute("flag", "false");
        return "addhk";
    }
    
    public String removehk() {
        try {
            deleteByCriteria(Hkpact.class, Restrictions.eq("id", ids[0]));
            return ajax(Status.success,"success");
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.success,"删除失败");
        }
    }
    
    public String saveHk(){
        try {
            hkpact = JSONObject.parseObject(formdata, Hkpact.class);
            String json = pactService.saveHk(hkpact,htId);
            return ajax(Status.success, json);
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }
    /**
     * 回款明细记录
     * @Title: getHkData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String getHkData(){
        return ajax(Status.success,pactService.getHkData(pager,id));
    }
    //////////////////////回款计划//////////////////////////////////////

    
    /**
     * 跳转回款记录列表
     * @Title: jhlist 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String jhlist(){
        return "jhlist";
    }
    /**
     * 回款添加页
     * @Title: addjh 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月8日 V 1.0
     */
    public String addjh(){
        return "addjh";
    }
    
    public String editjh(){
        jhpact = this.getBeanByCriteria(Jhpact.class, Restrictions.eq("id", id));
        return "addjh";
    }
    
    public String lookjh(){
        jhpact = this.getBeanByCriteria(Jhpact.class, Restrictions.eq("id", id));
        getRequest().setAttribute("flag", "false");
        return "addjh";
    }
    
    public String removejh() {
        try {
            deleteByCriteria(Jhpact.class, Restrictions.eq("id", ids[0]));
            return ajax(Status.success,"success");
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.success,"删除失败");
        }
    }
    
    public String saveJh(){
        try {
            jhpact = JSONObject.parseObject(formdata, Jhpact.class);
            String json = pactService.saveJh(jhpact,htId);
            return ajax(Status.success, json);
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }
    /**
     * 回款明细记录
     * @Title: getjhData 
     * @return 
     * String 
     * @author lizk
     * @since 2018年3月7日 V 1.0
     */
    public String getJhData(){
        return ajax(Status.success,pactService.getJhData(pager,id));
    }
    
    public String getJeBl(){
        String jeBl = pactService.getJeBl(hkje,type,proportion,htId);
        return ajax(Status.success,jeBl);
    }
    
    
    
    
    //////////////////////////////////////////////////////////////
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Pact getPact() {
        return pact;
    }

    public void setPact(Pact pact) {
        this.pact = pact;
    }

    public Hkpact getHkpact() {
        return hkpact;
    }

    public void setHkpact(Hkpact hkpact) {
        this.hkpact = hkpact;
    }

    public Jhpact getJhpact() {
        return jhpact;
    }

    public void setJhpact(Jhpact jhpact) {
        this.jhpact = jhpact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHtId() {
        return htId;
    }

    public void setHtId(String htId) {
        this.htId = htId;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getHkje() {
        return hkje;
    }

    public void setHkje(Double hkje) {
        this.hkje = hkje;
    }

    public Double getZhkje() {
        return zhkje;
    }

    public void setZhkje(Double zhkje) {
        this.zhkje = zhkje;
    }

    public Double getYghkje() {
        return yghkje;
    }

    public void setYghkje(Double yghkje) {
        this.yghkje = yghkje;
    }

    public Double getYjhkje() {
        return yjhkje;
    }

    public void setYjhkje(Double yjhkje) {
        this.yjhkje = yjhkje;
    }

    public ArrayList<Attachments> getImgList() {
        return imgList;
    }

    public void setImgList(ArrayList<Attachments> imgList) {
        this.imgList = imgList;
    }

    public String getAttachmentdata() {
        return attachmentdata;
    }

    public void setAttachmentdata(String attachmentdata) {
        this.attachmentdata = attachmentdata;
    }

    public Double getYkpje() {
        return ykpje;
    }

    public void setYkpje(Double ykpje) {
        this.ykpje = ykpje;
    }

    
    


    

}
