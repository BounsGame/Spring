package org.skypro.counter.Service;

import org.skypro.counter.model.basket.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {
    private Map<UUID, Product> availableProducts = new HashMap<>();

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    public Product getProduct(UUID id) {
        return availableProducts.get(id);
    }

    public void add(Product product) {
        availableProducts.put(product.getId(), product);
    }

    public void listOfProduct() {
        System.out.println(availableProducts);
    }

}
