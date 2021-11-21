package com.study.controller;

import com.study.service.ProductService;
import com.study.util.HtmlInjector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AddProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(HtmlInjector.buildPage("product.ftl", new HashMap<>()));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        resp.setContentType("text/html;charset=utf-8");
        if (name == null || name.isEmpty() || price == null || price.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pageVariables.put("message", "Product was not added as one or more fields were empty");
        } else {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            productService.addProduct(req);
            pageVariables.put("message", "Product was added");
        }
        resp.getWriter().println(HtmlInjector.buildPage("product.ftl", pageVariables));
    }
}
