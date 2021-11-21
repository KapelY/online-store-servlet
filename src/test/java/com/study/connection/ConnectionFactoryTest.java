package com.study.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConnectionFactoryTest {

    @Test
    @DisplayName("When ClassNotFoundException occurs")
    void getConnection1() {
        Properties properties = mock(Properties.class);
        when(properties.getProperty(ConnectionFactory.DRIVER)).thenReturn("hello");
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        Exception thrown = assertThrows(
                RuntimeException.class,
                connectionFactory::getConnection
        );
        assertTrue(thrown.getMessage().contains("hello"));
    }
}