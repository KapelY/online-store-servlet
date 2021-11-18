package com.study.dao;

import com.study.domain.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAllProducts();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);

}
