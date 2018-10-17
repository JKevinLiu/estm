package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.mapper.OutUserMapper;
import com.yucheng.estm.service.OutUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutUserServiceImpl implements OutUserService {

    @Autowired
    private OutUserMapper outUserMpper;

    @Override
    public OutUser getUserById(Integer id){
        return outUserMpper.selectByPrimaryKey(id);
    }
}