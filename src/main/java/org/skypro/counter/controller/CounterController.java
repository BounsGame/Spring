package org.skypro.counter.controller;

import org.skypro.counter.Service.BasketService;
import org.skypro.counter.Service.StorageService;
import org.skypro.counter.model.basket.Product;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.springframework.web.bind.annotation.*;
import org.skypro.counter.Service.CounterService;

import java.util.UUID;

@RestController
@RequestMapping
public class CounterController {
    private CounterService counterService;
    private BasketService basketService;
    private StorageService storageService;
    private ProductBasket productBasket;

    public CounterController(ProductBasket productBasket, StorageService storageService,
                             CounterService counterService, BasketService basketService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
        this.counterService = counterService;
        this.basketService = basketService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/counter")
    public String count() {
        counterService.countdown();
        return "Количество запросов " + counterService.getCount();
    }

    @GetMapping("/greetings")
    public String greetings(@RequestParam("name") String name,
                            @RequestParam("lastName") String lastName) {
        return "Hello " + name + " " + lastName;
    }

    @GetMapping("/shop/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.add(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/shop/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @GetMapping("/shop/AllProduct")
    public void allProduct() {
        Product pamelo = new Product(UUID.randomUUID(), "Pamelo", 300);
        Product nachos = new Product(UUID.randomUUID(), "Nachos", 150);
        Product iceCream = new Product(UUID.randomUUID(), "Obama", 70);
        Product melon = new Product(UUID.randomUUID(), " melon", 200);
        System.out.println(melon.getId());
        storageService.add(pamelo);
        storageService.add(nachos);
        storageService.add(iceCream);
        storageService.listOfProduct();
        productBasket.add(pamelo.getId());
        productBasket.add(nachos.getId());
        productBasket.add(iceCream.getId());
    }
}
