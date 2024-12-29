package com.pizzeria.pizzaserver.services;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.pizzeria.pizzaserver.models.User;

@Service
public class UserManagerService {
    
    @Autowired
    private Connection connection;

    public User registerUser(User user, String password) {
        
        User createdUser = null;
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        // Check if the username is already taken
        try (var preparedStatement = connection.prepareStatement(
            "SELECT username FROM users WHERE username = ?")) {
            
                preparedStatement.setString(1, user.username());
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        throw new IllegalArgumentException("Username is already taken.");
                    }
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insertion
        try (var preparedStatement = connection.prepareStatement(
            "INSERT INTO users (username, password_hash, salt, email, address, phone) VALUES (?, ?, ?, ?, ?, ?)")) {
            
            preparedStatement.setString(1, user.username());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, salt);
            preparedStatement.setString(4, user.email());
            preparedStatement.setString(5, user.address());
            preparedStatement.setString(6, user.phone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the user
        try (var preparedStatement = connection.prepareStatement(
            "SELECT id, username, email, address, phone FROM users WHERE username = ?")) {
            
            preparedStatement.setString(1, user.username());
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    createdUser = new User(id, username, email, address, phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createdUser;
    }
}
