package org.skypro.counter.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

public class Product {
    private final String name;
    private final UUID id;
    private int price;

    public Product(UUID id, String name, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID = " + id + " " + name;
    }

}
