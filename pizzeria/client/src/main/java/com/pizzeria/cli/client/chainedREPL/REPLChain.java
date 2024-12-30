package com.pizzeria.cli.client.chainedREPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.handlers.EntryHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.ExitHandler;
import com.pizzeria.cli.client.chainedREPL.handlers.InvalidHandler;

@Component
public class REPLChain {
    
    @Autowired
    private EntryHandler entryHandler;

    @Autowired
    private ExitHandler exitHandler;

    @Autowired
    private InvalidHandler invalidHandler;

    public Handler getChain() {
        // define the chain and return
        entryHandler.setNextHandler(exitHandler);
        exitHandler.setNextHandler(invalidHandler);

        return entryHandler;
    }
}
