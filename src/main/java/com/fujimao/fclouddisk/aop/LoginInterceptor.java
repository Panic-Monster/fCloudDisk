package com.fujimao.fclouddisk.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujimao.fclouddisk.common.BaseResponse;
import io.lettuce.core.dynamic.intercept.MethodInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 鉴权拦截器
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userInfoVo") != null) {
            return true;
        }

        BaseResponse<Object> resp = new BaseResponse<>(40100, null, "未登录", "");

        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
        return false;
    }
}
