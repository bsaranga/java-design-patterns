package com.pizzeria.cli.client.state.order;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.IState;

@Component
public class OrderState implements IState<Order> {
    
    private Order state;
    public String prompt;

    public OrderState() {
        this.prompt = "What would you like to order today?";
    }

    @Override
    public void setState(Order state) {
        this.state = state;
    }

    @Override
    public Order getState() {
        return state;
    }

    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
}
