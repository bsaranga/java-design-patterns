package com.pizzeria.pizzaserver.services;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.pizzeria.pizzaserver.dto.UserDto;
import com.pizzeria.pizzaserver.models.User;

@Service
public class UserManagerService {
    
    @Autowired
    private Connection connection;

    public User registerUser(UserDto userDto) {
        
        User createdUser = null;
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(userDto.password(), salt);

        // Check if the username is already taken
        try (var preparedStatement = connection.prepareStatement(
            "SELECT username FROM user WHERE username = ?")) {
            
                preparedStatement.setString(1, userDto.username());
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        throw new IllegalArgumentException("Username is already taken.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error checking username availability", e);
        }

        // Insertion
        try (var preparedStatement = connection.prepareStatement(
            "INSERT INTO user (username, password_hash, salt, email, address, phone) VALUES (?, ?, ?, ?, ?, ?)")) {
            
            preparedStatement.setString(1, userDto.username());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, salt);
            preparedStatement.setString(4, userDto.email());
            preparedStatement.setString(5, userDto.address());
            preparedStatement.setString(6, userDto.phone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting user into database", e);
        }

        // Return the user
        try (var preparedStatement = connection.prepareStatement(
            "SELECT id, username, email, address, phone FROM user WHERE username = ?")) {
            
            preparedStatement.setString(1, userDto.username());
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
            throw new RuntimeException("Error retrieving user from database", e);
        }

        return createdUser;
    }
}
