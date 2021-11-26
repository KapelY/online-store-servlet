package com.study.controller;

import com.study.service.LoginService;
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
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(TOKEN)) {
                loginService.logout(cookie.getValue());
                break;
            }
        }
        resp.sendRedirect("/login");
    }
}
