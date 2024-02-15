package com.webservice.crest.authentication.interceptors;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webservice.crest.authentication.annotations.NeedsLogin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Logon interceptor
 *
 * @Author: Naresh Peri
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    //This method is executed before accessing the interface. We only need to write the business logic to verify the login status here to verify the login status before the user calls the specified interface.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor::preHandle");
        /*
        if (handler instanceof HandlerMethod) {
            //Check method level annotation
            NeedsLogin needsLogin = ((HandlerMethod) handler).getMethodAnnotation(NeedsLogin.class);
            //Check class level annotation
            if (null == needsLogin) {
                needsLogin = ((HandlerMethod) handler).getMethod().getDeclaringClass()
                        .getAnnotation(NeedsLogin.class);
            }
            // Check login if you have login validation annotations
            if (null != needsLogin) {
                log.info("User Logged in - check");
                return true;//request.authenticate(response);
            }
        }*/
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //log.info("LoginInterceptor::postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //log.info("LoginInterceptor::afterCompletion");
    }
}
