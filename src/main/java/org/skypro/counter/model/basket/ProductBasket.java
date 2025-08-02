package org.skypro.counter.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private  final Map<UUID, Integer> product = new HashMap<>();

    public  void add(UUID id) {
        product.merge(id, 1, Integer::sum);
    }

    public  Map<UUID, Integer> getAll() {
        return Collections.unmodifiableMap(product);
    }


}
