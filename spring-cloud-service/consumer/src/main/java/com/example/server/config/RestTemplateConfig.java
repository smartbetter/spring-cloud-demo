package com.example.server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * @LoadBalanced 注解用来给RestTemplate标记, 以使用负载均衡的客户端(LoadBalancerClient)来配置它
     *
     * LoadBalancerClient接口:
     *
     * <pre>
     * public interface LoadBalancerClient extends ServiceInstanceChooser {
     *     // 根据传入的服务名serviceId,从负载均衡器中挑选一个对应服务的实例
     *     ServiceInstance choose(String serviceId);
     *     // 使用从负载均衡器中挑选出的服务实例来执行请求内容
     *     <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException;
     *     <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException;
     *     // 为系统构建一个合适的host:port形式的URI
     *     URI reconstructURI(ServiceInstance instance, URI original);
     * }
     * </pre>
     *
     * LoadBalancerAutoConfiguration 为实现客户端负载均衡器的自动化配置类
     * @return
     */
    @Bean
    @LoadBalanced // 开启客户端负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
