package ru.vmakarenko.filters;

import ru.vmakarenko.dto.users.AccessAuthDto;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@WebFilter(filterName = "AuthFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "mood", value = "awake")})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Cookie[] cookieList = ((HttpServletRequest)servletRequest).getCookies();
        String token = "";
        String id = "";
        for(Cookie cookie : cookieList){
            if(cookie.getName().equals(AccessAuthDto.PARAM_AUTH_ID)){
                id = cookie.getValue();
            }
            if(cookie.getName().equals(AccessAuthDto.PARAM_AUTH_TOKEN)){
                token = cookie.getValue();
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
