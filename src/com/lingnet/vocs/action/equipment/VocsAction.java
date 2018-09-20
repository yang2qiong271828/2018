package com.lingnet.vocs.action.equipment;

import java.util.ArrayList;
import java.util.HashMap;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;



public class VocsAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private  HashMap<String,String> vocs;
    
    
    public String list() {
        return LIST;
    }

    /**
     * @Title: add
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    public String add() {
        return ADD;
    }

    /**
     * @Title: remove
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, "success");
    }

    /**
     * 废弃
     * @Title: edit
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    public String edit() {
        vocs = new HashMap<String, String>();
        if(id.equals("EQ0001")){
            vocs.put("equipmentCode", "EQ0001");
            vocs.put("equipmentName", "0");
            vocs.put("equipmentLength", "1189");
            vocs.put("equipmentWidth", "232");
            vocs.put("equipmentHeight", "232");
            vocs.put("equipmentPower", "70千瓦");
            vocs.put("equipmentScope", "涂装行业");
            vocs.put("equipmentType", "吸附回收废气处理");
        }else{
            vocs.put("equipmentCode", "EQ0002");
            vocs.put("equipmentName", "1");
            vocs.put("equipmentLength", "1189");
            vocs.put("equipmentWidth", "232");
            vocs.put("equipmentHeight", "260");
            vocs.put("equipmentPower", "90千瓦");
            vocs.put("equipmentScope", "石化行业");
            vocs.put("equipmentType", "热氧化废气处理");
        }
        
        return ADD;
    }

    /**
     * @Title: view
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    public String look() {
        return ADD;
    }

    /**
     * 废弃
     * @Title: getListData
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getListData() {
        ArrayList arrlist = new ArrayList();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        map.put("id", "EQ0001");
        map.put("equipmentCode", "EQ0001");
        map.put("equipmentName", "活性炭纤维吸附回收");
        map.put("equipmentLength", "1189");
        map.put("equipmentWidth", "232");
        map.put("equipmentHeight", "232");
        map.put("equipmentPower", "70千瓦");
        map.put("equipmentScope", "涂装行业");
        map.put("equipmentType", "吸附回收废气处理");

        map2.put("id", "EQ0002");
        map2.put("equipmentCode", "EQ0002");
        map2.put("equipmentName", "旋转式蓄热氧化");
        map2.put("equipmentLength", "1189");
        map2.put("equipmentWidth", "232");
        map2.put("equipmentHeight", "260");
        map2.put("equipmentPower", "90千瓦");
        map2.put("equipmentScope", "石化行业");
        map2.put("equipmentType", "热氧化废气处理");
        arrlist.add(map);
        arrlist.add(map2);
        HashMap mapResult = new HashMap();
        mapResult.put("data", arrlist);
        mapResult.put("total", 1);
        String json = JsonUtil.Encode(mapResult);
        return ajax(Status.success, json);
    }

    /**
     * @Title: saveOrUpdate
     * @return String
     * @author lipx
     * @since 2017年6月3日 V 1.0
     */
    public String saveOrUpdate() {
        return ajax(Status.success, "success");
    }

    public HashMap<String, String> getVocs() {
        return vocs;
    }

    public void setVocs(HashMap<String, String> vocs) {
        this.vocs = vocs;
    }
}
