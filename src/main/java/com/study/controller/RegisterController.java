package com.study.controller;

import com.study.service.SecurityService;
import com.study.util.HtmlInjector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.study.controller.Constants.*;
import static com.study.controller.Constants.PASSWORD;

@AllArgsConstructor
@Slf4j
public class RegisterController extends HttpServlet {
    private SecurityService securityService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        if (!securityService.contains(email)) {
            String token = securityService.registerTokenNewUser(email, password);
            Cookie cookie = new Cookie(TOKEN, token);
            cookie.setMaxAge(COOKIE_MAX_AGE);
            resp.addCookie(cookie);
            log.info("SignUp successful");
            resp.sendRedirect("/products");
        } else {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("message", "Login is used!");
            resp.getWriter().println(HtmlInjector.buildPage("login.ftl", pageVariables));
        }
    }
}
