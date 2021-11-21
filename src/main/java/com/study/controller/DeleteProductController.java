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
public class DeleteProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Map<String, Object> pageVariables = new HashMap<>();
        resp.setContentType("text/html;charset=utf-8");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pageVariables.put("message", "Product wasn't deleted");
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            productService.deleteProduct(req);
            pageVariables.put("message", "Product was deleted");
        }
        resp.getWriter().println(HtmlInjector.buildPage("products.ftl", pageVariables));
    }
}
