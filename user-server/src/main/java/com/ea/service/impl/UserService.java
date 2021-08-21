package com.ea.service.impl;

import com.ea.entity.SysRole;
import com.ea.entity.User;
import com.ea.mapper.SysRoleMapper;
import com.ea.mapper.UserMapper;
import com.ea.service.IUserService;
import com.ea.utils.ShiroUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    @Transactional
    public String addUserAndRole(User user, String role) {
        SysRole sysRole = roleMapper.findByRole(role); //根据角色名获取角色对象
        if (sysRole == null) return "roleNotExist";//角色不存在

        Long uid = this.addUser(user);
        if (uid.equals(-1L) || uid == -1L) return "userExist";

        userMapper.insertUserRole(user.getUid(), sysRole.getRid());//向user_role表中插入数据
        return "success";
    }

    @Override
    public Long addUser(User user) {
        User one = userMapper.getOneByUsername(user.getUsername());
        if (one != null) return -1L;//用户名已存在

        Date date = new Date();
        user.setSalt(ShiroUtils.generateSalt());//生成盐并设置盐
        String saltPwd = ShiroUtils.encryptPassword("md5", user.getPassword(), user.getCredentialsSalt(), 2);//对密码加密
        user.setPassword(saltPwd);//加密后的密码
        user.setState((byte) 1);
        user.setCreated(date);
        user.setUpdated(date);
        return userMapper.insertUser(user);//向user表中插入数据
    }
}
