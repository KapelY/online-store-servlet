package com.study.dao;

import com.study.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products;";
    private static final String ADD_PRODUCT = "INSERT INTO products(name, price, date) VALUES(?, ?, ?);";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?;";
    private static final String UPDATE_PRODUCT = "UPDATE products SET name = ?, price = ?, date = ? WHERE id = ?;";
    private static final String SELECT_BY_ID_PRODUCT = "SELECT id, name, price, date FROM products WHERE id = ?;";
    private Connection connection;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(Product.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .date((resultSet.getTimestamp(4)).toLocalDateTime().toLocalDate())
                        .build());
            }
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when getAllProducts", e);
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void addProduct(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, Date.valueOf(product.getDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when addProduct", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, Date.valueOf(product.getDate()));
            preparedStatement.setLong(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when updateProduct", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when deleteProduct", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_PRODUCT)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Product.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .price(resultSet.getDouble(3))
                    .date((resultSet.getDate(4)).toLocalDate())
                    .build();
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when getProductById", e);
            throw new RuntimeException(e);
        }
    }
}
