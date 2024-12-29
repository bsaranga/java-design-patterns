package com.pizzeria.pizzaserver.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.pizzeria.pizzaserver.dto.LoginDto;
import com.pizzeria.pizzaserver.dto.SessionDto;
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

    public SessionDto loginUser(LoginDto loginDto) {
        
        int userId;
        String sessionToken = UUID.randomUUID().toString();
        String userName;

        // Check if the username exists
        try (var preparedStatement = connection.prepareStatement(
            "SELECT id, username, password_hash, salt FROM user WHERE username = ?")) {
            
                preparedStatement.setString(1, loginDto.username());
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new IllegalArgumentException("User does not exist.");
                    }

                    userId = resultSet.getInt("id");
                    userName = resultSet.getString("username");

                    String passwordHash = resultSet.getString("password_hash");

                    if (!BCrypt.checkpw(loginDto.password(), passwordHash)) {
                        throw new IllegalArgumentException("Incorrect password.");
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error checking username and password", e);
        }

        // Insert the session token
        try (var preparedStatement = connection.prepareStatement(
            "INSERT INTO user_session (user_id, session_token, expired) VALUES (?, ?, ?)")) {
            
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, sessionToken);
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting session into database", e);
        }

        return new SessionDto(userName, sessionToken);
    }

    public void logoutUser(SessionDto sessionDto) {

        // Get user id from username
        int userId;
        try (var preparedStatement = connection.prepareStatement(
            "SELECT id FROM user WHERE username = ?")) {
            
            preparedStatement.setString(1, sessionDto.username());
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                } else {
                    throw new IllegalArgumentException("User does not exist.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user id from database", e);
        }
        
        // Update the session token
        try (var preparedStatement = connection.prepareStatement(
            "UPDATE user_session SET expired = ? WHERE session_token = ? AND user_id = ?")) {
            
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, sessionDto.sessionToken());
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating session in database", e);
        }
    }
}
