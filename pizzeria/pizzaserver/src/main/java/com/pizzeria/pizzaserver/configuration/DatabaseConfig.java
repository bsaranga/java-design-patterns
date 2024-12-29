package com.pizzeria.pizzaserver.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class DatabaseConfig {
    
    @Value("${app.connection_string}")
    private String connectionString;

    private Connection connection;

    @Bean
    public Connection getDatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(connectionString);
        return connection;
    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        System.out.println("Closing database connection");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
