package com.study.controller;

import com.study.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

import static com.study.controller.Constants.TOKEN;

@AllArgsConstructor
public class LogoutController extends HttpServlet {
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(TOKEN)) {
                securityService.logout(cookie.getValue());
                break;
            }
        }
        resp.sendRedirect("/login");
    }
}
