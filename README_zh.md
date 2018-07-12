# spring-cloud-demo

## 1.微服务组成

| 服务模块 | 说明 | URL |
|:------------- |:------------- |:------------- |
| spring-cloud-zuul | API网关服务，支持自动路由映射到在 Eureka Server 上注册的服务，使用 @EnableZuulProxy 注解开启路由代理。所有的微服务对外只有一个接口，只需要访问一个网关地址即可由网关将我们的请求代理到不同的服务器中。还支持请求过滤等功能。 |  |
| spring-cloud-discovery | 服务注册中心，服务注册和发现，服务发现的主要目的是为了让每个服务之间可以互相通信，Eureka Server 为微服务注册中心。 | 服务治理控制台地址 http://localhost:8761/ |
| spring-cloud-config | 配置服务器，它有在分布式系统开发中外部配置的功能，集中存储所有应用的配置文件。配置服务器配置信息的实时更新可以通过 Spring Cloud Bus(消息总线) 来实现。 |  |
| spring-cloud-hystrix | 断路器仪表盘，用来实时监控 hystrix 的各项指标信息。断路器解决当某个方法调用失败时，调用后备方法来替代失败的方法，以达到容错、阻止级联错误等功能。使用 @EnableCircuitBreaker 注解开启断路器支持，使用 @HystrixCommand() 注解的 fallbackMethod 来指定后备方法。 | http://localhost:8989/hystrix.stream |
| spring-cloud-service | 服务示例 |  |
| spring-cloud-stream | 消息驱动的微服务，通过 Spring Integration 来连接消息代理中间件以实现消息事件驱动，目前支持kafka、rabbitmq的自动化配置。 |  |
| spring-cloud-sleuth | 分布式服务跟踪，其兼容了 Zipkin、HTrace 和 log-based 追踪。 |  |

spring-cloud-service 子模块如下：

| 服务模块 | 说明 | URL |
|:------------- |:------------- |:------------- |
| provider-service | 服务模块(提供者)，提供 REST 服务。 | http://localhost:8081/ |
| consumer | 界面模块(消费者)，提供外部访问的唯一入口，使用 Ribbon 或者 Feign 消费服务，两者都提供断路器功能、负载均衡。使用 Ribbon 直接注入一个 RestTemplate(已做好负载均衡的配置) 即可。使用 Feign 只需要定义一个有 @FeignClient 注解的接口，然后使用 @RequestMapping 注解在方法上映射远程的 REST 服务即可，此方法也是做好负载均衡配置的。 | http://localhost:8080/ |

## 2.微服务启动顺序

1、spring-cloud-discovery、spring-cloud-config
2、spring-cloud-hystrix
3、spring-cloud-service下微服务启动不分前后

此时访问 http://localhost:8761/ 查看 Eureka Server，访问 http://localhost:8080/ 查看 ui 服务。
