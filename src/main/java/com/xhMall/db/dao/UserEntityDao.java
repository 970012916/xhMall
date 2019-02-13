package com.xhMall.db.dao;

import com.xhMall.db.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityDao {
    void regist(UserEntity userEntity);

    UserEntity selectByUsername(String username);

    int updateUserByUsername(UserEntity userEntity);

    List<UserEntity> selectAllUsers();
}
