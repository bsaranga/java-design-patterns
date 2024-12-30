package com.pizzeria.cli.client.strategies;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class OrderStatusObserverStrategy implements IStrategy<AppStateProps> {

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
            bgDisplay.setBgColor(BgColor.YELLOW).display("Fetching status...");
            notify(poll(state));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retreiving order status", e);
        }
    }

    private void notify(String status) {
        System.out.println("\n");
        bgDisplay.setBgColor(BgColor.GREEN).display("Order status: " + status);
        System.out.println("\n");
    }

    private String poll(IState<AppStateProps> state) {
        String sessionToken = state.getSessionToken();
        String url = pizza_server_url + "/orders/state";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", sessionToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

        return responseBody;
    }
    
}
