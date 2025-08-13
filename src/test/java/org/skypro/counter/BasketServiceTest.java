package org.skypro.counter;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.counter.Service.BasketService;
import org.skypro.counter.Service.StorageService;
import org.skypro.counter.exception.NoSuchProductException;
import org.skypro.counter.model.basket.BasketItem;
import org.skypro.counter.model.basket.Product;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matchers.*;
import org.assertj.core.api.Assertions.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addError() {
        UUID id = UUID.randomUUID();

        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.add(id));
    }

    @Test
    void addSuccessful() {
        UUID id = UUID.randomUUID();
        Product testProduct = new Product(id, "test", 150);
        Optional<Product> optionalProduct = Optional.of(testProduct);
        when(storageService.getProductById(id)).thenReturn(optionalProduct);
        doNothing().when(productBasket).add(id);

        basketService.add(id);
        Mockito.verify(productBasket).add(id);
    }

    @Test
    void emptyGetUserBasket() {
        when(productBasket.getAll()).thenReturn(null);

        Assertions.assertNull(basketService.getUserBasket());
    }

    @Test
    void fullGetUserBasket() {
        Map<UUID, Integer> map = new HashMap<>();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        map.put(id1, 1);
        map.put(id2, 2);
        Product product1 = new Product(id1, "name1", 150);
        Product product2 = new Product(id2, "name2", 150);
        BasketItem basketItem1 = new BasketItem(product1, 1);
        BasketItem basketItem2 = new BasketItem(product2, 2);
        List<BasketItem> basketItemsList = new ArrayList<>();
        basketItemsList.add(basketItem2);
        basketItemsList.add(basketItem1);
        UserBasket userBasket = new UserBasket(basketItemsList);
        when(productBasket.getAll()).thenReturn(map);
        when(storageService.getProduct(id2)).thenReturn(product2);
        when(storageService.getProduct(id1)).thenReturn(product1);

        assertThat(userBasket).usingRecursiveComparison().isEqualTo(basketService.getUserBasket());
    }


}
