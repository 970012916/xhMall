package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

import java.util.Date;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/17,21:25
 */
public class UnitEntity extends BaseEntity {

    private static final long serialVersionUID = 8231468125292471100L;

    private String unitId;

    //扩展组织ID
    private String exOrgId;

    //组织类型Id
    private String unitTypeId;

    //组织名称
    private String unitName;

    //组织编码
    private String unitCode;

    //英文名称
    private String unitNameEn;

    private String unitNameShort;

    //所属城市
    private String unitCityId;

    private String unitAddress;

    //组织注册地址
    private String unitRegAddress;

    //组织联系人
    private String unitContact;

    private String unitTel;

    private String unitFax;

    private String mail;

    //邮编
    private String Postcodes;

    //说明
    private String unitRemark;

    private Integer unitStatus;

    //客户等级
    private Integer customerLevel;

    //标签
    private Integer unitFlag;

    private String createUserId;

    private Date createTime;

    private String modifyUserId;

    private Date modifyTime;

    private Integer isSys;

    //公司Id
    private String comUnitId;

    private String comUnitName;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getExOrgId() {
        return exOrgId;
    }

    public void setExOrgId(String exOrgId) {
        this.exOrgId = exOrgId;
    }

    public String getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(String unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public String getUnitNameShort() {
        return unitNameShort;
    }

    public void setUnitNameShort(String unitNameShort) {
        this.unitNameShort = unitNameShort;
    }

    public String getUnitCityId() {
        return unitCityId;
    }

    public void setUnitCityId(String unitCityId) {
        this.unitCityId = unitCityId;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitRegAddress() {
        return unitRegAddress;
    }

    public void setUnitRegAddress(String unitRegAddress) {
        this.unitRegAddress = unitRegAddress;
    }

    public String getUnitContact() {
        return unitContact;
    }

    public void setUnitContact(String unitContact) {
        this.unitContact = unitContact;
    }

    public String getUnitTel() {
        return unitTel;
    }

    public void setUnitTel(String unitTel) {
        this.unitTel = unitTel;
    }

    public String getUnitFax() {
        return unitFax;
    }

    public void setUnitFax(String unitFax) {
        this.unitFax = unitFax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPostcodes() {
        return Postcodes;
    }

    public void setPostcodes(String postcodes) {
        Postcodes = postcodes;
    }

    public String getUnitRemark() {
        return unitRemark;
    }

    public void setUnitRemark(String unitRemark) {
        this.unitRemark = unitRemark;
    }

    public Integer getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Integer getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(Integer unitFlag) {
        this.unitFlag = unitFlag;
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

    public String getComUnitId() {
        return comUnitId;
    }

    public void setComUnitId(String comUnitId) {
        this.comUnitId = comUnitId;
    }

    public String getComUnitName() {
        return comUnitName;
    }

    public void setComUnitName(String comUnitName) {
        this.comUnitName = comUnitName;
    }
}
