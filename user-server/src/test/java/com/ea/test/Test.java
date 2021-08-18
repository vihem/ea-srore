package com.ea.test;

import com.ea.entity.SysRole;
import com.ea.entity.User;
import com.ea.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
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
            List<User> users = mapper.getUserRole();
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
