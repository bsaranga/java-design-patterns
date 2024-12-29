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
import com.pizzeria.cli.client.dtos.SessionDto;
import com.pizzeria.cli.client.dtos.StatusDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class LogoutStrategy implements IStrategy<AppStateProps> {

    @Autowired
	private AppState appState;

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    Console console = System.console();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    LoginDtoBuilder loginDtoBuilder = new LoginDtoBuilder();

    @Override
    public void execute(IState<AppStateProps> state) {
        
        DisplayFacade.getColorDisplay().setColor(Color.BLUE).display("Logging out...\n");

        SessionDto sessionDto = new SessionDto(appState.loggedInUser, appState.sessionToken);

        StatusDto status = restTemplate.postForObject(String.format("%s/user/logout", pizza_server_url), sessionDto, StatusDto.class);

        if (status != null && !status.status().equals("loggedout")) {
            throw new RuntimeException("Logout failed");
        }

        state.setState(AppStateProps.LOGGEDOUT);
    }
    
}
