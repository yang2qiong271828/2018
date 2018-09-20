package com.lingnet.vocs.action.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager.Order;
import com.lingnet.vocs.entity.Area;
import com.lingnet.vocs.entity.Dictionary;
import com.lingnet.vocs.entity.DictionaryD;
import com.lingnet.vocs.entity.Entity;
import com.lingnet.vocs.service.baseinfo.DataDictionaryDService;
import com.lingnet.vocs.service.baseinfo.DataDictionaryService;

/**
 * 数据字典
 * 
 * @ClassName: DataDictionaryAction
 * @Description: TODO
 * @author 薛硕
 * @date 2017年6月22日 下午4:06:44
 * 
 */
public class DataDictionaryAction extends BaseAction {

	private static final long serialVersionUID = -7002517383448613971L;

	@Resource(name = "dataDictionaryService")
	private DataDictionaryService dataDicService;

	@Resource(name = "dataDictionaryDService")
	private DataDictionaryDService dataDicDService;

	List<Dictionary> lists = new ArrayList<Dictionary>();
	List<DictionaryD> list = new ArrayList<DictionaryD>();
	private String id;// 删除时传过来的id
	private Area area;// 地区对象
	private String formdata;// 接受从jsp页面传过来的数据
	private String chid;// 将状态转为停用时传过来的id
	private String treeid;// 从编辑页面传过来给树页面的id

	Dictionary dictionary;

	private String name;// 目录名称
	private String code;// 编号
	private String parentId;// 上级目录id
	private int orderNumber;// 顺序号
	private String isDefault;// 默认
	private String isSystem;// 系统数据
	// private String abbName;//缩写
	private String description;// 描述
	private String parentName;// 上级目录名称
	private String isValidflg;// 是否停用(1是0否)
	private String flag;// 删除标志

	private String baseid;// 拖拽前父节点ID
	private String preId;// 拖拽后上一个兄弟节点的id
	private String dragid;// 拖拽后父节点id；
	private String dragname;// 拖拽后父节点name
	private String childrenIds;// 拖拽后同级节点ID
	private Dictionary dicy;// 数字字典对象
	private DictionaryD dicyD;// 字典明细对象
	private String json;// json字符串
	private String pid;// 添加时父级记录id

	private String key;// excel即时编辑传过来的key
	private String value;// excel即时编辑传过来的value
	protected int page;// 第几页
	protected int rows;// 每页数据量
	private List<Entity> listm = new ArrayList<Entity>();

	/** 跳转到list页面 */
	public String list() {
		return "list";
	}

	/** 跳转到添加页面 */
	public String add() {

		return "add";
	}

	/** 进入地区树页面 */
	public String tree() {
		return "tree";

	}

	/** 进入添加字典明细页面 */
	public String addfunction() {
		return "function";
	}

	/**
	 * 保存或修改数据
	 * 
	 * @Title: saveorupdate
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String saveorupdate() {

		if (id != null && !id.equals("") && !id.equals("null")) {

			dicy = getBeanById(Dictionary.class, id);

			if (getBeanByCriteria(Dictionary.class,
					eq("name", dictionary.getName()), ne("id", id)) != null) {
				return ajax(Status.error, "名称已经存在请重新操作！");
			}
			if (getBeanByCriteria(Dictionary.class,
					eq("code", dictionary.getCode()), ne("id", id)) != null) {
				return ajax(Status.error, "编号已经存在请重新操作！");
			}

			if (dicy != null) {
				dicy.setName(dictionary.getName());
				dicy.setOrderNumber(dictionary.getOrderNumber());
				// dicy.setAbbName(dictionary.getAbbName());
				dicy.setDescription(dictionary.getDescription());
				// dicy.setParentName(dictionary.getParentName());
				dicy.setIsValidflg(dictionary.getIsValidflg().split(",")[0]);
			}

			dataDicService.update(dicy);

			return ajax(Status.success, "保存成功");

		} else {
			if (getBeanByCriteria(Dictionary.class,
					eq("name", dictionary.getName())) != null) {
				return ajax(Status.error, "名称已经存在请重新操作！");
			}
			if (getBeanByCriteria(Dictionary.class,
					eq("code", dictionary.getCode())) != null) {
				return ajax(Status.error, "编号已经存在请重新操作！");
			}
			Dictionary dicton = new Dictionary();
			dicton.setName(dictionary.getName());
			dicton.setCode(dictionary.getCode());
			dicton.setParentId(dictionary.getParentId());
			dicton.setOrderNumber(dictionary.getOrderNumber());
			// dicton.setAbbName(dictionary.getAbbName());
			dicton.setDescription(dictionary.getDescription());
			// dicton.setParentName(dictionary.getParentName());
			dicton.setIsValidflg(dictionary.getIsValidflg().split(",")[0]);

			dataDicService.save(dicton);

			return ajax(Status.success, "保存成功");

		}
	}

	/**
	 * 查询所有数据
	 * 
	 * @Title: findAll
	 * @return
	 * @throws Exception
	 *             String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findAll() throws Exception {

		lists = dataDicService.getAllOrderBySort();
		List<HashMap> mapList = new ArrayList<HashMap>();
		// 遍历集合
		for (Dictionary dicy : lists) {
			HashMap map = new HashMap();
			map.put("id", dicy.getId());
			// 字典根节点id初始化值是1
			if (dicy.getId().equals("1")) {
				// 设置根节点打开状态
				map.put("open", true);
			}
			map.put("name", dicy.getName());// 名称
			map.put("code", dicy.getCode());// 编号
			map.put("description", dicy.getDescription());// 描述
			map.put("ordernumber", dicy.getOrderNumber());// 顺序号
			// map.put("isdefault", dicy.getIsDefault());//默认
			// map.put("abbname", dicy.getAbbName());//缩写
			map.put("validflg", dicy.getIsValidflg());// 有效
			// map.put("system", dicy.getIsSystem());//系统
			// 根节点的父节点id初始值是ROOT
			if (dicy.getParentId() != null && dicy.getParentId().equals("ROOT")) {
				// 设置前端根节点父节点id是-1
				map.put("pId", "-1");
				// 设置前端根节点父节点name是ROOT
				map.put("pName", "ROOT");
				// drag，树节点是否可以拖拽
				map.put("drag", false);
				// 根节点打开状态
				map.put("open", true);

			} else {
				map.put("pId", dicy.getParentId());
				map.put("pName",
						dataDicService
								.get(Dictionary.class, dicy.getParentId())
								.getName());
				// 拖拽 暂时没用到
				map.put("drag", true);
			}
			// 启用状态 前端暂时屏蔽到了该功能
			if (dicy.getIsValidflg() != null
					&& dicy.getIsValidflg().equals("0")) {
				map.put("stopflag", 0);
			} else {
				map.put("stopflag", 1);
			}

			mapList.add(map);
		}
		HashMap result = new HashMap();
		result.put("data", mapList);
		result.put("total", pager.getTotalCount());
		String json = JsonUtil.Encode(mapList);
		return ajax(Status.success, json);
	}

	/**
	 * 跳转到字典明细tab页面
	 * 
	 * @Title: getDetail
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getDetail() {
		return "detail";
	}

	/**
	 * 查询字典明细数据 获取列表
	 * 
	 * @Title: getDetailList
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getDetailList() {

		String result = "";
		try {
			pager.setPageNumber(page);
			pager.setPageSize(rows);
			pager.setOrderBy("orderNumber");
			pager.setOrder(Order.asc);
			result = dataDicDService.getAllOrderBySort(pager, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(result);
	}

	/**
	 * execl表格即时编辑保存
	 * 
	 * @Title: editSave
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String editSave() {
		// id不是空的情况，代表是用来行编辑的
		if (id != null && !id.equals("")) {
			// 编辑状态 获取实体
			dicyD = dataDicDService.get(DictionaryD.class, id);
			// 判断名称重复
			if (getBeanByCriteria(DictionaryD.class, eq("name", name),
					ne("id", id), eq("code", code)) != null) {

				return ajax(Status.error, "保存失败！名称重复。");
			}
			// 同一个明细表中最多有一个是默认的 默认值为1时将其他默认值设置为0
			if (isDefault.equals("1")) {
				dicyD.setIsDefault(isDefault);
				// 将其他明细默认值改成0
				dataDicDService.setOtherDefault(dicyD);
			} else {
				dicyD.setIsDefault(isDefault);
			}
			dicyD.setName(name);
			dicyD.setDescription(description);
			dicyD.setOrderNumber(dicyD.getOrderNumber());
			dicyD.setIsValidflg(isValidflg);
			dicyD.setIsSystem(isSystem);
			dicyD.setValue(value);
			// 更新
			dataDicDService.update(dicyD);
			return ajax(Status.success, "保存成功！");
			// id为空时，代表是弹框添加
		} else {
			// 根据父级id获取父级实体
			Dictionary dictionay = getBeanById(Dictionary.class,
					dicyD.getParentId());
			if (dictionay != null) {
				code = dictionay.getCode();
				dicyD.setCode(code);
			} else {
				return ajax(Status.error, "保存失败！父级字典不存在。");
			}
			if (getBeanByCriteria(DictionaryD.class,
					eq("name", dicyD.getName()), eq("code", code)) != null) {
				return ajax(Status.error, "保存失败！名称重复。");
			}
			if ("1".equals(dicyD.getIsDefault())) {
				// 同一个明细表中最多有一个是默认的 默认值为1时将其他默认值设置为0
				dataDicDService.setOtherDefault(dicyD);
			}
			// 添加新纪录
			dataDicDService.save(dicyD);
			return ajax(Status.success, "success");
		}
	}

	/**
	 * 删除明细
	 * 
	 * @Title: remove
	 * @return String
	 * @author 薛硕
	 * @since 2017年8月3日 V 1.0
	 */
	public String remove() {
		if (id != null && id.length() > 0) {
			ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				this.deleteByCriteria(DictionaryD.class,
						Restrictions.eq("id", ids[i]));
			}
		}
		return ajax(Status.success, "删除成功！");
	}

	/** 删除dictionary */
	public String delete() {
		if (getBeanListByCriteria(DictionaryD.class, eq("parentId", id)).size() > 0) {
			return ajax(Status.error, "删除失败！请先删除字典明细。");
		}
		dataDicService.delete(id);
		return ajax(Status.success, "删除成功！");
	}

	/**
	 * 传入code返回字典明细集合
	 * 
	 * @Title: getListByCode
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	public String getListByCode() {
		ArrayList<DictionaryD> dList = dataDicDService
				.getListByCode(code, true);
		return ajax(Status.success, JsonUtil.Encode(dList));
	}

	/**
	 * 根据code字符串，获取所有对应的数据字典
	 * 
	 * @Title: getAllByCodes
	 * @return String
	 * @author 薛硕
	 * @since 2017年6月22日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getAllByCodes() {
		String[] codes = code.split(",");
		Map dMap = dataDicDService.getAllByCodes(codes, true);
		return ajax(Status.success, JsonUtil.Encode(dMap));
	}

	// ************************************************************
	public DataDictionaryService getdataDicService() {
		return dataDicService;
	}

	public void setdataDicService(DataDictionaryService dataDicService) {
		this.dataDicService = dataDicService;
	}

	public List<Dictionary> getLists() {
		return lists;
	}

	public void setLists(List<Dictionary> lists) {
		this.lists = lists;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getChid() {
		return chid;
	}

	public void setChid(String chid) {
		this.chid = chid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseid() {
		return baseid;
	}

	public void setBaseid(String baseid) {
		this.baseid = baseid;
	}

	public String getDragid() {
		return dragid;
	}

	public void setDragid(String dragid) {
		this.dragid = dragid;
	}

	public String getChildrenIds() {
		return childrenIds;
	}

	public void setChildrenIds(String childrenIds) {
		this.childrenIds = childrenIds;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public String getDragname() {
		return dragname;
	}

	public void setDragname(String dragname) {
		this.dragname = dragname;
	}

	public DataDictionaryService getDataDicService() {
		return dataDicService;
	}

	public void setDataDicService(DataDictionaryService dataDicService) {
		this.dataDicService = dataDicService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	// public String getAbbName() {
	// return abbName;
	// }
	//
	// public void setAbbName(String abbName) {
	// this.abbName = abbName;
	// }

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIsValidflg() {
		return isValidflg;
	}

	public void setIsValidflg(String isValidflg) {
		this.isValidflg = isValidflg;
	}

	public Dictionary getDicy() {
		return dicy;
	}

	public void setDicy(Dictionary dicy) {
		this.dicy = dicy;
	}

	public DataDictionaryDService getDataDicDService() {
		return dataDicDService;
	}

	public void setDataDicDService(DataDictionaryDService dataDicDService) {
		this.dataDicDService = dataDicDService;
	}

	public DictionaryD getDicyD() {
		return dicyD;
	}

	public void setDicyD(DictionaryD dicyD) {
		this.dicyD = dicyD;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public List<DictionaryD> getList() {
		return list;
	}

	public void setList(List<DictionaryD> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Entity> getListm() {
		return listm;
	}

	public void setListm(List<Entity> listm) {
		this.listm = listm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
