package com.origin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorslerInter implements HandlerInterceptor {
    /**
     * 跨域拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("----------------CorslerInter-------------------");
    	System.out.println("----------------CorslerInter-------------------");
    	System.out.println("----------------CorslerInter-------------------");
    	System.out.println("----------------CorslerInter-------------------");
    	System.out.println("----------------CorslerInter-------------------");
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Authorization");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }
}
