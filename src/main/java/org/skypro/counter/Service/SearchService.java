package org.skypro.counter.Service;

import org.skypro.counter.exception.NoSuchProductException;
import org.skypro.counter.model.basket.Product;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Product searchByName(String name) {
        try {
            if (storageService.getMap().isEmpty()) {
                throw new NoSuchProductException();
            }
        } catch (NullPointerException e) {
            throw new NoSuchProductException();
        }
        Product searchedProduct = storageService.getMap().values().stream()
                .filter(product -> product.getName().contains(name))
                .findFirst().orElse(null);
        if (searchedProduct != null) {
            return searchedProduct;
        } else {
            throw new NoSuchProductException();
        }

    }
}
