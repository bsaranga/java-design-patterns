package com.pizzeria.cli.client.strategies;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.builders.CustomPizzaDtoBuilder;
import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.PizzaIngredientDto;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.cache.CacheState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class PizzaBuilderStrategy implements IStrategy<AppStateProps> {
    
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
    String prompt = "Enter command: ";
    CustomPizzaDtoBuilder customPizzaDtoBuilder = new CustomPizzaDtoBuilder();
    
    @Override
    public void execute(IState<AppStateProps> state) {
        try {
            boldDisplay.display("Let's build your own pizza!");
            System.out.println("\n");

            colorDisplay.setColor(Color.GREEN).display("Please select a crust: \n\n");

            // crust selector
            PizzaIngredientDto[] crusts = restTemplate.getForObject(pizza_server_url + "/pizza/crust", PizzaIngredientDto[].class);
            cacheState.crusts.addAll(Arrays.asList(crusts));

            if (cacheState.crusts.size() > 0) {
                for (PizzaIngredientDto pizzaIngredientDto : cacheState.crusts) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No crusts available at the moment.");
            }
            
            /* if (crusts != null) {
                //cacheState.clearCrusts();
                if (cacheState.getCrusts().size() == 0) {
                    cacheState.addCrust(Arrays.asList(crusts));
                }
                for (PizzaIngredientDto pizzaIngredientDto : crusts) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No crusts available at the moment.");
            } */

            /* while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a crust, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                if (command.toLowerCase().equals("s")) {
                    continue;
                } else {
                    try {
                        int crustId = Integer.parseInt(command);
                        var crust = cacheState.getCrust(crustId);
                        customPizzaDtoBuilder.setCrust(crust.name());
                    } catch (NumberFormatException e) {
                        colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                        continue;
                    }
                    break;
                }
            }

            colorDisplay.setColor(Color.YELLOW).display("Please select a sauce: \n\n");

            // sauce selector
            while (true) {
                PizzaIngredientDto[] sauces = restTemplate.getForObject(pizza_server_url + "/pizza/sauce", PizzaIngredientDto[].class);
                if (sauces != null) {
                    cacheState.clearSauces();
                    cacheState.addSauce(Arrays.asList(sauces));
                    for (PizzaIngredientDto pizzaIngredientDto : sauces) {
                        boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                        colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                        colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                        System.out.println("\n");
                    }
                } else {
                    System.out.println("No sauces available at the moment.");
                }
                
                colorDisplay.setColor(Color.GREEN).display("\nTo shuffle sauces, press 's'");
                colorDisplay.setColor(Color.GREEN).display("\nTo select a sauce, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                if (command.toLowerCase().equals("s")) {
                    continue;
                } else {
                    try {
                        int sauceId = Integer.parseInt(command);
                        var sauce = cacheState.getCrust(sauceId);
                        customPizzaDtoBuilder.setSauce(sauce.name());
                    } catch (NumberFormatException e) {
                        colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                        continue;
                    }
                    break;
                }
            }

            colorDisplay.setColor(Color.YELLOW).display("Please select a cheese: \n\n");

            // sauce selector
            while (true) {
                PizzaIngredientDto[] cheese = restTemplate.getForObject(pizza_server_url + "/pizza/cheese", PizzaIngredientDto[].class);
                if (cheese != null) {
                    cacheState.clearCheeses();
                    cacheState.addCheese(Arrays.asList(cheese));
                    for (PizzaIngredientDto pizzaIngredientDto : cheese) {
                        boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                        colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                        colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                        System.out.println("\n");
                    }
                } else {
                    System.out.println("No cheese available at the moment.");
                }
                
                colorDisplay.setColor(Color.GREEN).display("\nTo shuffle cheese, press 's'");
                colorDisplay.setColor(Color.GREEN).display("\nTo select a cheese, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                if (command.toLowerCase().equals("s")) {
                    continue;
                } else {
                    try {
                        int cheeseId = Integer.parseInt(command);
                        var _cheese = cacheState.getCheese(cheeseId);
                        customPizzaDtoBuilder.setCheese(_cheese.name());
                    } catch (NumberFormatException e) {
                        colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                        continue;
                    }
                    break;
                }
            } */


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during pizza creation", e);
        }
    }
}
