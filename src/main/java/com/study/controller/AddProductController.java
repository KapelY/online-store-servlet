package com.study.controller;

import com.study.domain.Product;
import com.study.service.ProductService;
import com.study.util.HtmlInjector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.study.controller.Constants.NAME;
import static com.study.controller.Constants.PRICE;

@AllArgsConstructor
public class AddProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(HtmlInjector.buildPage("product.ftl", new HashMap<>()));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getParameter(NAME);
        String price = req.getParameter(PRICE);
        if (name == null || price == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pageVariables.put("message", "Product was not added as one or more fields were empty");
        } else {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Product product = Product.builder()
                    .name(req.getParameter(NAME))
                    .price(Double.parseDouble(req.getParameter(PRICE)))
                    .date(LocalDate.now()).build();
            productService.addProduct(product);
            pageVariables.put("message", "Product was added");
        }
        resp.getWriter().println(HtmlInjector.buildPage("product.ftl", pageVariables));
    }
}
