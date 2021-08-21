package com.ea.test;

import com.ea.entity.SysRole;
import com.ea.entity.User;
import com.ea.mapper.UserMapper;
import com.ea.utils.ShiroUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        Long n = getNum();
        System.out.println(n.equals(-1L));
    }
    public static Long getNum(){
        return -1L;
    }
    public static void testUser() {
        User user = new User("admin","123456","18575821660","yeasue@11.com");
        Date date = new Date();
        user.setSalt(ShiroUtils.generateSalt());//生成盐并设置盐
        String password = ShiroUtils.encryptPassword("md5", user.getPassword(), user.getCredentialsSalt(), 2);//对密码加密
        user.setPassword(password);//加密后的密码
        user.setState((byte) 1);
        user.setCreated(date);
        user.setUpdated(date);
        user.show();
        System.out.println(user);
    }

    public static void testMybatis() {
        // Mybatis 配置文件
        String resource = "mybatis/mybatis-config.xml";

        // 得到配置文件流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建会话工厂，传入 MyBatis 的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        getUserRole();
    }
    private static void getUserRole(){
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            List<User> users = mapper.getUserRoles();
            session.commit();
            for(User user:users){
                user.show();
                List<SysRole> roles = user.getRoleList();
                for(SysRole role:roles){
                    System.out.println(role.getRid()+","+role.getRole()+","+role.getAvailable());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }

    private static void getOne(){
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User user = mapper.findByUsername("zhangsan");
            user.show();
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
}
