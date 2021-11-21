package com.study;

import com.study.connection.ConnectionFactory;
import com.study.controller.AddProductController;
import com.study.controller.AllProductsController;
import com.study.controller.DeleteProductController;
import com.study.controller.UpdateProductController;
import com.study.dao.ProductDao;
import com.study.dao.ProductDaoImpl;
import com.study.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Starter {
    public static final String PROPERTIES = "application.properties";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory(Starter.getProperties());

        ProductDao productDao = new ProductDaoImpl(connectionFactory.getConnection());
        ProductService productService = new ProductService(productDao);
        AddProductController addProductController = new AddProductController(productService);
        AllProductsController allProductController = new AllProductsController(productService);
        DeleteProductController deleteProductController = new DeleteProductController(productService);
        UpdateProductController updateProductController = new UpdateProductController(productService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductController), "/product");
        context.addServlet(new ServletHolder(allProductController), "/products");
        context.addServlet(new ServletHolder(deleteProductController), "/delete");
        context.addServlet(new ServletHolder(updateProductController), "/update");

        Server server = new Server(8080);
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
