package com.pizzeria.cli.client.strategies;

import com.pizzeria.cli.client.state.IState;

public interface IStrategy<T> {
    void execute(IState<T> state);
}
