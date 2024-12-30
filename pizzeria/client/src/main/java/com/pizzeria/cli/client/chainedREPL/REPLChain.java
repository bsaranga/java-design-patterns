package com.pizzeria.cli.client.chainedREPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.handlers.EntryHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.ExitHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.InvalidHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.PizzaDisplayHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.StateHandler;

@Component
public class REPLChain {
    
    @Autowired
    private EntryHandler entryHandler;

    @Autowired
    private ExitHandler exitHandler;

    @Autowired
    private InvalidHandler invalidHandler;

    @Autowired
    private PizzaDisplayHandler pizzaDisplayHandler;

    @Autowired
    private StateHandler stateHandler;

    /*
     * Rules:
     * Invalid handler should be the last handler in the chain
     * Exit handler should be the second last handler in the chain
     * State handler should be the third last handler in the chain
    */
    public Handler getChain() {
        // define the chain and return
        entryHandler.setNextHandler(pizzaDisplayHandler);
        pizzaDisplayHandler.setNextHandler(stateHandler);
        stateHandler.setNextHandler(exitHandler);
        exitHandler.setNextHandler(invalidHandler);

        return entryHandler;
    }
}
