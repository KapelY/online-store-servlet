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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.study.controller.Constants.*;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@AllArgsConstructor
@Slf4j
public class UpdateProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = productService.getProductById(Long.parseLong(req.getParameter(ID)));
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", product);
        log.info(product.toString());
        resp.getWriter().println(HtmlInjector.buildPage("update.ftl", pageVariables));
        resp.setStatus(SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String id = req.getParameter(ID);
        String name = req.getParameter(NAME);
        String price = req.getParameter(PRICE);
        String date = req.getParameter(DATE);
        if (id == null || name == null || price == null || date == null) {
            resp.setStatus(SC_BAD_REQUEST);
            pageVariables.put("message", "Not allowed!");
        } else {
            resp.setStatus(SC_OK);
            Product product = new Product(Long.valueOf(id), name, Double.parseDouble(price), LocalDate.parse(date));
            productService.updateProduct(product);
            pageVariables.put("message", "All done");
        }
        resp.getWriter().println(HtmlInjector.buildPage("products.ftl", pageVariables));
    }
}
