package org.skypro.counter.Service;

import org.skypro.counter.model.basket.BasketItem;
import org.skypro.counter.model.basket.Product;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private static final Map<UUID, Integer> productBasket = new HashMap<>();

    public static void add(UUID id) {
        if (StorageService.getProductById(id).isEmpty()) {
            throw new IllegalArgumentException("такого продукта нет");
        }

        productBasket.merge(id, 1, Integer::sum);
    }

    public static UserBasket getUserBasket() {
        return new UserBasket(ProductBasket.getAll()
                .entrySet().stream().map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = StorageService.getProduct(productId);

                    return new BasketItem(product, quantity);
                }).collect(Collectors.toList()));
    }
}
