package com.pizzeria.cli.client.strategies;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.order.Order;
import com.pizzeria.cli.client.state.order.OrderState;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Context {
    
    @Autowired
	private OrderState orderState;
    private IStrategy<Order> strategy;

    public void setStrategy(IStrategy<Order> strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        if (strategy != null) {
            strategy.execute(orderState);
        } else throw new IllegalStateException("Strategy not set");
    }
}
