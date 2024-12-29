package com.pizzeria.cli.client.builders;

import com.pizzeria.cli.client.dtos.LoginDto;

public class LoginDtoBuilder {
    
    private String username;
    private String password;

    public LoginDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginDtoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginDto build() {
        return new LoginDto(username, password);
    }
}
