package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/11,22:39
 */
public class RoleEntity extends BaseEntity{

    private String roleId;

    //扩展角色id
    private String exRoleId;

    //所属应用Id
    private String regAppId;

    private String roleCode;

    private String roleName;

    //角色简称
    private String roleNameShort;

    //角色职务
    private String roleNameTitle;

    //SSO编号
    private String roleSsoCode;

    private String createUserId;

    private Date createTime;

    private String modifyUserId;

    private Date modifyTime;

    //是否系统创建
    private Integer isSys;

    //组织ID
    private String unitId;

    //默认标志
    private Integer defaultFlag;

    //职务描述
    private String userTitel;

    //系统菜单
    private List<MenuEntity> menus;

    //部门名称
    private String deptName;

    //部门简称
    private String deptNameShort;

    //部门Code
    private String deptCode;

    //行政上级用户
    private String reportToUserId;

    //行政上级角色
    private String reportToRoleId;

    //公司ID
    private String compUnitId;

    //公司名称
    private String compUnitName;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getExRoleId() {
        return exRoleId;
    }

    public void setExRoleId(String exRoleId) {
        this.exRoleId = exRoleId;
    }

    public String getRegAppId() {
        return regAppId;
    }

    public void setRegAppId(String regAppId) {
        this.regAppId = regAppId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameShort() {
        return roleNameShort;
    }

    public void setRoleNameShort(String roleNameShort) {
        this.roleNameShort = roleNameShort;
    }

    public String getRoleNameTitle() {
        return roleNameTitle;
    }

    public void setRoleNameTitle(String roleNameTitle) {
        this.roleNameTitle = roleNameTitle;
    }

    public String getRoleSsoCode() {
        return roleSsoCode;
    }

    public void setRoleSsoCode(String roleSsoCode) {
        this.roleSsoCode = roleSsoCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getUserTitel() {
        return userTitel;
    }

    public void setUserTitel(String userTitel) {
        this.userTitel = userTitel;
    }

    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNameShort() {
        return deptNameShort;
    }

    public void setDeptNameShort(String deptNameShort) {
        this.deptNameShort = deptNameShort;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getCompUnitId() {
        return compUnitId;
    }

    public void setCompUnitId(String compUnitId) {
        this.compUnitId = compUnitId;
    }

    public String getCompUnitName() {
        return compUnitName;
    }

    public void setCompUnitName(String compUnitName) {
        this.compUnitName = compUnitName;
    }
}
