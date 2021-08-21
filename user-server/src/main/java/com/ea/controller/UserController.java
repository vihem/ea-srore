package com.ea.controller;

import com.ea.entity.User;
import com.ea.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(value = "用户管理", protocols = "http")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户")
    public Object getAll(){
        return userService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "getByUsername", method = RequestMethod.GET)
    @ApiOperation(value = "根据名字获取用户信息")
    public Object getByUsername(String username){
        return userService.findByUsername(username);
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ApiOperation("注册新用户")
    public Object register(String username, String password, String phone, String email){
        return userService.addUser(new User(username,password,phone,email));
    }

    /**
     * 用户查询
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:view")//权限管理
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加
     */
    @RequestMapping("/add")
    @RequiresPermissions("user:add")
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     */
    @RequestMapping("/del")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}
