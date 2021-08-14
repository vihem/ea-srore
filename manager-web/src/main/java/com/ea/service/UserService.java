package com.ea.service;

import com.ea.client.UserClientFeign;
import com.ea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserClientFeign userClientFeign;

    public List<User> getAll(){
        return userClientFeign.getAll();
    }
}
