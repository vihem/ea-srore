package com.ea.mapper;
import com.ea.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<User> getAll();
    User findByUsername(String username);
}
