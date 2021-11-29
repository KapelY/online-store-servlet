package com.study;

import com.study.configuration.PropertyReader;
import com.study.connection.ConnectionFactory;
import com.study.connection.DataSource;
import com.study.controller.*;
import com.study.dao.ProductDao;
import com.study.dao.ProductDaoImpl;
import com.study.dao.UserDao;
import com.study.dao.UserDaoImpl;
import com.study.filter.SecurityFilter;
import com.study.service.SecurityService;
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
        DataSource dataSource = new ConnectionFactory(new PropertyReader(PROPERTIES).getProperties());

        ProductDao productDao = new ProductDaoImpl(dataSource.getConnection());
        ProductService productService = new ProductService(productDao);
        AddProductController addProductController = new AddProductController(productService);
        AllProductsController allProductController = new AllProductsController(productService);
        DeleteProductController deleteProductController = new DeleteProductController(productService);
        UpdateProductController updateProductController = new UpdateProductController(productService);

        UserDao userDao = new UserDaoImpl(dataSource.getConnection());
        UserService userService = new UserService(userDao);
        SecurityService securityService = new SecurityService(userService);
        LoginController loginController = new LoginController(securityService);
        RegisterController registerController = new RegisterController(securityService);
        LogoutController logoutController = new LogoutController(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductController), "/product");
        context.addServlet(new ServletHolder(allProductController), "/products");
        context.addServlet(new ServletHolder(deleteProductController), "/delete");
        context.addServlet(new ServletHolder(updateProductController), "/update");
        context.addServlet(new ServletHolder(loginController), "/login");
        context.addServlet(new ServletHolder(registerController), "/register");
        context.addServlet(new ServletHolder(logoutController), "/logout");

        SecurityFilter filter = new SecurityFilter(securityService);
        context.addFilter(new FilterHolder(filter), "/*", EnumSet.allOf(DispatcherType.class));

        Server server = new Server(Integer.parseInt(System.getenv().get("PORT")));
        server.setHandler(context);
        server.start();
    }
}
