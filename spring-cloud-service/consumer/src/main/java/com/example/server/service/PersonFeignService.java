package com.example.server.service;

import java.util.List;

import com.example.server.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Cloud Feign声明式服务调用
 *
 * 基于 Netflix Feign 实现的客户端负载均衡工具，整合了 Spring Cloud Ribbon 和 Spring Cloud Hystrix，提供了声明式的 Web 服务客户端定义方式。
 */
@FeignClient("provider-service") // 指定服务名来绑定服务(服务名不区分大小写)
public interface PersonFeignService {

    /**
     * 在方法上映射远程的REST服务,此方法是做好负载均衡配置的
     *
     * @param person
     * @return
     */
    @PostMapping(value = "/person/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Person> save(@RequestBody Person person);
}
