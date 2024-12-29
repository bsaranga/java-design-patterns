package com.pizzeria.cli.client.state.order;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.IState;

@Component
public class AppState implements IState<AppStateProps> {
    
    private AppStateProps state;
    public String prompt;
    
    public String loggedInUser;
    public String sessionToken;

    public AppState() {
        this.prompt = "Welcome, to Arshvin's Pizzeria. Before you order, please login or create an account: ";
    }

    @Override
    public void setState(AppStateProps state) {
        this.state = state;
    }

    @Override
    public AppStateProps getState() {
        return state;
    }

    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void setSession(String username, String sessionToken) {
        this.loggedInUser = username;
        this.sessionToken = sessionToken;
    }
    
}
