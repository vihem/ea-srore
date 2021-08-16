package com.ea;

import com.ea.utils.NetUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {
    @Value("${server.port}")
    private static int port;
    public static void main(String[] args) {
        NetUtils.checkPort(port);
        SpringApplication.run(ZuulApplication.class, args);
    }
}
