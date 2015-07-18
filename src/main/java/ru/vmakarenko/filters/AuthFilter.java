package ru.vmakarenko.filters;

import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.util.Util;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@WebFilter(filterName = "AuthFilter",
        urlPatterns = {"/api/private/*"})
public class AuthFilter implements Filter {
    @Inject
    private TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        String token = Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_TOKEN, httpServletRequest);
        String email = Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_EMAIL, httpServletRequest);

        if(tokenService.check(token, email)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
