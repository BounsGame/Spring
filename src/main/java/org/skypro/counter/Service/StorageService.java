package org.skypro.counter.Service;

import org.skypro.counter.model.basket.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {
    private static Map<UUID, Product> availableProducts = new HashMap<>();

    public static Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    public static Product getProduct(UUID id) {
        return availableProducts.get(id);
    }

    public static void add(Product product) {
        availableProducts.put(product.getId(), product);
    }

    public static void listOfProduct() {
        System.out.println(availableProducts);
    }

}
