/**
 * @ContactsDaoImpl.java
 * @com.lingnet.vocs.dao.impl.partner
 * @Description：
 * 
 * @author zhangyu 
 * @copyright  2017
 * @version V
 * @since 2017年6月19日
 */

package com.lingnet.vocs.dao.impl.partner;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.partner.ContactsDao;
import com.lingnet.vocs.dao.partner.ContactsReportDao;
import com.lingnet.vocs.entity.Contacts;
import com.lingnet.vocs.entity.ContactsReport;

/**
 * 
 * @ClassName: ContactsReportDaoImpl 
 * @Description: TODO 
 * @author yic
 * @date 2018年1月22日 下午4:27:18 
 *
 */
@Repository("contactsReportDao")
public class ContactsReportDaoImpl extends BaseDaoImplInit<ContactsReport, String>
        implements ContactsReportDao {

}
