package com.xhMall.service.impl;

import com.xhMall.exception.MallException;
import com.xhMall.common.constant.UserAccountStatus;
import com.xhMall.common.util.SendEmailUtil;
import com.xhMall.db.dao.UserEntityDao;
import com.xhMall.db.entity.UserEntity;
import com.xhMall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityDao userEntityDao;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regist(UserEntity userEntity) {
        userEntityDao.regist(userEntity);
        sendEmailUtil.submit(userEntity);
    }

    @Override
    public UserEntity selectByUsername(String username) {
        UserEntity userEntity;
        userEntity = userEntityDao.selectByUsername(username);
        if(null == userEntity ) {
          throw new MallException("用户不存在");
        }
        String status = userEntity.getStatus();
        if (UserAccountStatus.DISABLE.equals(status)) {
            throw new MallException("当前用户被禁用");
        }

        if (UserAccountStatus.UNACTIVE.equals(status)) {
            throw new MallException("当前用户未激活");
        }
        return userEntity;
    }

    @Override
    //TODO 可以将逻辑处理写成存储过程
    public void updateUserByUsername(String username,String validateCode) {
        UserEntity userEntity = userEntityDao.selectByUsername(username);

        if(null == userEntity ) {
            throw new MallException("用户不存在");
        }
        int isSuccess = userEntityDao.updateUserByUsername(userEntity);
        if (1 != isSuccess) {
            throw new MallException("激活用户状态失败");
        }
    }

    @Override
    public List<UserEntity> selectAllUsers() {
        List<UserEntity> list = userEntityDao.selectAllUsers();
        return list;
    }


}
