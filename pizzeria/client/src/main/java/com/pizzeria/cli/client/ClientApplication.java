package com.pizzeria.cli.client;

import java.io.Console;
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
import com.pizzeria.cli.client.state.order.Order;
import com.pizzeria.cli.client.state.order.OrderState;

@Profile("!test")
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.pizzaserver}")
	private String pizza_server_url;

	@Autowired
	private OrderState orderState;

	@Autowired
	private Executor executor;

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	private final Console console = System.console();

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		orderState.setState(Order.NOOP);
		String command = "";
		
		while (!command.equalsIgnoreCase("exit")) {
			command = console.readLine(orderState.prompt).trim();

			switch (command.toLowerCase()) {
				case "order":
					log.info("Making order...");
					orderState.setState(Order.ORDERED);
					break;
				case "cancel":
					log.info("Cancelling order...");
					orderState.setState(Order.CANCELLED);
					break;
				case "status":
					log.info("Checking order status...");
					log.info("Order status: {}", orderState.getState());
					break;
				case "exit":
					executor.setCommand(new ExitCommand());
					executor.executeCommand();
					break;
				default:
					log.warn("Unknown command: {}", command);
					break;
			}
		}
	}
}