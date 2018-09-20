/*
 * @file Goodcat.java
 * 文件摘要.
 * 文件的详细说明
 * :  
 * @author 刘青勇
 * @copyright     青岛一凌网集成有限公司2013
 * @version 20120101 刘青勇  v1.0
 */
package com.lingnet.vocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 商品种类
 * @ClassName: Goodcat 
 * @Description: TODO 
 * @author 薛硕
 * @date 2017年6月20日 下午4:00:21 
 *
 */
@Entity
@Table(name = "JCSJ_WLLX")
public class Goodcat extends BaseEntity implements java.io.Serializable {
    
    private static final long serialVersionUID = 1031814843946214620L;
    
    public static final String DELETE = "1";
    
    private String   goodcatcode;       //商品分类代号
    private String   goodcatname;       //商品分类名称
    private String   codeheader;        //编码前缀
    private Integer  connector;         //连接符
    private Integer  accountsize;       //流水号长度
    private Integer  accountnum;        //当前流水号
    private String   codefooter;        //编码后缀
    private String   pgoodcatcode;      //父-商品分类代号
    private String is_delete;//删除状态
    /*   
     * 创建一个新的实例 Goodcat.    
     *    
     * @param goodcatcode
     * @param goodcatname
     * @param codeheader
     * @param connector
     * @param accountsize
     * @param accountnum
     * @param codefooter
     * @param pid    
     */
    public Goodcat(String goodcatcode, String goodcatname, String codeheader,
            Integer connector, Integer accountsize, Integer accountnum,
            String codefooter, String pgoodcatcode) {
        super();
        this.goodcatcode = goodcatcode;
        this.goodcatname = goodcatname;
        this.codeheader = codeheader;
        this.connector = connector;
        this.accountsize = accountsize;
        this.accountnum = accountnum;
        this.codefooter = codefooter;
        this.pgoodcatcode = pgoodcatcode;
    }

       
    /*   
     * 创建一个新的实例 Goodcat.    
     *        
     */
    public Goodcat() {
    }

    /************************************************************************/
    @Column(name = "WLLXDM", length = 100)
    public String getGoodcatcode() {
        return goodcatcode;
    }

    public void setGoodcatcode(String goodcatcode) {
        this.goodcatcode = goodcatcode;
    }

    @Column(name = "WLLXMC")
    public String getGoodcatname() {
        return goodcatname;
    }

    public void setGoodcatname(String goodcatname) {
        this.goodcatname = goodcatname;
    }

    @Column(name = "BMQZ", length = 50)
    public String getCodeheader() {
        return codeheader;
    }

    public void setCodeheader(String codeheader) {
        this.codeheader = codeheader;
    }

    @Column(name = "LJF", length = 50)
    public Integer getConnector() {
        return connector;
    }

    public void setConnector(Integer connector) {
        this.connector = connector;
    }

    @Column(name = "LSHCD", length = 50)
    public Integer getAccountsize() {
        return accountsize;
    }

    public void setAccountsize(Integer accountsize) {
        this.accountsize = accountsize;
    }

    @Column(name = "LSH", length = 50)
    public Integer getAccountnum() {
        return accountnum;
    }
    
    public void setAccountnum(Integer accountnum) {
        this.accountnum = accountnum;
    }

    @Column(name = "BMHZ", length = 50)
    public String getCodefooter() {
        return codefooter;
    }

    public void setCodefooter(String codefooter) {
        this.codefooter = codefooter;
    }

    @Column(name = "P_WLLXDM", length = 100)
    public String getPgoodcatcode() {
        return pgoodcatcode;
    }

    public void setPgoodcatcode(String pgoodcatcode) {
        this.pgoodcatcode = pgoodcatcode;
    }

    public String getIs_delete() {
        return is_delete;
    }


    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
