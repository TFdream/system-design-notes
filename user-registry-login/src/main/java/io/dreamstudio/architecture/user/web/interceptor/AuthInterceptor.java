package io.dreamstudio.architecture.user.web.interceptor;

import io.dreamstudio.architecture.user.annotation.OptionalAuth;
import io.dreamstudio.architecture.user.annotation.RequiredAuth;
import io.dreamstudio.architecture.user.model.UserToken;
import io.dreamstudio.architecture.user.service.TokenService;
import io.dreamstudio.architecture.user.web.context.DefaultRequestContext;
import io.dreamstudio.architecture.user.web.context.InvalidTokenException;
import io.dreamstudio.architecture.user.web.context.RequestContext;
import io.dreamstudio.architecture.user.web.context.RequestContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一权限校验拦截器
 * @author Ricky Fung
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Resource(name = "tokenService")
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiredAuth requiredAuth = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequiredAuth.class);
        if (requiredAuth != null) {
            return checkAuth(request, true);
        }
        OptionalAuth optionalAuth = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), OptionalAuth.class);
        if (optionalAuth != null) {
            return checkAuth(request, false);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //clear
        RequestContextHolder.clear();
    }

    private boolean checkAuth(HttpServletRequest request, boolean required) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(token) && required) {
            throw new InvalidTokenException("token为空");
        }
        RequestContext context;
        try {
            //JWT
            context = decodeJwt(token);
        } catch (Exception e) {
            if (required) {
                throw new InvalidTokenException("token非法", e);
            }
            context = new DefaultRequestContext(null, null);
        }
        RequestContextHolder.setContext(context);
        return true;
    }

    private RequestContext decodeJwt(String token) throws Exception {
        //JWT
        UserToken userToken = tokenService.decodeToken(token);
        RequestContext context = new DefaultRequestContext(userToken.getUserId(), userToken.getLastLoginTime());
        return context;
    }
}
