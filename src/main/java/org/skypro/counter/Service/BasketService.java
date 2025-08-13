package org.skypro.counter.Service;

import org.skypro.counter.exception.NoSuchProductException;
import org.skypro.counter.model.basket.BasketItem;
import org.skypro.counter.model.basket.Product;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private ProductBasket productBasket;
    private StorageService storageService;

    public BasketService(StorageService storageService, ProductBasket productBasket1) {
        this.storageService = storageService;
        this.productBasket = productBasket1;
    }

    public void add(UUID id) {
        try {
            if (storageService.getProductById(id).isEmpty()) {
                throw new NoSuchProductException();
            }
        } catch (NullPointerException e) {
            throw new NoSuchProductException();
        }

        productBasket.add(id);
    }

    public UserBasket getUserBasket() {
        try {
            if (productBasket.getAll().isEmpty()) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            return null;
        }
        return new UserBasket(productBasket.getAll()
                .entrySet().stream().map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProduct(productId);

                    return new BasketItem(product, quantity);
                }).collect(Collectors.toList()));
    }

    public ProductBasket getProductBasket() {
        return productBasket;
    }
}
