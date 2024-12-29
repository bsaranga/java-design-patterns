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
import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Display;
import com.pizzeria.cli.client.state.order.Order;
import com.pizzeria.cli.client.state.order.OrderState;

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

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	private final Console console = System.console();

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Display display = new Display();
		Bold boldDisplay = new Bold(display);
		Colored colorDisplay = new Colored(display);
		Bg bgDisplay = new Bg(display);

		boldDisplay.display("🍕🍕🍕 Welcome to Arshvin's Pizzeria 🍕🍕🍕\n");
		colorDisplay.setColor(Color.GREEN).display("つ ◕_◕ ༽つ Authentic Italian Pizzas ‧₊˚⋅𓐐𓎩 ‧₊˚⋅\n\n");
		display.display("Available Commands:\n");
		display.display("    1. Create Account\n");
		display.display("    2. Login\n");
		display.display("    3. Exit\n\n");
		
		orderState.setState(Order.NOOP);
		String command = "";
		
		while (orderState.getState() != Order.EXIT) {
			command = console.readLine(bgDisplay.setBgColor(BgColor.YELLOW).text(orderState.prompt)).trim();

			switch (command.toLowerCase()) {
				case "1":
					colorDisplay.setColor(Color.BLUE).display("Creating account...\n");
					break;
				case "2":
					colorDisplay.setColor(Color.BLUE).display("Logging in...\n");
					break;
				case "3":
					executor.setCommand(new ExitCommand()).execute();
					break;
				default:
					log.warn("Unknown command: {}", command);
					break;
			}
		}
	}
}