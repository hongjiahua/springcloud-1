package com.gnnu.filter;

import com.gnnu.feign.SsoFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter extends ZuulFilter {
    @Autowired
    private SsoFeign ssoFeign;

    @Override
    public String filterType() {
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
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String url = request.getRequestURL().toString();
        String accessToken = request.getParameter("accessToken");
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies
            ) {
                if ("accessToken".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    System.out.println(accessToken);

                }

            }
        }
        if (url.contains("/sso/login") || url.contains("/sso/loginPage") || (!StringUtils.isEmpty(accessToken) && ssoFeign.hasKey(accessToken))) {
            context.setResponseStatusCode(200);
            context.setSendZuulResponse(true);
        } else {
            context.setResponseStatusCode(400);
            context.setSendZuulResponse(false);
            try {
                response.sendRedirect("http://localhost:8888/sso/loginPage?url=" + url);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return null;
    }
}
