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

import static com.study.controller.Constants.ID;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@AllArgsConstructor
public class DeleteProductController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter(ID);
        Map<String, Object> pageVariables = new HashMap<>();
        if (id == null) {
            resp.setStatus(SC_BAD_REQUEST);
            pageVariables.put("message", "Product wasn't deleted");
        } else {
            resp.setStatus(SC_OK);
            long productId = Long.parseLong(id);
            productService.deleteProduct(productId);
            pageVariables.put("message", "Product was deleted");
        }
        resp.getWriter().println(HtmlInjector.buildPage("products.ftl", pageVariables));
    }
}
