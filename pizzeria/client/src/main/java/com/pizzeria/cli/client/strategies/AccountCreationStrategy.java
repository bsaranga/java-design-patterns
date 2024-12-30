package com.pizzeria.cli.client.strategies;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.builders.UserDtoBuilder;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.StatusDto;
import com.pizzeria.cli.client.dtos.UserDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class AccountCreationStrategy implements IStrategy<AppStateProps> {

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    Console console = System.console();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    UserDtoBuilder userDtoBuilder = new UserDtoBuilder();
    String prompt = "Enter username: ";

    @Override
    public void execute(IState<AppStateProps> state) {

        try {
            colorDisplay.setColor(Color.GREEN).display("Enter your details to create the account.\n");
        
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

            // network call
            StatusDto status = restTemplate.postForObject(String.format("%s/user/register", pizza_server_url), userDto, StatusDto.class);

            if (status != null && !status.status().equals("registered")) {
                throw new RuntimeException("Account creation failed");
            }

            state.setState(AppStateProps.REGISTERED);
            state.setPrompt("Select command: ");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during account creation", e);
        }
    }
}