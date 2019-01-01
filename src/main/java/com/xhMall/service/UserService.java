package com.xhMall.service;

import com.xhMall.db.entity.UserEntity;

import java.util.List;

public interface UserService {

    /**
     * @Author: 零度亲吻gy
     * @Description: 注册用户
     * @Date: 16:22 2018/3/3
     * @param userEntity
     */
    public void regist(UserEntity userEntity);


    /**
     * @Author: 零度亲吻gy
     * @Description: 查询用户
     * @Date: 16:22 2018/3/3
     * @param username
     */
    public UserEntity selectByUsername(String username);

    /**
     * @Author: 零度亲吻gy
     * @Description: 更新用户状态
     * @Date: 16:22 2018/3/3
     * @param username,validateCode
     */
    void updateUserByUsername(String username, String validateCode);

    /**
     * @Author: 零度亲吻gy
     * @Description: 查询用户
     * @Date: 16:22 2018/3/3
     */
    public List<UserEntity> selectAllUsers();
}
