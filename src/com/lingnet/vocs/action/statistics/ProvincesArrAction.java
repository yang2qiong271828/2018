package com.lingnet.vocs.action.statistics;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 空气处理量统计
 * 
 * @ClassName: ProvincesArrAction
 * @Description: TODO
 * @author xues
 * @date 2017年6月13日 上午8:12:33
 * 
 */
public class ProvincesArrAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 获取所有中国 省份及一级城市
	 * */
	public String weather() {
		// TODO Auto-generated method stub
		String ws_url = "http://m.weather.com.cn/data5/city.xml";
		String str = "";
		try {
			URL url = new URL(ws_url);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "utf-8"));// 解决乱码问题
			StringBuffer sb = new StringBuffer();
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n"); // 将内容读取到StringBuffer中
			}
			br.close();
			// System.out.println(sb.toString()); 屏幕
			str = new String(sb.toString().getBytes());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 根据传入参数获取二级城市
	 * */
	public String secondCity(String id) {
		String ws_url = "http://m.weather.com.cn/data5/city" + id + ".xml";
		String str = "";
		try {
			URL url = new URL(ws_url);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "utf-8"));// 解决乱码问题
			StringBuffer sb = new StringBuffer();
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n"); // 将内容读取到StringBuffer中
			}
			br.close();
			// System.out.println(sb.toString()); 屏幕
			str = new String(sb.toString().getBytes());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public String charts() {

		return "charts";
	}

	@SuppressWarnings("all")
	public String getProvincesTreeData() {
		ArrayList arrlist = new ArrayList();
		String[] strArray = weather().split(",");
		for (int i = 0; i < 10; i++) {
			String[] strArr = strArray[i].split("\\|");
			HashMap map = new HashMap();
			map.put("id", strArr[0]);
			map.put("text", strArr[1]);
			arrlist.add(map);
			//
			String[] strArray2 = secondCity(strArr[0]).split(",");
			for (int j = 0; j < strArray2.length; j++) {
				String[] strArr2 = strArray2[j].split("\\|");
				HashMap map1 = new HashMap();
				map1.put("id", strArr2[0]);
				map1.put("text", strArr2[1]);
				map1.put("pid", strArr[0]);
				arrlist.add(map1);
			}
		}
		String json = JsonUtil.Encode(arrlist);
		return ajax(Status.success, json);
	}

	public String getChartHandlingData() {
		String jsonString = "";
		jsonString += "[{ \"id\":\"1\", \"company\":\"CO2\", \"supplier\":\"15\", \"chartdate\":\"17-06-01\" }"
				+ " ,{ \"id\":\"2\", \"company\":\"CO\", \"supplier\":\"20\", \"chartdate\":\"17-06-02\" }"
				+ " ,{ \"id\":\"2\",  \"company\":\"SO2\", \"supplier\":\"20\", \"chartdate\":\"17-06-02\" }"
				+ " ,{ \"id\":\"2\",  \"company\":\"PM10\", \"supplier\":\"30\", \"chartdate\":\"17-06-02\" }"
				+ " ,{ \"id\":\"2\",  \"company\":\"O3\", \"supplier\":\"10\", \"chartdate\":\"17-06-02\" }"
				+ " ,{ \"id\":\"2\",  \"company\":\"NO2\", \"supplier\":\"25\", \"chartdate\":\"17-06-02\" }"
				+ "]";
		return ajax(Status.success, JsonUtil.Encode(jsonString));
	}

}
