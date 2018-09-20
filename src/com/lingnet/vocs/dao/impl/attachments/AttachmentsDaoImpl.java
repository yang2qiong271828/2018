package com.lingnet.vocs.dao.impl.attachments;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.vocs.dao.attachments.AttachmentsDao;
import com.lingnet.vocs.entity.Attachments;

@Repository("attachmentsDao")
public class AttachmentsDaoImpl extends BaseDaoImplInit<Attachments, String>
        implements AttachmentsDao {


}
