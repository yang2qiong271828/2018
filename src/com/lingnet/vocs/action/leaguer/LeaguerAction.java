package com.lingnet.vocs.action.leaguer;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.vocs.service.leaguer.LeaguerService;

/**
 * 会员信息管理
 * 
 * @ClassName: LeaguerAction
 * @Description: TODO
 * @author wanl
 * @date 2017年7月8日 下午5:44:21
 * 
 */

@SuppressWarnings("all")
public class LeaguerAction extends BaseAction {

    private static final long serialVersionUID = -7368057178862917623L;

    @Resource(name = "leaguerService")
    private LeaguerService leaguerService;
    // @Resource(name = "leaguerDao")
    // private LeaguerDao leaguerDao;

    private String formdata;

    private QxUsers qxUsers;

    public String list() {

        return LIST;
    }

    public String getListData() {
        if (id == null || "-1".equals(id)) {
            id = this.getSession("partnerId").toString();
        }
        return ajax(Status.success, leaguerService.getListData(pager, id));
    }

    public String edit() {
        qxUsers = this.getBeanById(QxUsers.class, id);
        return ADD;
    }

    public String look() {
        qxUsers = this.getBeanById(QxUsers.class, id);
        return "look";
    }

    public String saveOrUpdate() {
        qxUsers = JsonUtil.toObject(formdata, QxUsers.class);
        operate("会员信息管理", "会员信息编辑", qxUsers.getCode());
        try {
            return ajax(Status.success, leaguerService.saveOrUpdate(qxUsers));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /********************************************************* get Set ***************************************************/

    public QxUsers getQxUsers() {
        return qxUsers;
    }

    public void setQxUsers(QxUsers qxUsers) {
        this.qxUsers = qxUsers;
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

}
