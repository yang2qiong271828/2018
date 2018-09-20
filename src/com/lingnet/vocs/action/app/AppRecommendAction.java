/**
 * @AppRecommendAction.java
 * @com.lingnet.mec.action.app
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2017
 * @version V
 * @since 2017年8月2日
 */
package com.lingnet.vocs.action.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.AppRecommend;
import com.lingnet.vocs.service.app.AppRecommendService;

/** 
 * @ClassName: AppRecommendAction 
 * @Description: 推荐配置
 * @author xuhp
 * @date 2017年8月2日 上午8:42:28 
 *  
 */

public class AppRecommendAction extends BaseAction {
	private static final long serialVersionUID = -7999996038370500831L;
	@Resource(name="appRecommendService")
	private AppRecommendService appRecommendService;
	private AppRecommend appRecommend;
	private String data;
	private List<AppRecommend> list;
	public String list(){
        return "list";
    }
	
	public String add(){
        return "add";
    }
	
	public String edit(){
		appRecommend=getBeanByCriteria(AppRecommend.class, Restrictions.eq("id",id));
		return "add";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getListData(){
		pager=appRecommendService.findPager(AppRecommend.class, pager
				, Order.desc("state"),Order.asc("loc"),Order.asc("sort")
				);
		Map result = new HashMap();
		result.put("data", pager.getResult());
        result.put("total", pager.getTotalCount());
        return ajax(Status.success,JsonUtil.Encode(result));
	}
	
	public String enable(){
		return ajax(Status.success,appRecommendService.chengestate(id,1)); 
	}
	
	public String disable(){
		return ajax(Status.success,appRecommendService.chengestate(id,0)); 
	}
	
	public String saveOrUpdate(){
        return ajax(Status.success,appRecommendService.saveOrUpdate(data));
    }
	
	public String remove(){
		if(StringUtils.isEmpty(id)){
			return ajax(Status.success,"请选中一条或多条记录！");
		}
		list =  getBeanListByCriteria(AppRecommend.class, Restrictions.in("id", id.split(",")));
		for(AppRecommend app :list){
			if(app.getState() == 0){
				appRecommendService.deleteByCriteria(AppRecommend.class, Restrictions.in("id", id.split(",")));
			}else{
				List<AppRecommend> ab = getBeanListByCriteria(AppRecommend.class, Restrictions.eq("state", app.getState()),Restrictions.ne("id", app.getId()));
				if(ab.size() != 4){
					return ajax(Status.success,"需有四条启用状态的数据,请改为停用状态后删除!");
				}
			}
		}
		
		
        return ajax(Status.success,"删除成功");
    }
	
	/*public String getListDataPicture(){
		pager=appRecommendService.findPager(AppRecommend.class, pager
				, Order.desc("state"),Order.asc("loc"),Order.asc("sort")
				);
		List<AppRecommend> app = (List<AppRecommend>) pager.getResult();
		for(AppRecommend dd :app){
			
		}
	}*/
	///////////////////////////////
	public AppRecommend getAppRecommend() {
		return appRecommend;
	}

	public void setAppRecommend(AppRecommend appRecommend) {
		this.appRecommend = appRecommend;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<AppRecommend> getList() {
		return list;
	}

	public void setList(List<AppRecommend> list) {
		this.list = list;
	}
	
}
