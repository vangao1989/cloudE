package com.cloudE.ucenter.manager;

import com.cloudE.entity.User;
import com.cloudE.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserManager {

    @Resource
    private UserMapper userMapper;

    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
