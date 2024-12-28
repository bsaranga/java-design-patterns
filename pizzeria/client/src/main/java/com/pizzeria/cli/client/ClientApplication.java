package com.pizzeria.cli.client;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import com.pizzeria.cli.client.dtos.GreetingDTO;

@SpringBootApplication
@Profile("!test")
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.print("Enter 'REQ' to call API: ");
		String command = "";
		Scanner scanner = new Scanner(System.in);
		command = scanner.nextLine();

		if (command.equalsIgnoreCase("REQ")) {
			System.out.println("Calling API....");
			GreetingDTO greeting = restTemplate.getForObject("http://localhost:8080/greeting?name=Saranga", GreetingDTO.class);
			if (greeting != null) {
				log.info(greeting.toString());
			}
		} else {
			System.out.println("Invalid command....");
		}

		scanner.close();
	}
}
