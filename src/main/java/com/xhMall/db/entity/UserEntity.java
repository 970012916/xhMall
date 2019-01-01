package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1094659980892922338L;

    private String id;

    @NotEmpty(message="用户名不能为空")
    private String username;

    @NotEmpty(message="密码不能为空")
    private String password;

    @Email(message="邮箱格式不正确")
    private String email;

    @NotEmpty(message="手机号不能为空")
    private String mobileNumber;

    @NotEmpty(message="生日不能为空")
    private String birthDate;
    @NotEmpty(message="性别不能为空")
    private String sexy;

    private String status;

    private String createTime;

    private String modifyTime;

    @NotEmpty(message="验证码不能为空")
    private String verifyCode;

    @NotEmpty(message="确认密码不能为空")
    private String comfirmPassword;

    //是否自动登陆 0-否 1-是
    private Integer autoLoginFlag;

    public Integer getAutoLoginFlag() {
        return autoLoginFlag;
    }

    public void setAutoLoginFlag(Integer autoLoginFlag) {
        this.autoLoginFlag = autoLoginFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }
}