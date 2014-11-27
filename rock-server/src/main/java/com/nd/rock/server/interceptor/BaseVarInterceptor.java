package com.nd.rock.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class BaseVarInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(BaseVarInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String basePath = requestURL.substring(0, requestURL.indexOf(request.getContextPath()))
                + request.getContextPath();
        request.setAttribute("baseUrl", basePath);
        request.setAttribute("basePath", basePath);
        request.setAttribute("requestURL", requestURL.toString());
        // SimpleSSOUser user = SimpleUserUtil.findUser(request);
        //logger.info(request.getRemoteAddr() + " visted. Url is: " + request.getRequestURI());

        return super.preHandle(request, response, handler);
    }

}
