package com.example.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问过滤
 */
public class AccessFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型 决定了过滤器在请求的哪个生命周期中执行
     *
     * pre:表示会在请求被路由之前执行
     * routing:在路由请求时被调用
     * post:在routing和error过滤器之后被调用
     * error:处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序 当请求在一个阶段中存在多个过滤器时,需要根据该方法返回的值(int)来依次执行,数值越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行 true表示该过滤器对所有请求都会生效,也可以用该函数来指定过滤器的有效范围
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * 在这里可以实现自定义的过滤逻辑,来确定是否需要拦截当前请求,不对其进行后续的路由,或是在请求路由返回结果之后,对处理结果做一些加工等
     * http://localhost/api-a/ui/getMessage?accessToken=token
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            logger.warn("access token is empty");
            // 过滤器过滤该请求
            ctx.setSendZuulResponse(false);
            // 设置返回错误码
            ctx.setResponseStatusCode(401);
            // 设置返回body
            ctx.setResponseBody("{\"code\":\"401\"}");
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
