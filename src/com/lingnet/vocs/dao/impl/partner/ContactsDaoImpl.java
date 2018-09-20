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
import com.lingnet.vocs.entity.Contacts;

/**
 * @ClassName: ContactsDaoImpl
 * @Description: TODO
 * @author zhangyu
 * @date 2017年6月19日 下午2:30:06
 * 
 */
@Repository("contactsDao")
public class ContactsDaoImpl extends BaseDaoImplInit<Contacts, String>
        implements ContactsDao {

}
