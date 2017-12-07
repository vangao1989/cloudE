package com.cloudE;

import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * @author vangao1989
 * @Description: 微服务
 * @date 2017年7月24日
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@EnableSpringBootMetricsCollector
@RefreshScope
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }
}