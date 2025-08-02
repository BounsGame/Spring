package org.skypro.counter.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

public class UserBasket {

    private final List<BasketItem> userBasket;
    private final int total;

    public UserBasket(List<BasketItem> userBasket) {
        this.userBasket = userBasket;
        this.total = userBasket.stream().mapToInt(p -> p.getProduct().getPrice() * p.getValue()).sum();
    }

    public int getTotal() {
        return total;
    }

    public List<BasketItem> getUserBasket() {
        return userBasket;
    }
}
