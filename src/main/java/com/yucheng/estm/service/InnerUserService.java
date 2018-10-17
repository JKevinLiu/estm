package com.yucheng.estm.service;

import com.yucheng.estm.entity.InnerUser;

public interface InnerUserService {
    InnerUser dologinUser(String username, String password);
}