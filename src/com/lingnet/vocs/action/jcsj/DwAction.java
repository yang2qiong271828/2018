package com.lingnet.vocs.action.jcsj;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.entity.Jcsj_Dw;
import com.lingnet.vocs.service.aominfo.DwService;
import com.lingnet.vocs.service.jcsj.GoodcatManageService;

/**
 * 
 * @ClassName: DwAction
 * @Description: 单位设置Action
 * @author 姜平豹
 * @date 2017-9-11 下午2:34:57
 * 
 */

public class DwAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201573729054469521L;

	@Resource(name = "dwService")
	private DwService dwService;// 单位service
	@Resource(name = "goodcatManageService")
	private GoodcatManageService goodcatManageService;

	private Jcsj_Dw jcsjdw;// 单位实体类
	private String prodata;// data对象
	private String projson;// projson对象
	private String id;// 获得id
	private String dwdh;// 单位代号
	private String json; // json对象

	// 跳转到单位列表页
	public String list() {
		return LIST;
	}

	// 跳转到单位编辑页
	public String edit() {
		if (id != null) {
			jcsjdw = (Jcsj_Dw) dwService.getOne(id);
		}
		return "add";
	}

	// 跳转到单位添加页
	public String add() {
		return "add";
	}

	// 跳转到单位删除页

	public String delete() throws Exception {
		String[] ids = id.split(",");
		// 判断单位类型是否有使用
		for (int i = 0; i < ids.length; i++) {
			jcsjdw = dwService.getOne(ids[i]);
			int num = goodcatManageService
					.isHave(pager, jcsjdw.getId(), "jcsj");
			if (num > 0) {
				return ajax(Status.error, jcsjdw.getDwdh() + "存在占用，删除失败！");
			}
		}
		for (int i = 0; i < ids.length; i++) {
			jcsjdw = dwService.getOne(ids[i]);
			jcsjdw.setIs_delete(Jcsj_Dw.DELETE);
			operate("物料单位管理", "删除", jcsjdw.getDwdh());
			dwService.update(jcsjdw);
		}
		return ajax(Status.success, "success");
	}

	// 获得单位列表内容
	public String lieb() {
		return ajax(Status.success, JsonUtil.Encode(getBeanListByCriteria(
				Jcsj_Dw.class, Restrictions.eq("is_delete", "0"))));
	}

	// 获得单位列表内容
	public String getdata() {
		if (pager.getSearchBy() != null) {
			pager.setSearchBy(pager.getSearchBy() + ",is_delete");
			pager.setKeyword(pager.getKeyword() + ",0");
		} else {
			pager.setSearchBy("is_delete");
			pager.setKeyword("0");
		}
		pager.setOrderBy("dwdh");
		pager.setOrder(Pager.Order.asc);
		return ajax(Status.success, JsonUtil.Encode(dwService.getDws(pager)));
	}

	/**
	 * 库存单位/采购单位/包装箱体积参数.长度单位/重量单位...访问单位设置列表（锁定的）
	 */
	public String getdataone() {
		return ajax(Status.success, JsonUtil.Encode(getBeanListByCriteria(
				Jcsj_Dw.class, Restrictions.eq("is_delete", "0"))));
	}

	/**
	 * 库存单位/采购单位/包装箱体积参数.长度单位/重量单位...访问单位设置列表（所有的）
	 */
	public String getdataones() {
		return ajax(
				Status.success,
				JsonUtil.Encode(dwService.getList(Jcsj_Dw.class,
						Restrictions.eq("is_delete", "0"))));
	}

	/**
	 * 
	 * @Title: findOne
	 * @return String 通过单位代号找出对应的数据
	 * @author 张丽丽
	 * @since 2014-4-18 V 1.0
	 */
	public String findOne() {
		String message = "success";
		jcsjdw = getBeanByCriteria(Jcsj_Dw.class,
				Restrictions.eq("dwdh", dwdh),
				Restrictions.eq("is_delete", "0"));
		if (message == "success") {
			return ajax(Status.success, message);
		} else {
			return ajax(Status.success, message);
		}
	}

	/**
	 * 保存更新功能
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author 张丽丽
	 * @since 2014-7-30 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			String flg = dwService.saveOrUpdate(prodata);
			if (flg.equals("success")) {
				return ajax(Status.success, "success");
			} else {
				return ajax(Status.error, flg);
			}
		} catch (Exception e) {
			return ajax(Status.error,
					e.toString().substring(e.toString().indexOf(":") + 1));
		}
	}

	/**
	 * 
	 * @Title: 返回给页面单位json，作为combobox的选项
	 * @return String
	 * @author 岳云鹏
	 * @since 2014-1-13 V 1.0
	 */
	public String dw() {
		return ajax(Status.success, dwService.dw());
	}

	// //////////////////////////////////////
	public Jcsj_Dw getJcsjdw() {
		return jcsjdw;
	}

	public void setJcsjdw(Jcsj_Dw jcsjdw) {
		this.jcsjdw = jcsjdw;
	}

	public String getProdata() {
		return prodata;
	}

	public void setProdata(String prodata) {
		this.prodata = prodata;
	}

	public String getProjson() {
		return projson;
	}

	public void setProjson(String projson) {
		this.projson = projson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
