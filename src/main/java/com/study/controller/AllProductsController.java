package com.study.controller;

import com.study.service.ProductService;
import com.study.util.HtmlInjector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.HashMap;

@AllArgsConstructor
public class AllProductsController extends HttpServlet {

    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productService.findAll());
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(HtmlInjector.buildPage("products.ftl", pageVariables));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
