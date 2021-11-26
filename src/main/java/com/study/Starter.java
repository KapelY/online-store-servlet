package com.study;

import com.study.connection.ConnectionFactory;
import com.study.connection.DataSource;
import com.study.controller.*;
import com.study.dao.ProductDao;
import com.study.dao.ProductDaoImpl;
import com.study.dao.UserDao;
import com.study.dao.UserDaoImpl;
import com.study.filter.DomainFilter;
import com.study.service.LoginService;
import com.study.service.ProductService;
import com.study.service.UserService;
import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;

@Slf4j
public class Starter {
    public static final String PROPERTIES = "application.properties";

    public static void main(String[] args) throws Exception {
        DataSource dataSource = new ConnectionFactory(Starter.getProperties());

        ProductDao productDao = new ProductDaoImpl(dataSource.getConnection());
        ProductService productService = new ProductService(productDao);
        AddProductController addProductController = new AddProductController(productService);
        AllProductsController allProductController = new AllProductsController(productService);
        DeleteProductController deleteProductController = new DeleteProductController(productService);
        UpdateProductController updateProductController = new UpdateProductController(productService);

        UserDao userDao = new UserDaoImpl(dataSource.getConnection());
        UserService userService = new UserService(userDao);
        LoginService loginService = new LoginService(userService);
        LoginController loginController = new LoginController(loginService);
        LogoutController logoutController = new LogoutController(loginService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductController), "/product");
        context.addServlet(new ServletHolder(allProductController), "/products");
        context.addServlet(new ServletHolder(deleteProductController), "/delete");
        context.addServlet(new ServletHolder(updateProductController), "/update");
        context.addServlet(new ServletHolder(loginController), "/login");
        context.addServlet(new ServletHolder(logoutController), "/logout");

        DomainFilter filter = new DomainFilter(loginService);
        context.addFilter(new FilterHolder(filter), "/*", EnumSet.allOf(DispatcherType.class));

        Server server = new Server(Integer.parseInt(System.getenv().get("PORT")));
        server.setHandler(context);
        server.start();
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream input = Starter.class.getClassLoader().getResourceAsStream(PROPERTIES)) {
            properties.load(input);
        } catch (IOException e) {
            log.error("IO error in read props", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
