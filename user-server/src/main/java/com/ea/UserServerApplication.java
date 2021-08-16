package com.ea;

import com.ea.utils.NetUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ea.mapper")
public class UserServerApplication {
    @Value("${server.port}")
    private static int port;
    public static void main(String[] args) {
        NetUtils.checkPort(port);
        SpringApplication.run(UserServerApplication.class, args);
    }
}
