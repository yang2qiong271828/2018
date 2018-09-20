package com.lingnet.vocs.action.standard;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.vocs.entity.StandardGas;
import com.lingnet.vocs.service.standard.StandardGasService;

/**
 * 
 * @ClassName: StandardGasAction
 * @Description: 监测标准气体配置Action
 * @author duanjj
 * @date 2017年6月6日 下午2:04:00
 * 
 */
@Controller
@Namespace("/standard")
public class StandardGasAction extends BaseAction {

	/**
     * 
     */
	private static final long serialVersionUID = -4834538210347298116L;
	private StandardGas standardGas;
	@Resource(name = "standardGasService")
	private StandardGasService standardGasService;

	/**
	 * 跳转到list页面
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 跳转到ADD页面
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 增加保存方法
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author duanjj
	 * @since 2017年6月6日 V 1.0
	 */
	public String saveOrUpdate() {
		try {
			String msg = standardGasService.saveOrUpdate(standardGas);
			return ajax(Status.success, msg);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, e.getMessage());
		}
	}

	public StandardGas getStandardGas() {
		return standardGas;
	}

	public void setStandardGas(StandardGas standardGas) {
		this.standardGas = standardGas;
	}
}
