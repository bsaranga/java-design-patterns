package com.pizzeria.pizzaserver.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.pizzaserver.models.CuratedPizza;
import com.pizzeria.pizzaserver.models.PizzaIngredient;
import java.util.Collections;

@Service
public class PizzaService {

    @Autowired
    private Connection connection;

    public List<PizzaIngredient> getAllPizzaIngredients() {
        try (
            Statement statement = connection.createStatement();
        ){
            statement.setQueryTimeout(30);
            List<PizzaIngredient> components = new ArrayList<>();
            
            ResultSet rs = statement.executeQuery("""
                select c.id, c.name, ct.component_type as type, c.description, c.price from components c 
                inner join components_types ct on c.type_id = ct.id
                """);
            
            while (rs.next()) {
                components.addLast(new PizzaIngredient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getDouble("price")
                ));
            }

            return components;

        } catch (Exception e) {
            System.out.println(e);
        }
        return List.of();
    }

    public List<CuratedPizza> getAllCuratedPizzas() {
        try (
            Statement statement = connection.createStatement();
        ){
            statement.setQueryTimeout(30);
            List<CuratedPizza> pizzas = new ArrayList<>();
            
            ResultSet rs = statement.executeQuery("""
                select id, name, ingredients, description, price from curated_pizzas
                """);
            
            while (rs.next()) {
                pizzas.add(new CuratedPizza(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("ingredients"),
                    rs.getString("description"),
                    rs.getDouble("price")
                ));
            }

            return pizzas;

        } catch (Exception e) {
            System.out.println(e);
        }
        return List.of();
    }

    public List<PizzaIngredient> getRandomPizzaIngredientsByType(String componentType) {
        try (
            Statement statement = connection.createStatement();
        ){
            statement.setQueryTimeout(30);
            List<PizzaIngredient> components = new ArrayList<>();
            
            ResultSet rs = statement.executeQuery("""
                select c.id, c.name, ct.component_type as type, c.description, c.price from components c 
                inner join components_types ct on c.type_id = ct.id
                where ct.component_type = '""" + componentType + "'");

            while (rs.next()) {
                components.add(new PizzaIngredient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getDouble("price")
                ));
            }

            Collections.shuffle(components);
            return components.stream().limit(5).toList();

        } catch (Exception e) {
            System.out.println(e);
        }
        return List.of();
    }
}
