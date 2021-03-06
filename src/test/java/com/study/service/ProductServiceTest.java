package com.study.service;

import com.study.controller.Constants;
import com.study.dao.ProductDao;
import com.study.dao.ProductDaoImpl;
import com.study.domain.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    private ProductDao productDao;
    private HttpServletRequest request;

    @Test
    @DisplayName("When call findAll and correct list returned")
    void findAll() {
        productDao = mock(ProductDaoImpl.class);
        request = mock(HttpServletRequest.class);
        List<Product> products = List.of(new Product(), new Product());
        when(productDao.getAllProducts()).thenReturn(products);
        ProductService productService = new ProductService(productDao);
        assertEquals(2, productService.findAll().size());
    }
    @Test
    @DisplayName("When product is found by id")
    void getProductById() {
        productDao = mock(ProductDaoImpl.class);
        request = mock(HttpServletRequest.class);
        ProductService productService = new ProductService(productDao);
        Product product = Product.builder().id(1L).build();
        when(productDao.getProductById(product.getId())).thenReturn(product);
        when(request.getParameter(Constants.ID)).thenReturn("1");
        Product productById = productService.getProductById(1L);
        assertEquals(1L, productById.getId());
    }
}