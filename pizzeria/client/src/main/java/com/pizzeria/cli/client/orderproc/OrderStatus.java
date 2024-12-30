package com.pizzeria.cli.client.orderproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.dtos.OrderDto;

@Component
public class OrderStatus {
    
    @Autowired
    private Cart cart;

    private OrderType orderType;

    private DeliveryMethod deliveryMethod;

    Bg bgDisplay = DisplayFacade.getBgDisplay();

    public void displayNumItemsInCart() {
        int numItems = cart.getProductIds().size();
        bgDisplay.setBgColor(BgColor.CYAN).display(numItems + " items in cart");
        System.out.println('\n');
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public OrderDto getOrderDto() {
        return new OrderDto(orderType, deliveryMethod, cart.getProductIds());
    }
}
