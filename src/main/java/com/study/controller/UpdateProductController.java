package com.study.controller;

import com.study.domain.Product;
import com.study.service.ProductService;
import com.study.util.HtmlInjector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Slf4j
public class UpdateProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = productService.getProductById(req);
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", product);
        log.info(product.toString());
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(HtmlInjector.buildPage("update.ftl", pageVariables));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String date = req.getParameter("date");
        if (name == null || price == null || date == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pageVariables.put("message", "Not allowed!");
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            productService.updateProduct(req);
            pageVariables.put("message", "All done");
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(HtmlInjector.buildPage("products.ftl", pageVariables));
    }
}
