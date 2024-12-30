package com.pizzeria.cli.client.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyFacade {
    
    @Autowired
    private LoginStrategy loginStrategy;

    @Autowired
    private AccountCreationStrategy accountCreationStrategy;

    @Autowired
    private LogoutStrategy logoutStrategy;

    @Autowired
    private CuratedSelectionStrategy curatedSelectionStrategy;

    @Autowired
    private CheckoutStrategy checkoutStrategy;

    public LoginStrategy getLoginStrategy() {
        return loginStrategy;
    }

    public AccountCreationStrategy getAccountCreationStrategy() {
        return accountCreationStrategy;
    }

    public LogoutStrategy getLogoutStrategy() {
        return logoutStrategy;
    }

    public CuratedSelectionStrategy getCuratedSelectionStrategy() {
        return curatedSelectionStrategy;
    }

    public CheckoutStrategy getCheckoutStrategy() {
        return checkoutStrategy;
    }
}
