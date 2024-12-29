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

    public void setCommand(ICommand<Order> command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute(state);
    }
}
