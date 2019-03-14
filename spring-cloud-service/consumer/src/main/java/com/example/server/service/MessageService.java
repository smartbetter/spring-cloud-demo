package com.example.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Cloud Ribbon服务调用
 *
 * Ribbon是一个基于 HTTP 和 TCP 的客户端负载均衡工具，它基于 Netflix Ribbon 实现。
 * 通过 Spring Cloud 的封装，可以轻松将面向服务的 REST 模板请求自动转换成客户端负载均衡的服务调用。
 *
 * RestTemplate GET请求调用实现:
 *
 * <pre>
 * // String.class为返回类型,也可以设置为实体类,而tom会替换url中的{1}占位符
 * ResponseEntity<String> entity = restTemplate.getForEntity("http://PROVIDER-SERVICE/user/{1}", String.class, "tom");
 * String result = entity.getBody();
 *
 * String result = restTemplate.getForObject("http://PROVIDER-SERVICE/message/getMessage", String.class);
 * </pre>
 *
 * RestTemplate POST请求调用实现:
 *
 * <pre>
 * User user = new User("zhangsan");
 * ResponseEntity<String> entity = restTemplate.postForEntity("http://PROVIDER-SERVICE/user", user, String.class);
 * String result = entity.getBody();
 *
 * String result = restTemplate.postForObject("http://PROVIDER-SERVICE/user", user, String.class);
 *
 * URI responseUri = restTemplate.postForLocation("http://PROVIDER-SERVICE/user", user);
 * </pre>
 *
 * RestTemplate PUT请求调用实现:
 *
 * <pre>
 * restTemplate.put("http://PROVIDER-SERVICE/user/{1}", user, id);
 * </pre>
 *
 * RestTemplate DELETE请求调用实现:
 *
 * <pre>
 * restTemplate.delete("http://PROVIDER-SERVICE/user/{1}", id);
 * </pre>
 */
@Service
public class MessageService {

    /**
     * 使用Ribbon只需要注入一个RestTemplate即可
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用断路器核心注解@HystrixCommand的fallbackMessage参数指定,当本方法调用失败时,调用后备方法fallbackMessage
     *
     * 异常传播:
     *
     * <pre>
     * @HystrixCommand(fallbackMethod = "fallbackMessage", ignoreExceptions = {RuntimeException.class})
     * </pre>
     *
     * ignoreExceptions参数:当getMessage抛出了类型为RuntimeException的异常时,Hystrix会将它包装在HystrixBadRequestException中抛出,这样就不会触发后续的fallback逻辑
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMessage")
    public String getMessage() {
        return restTemplate.getForObject("http://PROVIDER-SERVICE/message/getMessage", String.class);
    }

    /**
     * 异常获取:
     *
     * <pre>
     * fallbackMessage(Throwable e)
     * </pre>
     *
     * fallbackMessage参数可以比getMessage参数多一个Throwable,这样就可以在方法内部获取触发服务降级的具体异常内容了
     *
     * @return
     */
    public String fallbackMessage() {
        return "Message Service 模块故障";
    }
}
