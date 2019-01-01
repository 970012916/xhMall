package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by sheting on Administrator
 * DateTime  2018/9/12,21:56
 */
public class MenuEntity extends BaseEntity{


    private static final long serialVersionUID = -6956746595636439307L;

    private String menuId;

    //菜单所属应用
    private String regAppId;

    private String menuCode;

    private String menuName;

    private String parentMenuId;

    //菜单级别
    private Integer menuLevel;

    private String urlPath;

    private String iconPath;

    //排序
    private Integer displayIndex;

    //是否显示
    private Integer isVisable;

    //创建人ID
    private String createUserId;

    private Date createTime;

    private String modifyUserId;

    private Date modifyTime;

    //是否系统创建
    private Integer isSys;

    //是否第三方系统
    private Integer isThird;

    private ArrayList<MenuEntity> children;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRegAppId() {
        return regAppId;
    }

    public void setRegAppId(String regAppId) {
        this.regAppId = regAppId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Integer getIsVisable() {
        return isVisable;
    }

    public void setIsVisable(Integer isVisable) {
        this.isVisable = isVisable;
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

    public Integer getIsThird() {
        return isThird;
    }

    public void setIsThird(Integer isThird) {
        this.isThird = isThird;
    }

    public ArrayList<MenuEntity> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MenuEntity> children) {
        this.children = children;
    }
}
