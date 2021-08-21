package com.ea.mapper;
import com.ea.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<User> getAll();

    /**
     * 根据 用户名 查一个用户信息
     * @param username 用户名
     * @return user
     */
    User getOneByUsername(String username);

    /**
     * 根据用户名 查询用户相关所有信息，角色，权限
     * @param username 用户名
     * @return user,role,permission
     */
    User findByUsername(String username);
    /**
     * 获取所有用户及他们的所有角色
     */
    List<User> getUserRoles();

    /**
     * 添加用户
     * @return Long 自增 uid
     */
    Long insertUser(User user);
    void insertUserRole(Long uid, Long rid);
}
