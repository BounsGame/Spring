package org.skypro.counter.model.basket;

import org.springframework.web.context.annotation.SessionScope;

public class BasketItem {
    private final Product product;
    private final int value;

    public Product getProduct() {
        return product;
    }

    public int getValue() {
        return value;
    }

    public BasketItem(Product product, int value) {
        this.product = product;
        this.value = value;
    }
}
