package com.pizzeria.cli.client.strategies;

import java.io.Console;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.CuratedPizzaDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.cache.CacheState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class CuratedSelectionStrategy implements IStrategy<AppStateProps> {

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    @Autowired
    private CacheState cacheState;

    Console console = System.console();
    Bg bgDisplay = DisplayFacade.getBgDisplay();
    Bold boldDisplay = DisplayFacade.getBoldDisplay();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    String prompt = "Select a pizza, or enter command: ";

    @Override
    public void execute(IState<AppStateProps> state) {
        
        try {

            CuratedPizzaDto[] pizzasArray = restTemplate.getForObject(String.format("%s/pizza/curated", pizza_server_url), CuratedPizzaDto[].class);

            if (cacheState.curatedPizzas.size() == 0) {
                cacheState.addCuratedPizzas(Arrays.asList(pizzasArray));
            }
            
            System.out.println("\n");
            if (pizzasArray != null && pizzasArray.length > 0) {
                for (CuratedPizzaDto pizza : pizzasArray) {
                    boldDisplay.display(String.format("%d. %s\n", pizza.id(), pizza.name()));
                    colorDisplay.setColor(Color.GREEN).display(String.format("    %s\n", pizza.description()));
                    colorDisplay.setColor(Color.YELLOW).display(String.format("    Ingredients: %s\n", pizza.ingredients()));
                    colorDisplay.setColor(Color.BLUE).display(String.format("    Price: $%.2f\n\n", pizza.price()));
                    System.out.println("\n");
                }
            } else {
                colorDisplay.setColor(Color.RED).display("No pizzas available at the moment. Please try again later.");
            }
            
            state.setPrompt(prompt);
            state.setState(AppStateProps.SELECTIONMODE);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during pizza selection", e);
        }
    }
    
}
