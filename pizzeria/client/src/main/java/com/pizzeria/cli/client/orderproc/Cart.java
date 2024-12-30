package com.pizzeria.cli.client.orderproc;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Integer> productIds = new ArrayList<>();

    public void addProduct(int productId) {
        productIds.add(productId);
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void clearCart() {
        productIds.clear();
    }
}
