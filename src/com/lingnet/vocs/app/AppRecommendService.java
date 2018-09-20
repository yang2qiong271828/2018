/**
 * @AppRecommendService.java
 * @com.lingnet.mec.service.app
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2017
 * @version V
 * @since 2017年8月2日
 */
package com.lingnet.vocs.service.app;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.AppRecommend;

/** 
 * @ClassName: AppRecommendService 
 * @Description: AppRecommend
 * @author xuhp
 * @date 2017年8月2日 上午8:47:48 
 *  
 */

public interface AppRecommendService extends BaseService<AppRecommend, String> {
	/**
	 * 
	 * @Title: chengestate 
	 * @param ids
	 * @param state
	 * @return 
	 * String 
	 * @author xuhp
	 * @since 2017年8月2日 V 1.0
	 */
	String chengestate(String ids,int state);
	/**
	 * 
	 * @Title: saveOrUpdate 
	 * @param jsondata
	 * @return 
	 * String 
	 * @author xuhp
	 * @since 2017年8月2日 V 1.0
	 */
	String saveOrUpdate(String jsondata);
}
