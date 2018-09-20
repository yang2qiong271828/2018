package com.lingnet.vocs.action.monit;

import com.lingnet.common.action.BaseAction;

/**
 * 指标对比管理
 * 
 * @ClassName: NormContrastAction
 * @Description: TODO
 * @author xues
 * @date 2017年6月6日 下午2:24:26
 * 
 */
public class NormContrastAction extends BaseAction {

	private static final long serialVersionUID = 3501004065616902406L;

	// 对比报警
	public void normContrast() {
		int now = getNowChartData();
		int norm = getNormData();
		if (now > norm) {
			System.out.println("报警");
		}
	}

	// 返回实时数据
	public int getNowChartData() {
		java.util.Random r = new java.util.Random();
		int result = r.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
		// String jsonString ="[{\"data1\":\""+result+"\"}]";
		return result;
	}

	// 返回标准值
	public int getNormData() {

		return 8;
	}

}
