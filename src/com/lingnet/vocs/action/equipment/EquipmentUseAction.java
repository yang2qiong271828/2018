package com.lingnet.vocs.action.equipment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.entity.Attachments;
import com.lingnet.vocs.entity.Category;
import com.lingnet.vocs.entity.Machine;
import com.lingnet.vocs.entity.Supplier;
import com.lingnet.vocs.entity.WorkOrder;
import com.lingnet.vocs.service.attachments.AttachmentsService;
import com.lingnet.vocs.service.equipment.EquipmentUseService;
import com.lingnet.vocs.service.partner.PartnerService;
import com.lingnet.vocs.service.supplier.SupplierService;
import com.lingnet.vocs.service.workorder.WorkOrderService;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

@ParentPackage("equipment")
@Namespace("/equipment")
public class EquipmentUseAction extends BaseAction {

	private static final long serialVersionUID = -7101127324263330424L;

	private Machine machine;
	@Resource(name = "equipmentUseService")
	private EquipmentUseService equipmentUseService;
	@Resource(name = "attachmentsService")
	private AttachmentsService attachmentsService;
	@Resource(name = "supplierService")
	private SupplierService supplierService;
	@Resource(name = "workOrderService")
	private WorkOrderService workOrderService;
	
	private String formdata;
	private String attachmentdata;
	private ArrayList<Attachments> imgList;
	private String sbmc;
	private String gylx;
	private String sbcs;
	private Machine ma;
	private String start;
	private String count;
	private String isrm;
	private String griddata;// 子页面数据
	
	private List<WorkOrder> pre;
	
	
	
	

	@Resource(name = "partnerService")
	private PartnerService partnerService;
	
	/**
	 * 设备基础信息列表页跳转
	 * 
	 * @Title: list
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 设备基础信息增加
	 * 
	 * @Title: add
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 设备信息展示
	 * 
	 * @Title: show
	 * @return String
	 * @author lizk
	 * @since 2017年10月24日 V 1.0
	 */
	public String show() {
		return "show";
	}

	/**
	 * 设备详情展示
	 * 
	 * @Title: sbxq
	 * @return String
	 * @author lizk
	 * @since 2017年10月24日 V 1.0
	 */
	public String sbxq() {
		ma = getBeanById(Machine.class, id);
		Supplier supplier = supplierService.getSupplierBySupplierId(ma.getSupplierid());
		ma.setEquipmentfirm(supplier.getSupplier_qyname());
		if (StringUtils.isNotEmpty(ma.getTrade())) {
			Category category = this.getBeanByCriteria(Category.class, Restrictions.eq("id", ma.getTrade()));
			if (category != null) {
				ma.setTrade(category.getName());
			}
		}
		
		List<WorkOrder> list = workOrderService.getReplayDataAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOpinion()==null||"".equals(list.get(i).getOpinion())) {
				list.get(i).setOpinion("该用户暂无评论");
			}
			
		}
		pre= list;
		return "sbxq";
	}

	public String getTp() {
		String json = JsonUtil.Encode(imgList = attachmentsService
				.getAttachmentListByEntityId(id));
		return ajax(Status.success, json);
	}

	/**
	 * 设备基础信息删除
	 * 
	 * @Title: remove
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String remove() {
		equipmentUseService.delete(ids);
		return ajax(Status.success, "success");
	}

	/**
	 * 设备基础信息编辑跳转
	 * 
	 * @Title: edit
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String edit() {
		machine = this.getBeanById(Machine.class, id);
		if (machine == null) {
			machine = new Machine();
		}
		imgList = attachmentsService.getAttachmentListByEntityId(id);
		return ADD;
	}

	/**
	 * 设备基础信息查看
	 * 
	 * @Title: look
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String look() {
		machine = this.getBeanById(Machine.class, id);
		if (machine == null) {
			machine = new Machine();
		}
		imgList = attachmentsService.getAttachmentListByEntityId(id);
		return "look";
	}

	/**
	 * 设备基础信息展示
	 * 
	 * @Title: getListData
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String getListData() {

		//String json = equipmentUseService.getListData(pager);
		return ajax(Status.success, equipmentUseService.getData(pager));

	}

	public String getSblist() {
	
		String json = equipmentUseService.getSblist(sbmc, gylx, sbcs, id,start,count,isrm);
		return ajax(Status.success, json);
	}

	public String getRmlist() {
		String json = equipmentUseService.getRmlist();
		return ajax(Status.success, json);
	}

	public String getGylx() {
		String json = equipmentUseService.getGylx();
		return ajax(Status.success, json);
	}

	public String getSbcj() {
		String json = equipmentUseService.getSbcj();
		return ajax(Status.success, json);
	}

	/**
	 * 设备基础信息修改保存
	 * 
	 * @Title: saveOrUpdate
	 * @return String
	 * @author suihl
	 * @since 2017年10月09日 V 1.0
	 */
	public String saveOrUpdate() {
		machine = JsonUtil.toObject(formdata, Machine.class);
		// operate("设备管理", "设备基础信息增加编辑", machine.getModel());
		try {
			String json = equipmentUseService.saveOrUpdate(machine,griddata);
			if (attachmentdata != null && !"[]".equals(attachmentdata)) {
				attachmentsService.saveOrUpdateAttachments(attachmentdata,
						machine.getId(), "machine");
			}
			return ajax(Status.success, json);
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}
	}

	public String fabu() {
		Machine mc = this.getBeanById(Machine.class, id);
		mc.setStatus("0");
		equipmentUseService.update(mc);
		return ajax(Status.success, "success");
	}

	public String chehui() {
		Machine mc = this.getBeanById(Machine.class, id);
		mc.setStatus("1");
		equipmentUseService.update(mc);
		return ajax(Status.success, "success");
	}

	public String remen() {
		Machine mc = this.getBeanById(Machine.class, id);
		if ("0".equals(mc.getIsrm())) {
			mc.setIsrm("1");
			equipmentUseService.update(mc);
			return ajax(Status.success, "热门取消");
		} else {
			mc.setIsrm("0");
			equipmentUseService.update(mc);
			return ajax(Status.success, "热门成功");
		}
	}

	public String zhiding() {
		Machine mc = this.getBeanById(Machine.class, id);
		if ("0".equals(mc.getIszd())) {
			mc.setIszd("1");
			equipmentUseService.update(mc);
			return ajax(Status.success, "置顶取消");
		} else {
			mc.setIszd("0");
			equipmentUseService.update(mc);
			return ajax(Status.success, "置顶成功");
		}
	}

	public String tzrm(){
		return "tzrm";
	}
	
	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public EquipmentUseService getEquipmentUseService() {
		return equipmentUseService;
	}

	public void setEquipmentUseService(EquipmentUseService equipmentUseService) {
		this.equipmentUseService = equipmentUseService;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getAttachmentdata() {
		return attachmentdata;
	}

	public void setAttachmentdata(String attachmentdata) {
		this.attachmentdata = attachmentdata;
	}

	public ArrayList<Attachments> getImgList() {
		return imgList;
	}

	public void setImgList(ArrayList<Attachments> imgList) {
		this.imgList = imgList;
	}

	public String getSbmc() {
		return sbmc;
	}

	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getSbcs() {
		return sbcs;
	}

	public void setSbcs(String sbcs) {
		this.sbcs = sbcs;
	}

	public void setGylx(String gylx) {
		this.gylx = gylx;
	}

	public Machine getMa() {
		return ma;
	}

	public void setMa(Machine ma) {
		this.ma = ma;
	}

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIsrm() {
        return isrm;
    }

    public void setIsrm(String isrm) {
        this.isrm = isrm;
    }

	public String getGriddata() {
		return griddata;
	}

	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}
    //新增字段
	public List<WorkOrder> getPre() {
		return pre;
	}

	public void setPre(List<WorkOrder> pre) {
		this.pre = pre;
	}
    

}
