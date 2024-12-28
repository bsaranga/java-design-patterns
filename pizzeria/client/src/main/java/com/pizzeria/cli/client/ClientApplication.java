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
import com.pizzeria.cli.client.dtos.GreetingDTO;

@Profile("!test")
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.pizzaserver}")
	private String pizza_server_url;

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	private final Console console = System.console();

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String command = "";
		command = console.readLine("Enter 'REQ' to call API: ");
		if (command.equalsIgnoreCase("REQ")) {
			System.out.println("Calling API....");
			GreetingDTO greeting = restTemplate.getForObject(String.format("%s/greeting?name=Saranga", pizza_server_url), GreetingDTO.class);
			if (greeting != null) {
				log.info(greeting.toString());
			}
		} else {
			System.out.println("Invalid command....");
		}
	}
}
