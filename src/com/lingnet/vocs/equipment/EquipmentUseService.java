package com.lingnet.vocs.service.equipment;


import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Machine;


public interface EquipmentUseService extends BaseService<Machine, String>{

    /**
     * 设备基础信息编辑保存
     * 
     * @Title: saveOrUpdate
     * @param machine
     * @return
     * @throws Exception
     *             String
     * @author suihl    
     * @since 2017年10月09日 V 1.0
     */
    public String saveOrUpdate(Machine machine , String girdData) throws Exception;

    /**
     * 设备基础信息列表展示
     * 
     * @Title: getListData
     * @param pager
     * @return String
     * @author suihl
     * @since 2017年10月09日 V 1.0
     */
    public String getListData(Pager pager);

    /**
     * 设备基础信息删除
     * 
     * @Title: remove
     * @param inspectId
     * @return
     * @throws Exception
     *             String
     * @author suihl
     * @since 2017年10月09日 V 1.0
     */
    public String remove(String machineId) throws Exception;

    /**
     * 设备信息展示
     * @Title: getSblist 
     * @param id
     * @return 
     * String 
     * @author lizk
     * @param type 
     * @since 2017年10月25日 V 1.0
     */
    public String getSblist(String sbmc,String gylx,String sbcs,String id,String start,String count,String isrm);
    public String getRmlist();
    public String getGylx();
    public String getSbcj();

	public String getData(Pager pager);

	public List<Machine> findBySupplierId(String id);
    
    
}
