package com.pizzeria.cli.client.commands;

import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.Order;

public class ExitCommand implements ICommand<Order> {
    
    @Override
    public void execute(IState<Order> state) {
        
        if (state.getState() == Order.NOOP) {
            System.out.println("We hope you will order next time! Have a nice day.");
        }

        System.out.println("Thanks, and enjoy your pizza!");

        state.setState(Order.EXIT);
    }    
}