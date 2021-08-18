package com.ea.mapper;
import com.ea.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<User> getAll();
    User findByUsername(String username);

    /**
     * 获取所有用户及他们的所有角色
     */
    List<User> getUserRole();
}
