package com.lingnet.vocs.action.jcsj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Goodcat;
import com.lingnet.vocs.service.jcsj.GoodcatManageService;

/*
 * GoodcatManageAction
 * 摘要说明.
 * @Description:
 *  :
 * @author 刘青勇
 * @version 2017-9-12 
 */
@Controller
@ParentPackage("jcsj")
public class GoodcatManageAction extends BaseAction {

	private static final long serialVersionUID = 3774707022962529455L;

	@Resource(name = "goodcatManageService")
	private GoodcatManageService goodcatManageService;

	private String goodcatcode; // 商品分类代号
	private String goodcatname; // 商品分类名称
	private String codeheader; // 编码前缀
	private String connector; // 连接符
	private String accountsize; // 流水号长度
	private String accountnum; // 当前流水号
	private String codefooter; // 编码后缀
	private Goodcat goodcat; // 商品种类对象
	private String child; // 子节点
	private String parent; // 父级商品种类

	// 导入
	private File uploadFile;
	private String uploadFileFileName;
	private String fromLine;
	private String toLine;
	private String pgoodcatcode1;

	/**
	 * 跳转到商品类型显示页面
	 */
	public String list() {

		return LIST;
	}

	/**
	 * 跳转到商品类型新增页面
	 */
	public String add() {
		goodcat = goodcatManageService.get(Goodcat.class, id);// 获取选中的商品种类

		if (goodcat == null) {
			return ajax(Status.error, "选中的节点不存在，请刷新后重新操作！");
		}
		String parent = goodcat.getGoodcatcode();// 获取其id作为新记录的父级代号
		goodcat = new Goodcat();// 生成子节点
		goodcat.setPgoodcatcode(parent);// 设置新节点的父级代号

		return ADDORUPDATE;
	}

	/**
	 * 跳转到商品类型修改页面
	 */
	public String edit() {
		if (id != null) {
			goodcat = goodcatManageService.load(Goodcat.class, id);
			if (goodcat == null) {
				return ajax(Status.error, "选中的节点不存在，请刷新后重新操作！");
			}
		}
		return ADDORUPDATE;
	}

	/**
	 * 跳转到商品类型tree页面(不能选中父节点)
	 */
	public String tree() {
		return "tree";
	}

	/**
	 * 跳转到商品类型tree页面(可以选中父节点)
	 */
	public String tree1() {
		return "tree1";
	}

	/**
	 * 跳转到商品类型tree页面(商品种类必须选择最底层【无下级子种类】)
	 */
	public String tree2() {
		return "tree2";
	}

	/**
	 * 获取连接符数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getConnectors() {
		// 连接符：1：-
		ArrayList resultList = new ArrayList();
		HashMap result = new HashMap();
		result.put("id", "1");
		result.put("name", "-");
		resultList.add(result);
		return ajax(Status.success, JsonUtil.Encode(resultList));
	}

	/**
	 * 返回商品类型结构树信息
	 */
	public String getdata() {
		return ajax(Status.success,
				JsonUtil.Encode(goodcatManageService.getTreeList()));
	}

	/**
	 * 返回商品类型结构树信息
	 */
	public String treedata() {
		return ajax(Status.success,
				JsonUtil.Encode(goodcatManageService.getTreeData()));
	}

	/**
	 * 返回商品类型结构树信息
	 */
	public String treedata1() {
		return ajax(Status.success,
				JsonUtil.Encode(goodcatManageService.getTreeData1()));
	}

	/**
	 * 保存更新数据 整理代码将实现方法放到impl中 栾胜鹏 2014-10-16
	 */
	public String saveOrUpdate() throws Exception {
		try {
			return ajax(Status.success, goodcatManageService.saveOrUpdate(id,
					goodcatcode, goodcatname, codeheader, connector, parent,
					child));
		} catch (Exception e) {
			return ajax(Status.error,
					e.toString().substring(e.toString().indexOf(":") + 1));
		}
	}

	/**
	 * 删除商品类型信息
	 * 
	 * @throws Exception
	 */
	public String delete() throws Exception {

		// 循环删除商品类型信息
		String[] ids = id.split(",");
		// 判断商品类型是否有使用
		for (int i = 0; i < ids.length; i++) {
			goodcat = goodcatManageService.load(Goodcat.class, ids[i]);

			int num = goodcatManageService.isHave(pager,
					goodcat.getGoodcatcode(), "goodcat");
			if (num > 0) {
				return ajax(Status.error, goodcat.getGoodcatcode()
						+ "  存在占用，删除失败！");
			}
		}

		for (int i = 0; i < ids.length; i++) {
			goodcat = goodcatManageService.load(Goodcat.class, ids[i]);
			goodcat.setIs_delete(Goodcat.DELETE);
			goodcatManageService.update(goodcat);
			operate("物料种类管理", "删除", goodcat.getGoodcatcode());// 记录日志
		}
		return ajax(Status.success, "success");
	}

	// 上传页面
	public String upload() {

		return "upload";
	}

	@SuppressWarnings("unused")
	public List<String[]> readData(String fromLine, String toLine,
			File uploadFile) throws Exception {

		// 创建对Excel工作簿文件的引用
		// 项目下面的路径
		// 根据文件后缀判断是xls还是xlsxs
		// try {

		Workbook wookbook = this
				.createWorkBook(new FileInputStream(uploadFile));
		// 在Excel文档中，第一张工作表的缺省索引是0
		Sheet sheet = wookbook.getSheet("Sheet1");
		if (sheet == null) {
			throw new Exception("上传模板不正确！");
		}
		// 获取到Excel文件中的所有行数
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows == 1) {
			throw new Exception("上传模板中内容不能为空！");
		}
		// 得到起始行和结束行
		int start = Integer.parseInt(fromLine) - 1;
		int end = Integer.parseInt(toLine) - 1;
		List<String[]> list = new ArrayList<String[]>();
		// 遍历行
		if (start > end) {
			throw new Exception("起始行数不能大于结束行数！");
		}
		String[] allItemCode = new String[end];
		String[] allItemCode1 = new String[end];
		String[] allItemCode2 = new String[end];
		String[] allItemCode3 = new String[end];
		if (end <= rows - 1) {

			for (int i = start; i <= end; i++) {
				// 读取左上端单元格
				Row row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// 获取到Excel文件中的所有的列
					int cells = row.getLastCellNum();
					if (cells != 3 && cells != 4 && cells != 5) {
						throw new Exception("第" + (i + 1) + "行列不符合导入数据要求！");
					}
					String value = "";
					// 遍历列
					String[] values = new String[cells];
					for (int j = 0; j < cells; j++) {
						// 获取到列的值
						Cell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										value = new SimpleDateFormat(
												"yyyy-MM-dd").format(date);
									} else {
										value = "";
									}
								} else {
									value = new DecimalFormat("0.####")
											.format(cell.getNumericCellValue());
								}
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							default:
								value = "";
								break;
							}
						} else {
							value = "";
						}
						// 把列放入数组
						values[j] = value;
					}
					// begain add zrl 2016-7-4 同一批次导入时去重（同一次从excel表中导入的去重）

					/*
					 * String s2=values[0]+","+values[1]+","+values[2];
					 * allItemCode[i-1] = s2; for(int n = 0 ; n < i-1 ; n++){
					 * if(s2.equals(allItemCode[n])){ throw new
					 * Exception("第"+(i+1)+"行商品种类代号，商品种类名称，编码前缀全部重复，导入失败！"); } }
					 */

					// end add zrl 2016-7-4 同一批次导入时去重（同一次从excel表中导入的去重）
					// begain add mzm 2016-7-19 同一批次导入时单列去重判断（同一次从excel表中导入的去重）
					String catcode = values[0];
					allItemCode1[i - 1] = catcode;

					for (int n = 0; n < i - 1; n++) {
						if (catcode.equals(allItemCode1[n])) {
							throw new Exception("第" + (i + 1)
									+ "行商品代号与之前有重复，导入失败！");
						}
					}
					String catname = values[1];
					allItemCode2[i - 1] = catname;

					for (int n = 0; n < i - 1; n++) {
						if (catname.equals(allItemCode2[n])) {
							throw new Exception("第" + (i + 1)
									+ "行商品种类名称与之前有重复，导入失败！");
						}
					}
					String codeheader = values[2];
					allItemCode3[i - 1] = codeheader;
					for (int n = 0; n < i - 1; n++) {
						if (codeheader.equals(allItemCode3[n])) {
							throw new Exception("第" + (i + 1)
									+ "行编码前缀重复与之前有重复，导入失败！");
						}
					}
					// end add mzm 2016-7-19 同一批次导入时去重单列去重判断（同一次从excel表中导入的去重）
					String content = values[2];
					String regex = "^[a-zA-Z0-9]+$";
					Pattern pattern = Pattern.compile(regex);
					Matcher match = pattern.matcher(content);
					boolean b = match.matches();
					if (!b) {
						throw new Exception("编码前缀必须为字母或数字！");
					}
					// end add 2016/07/04 zrl 筛选编码前缀为字母或者数字
					// 把行数据放入list
					list.add(values);
				}
			}
		} else {
			throw new Exception("结束行数大于最大行数！");
		}
		return list;
		// }
		// catch (Exception e) {
		// throw new Exception("上传格式不正确！");
		// }
	}

	/**
	 * 批量导入数据保存
	 */
	public String beachSave() {
		String message = "";
		Boolean bool = true;
		// 判断数据是否有效
		if (fromLine == null || "".equals(fromLine)) {
			bool = false;
			message = "请输入合法的起始行数！";
		}
		if (toLine == null || "".equals(toLine)) {
			bool = false;
			message = "请输入合法的结束行数！";
		}
		if (uploadFile == null || "".equals(uploadFile)) {
			bool = false;
			message = "请选择上传文件！";
		} else {
			if (!uploadFileFileName.toLowerCase().endsWith("xls")
					&& !uploadFileFileName.toLowerCase().endsWith("xlsx")) {
				bool = false;
				message = "请选择正确的文件类型，必须是以.xls或.xlsx结尾！";
			}
		}
		if (bool) {
			try {
				// 读取数据
				List<String[]> list = this.readData(fromLine, toLine,
						uploadFile);
				// 数据读取完成后将数据提交给service处理并保存
				message = goodcatManageService
						.beachSave(fromLine, toLine, list);
				if (message.equals("success")) {
					operate("产品类别", "批量上传", "批量上传产品类别");
				}
			} catch (Exception e) {
				// message="上传数据格式不符合要求";
				message = e.toString().substring(e.toString().indexOf(":") + 1);
				e.printStackTrace();
			}
		}

		this.getRequest().getSession().setAttribute("message", message);

		return "upload";
	}

	/**
	 * 判断是xls文件还是xlsx文件
	 */
	private Workbook createWorkBook(FileInputStream is) throws IOException {

		// 需要在盖方法中判断是xls文件还是xlsx文件
		if (uploadFileFileName.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(is);
		}
		if (uploadFileFileName.toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(is);
		}
		return null;
	}

	// ////////////////////////////////////////////////////////////////
	public String getGoodcatcode() {
		return goodcatcode;
	}

	public void setGoodcatcode(String goodcatcode) {
		this.goodcatcode = goodcatcode;
	}

	public String getGoodcatname() {
		return goodcatname;
	}

	public void setGoodcatname(String goodcatname) {
		this.goodcatname = goodcatname;
	}

	public String getCodeheader() {
		return codeheader;
	}

	public void setCodeheader(String codeheader) {
		this.codeheader = codeheader;
	}

	public String getAccountsize() {
		return accountsize;
	}

	public void setAccountsize(String accountsize) {
		this.accountsize = accountsize;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	public String getCodefooter() {
		return codefooter;
	}

	public void setCodefooter(String codefooter) {
		this.codefooter = codefooter;
	}

	public Goodcat getGoodcat() {
		return goodcat;
	}

	public void setGoodcat(Goodcat goodcat) {
		this.goodcat = goodcat;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getFromLine() {
		return fromLine;
	}

	public void setFromLine(String fromLine) {
		this.fromLine = fromLine;
	}

	public String getToLine() {
		return toLine;
	}

	public void setToLine(String toLine) {
		this.toLine = toLine;
	}

	public String getPgoodcatcode1() {
		return pgoodcatcode1;
	}

	public void setPgoodcatcode1(String pgoodcatcode1) {
		this.pgoodcatcode1 = pgoodcatcode1;
	}

}
