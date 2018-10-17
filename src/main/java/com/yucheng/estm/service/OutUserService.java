package com.yucheng.estm.service;

import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.mapper.OutUserMapper;

public interface OutUserService {
    OutUser getUserById(Integer id);
}