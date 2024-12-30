package com.pizzeria.cli.client.chainedREPL.handlers;

import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.DisplayFacade;

@Component
public class InvalidHandler extends Handler {

    // This is the last link of the chain
    @Override
    public void handleRequest(REPLRequest request) {
        DisplayFacade.getBgDisplay().setBgColor(BgColor.RED).display("Invalid command, try again...");
    }
    
}
