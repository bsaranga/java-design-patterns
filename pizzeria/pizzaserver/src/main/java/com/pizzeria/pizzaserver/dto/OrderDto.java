package com.pizzeria.pizzaserver.dto;

import java.util.List;

import com.pizzeria.pizzaserver.enums.DeliveryMethod;
import com.pizzeria.pizzaserver.enums.OrderType;

public record OrderDto (
    OrderType orderType, 
    DeliveryMethod deliveryMethod,
    List<Integer> productIds
    ) {}
