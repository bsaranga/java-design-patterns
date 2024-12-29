package com.pizzeria.cli.client.builders;

import com.pizzeria.cli.client.dtos.UserDto;

public class UserDtoBuilder {
    
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;

    public UserDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserDtoBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserDto build() {
        return new UserDto(username, password, email, address, phone);
    }
}
