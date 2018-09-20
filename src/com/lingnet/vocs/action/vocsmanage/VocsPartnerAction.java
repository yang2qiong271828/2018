package com.lingnet.vocs.action.vocsmanage;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.service.vocsmanage.VocsPartnerService;

public class VocsPartnerAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    
    @Resource(name = "vocsPartnerService")
    private VocsPartnerService vocsPartnerService;
    private  HashMap<String,String> vocsPartner;
    
    /**
     * VOCs治理单元与合作商关系维护列表
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String list(){
        return LIST;
    }
    
    /**
     * 跳转新增VOCs治理单元与合作商关系页面
     * @Title: add
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String add(){
        return ADD;
    }
    
    /**
     * 跳转编辑VOCs治理单元与合作商关系页面
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String edit(){
        vocsPartner = new HashMap<String, String>();
        vocsPartner.put("vocsName","VOCs2056");
        vocsPartner.put("partnerName","大众4s");
        return ADD;
    }
    
    /**
     * 查看VOCS客户
     * @Title: view 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String view(){
        return VIEW;
    }
    
    /**
     * 获取VOCs治理单元与合作商关系信息
     * @Title: getListData 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    @SuppressWarnings("rawtypes")
	public String getListData(){
        HashMap result = vocsPartnerService.getListData(pager);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    
    /**
     * 保存或编辑VOCs治理单元与合作商关系
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String saveOrUpdate(){
        return ajax(Status.success,"success");
    }
    
    /**
     * 删除VOCs治理单元与合作商关系维护
     * @Title: remove 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String remove(){
        return ajax(Status.success,"success");
    }
    
    /**
     * vocs选择页面
     * @Title: vocsList 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    public String vocsShow(){
        return "vocsShow";
    }
    
    /**
     * 获取VOCs治理单元数据集合
     * @Title: getvocsData 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getVocsData(){
        ArrayList data = new ArrayList();
        HashMap<String, String> record = new HashMap<String, String>();
        record.put("vocsId", "1");
        record.put("vocsName", "VOCs2056");
        data.add(record);
        HashMap result = new HashMap();
        pager.setTotalCount(1);
        result.put("data", data);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    
    
    /**
     * 合作商选择页面
     * @Title: vocsList 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    public String partnerShow(){
        return "partnerShow";
    }
    
    /**
     * 获取合作商数据集合
     * @Title: getvocsData 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPartnerData(){
        ArrayList data = new ArrayList();
        HashMap<String, String> record = new HashMap<String, String>();
        record.put("partnerId", "1");
        record.put("partnerName", "大众4s");
        data.add(record);
        pager.setTotalCount(1);
        HashMap result = new HashMap();
		result.put("data", data);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }

    public HashMap<String, String> getVocsPartner() {
        return vocsPartner;
    }

    public void setVocsPartner(HashMap<String, String> vocsPartner) {
        this.vocsPartner = vocsPartner;
    }

}
