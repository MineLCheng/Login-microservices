package com.example.gatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Component
public class PreRequestLogFilter extends ZuulFilter {
    private static final Logger logger =
            LoggerFactory.getLogger(
                    PreRequestLogFilter.class);
    @Override
    public String filterType(){
        return "pre";
    }
    @Override
    public int filterOrder(){
        return 1;
    }
    @Override
    public boolean shouldFilter(){
        return true;
    }
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("进入访问过滤器，访问的url:{}，访问的方法： {}",
                request.getRequestURL(),request.getMethod());
        String accessToken = request.getHeader("accessToken");
        if(StringUtils.isEmpty(accessToken)) {
            logger.info("当前请求没有accessToken");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        logger.info("请求通过过滤器");
        return null;}
}



