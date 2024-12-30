package com.pizzeria.cli.client.orderproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.DisplayFacade;

@Component
public class OrderStatus {
    
    @Autowired
    private Cart cart;

    Bg bgDisplay = DisplayFacade.getBgDisplay();

    public void displayNumItemsInCart() {
        int numItems = cart.getProductIds().size();
        bgDisplay.setBgColor(BgColor.CYAN).display(numItems + " items in cart");
    }
}
