package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public User getUserById(Integer id);

    public List<User> getUserList();

    public int add(User user);

    public int update(@Param("user") User user);

    public int delete(Integer id);

    public User findUserByUsername(String username);
}