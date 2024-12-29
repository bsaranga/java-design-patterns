package com.pizzeria.pizzaserver.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentsService {
    
    @Autowired
    private Connection connection;

    public void getAllPizzaComponents() {
        try (
            Statement statement = connection.createStatement();
        ){
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT * FROM components");
            while (rs.next()) {
                System.out.print("id = " + rs.getInt("id"));
                System.out.println("content = " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
