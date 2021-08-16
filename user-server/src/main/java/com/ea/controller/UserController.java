package com.ea.controller;

import com.ea.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理", protocols = "http")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有用户")
    public Object getAll(){
        return userService.getAll();
    }

    @RequestMapping(value = "getByUsername", method = RequestMethod.GET)
    @ApiOperation(value = "根据名字获取用户信息")
    public Object findByUsername(String username){
        return userService.findByUsername(username);
    }
}
