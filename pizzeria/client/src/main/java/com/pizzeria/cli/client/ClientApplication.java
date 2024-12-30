package com.pizzeria.cli.client;

import java.io.Console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.pizzeria.cli.client.chainedREPL.REPLChain;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.orderproc.Cart;
import com.pizzeria.cli.client.prompters.Prompter;
import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.state.order.AppState;

@Profile("!test")
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private AppState state;

	@Autowired
	private REPLChain replChain;

	@Autowired
	private Prompter prompter;

	@Autowired
	private Cart cart; // just to initialize

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	private final Console console = System.console();

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			DisplayFacade.getBoldDisplay().display("🍕🍕🍕 Welcome to Arshvin's Pizzeria 🍕🍕🍕\n");
			DisplayFacade.getColorDisplay().setColor(Color.GREEN).display("つ ◕_◕ ༽つ Authentic Italian Pizza ‧₊˚⋅𓐐𓎩 ‧₊˚⋅\n\n");
			
			state.setState(AppStateProps.NOOP);
			String command = "";
			
			while (state.getState() != AppStateProps.EXIT) {
			
				prompter.DisplayPromptForState();
				command = console.readLine(DisplayFacade.getBgDisplay().setBgColor(BgColor.YELLOW).text(state.prompt)).trim();
				
				REPLRequest request = new REPLRequest(command, state.getState());
				replChain.getChain().handleRequest(request);
			}
		} catch (Exception e) {
			DisplayFacade.getBgDisplay().setBgColor(BgColor.RED).display("An unexpected error occurred.");
			log.error("An unexpected error occurred.", e);
		}
	}
}