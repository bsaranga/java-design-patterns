package com.pizzeria.cli.client.strategies;

import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.Order;

@Component
public class LoginStrategy implements IStrategy<Order> {

    @Override
    public void execute(IState<Order> state) {
        DisplayFacade.getColorDisplay().setColor(Color.BLUE).display("Logging in...\n");
        state.setState(Order.LOGGEDIN);
        state.setPrompt("You may order now, select an option\n");
    }
}
