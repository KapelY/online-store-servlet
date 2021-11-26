package com.study.dao;

import com.study.domain.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    private static final String GET_USER_BY_EMAIL = "SELECT id, password_hash, salt FROM users WHERE email = ?;";
    private static final String SAVE_USER = "INSERT INTO users(email, password_hash, salt) VALUES(?, ?, ?);";

    private Connection connection;

    @Override
    public User getUserByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .email(email)
                        .passwordHash(resultSet.getString(2))
                        .salt(resultSet.getString(3)).build();
            }
            return null;
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when getUserByEmail", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer saveUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPasswordHash());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new RuntimeException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            log.info("DB access error or connection is closed, when addProduct", e);
            throw new RuntimeException(e);
        }
    }
}
