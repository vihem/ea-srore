package com.ea.service;

import com.ea.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User findByUsername(String username);

    /**
     * 添加用户
     * @param user 用户
     * @return 自增主键 uid
     */
    Long addUser(User user);
    /**
     * 添加用户并给予相应角色
     * @param user 用户
     * @param role 角色名
     */
    String addUserAndRole(User user, String role);

}
