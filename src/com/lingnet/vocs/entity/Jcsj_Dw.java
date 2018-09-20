package com.lingnet.vocs.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 
 * @ClassName: Jcsj_Dw 
 * @Description: 单位实体类
 * @author 姜平豹
 * @date 2013-9-11 下午5:06:46 
 *
 */
@Entity
@Table(name = "JCSJ_DW")
public class Jcsj_Dw extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6866597724178373398L;
    
    public static final String DELETE = "1";
    
    // 单位说明
    private String description;
    //单位代号
    private String dwdh;
    
    private String createMan;//创建人
    private String modifyMan;//修改人
    
    private String is_delete;//删除状态

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
   
    public String getDwdh() {
        return dwdh;
    }
    public void setDwdh(String dwdh) {
        this.dwdh = dwdh;
    }
    
    @Column(name = "CREATE_MAN")
    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }


    @Column(name = "MODIFY_MAN")
    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }
    
    public String getIs_delete() {
        return is_delete;
    }
    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}