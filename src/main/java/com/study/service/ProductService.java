package com.study.service;

import com.study.dao.ProductDao;
import com.study.domain.Product;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class ProductService {
    static final String NAME = "name";
    static final String PRICE = "price";
    static final String DATE = "date";
    static final String ID = "id";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ProductDao productDao;

    public List<Product> findAll() {
        return productDao.getAllProducts();
    }

    public void updateProduct(HttpServletRequest request) {
        productDao.updateProduct(Product.builder()
                .id(Long.valueOf(request.getParameter(ID)))
                .name(request.getParameter(NAME))
                .price(Double.valueOf(request.getParameter(PRICE)))
                .date(LocalDate.parse(request.getParameter(DATE), FORMATTER)).build());
    }

    public void deleteProduct(HttpServletRequest request) {
        productDao.deleteProduct(Long.parseLong(
                request.getParameter(ID)));
    }

    public void addProduct(HttpServletRequest request) {
        productDao.addProduct(Product.builder()
                .name(request.getParameter(NAME))
                .price(Double.valueOf(request.getParameter(PRICE)))
                .date(LocalDate.now()).build());
    }

    public Product getProductById(HttpServletRequest request) {
        return productDao.getProductById(Long.parseLong(
                request.getParameter(ID)));
    }
}
