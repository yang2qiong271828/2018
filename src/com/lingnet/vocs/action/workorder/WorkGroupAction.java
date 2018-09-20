package com.lingnet.vocs.action.workorder;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

@Controller
public class WorkGroupAction extends BaseAction {

	private static final long serialVersionUID = 4671471681625093042L;

	public String remove() {
		return SUCCESS;
	}

	public String list() {
		return "list";
	}

	/**
	 * 增加组
	 * 
	 * @Title: addmenu
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月12日 V 1.0
	 */
	public String addmenu() {
		return "addmenu";
	}

	/**
	 * 增加成员
	 * 
	 * @Title: addmember
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月12日 V 1.0
	 */
	public String addmember() {
		return "addmember";
	}

	/**
	 * 组成员列表数据
	 * 
	 * @Title: json
	 * @return String
	 * @author TANGJW
	 * @since 2017年6月12日 V 1.0
	 */
	public String json() {
		return ajax(Status.success, "success");
	}

	public String memberlist() {
		return "memberlist";
	}

	/**
	 * 棄用
	 * 
	 * @Title: treelist
	 * @return String
	 * @author lxf
	 * @since 2017年6月13日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String treelist() {
		HashMap map = new HashMap();
		map.put("id", "PT00001");
		map.put("text", "一组");
		HashMap map2 = new HashMap();
		map2.put("id", "PT00002");
		map2.put("text", "二组");
		ArrayList arrlist = new ArrayList();
		arrlist.add(map);
		arrlist.add(map2);
		String json = JsonUtil.Encode(arrlist);
		return ajax(Status.success, json);
	}

	/**
	 * 棄用
	 * 
	 * @Title: getGroupMember
	 * @return String
	 * @author lxf
	 * @since 2017年6月13日 V 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getGroupMember() {
		ArrayList arrlist = new ArrayList();
		if (id.equals("PT00001")) {
			HashMap map = new HashMap();
			map.put("id", "EQ0001");
			map.put("username", "EQ0001");
			map.put("name", "张志");
			map.put("email", "zhangzhi@i-lingnet.com");
			map.put("createdate", "2017-06-13");
			arrlist.add(map);
		} else {
			HashMap map2 = new HashMap();
			map2.put("id", "EQ0002");
			map2.put("username", "EQ0002");
			map2.put("name", "王晓鸥");
			map2.put("email", "wangxiaoou@i-lingnet.com");
			map2.put("createdate", "2017-06-13");
			arrlist.add(map2);
		}
		HashMap mapResult = new HashMap();
		mapResult.put("data", arrlist);
		mapResult.put("total", 1);
		String json = JsonUtil.Encode(mapResult);
		return ajax(Status.success, json);
	}
}
