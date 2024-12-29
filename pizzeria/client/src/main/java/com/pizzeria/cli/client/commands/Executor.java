package com.pizzeria.cli.client.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.order.Order;
import com.pizzeria.cli.client.state.order.OrderState;

@Component
public class Executor {
    
    @Autowired
    private OrderState state;

    private ICommand<Order> command;

    public Executor setCommand(ICommand<Order> command) {
        this.command = command;
        return this;
    }

    public void execute() {
        command.execute(state);
    }
}
