package com.lingnet.vocs.dao.impl.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.baseinfo.AreaDao;
import com.lingnet.vocs.entity.Area;

/**
 * 
 * @ClassName: AreaDaoImpl
 * @Description: 地区
 * @author adam
 * @date 2016-2-5 下午2:36:25
 *
 */
@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImplInit<Area, String> implements
        AreaDao {

    /** 返回该员工的负责管辖地区map<地区全称，Id> */
    @SuppressWarnings("unchecked")
    public HashMap<String, String> getAllFullname(String userId) {
        String selectSql = "select a.id , a.fullname from Area a";
        Query query = this.getSession().createQuery(selectSql);
        List<Object[]> list = query.list();
        HashMap<String, String> result = new HashMap<String, String>();
        for (Object[] os : list) {
            String id = (String) os[0];
            String fullname = (String) os[1];
            result.put(fullname, id);
        }
        return result;
    }

    public ArrayList<HashMap<String, String>> getChildrenArea(String pid) {
        SQLQuery query = this.getSession().createSQLQuery(
                "select * from b_area where pid ='" + pid + "' order by id asc");
        List<Object[]> list = query.list();
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
        for (Object[] os : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", (String) os[0]);
            map.put("name", (String) os[1]);
            map.put("pid", (String) os[2]);
            maplist.add(map);
        }
        return maplist;
    }
    
    @Override
    public List<String[]> getChildrenList(String pid) {
        
        SQLQuery query = this.getSession().createSQLQuery(
                "select id from b_area where pid ='" + pid + "'");
        List<String[]> list = query.list();
       
        return list;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> getAreaListLazy(String pid) {

		ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
		SQLQuery query = getSession().createSQLQuery("select * from b_area where pid ='" + pid + "' order by id asc").addEntity(Area.class);
		
		List<Area> list = query.list();
		Area pArea;
		if ("-1".equals(pid)) {
			pArea = get("0");
			HashMap map = new HashMap();
			map.put("id", pArea.getId());
			map.put("name", pArea.getName());
			map.put("pId", "");
			map.put("pName", "ROOT");
			map.put("areacode", pArea.getAreacode());
			map.put("areadesp", pArea.getAreadesp());
			map.put("stopflag", pArea.getStopflag());
			if (pArea.getLevel() != 2) {
				map.put("isLeaf", false);
				map.put("expanded", false);
			} else {
				map.put("isLeaf", true);
			}
			maplist.add(map);
		} else {
			pArea = get(pid);
			for (Area area : list) {
				HashMap map = new HashMap();
				map.put("id", area.getId());
				map.put("name", area.getName());
				map.put("pId", area.getPid());
				map.put("pName", pArea.getName());
				map.put("areacode", area.getAreacode());
				map.put("areadesp", area.getAreadesp());
				map.put("stopflag", area.getStopflag());
				if (area.getLevel() != 2) {
					map.put("isLeaf", false);
					map.put("expanded", false);
				} else {
					map.put("isLeaf", true);
				}
				maplist.add(map);
			}
		}
		return maplist;

	}

	@Override
	public List getCountOrder() {
		StringBuilder sql= new StringBuilder();
		sql.append("      SELECT b.id,b.NAME from B_AREA b  left JOIN sewage e on b.ID=e.province where LEVELS='0' GROUP BY b.NAME,b.id order by count(b.name) DESC  ");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}
}