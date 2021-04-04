package org.dclm.sealApp.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestHeaderInterceptor extends HandlerInterceptorAdapter {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getHeader("token").isEmpty()){
            logger.info("......Empty string.....");
        }else{
            logger.info("......TOKEN..... "+request.getHeader("token"));
        }
        return super.preHandle(request, response, handler);
    }
}
