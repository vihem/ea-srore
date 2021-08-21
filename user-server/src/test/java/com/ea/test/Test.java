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
        user.setSalt(ShiroUtils.generateSalt());//�����β�������
        String password = ShiroUtils.encryptPassword("md5", user.getPassword(), user.getCredentialsSalt(), 2);//���������
        user.setPassword(password);//���ܺ������
        user.setState((byte) 1);
        user.setCreated(date);
        user.setUpdated(date);
        user.show();
        System.out.println(user);
    }

    public static void testMybatis() {
        // Mybatis �����ļ�
        String resource = "mybatis/mybatis-config.xml";

        // �õ������ļ���
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // �����Ự���������� MyBatis �������ļ���Ϣ
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        getUserRole();
    }
    private static void getUserRole(){
        // ͨ�������õ� SqlSession
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
        // �ͷ���Դ
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
        // �ͷ���Դ
        session.close();
    }
}
