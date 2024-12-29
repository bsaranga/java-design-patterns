package com.pizzeria.cli.client;

import java.io.Console;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.commands.Executor;
import com.pizzeria.cli.client.commands.ExitCommand;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.prompters.Prompter;
import com.pizzeria.cli.client.state.order.Order;
import com.pizzeria.cli.client.state.order.OrderState;
import com.pizzeria.cli.client.strategies.AccountCreationStrategy;
import com.pizzeria.cli.client.strategies.Context;
import com.pizzeria.cli.client.strategies.LoginStrategy;

@Profile("!test")
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	@Value("${app.pizzaserver}")
	private String pizza_server_url;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderState orderState;

	@Autowired
	private Executor executor;

	@Autowired
	private Context strategyContext;

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	private final Console console = System.console();

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Prompter prompter = new Prompter(DisplayFacade.getDisplay());

		DisplayFacade.getBoldDisplay().display("🍕🍕🍕 Welcome to Arshvin's Pizzeria 🍕🍕🍕\n");
		DisplayFacade.getColorDisplay().setColor(Color.GREEN).display("つ ◕_◕ ༽つ Authentic Italian Pizzas ‧₊˚⋅𓐐𓎩 ‧₊˚⋅\n\n");
		
		orderState.setState(Order.NOOP);
		String command = "";
		
		while (orderState.getState() != Order.EXIT) {
			
			prompter.DisplayPromptForState(orderState.getState());
			command = console.readLine(DisplayFacade.getBgDisplay().setBgColor(BgColor.YELLOW).text(orderState.prompt)).trim();

			if (Arrays.asList(Order.NOOP, Order.REGISTERED).contains(orderState.getState())) {
				switch (command.toLowerCase()) {
					case "1":
						command = "";
						strategyContext.setStrategy(new AccountCreationStrategy());
						strategyContext.executeStrategy();
						break;
					case "2":
						command = "";
						strategyContext.setStrategy(new LoginStrategy());
						strategyContext.executeStrategy();
						break;
					case "3":
						command = "";
						executor.setCommand(new ExitCommand()).execute();
						break;
				}
			}

			// Common case for every state
			switch (command.toLowerCase()) {
				case "":
					break;
				case "3":
					executor.setCommand(new ExitCommand()).execute();
					break;
				default:
					DisplayFacade.getBgDisplay().setBgColor(BgColor.RED).display("Invalid command, try again...");
					break;
			}
		}
	}
}