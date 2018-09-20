package com.lingnet.vocs.action.vocsmanage;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.service.vocsmanage.VocsCustomerService;

public class VocsCustomerAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    @Resource(name = "vocsCustomerService")
    private VocsCustomerService vocsCustomerService;
    private  HashMap<String,String> vocsCustomer;
    
    
    /**
     * VOCs治理单元与客户关系维护列表
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String list(){
        return LIST;
    }
    
    /**
     * 跳转新增VOCs治理单元与客户关系页面
     * @Title: add
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String add(){
        return ADD;
    }
    
    /**
     * 跳转编辑VOCs治理单元与客户关系页面
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String edit(){
        vocsCustomer = new HashMap<String, String>();
        vocsCustomer.put("vocsName","VOCs2056");
        vocsCustomer.put("customerName","大众4s");
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
     * 获取VOCs治理单元与客户关系信息
     * @Title: getListData 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    @SuppressWarnings("rawtypes")
	public String getListData(){
        HashMap result = vocsCustomerService.getListData(pager);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    
    /**
     * 保存或编辑VOCs治理单元与客户关系
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
     * 删除VOCs治理单元与客户关系维护
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
     * 客户选择页面
     * @Title: vocsList 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    public String customerShow(){
        return "customerShow";
    }
    
    /**
     * 获取客户数据集合
     * @Title: getvocsData 
     * @return 
     * @author Lipx
     * @since 2017年6月3日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCustomerData(){
        ArrayList data = new ArrayList();
        HashMap<String, String> record = new HashMap<String, String>();
        record.put("customerId", "1");
        record.put("customerName", "李先生");
        data.add(record);
        pager.setTotalCount(1);
        HashMap result = new HashMap();
        result.put("data", data);
		result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }

    public HashMap<String, String> getVocsCustomer() {
        return vocsCustomer;
    }

    public void setVocsCustomer(HashMap<String, String> vocsCustomer) {
        this.vocsCustomer = vocsCustomer;
    }

}
