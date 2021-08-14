package com.ea;

import com.ea.utils.NetUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    @Value("${server.port}")
    private static int port;
    public static void main(String[] args) {
        NetUtils.checkPort(port);
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
