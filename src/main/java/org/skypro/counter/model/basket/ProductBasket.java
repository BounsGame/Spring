package org.skypro.counter.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
public class ProductBasket {
    private static final Map<UUID, Integer> product = new HashMap<>();

    public static void add(UUID id) {
        product.merge(id, 1, Integer::sum);
    }

    public static Map<UUID, Integer> getAll() {
        return Collections.unmodifiableMap(product);
    }


}
