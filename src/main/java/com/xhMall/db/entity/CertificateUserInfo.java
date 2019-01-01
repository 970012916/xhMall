package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseBean;

import java.util.List;
import java.util.Map;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,21:02
 */
public class CertificateUserInfo extends BaseBean{

    private static final long serialVersionUID = -698797882346567239L;

    //CAS 账票
    private String ticket;

    private String userId;

    private String userName;
    //部门编号
    private String deptCode;

    //部门名称
    private String deptName;

    //职位名称
    private String posName;

    private String posCode;

    //公司编号
    private String companyCode;

    //公司名称
    private String companyName;

    //当前用户角色
    private String curRoleId;

    //行政上级用户
    private String reportToUserId;

    //行政上级角色
    private String reportToRoleId;

    private String mail;

    private Map<String,String> lastClickMenu;

    private List<RoleEntity> roles;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurRoleId() {
        return curRoleId;
    }

    public void setCurRoleId(String curRoleId) {
        this.curRoleId = curRoleId;
    }

    public String getReportToUserId() {
        return reportToUserId;
    }

    public void setReportToUserId(String reportToUserId) {
        this.reportToUserId = reportToUserId;
    }

    public String getReportToRoleId() {
        return reportToRoleId;
    }

    public void setReportToRoleId(String reportToRoleId) {
        this.reportToRoleId = reportToRoleId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Map<String, String> getLastClickMenu() {
        return lastClickMenu;
    }

    public void setLastClickMenu(Map<String, String> lastClickMenu) {
        this.lastClickMenu = lastClickMenu;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }
}
