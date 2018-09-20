package com.lingnet.vocs.action.remotedebug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

/**
 * 远程调试
 * 
 * @ClassName: RemoteDebugAction
 * @Description: TODO
 * @author tangjw
 * @date 2017年6月2日 下午5:00:17
 * 
 */
@Controller
@ParentPackage("remotedebug")
@Namespace("/remotedebug")
public class RemoteDebugAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备参数列表
	 * 
	 * @Title: list
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 增加设备参数
	 * 
	 * @Title: add
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 编辑设备运行参数
	 * 
	 * @Title: edit
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String edit() {
		return ADD;
	}

	/**
	 * 查看运行参数
	 * 
	 * @Title: look
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月5日 V 1.0
	 */
	public String look() {
		return ADD;
	}

	/**
	 * 选择设备
	 * 
	 * @Title: item
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月5日 V 1.0
	 */
	public String item() {
		return "item";
	}

	/**
	 * 删除设备运行参数
	 * 
	 * @Title: remove
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String remove() {
		return ajax(Status.success, "success");
	}

	/**
	 * 棄用 获取设备运行参数信息
	 * 
	 * @Title: getListData
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getListData() {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("id", "1");
		map.put("equipmentCode", "a1");
		map.put("partner", "合作商1");
		map.put("customer", "客户1");
		map.put("address", "青岛");
		map.put("param1", "参数一");
		map.put("param2", "参数二");
		map.put("param3", "参数三");
		map.put("param4", "参数四");
		map.put("param5", "参数五");
		/* map.put("status", 1); */
		map.put("run_status", 0);
		Map map1 = new HashMap();
		map1.put("id", "2");
		map1.put("equipmentCode", "a2");
		map1.put("partner", "合作商1");
		map1.put("customer", "客户2");
		map1.put("address", "青岛");
		map1.put("param1", "参数一");
		map1.put("param2", "参数二");
		map1.put("param3", "参数三");
		map1.put("param4", "参数四");
		map1.put("param5", "参数五");
		/* map1.put("status", 1); */
		map1.put("run_status", 1);
		list.add(map);
		list.add(map1);
		return ajax(Status.success, JsonUtil.Encode(list));
	}

	/**
	 * 保存或更新设备运行参数
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String saveOrUpdate() {
		return ajax(Status.success, "success");
	}

	/**
	 * plc更新历史列表
	 * 
	 * @Title: plclist
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String plclist() {
		return "plclist";
	}

	/**
	 * 获取设备plc信息
	 * 
	 * @Title: getPlcData
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月3日 V 1.0
	 */
	public String getPlcData() {
		return ajax(Status.success, "success");
	}

	/**
	 * 上传plc文件包
	 * 
	 * @Title: plcUpload
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月5日 V 1.0
	 */
	public String plcUpload() {
		return "plcUpload";
	}
}
