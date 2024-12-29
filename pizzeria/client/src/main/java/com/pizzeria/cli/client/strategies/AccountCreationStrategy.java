package com.pizzeria.cli.client.strategies;

import java.io.Console;

import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.builders.UserDtoBuilder;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.UserDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.Order;

@Component
public class AccountCreationStrategy implements IStrategy<Order> {

    Console console = System.console();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    UserDtoBuilder userDtoBuilder = new UserDtoBuilder();
    String prompt = "Enter username: ";

    @Override
    public void execute(IState<Order> state) {
        colorDisplay.setColor(Color.BLUE).display("Enter your details to create the account.\n");
        
        String input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
        userDtoBuilder.setUsername(input);

        prompt = "Enter password: ";
        input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
        userDtoBuilder.setPassword(input);

        prompt = "Enter email: ";
        input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
        userDtoBuilder.setEmail(input);

        prompt = "Enter address: ";
        input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
        userDtoBuilder.setAddress(input);

        prompt = "Enter phone: ";
        input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
        userDtoBuilder.setPhone(input);

        UserDto userDto = userDtoBuilder.build();
        
        state.setState(Order.REGISTERED);
        state.setPrompt("Enter command...\n");
    }
}