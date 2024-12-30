package com.pizzeria.pizzaserver.services;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.pizzaserver.dto.OrderDto;
import com.pizzeria.pizzaserver.enums.OrderType;

@Service
public class OrderService {

    @Autowired
    private Connection connection;

    public void createOrder(OrderDto orderDto, String sessionToken) {
        try {
            // Query to get user_id from session_token
            String getUserIdQuery = "SELECT user_id FROM user_session WHERE session_token = ? AND expired = 0";
            var preparedStatement = connection.prepareStatement(getUserIdQuery);
            preparedStatement.setString(1, sessionToken);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");

                // Query to get the order_state_id for 'ordered'
                String getOrderStateIdQuery = "SELECT id FROM order_states WHERE order_state = 'ordered'";
                var orderStateStatement = connection.prepareStatement(getOrderStateIdQuery);
                var orderStateResultSet = orderStateStatement.executeQuery();

                if (orderStateResultSet.next()) {
                    int orderTypeId;
                    int orderStateId = orderStateResultSet.getInt("id");

                    if (orderDto.orderType().equals(OrderType.COLLECTION)) {
                        orderTypeId = 1;
                    } else {
                        orderTypeId = 2;
                    }

                    // Insert the order into the orders table
                    String insertOrderQuery = "INSERT INTO orders (user_id, product_ids, order_type_id, order_state_id, total_price) VALUES (?, ?, ?, ?, ?)";
                    var insertOrderStatement = connection.prepareStatement(insertOrderQuery);
                    insertOrderStatement.setInt(1, userId);
                    insertOrderStatement.setString(2, orderDto.productIds().toString());
                    insertOrderStatement.setInt(3, orderTypeId);
                    insertOrderStatement.setInt(4, orderStateId);
                    insertOrderStatement.setDouble(5, 99);
                    insertOrderStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create order", e);
        }
    }

    public void updateOrderState(int newOrderStateId, String sessionToken) {
        try {
            // Query to get user_id from session_token
            String getUserIdQuery = "SELECT user_id FROM user_session WHERE session_token = ? AND expired = 0";
            var preparedStatement = connection.prepareStatement(getUserIdQuery);
            preparedStatement.setString(1, sessionToken);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");

                // Query to find an order that doesn't have order_state_id = delivered
                String findOrderQuery = "SELECT id FROM orders WHERE user_id = ? AND order_state_id != (SELECT id FROM order_states WHERE order_state = 'delivered')";
                var findOrderStatement = connection.prepareStatement(findOrderQuery);
                findOrderStatement.setInt(1, userId);
                var orderResultSet = findOrderStatement.executeQuery();

                if (orderResultSet.next()) {
                    int orderId = orderResultSet.getInt("id");

                    // Update the order with the new order state
                    String updateOrderQuery = "UPDATE orders SET order_state_id = ? WHERE id = ?";
                    var updateOrderStatement = connection.prepareStatement(updateOrderQuery);
                    updateOrderStatement.setInt(1, newOrderStateId);
                    updateOrderStatement.setInt(2, orderId);
                    updateOrderStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update order state", e);
        }
    }
    
    public String getOrderState(String sessionToken) {
        try {
            // Query to get user_id from session_token
            String getUserIdQuery = "SELECT user_id FROM user_session WHERE session_token = ? AND expired = 0";
            var preparedStatement = connection.prepareStatement(getUserIdQuery);
            preparedStatement.setString(1, sessionToken);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");

                // Query to get the current order state
                String getOrderStateQuery = "SELECT os.order_state FROM orders o JOIN order_states os ON o.order_state_id = os.id WHERE o.user_id = ? ORDER BY o.id DESC LIMIT 1";
                var orderStateStatement = connection.prepareStatement(getOrderStateQuery);
                orderStateStatement.setInt(1, userId);
                var orderStateResultSet = orderStateStatement.executeQuery();

                if (orderStateResultSet.next()) {
                    return orderStateResultSet.getString("order_state");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get order state", e);
        }
        return null;
    }
}
