package com.lingnet.vocs.service.attachments;

import java.util.ArrayList;

import com.lingnet.common.service.BaseService;
import com.lingnet.vocs.entity.Attachments;

public interface AttachmentsService extends BaseService<Attachments, String> {

    /**
     * 暂时停用
     * 
     * @Title: saveAttachment
     * @param name
     * @param url
     * @param entityId
     * @param entityName
     * @author zy
     * @since 2017年7月1日 V 1.0
     */
    void saveAttachment(String name, String url,String entityId,String entityName);

    /**
     * 多附件方法： 保存或更新
     * 
     * @Title: saveOrUpdateAttachment
     * @param arrAttachemntUrl
     *            ：String类型，Json数组形式，保存附件的id，name，url
     * @param entityId
     *            ：与附件关联的实体Id
     * @param entityName
     *            ：与附件关联实体类的名字
     * @author zy
     * @since 2017年6月30日 V 1.0
     */
    void saveOrUpdateAttachments(String attachmentdata, String entityId,String entityName);

    /**
     * 多附件方法：根据实体id取得附件列表
     * 
     * @Title: getAttachmentListByEntityId
     * @param entityId
     * @return
     * @author zy
     * @since 2017年6月30日 V 1.0
     */
    ArrayList<Attachments> getAttachmentListByEntityId(String entityId);

    /**
     * 根据实体id删除附件
     * 
     * @Title: removeAttachmentsByEntityId
     * @param entityId
     * @return
     * @author zy
     * @since 2017年6月30日 V 1.0
     */
    int removeAttachmentsByEntityId(String entityId);
}
