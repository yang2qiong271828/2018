package com.lingnet.vocs.service.alarm;


import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.MsgTemplate;

public interface MsgTemplateService extends BaseService<MsgTemplate, String> {
    /**
     * 获取列表数据
     * 
     * @Title: getListData
     * @param pager
     * @param id
     * @return
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    HashMap getListData(Pager pager, String id);

    /**
     * 删除
     * 
     * @Title: remove
     * @param id
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    String remove(String id) throws Exception;

    /**
     * 保存或更新
     * 
     * @Title: saveOrUpdate
     * @param msgTemplate
     * @throws Exception
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    void saveOrUpdate(MsgTemplate msgTemplate) throws Exception;
    
    /**
     * 发送短信
     * 
     * @Title: sendMsg
     * @param formjson
     * @param type
     * @return
     * @throws Exception
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    String sendMsg(String formjson, int type) throws Exception;

    /**
     * 修改停用启用状态
     * 
     * @Title: changeIsEnabled
     * @param id
     * @param key
     * @throws Exception
     * @author zy
     * @since 2017年7月11日 V 1.0
     */
    void changeIsEnabled(String id, String key) throws Exception;
}
