package com.study.service;

import com.study.dao.ProductDao;
import com.study.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class ProductService {
    private ProductDao productDao;

    public List<Product> findAll() {
        return productDao.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
