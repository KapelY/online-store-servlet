package com.study.filter;

import com.study.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.study.controller.Constants.TOKEN;

@Slf4j
@AllArgsConstructor
public class SecurityFilter implements Filter {
    private SecurityService securityService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = getToken(req);
        String servletPath = req.getServletPath();

        if (servletPath.equals("/login") || servletPath.equals("/register")) {
            log.info("login or register");
            chain.doFilter(request, response);
        } else if (token != null && securityService.validToken(token)) {
            chain.doFilter(request, response);
        } else {
            log.info("Invalid token ->" + token);
            resp.sendRedirect("/login");
        }
    }

    private String getToken(HttpServletRequest req) {
        String token = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(TOKEN)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
