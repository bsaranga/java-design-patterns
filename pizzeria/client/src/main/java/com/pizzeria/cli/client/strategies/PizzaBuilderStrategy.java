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
import com.pizzeria.cli.client.dtos.CustomPizzaDto;
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
            cacheState.clearCrusts();
            cacheState.clearSauces();
            cacheState.clearCheeses();
            cacheState.clearToppings();
            cacheState.clearSeasonings();

            boldDisplay.display("Let's build your own pizza!");
            System.out.println("\n");

            colorDisplay.setColor(Color.GREEN).display("Please select a crust: \n\n");

            // crust selector
            PizzaIngredientDto[] crusts = restTemplate.getForObject(pizza_server_url + "/pizza/crust", PizzaIngredientDto[].class);
            cacheState.crusts.addAll(Arrays.asList(crusts));

            boldDisplay.display("Crusts: \n");
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

            // LOOP Until selected
            while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a crust, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                try {
                    int crustId = Integer.parseInt(command);
                    var crust = cacheState.getCrust(crustId);
                    customPizzaDtoBuilder.setCrust(crust.name());
                    break;
                } catch (NumberFormatException e) {
                    colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                    continue;
                }
            }

            colorDisplay.setColor(Color.GREEN).display("----------------------------------\n\n");

            // sauce selector
            PizzaIngredientDto[] sauces = restTemplate.getForObject(pizza_server_url + "/pizza/sauce", PizzaIngredientDto[].class);
            cacheState.sauces.addAll(Arrays.asList(sauces));

            boldDisplay.display("Sauces: \n");
            if (cacheState.sauces.size() > 0) {
                for (PizzaIngredientDto pizzaIngredientDto : cacheState.sauces) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No sauces available at the moment.");
            }

            // LOOP Until selected
            while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a sauce, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                try {
                    int sauceId = Integer.parseInt(command);
                    var sauce = cacheState.getSauce(sauceId);
                    customPizzaDtoBuilder.setSauce(sauce.name());
                    break;
                } catch (NumberFormatException e) {
                    colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                    continue;
                }
            }

            colorDisplay.setColor(Color.GREEN).display("----------------------------------\n\n");
            
            // cheese selector
            PizzaIngredientDto[] cheese = restTemplate.getForObject(pizza_server_url + "/pizza/cheese", PizzaIngredientDto[].class);
            cacheState.cheeses.addAll(Arrays.asList(cheese));

            boldDisplay.display("Cheese: \n");
            if (cacheState.cheeses.size() > 0) {
                for (PizzaIngredientDto pizzaIngredientDto : cacheState.cheeses) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No cheese available at the moment.");
            }

            // LOOP Until selected
            while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a cheese, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                try {
                    int cheeseId = Integer.parseInt(command);
                    var _cheese = cacheState.getCheese(cheeseId);
                    customPizzaDtoBuilder.setCheese(_cheese.name());
                    break;
                } catch (NumberFormatException e) {
                    colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                    continue;
                }
            }

            colorDisplay.setColor(Color.GREEN).display("----------------------------------\n\n");

            // toppings selector
            PizzaIngredientDto[] toppings = restTemplate.getForObject(pizza_server_url + "/pizza/toppings", PizzaIngredientDto[].class);
            cacheState.toppings.addAll(Arrays.asList(toppings));

            boldDisplay.display("Toppings: \n");
            if (cacheState.toppings.size() > 0) {
                for (PizzaIngredientDto pizzaIngredientDto : cacheState.toppings) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No toppings available at the moment.");
            }

            // LOOP Until selected
            while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a topping, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                try {
                    int toppingId = Integer.parseInt(command);
                    var _topping = cacheState.getTopping(toppingId);
                    customPizzaDtoBuilder.setTopping(_topping.name());
                    break;
                } catch (NumberFormatException e) {
                    colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                    continue;
                }
            }

            colorDisplay.setColor(Color.GREEN).display("----------------------------------\n\n");

            // seasoning selector
            PizzaIngredientDto[] seasoning = restTemplate.getForObject(pizza_server_url + "/pizza/seasoning", PizzaIngredientDto[].class);
            cacheState.seasonings.addAll(Arrays.asList(seasoning));

            boldDisplay.display("Seasoning: \n");
            if (cacheState.seasonings.size() > 0) {
                for (PizzaIngredientDto pizzaIngredientDto : cacheState.seasonings) {
                    boldDisplay.display(pizzaIngredientDto.id() + ". " + pizzaIngredientDto.name() + "\n");
                    colorDisplay.setColor(Color.YELLOW).display(pizzaIngredientDto.description() + "\n");
                    colorDisplay.setColor(Color.CYAN).display("Price: $" + pizzaIngredientDto.price() + "\n");
                    System.out.println("\n");
                }
            } else {
                System.out.println("No seasoning available at the moment.");
            }

            // LOOP Until selected
            while (true) {
                colorDisplay.setColor(Color.GREEN).display("\nTo select a seasoning, enter the number...\n");
                String command = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Command: ")).trim();
                
                try {
                    int seasoningId = Integer.parseInt(command);
                    var _seasoning = cacheState.getSeasoning(seasoningId);
                    customPizzaDtoBuilder.setSeasoning(_seasoning.name());
                    break;
                } catch (NumberFormatException e) {
                    colorDisplay.setColor(Color.RED).display("Invalid input. Please enter a valid number.\n");
                    continue;
                }
            }

            customPizzaDtoBuilder.setPrice(Math.random() * 10 + 6.50);
            CustomPizzaDto customPizzaDto = customPizzaDtoBuilder.build();
            boldDisplay.display("Here's your custom made pizza\n");

            colorDisplay.setColor(Color.GREEN).display("Crust: " + customPizzaDto.crust() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Sauce: " + customPizzaDto.sauce() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Cheese: " + customPizzaDto.cheese() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Topping: " + customPizzaDto.topping() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Seasoning: " + customPizzaDto.seasoning() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Price: $" + customPizzaDto.price() + "\n\n");

            boldDisplay.display("What would you name it?\n");
            String name = console.readLine(colorDisplay.setColor(Color.GREEN).text("Enter Name: ")).trim();
            customPizzaDtoBuilder.setName(name);

            customPizzaDto = customPizzaDtoBuilder.build();
            System.out.println("\n");
            boldDisplay.display("Here's your final pizza!\n");

            colorDisplay.setColor(Color.GREEN).display("Name: " + customPizzaDto.name() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Crust: " + customPizzaDto.crust() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Sauce: " + customPizzaDto.sauce() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Cheese: " + customPizzaDto.cheese() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Topping: " + customPizzaDto.topping() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Seasoning: " + customPizzaDto.seasoning() + "\n");
            colorDisplay.setColor(Color.GREEN).display("Price: $" + customPizzaDto.price() + "\n\n");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during pizza creation", e);
        }
    }
}
