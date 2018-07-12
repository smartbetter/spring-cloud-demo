package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient // 开启服务注册中心客户端的支持
@EnableFeignClients    // 开启Feign(声明式服务调用)客户端支持
@EnableCircuitBreaker  // 开启Hystrix断路器支持(在hystrix仪表盘输入localhost:8080/hystrix.stream即可看到监控信息)
@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
