package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.User;
import com.yucheng.estm.mapper.UserMapper;
import com.yucheng.estm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int save(User user) {
        Assert.notNull(user, "用户为空！");

        if(user.getId() != 0){
            return userMapper.update(user);
        }else{
            return userMapper.add(user);
        }
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}