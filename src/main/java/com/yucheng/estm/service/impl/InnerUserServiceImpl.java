package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.InnerUser;
import com.yucheng.estm.mapper.InnerUserMapper;
import com.yucheng.estm.service.InnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InnerUserServiceImpl implements InnerUserService {

    @Autowired
    private InnerUserMapper innerUserMapper;

    @Override
    public InnerUser dologinUser(String username, String password) {
        InnerUser user = innerUserMapper.selectUserByUserName(username);

        if(user == null){
            throw new RuntimeException("用户名不存在！");
        }

        if(password.equalsIgnoreCase(user.getPassword())){
            return user;
        }else{
            throw new RuntimeException("密码不正确！");
        }
    }
}