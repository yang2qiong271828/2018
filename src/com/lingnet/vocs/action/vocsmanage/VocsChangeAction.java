package com.lingnet.vocs.action.vocsmanage;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.service.vocsmanage.VocsChangeService;

public class VocsChangeAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    @Resource(name = "vocsChangeService")
    private VocsChangeService vocsChangeService;
    private  HashMap<String,String> vocsChange;
    private String companyName;
    private String companyId;
    
    
    /**
     * VOCs治理单元更迭记录
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String list(){
        return LIST;
    }
    
    /**
     * 跳转新增VOCs治理单元更迭记录
     * @Title: add
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String add(){
        companyId = "1";
        companyName = "华世洁";
        return ADD;
    }
    
    /**
     * 跳转编辑VOCs治理单元更迭记录
     * @Title: list
     * @return String
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String edit(){
        companyId = "1";
        companyName = "华世洁";
        vocsChange = new HashMap<String, String>();
        vocsChange.put("changeMan","李先生");
        vocsChange.put("oldVocs","VOCs0001");
        vocsChange.put("newVocs","VOCs0002");
        return ADD;
    }
    
    /**
     * 查看VOCs治理单元更迭记录
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
     * 获取VOCs治理单元更迭记录
     * @Title: getListData 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    @SuppressWarnings("rawtypes")
	public String getListData(){
		HashMap result = vocsChangeService.getListData(pager);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    
    /**
     * 保存或编辑VOCs治理单元更迭记录
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
     * 删除VOCs治理单元更迭记录
     * @Title: remove 
     * @return 
     * String 
     * @author lipx
     * @since 2017-06-01 V 1.0
     */
    public String remove(){
        return ajax(Status.success,"success");
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public HashMap<String, String> getVocsChange() {
        return vocsChange;
    }

    public void setVocsChange(HashMap<String, String> vocsChange) {
        this.vocsChange = vocsChange;
    }

}
