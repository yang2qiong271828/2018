package com.lingnet.vocs.action.workorder;

import java.util.HashMap;

import com.lingnet.common.action.BaseAction;

public class MaterielOutlayAction extends BaseAction {

	private static final long serialVersionUID = -8646430481288770180L;

	private HashMap<String, String> materiel;

	public String list() {

		return LIST;
	}

	public String getListData() {
		String json = "";
		json += "[{'name':'用料A','code':'YL001','num':'10kg','numprice':'10 RMB','price':'100 RMB','mc':'类别A'}"
				+ " ,{'name':'用料B','code':'YL002','num':'10kg','numprice':'10 RMB','price':'100 RMB','mc':'类别B'}"
				+ "]";
		return ajax(Status.success, json);
	}

	public String add() {

		return ADD;
	}

	public String remove() {

		return ajax(Status.success, "success");
	}

	public String edit() {
		materiel = new HashMap<String, String>();
		materiel.put("name", "用料A");
		materiel.put("code", "YL001");
		materiel.put("num", "10kg");
		materiel.put("numprice", "10");
		materiel.put("price", "100");
		materiel.put("mc", "类别A");
		return ADD;
	}

	public String look() {
		materiel = new HashMap<String, String>();
		materiel.put("name", "用料A");
		materiel.put("code", "YL001");
		materiel.put("num", "10kg");
		materiel.put("numprice", "10");
		materiel.put("price", "100");
		materiel.put("mc", "类别A");
		return ADD;
	}

	/******************************************* get set ********************************************/
	public HashMap<String, String> getMateriel() {
		return materiel;
	}

	public void setMateriel(HashMap<String, String> materiel) {
		this.materiel = materiel;
	}

}
