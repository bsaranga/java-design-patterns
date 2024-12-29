package com.pizzeria.cli.client.commands;

import com.pizzeria.cli.client.state.IState;

public interface ICommand<T> {
    void execute(IState<T> state);
}
