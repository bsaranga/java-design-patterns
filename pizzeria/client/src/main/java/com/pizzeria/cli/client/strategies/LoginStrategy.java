package com.pizzeria.cli.client.strategies;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.builders.LoginDtoBuilder;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.LoginDto;
import com.pizzeria.cli.client.dtos.SessionDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class LoginStrategy implements IStrategy<AppStateProps> {

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    Console console = System.console();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    LoginDtoBuilder loginDtoBuilder = new LoginDtoBuilder();
    String prompt = "Enter username: ";

    @Override
    public void execute(IState<AppStateProps> state) {

        try {
            
            colorDisplay.setColor(Color.GREEN).display("Enter your details to login.\n");
        
            String input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
            loginDtoBuilder.setUsername(input);

            prompt = "Enter password: ";
            input = console.readLine(colorDisplay.setColor(Color.BLUE).text(prompt)).trim();
            loginDtoBuilder.setPassword(input);

            LoginDto loginDto = loginDtoBuilder.build();

            DisplayFacade.getColorDisplay().setColor(Color.BLUE).display("Logging in...\n");

            SessionDto sessionDto = restTemplate.postForObject(String.format("%s/user/login", pizza_server_url), loginDto, SessionDto.class);

            if (sessionDto == null || sessionDto.sessionToken().equals("")) {
                throw new RuntimeException("Login failed");
            }

            state.setSession(loginDto.username(), sessionDto.sessionToken());
            state.setState(AppStateProps.LOGGEDIN);
            state.setPrompt("You may order now, select an option: ");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during login", e);
        }
    }
}
