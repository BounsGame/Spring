package org.skypro.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.counter.Service.SearchService;
import org.skypro.counter.Service.StorageService;
import org.skypro.counter.exception.NoSuchProductException;
import org.skypro.counter.model.basket.Product;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void whenNoProduct() {
        when(storageService.getMap()).thenReturn(null);

        Assertions.assertThrows(NoSuchProductException.class, () -> {
            searchService.searchByName("non-existing-product");
        });
    }

    @Test
    void whenProductFound() {
        Map<UUID, Product> testProduct = new HashMap<>();
        testProduct.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "pamelo", 150));
        testProduct.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "icecream", 150));
        when(storageService.getMap()).thenReturn(testProduct);

        Product result = searchService.searchByName("icecream");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("icecream", result.getName());
    }

    @Test
    void whenProductNotFound() {
        Map<UUID, Product> testProduct = new HashMap<>();
        testProduct.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "pamelo", 150));
        testProduct.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "icecream", 150));
        when(storageService.getMap()).thenReturn(testProduct);

        Assertions.assertThrows(NoSuchProductException.class, () -> {
            searchService.searchByName("milk");
        });
    }
}
