package com.xhMall.service.impl;

import com.xhMall.db.entity.UserEntity;
import com.xhMall.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2019/1/27,14:06
 */
@Service(value = "userServiceImpl2")
public class UserServiceImpl2 implements UserService{
    @Override
    public void regist(UserEntity userEntity) {

    }

    @Override
    public UserEntity selectByUsername(String username) {
        return null;
    }

    @Override
    public void updateUserByUsername(String username, String validateCode) {

    }

    @Override
    public List<UserEntity> selectAllUsers() {
        return null;
    }
}
