package com.hidetora.multitenant.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("In the preHandler we are intercepting the Request");
        log.info("_________________________________________________");
        String requestURI = request.getRequestURI();
        String tenantId = request.getHeader("X-TenantID");
        log.info("Request URI:: " + requestURI + " || Search for X-TenantID  :: " + tenantId);
        log.info("_________________________________________________");
        if(tenantId == null || tenantId.isEmpty()) {
            log.info("Tenant ID is missing");
            response.getWriter().write("X-TenantID is missing in the request header");
            response.sendError(400, "Tenant ID is missing");
            response.setStatus(400);
            return false;
        }
        TenantContext.setCurrentTenant(tenantId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("In the postHandler we clear the Tenant Context");
        TenantContext.clear();
    }
}
