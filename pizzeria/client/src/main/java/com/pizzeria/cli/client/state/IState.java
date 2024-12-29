package com.pizzeria.cli.client.state;

public interface IState<T> {
    
    public T getState();
    public void setState(T state);
    public void setPrompt(String prompt);
}
