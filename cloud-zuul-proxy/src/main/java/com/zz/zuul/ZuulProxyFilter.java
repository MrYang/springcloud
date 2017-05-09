package com.zz.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.Yang
 * @since 2017-05-09
 */

@Component
public class ZuulProxyFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(ZuulProxyFilter.class);

    @Override
    public String filterType() {
        // pre, routing, post, error
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("{} >>> {}", request.getMethod(), request.getRequestURI());
        return null;
    }
}
