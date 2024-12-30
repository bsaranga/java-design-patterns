package com.pizzeria.cli.client.strategies;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class FeedbackStrategy implements IStrategy<AppStateProps> {

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    Console console = System.console();
    Bg bgDisplay = DisplayFacade.getBgDisplay();
    Bold boldDisplay = DisplayFacade.getBoldDisplay();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    String prompt = "Enter command: ";

    @Override
    public void execute(IState<AppStateProps> state) {
        try {
            String feedback = console.readLine(boldDisplay.text("Please provide feedback for our service: "));
            //restTemplate.postForObject(String.format("%s/feedback", pizza_server_url), feedback, String.class);
            colorDisplay.setColor(Color.GREEN).display("Thank you for your feedback!\n");
            colorDisplay.setColor(Color.GREEN).display("Please rate us out of 5\n");
            int rating = Integer.parseInt(console.readLine(boldDisplay.text("Rating: ")));
            //restTemplate.postForObject(String.format("%s/rating", pizza_server_url), rating, String.class);
            colorDisplay.setColor(Color.GREEN).display("Thank you for your rating!\n");

            state.setState(AppStateProps.LOGGEDIN);
            state.setPrompt(prompt);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during feedback", e);
        }
    }
    
}
