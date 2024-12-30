package com.pizzeria.cli.client.dtos;

import java.util.List;
import com.pizzeria.cli.client.orderproc.DeliveryMethod;
import com.pizzeria.cli.client.orderproc.OrderType;

public record OrderDto (
    OrderType orderType, 
    DeliveryMethod deliveryMethod,
    List<Integer> productIds,
    String sessionToken
    ) {}
