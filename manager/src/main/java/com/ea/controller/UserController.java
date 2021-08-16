package com.ea.controller;

import com.ea.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", protocols = "http")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/all")
    @ApiOperation(value = "获取所有用户")
    public Object getAll(){return userService.getAll();}
}
