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

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
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
        BasketService.add(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/shop/basket")
    public UserBasket getUserBasket() {
        return BasketService.getUserBasket();
    }

    @GetMapping("/shop/AllProduct")
    public void allProduct() {
        Product pamelo = new Product(UUID.randomUUID(), "Pamelo", 300);
        Product nachos = new Product(UUID.randomUUID(), "Nachos", 150);
        Product iceCream = new Product(UUID.randomUUID(), "Obama", 70);
        StorageService.add(pamelo);
        StorageService.add(nachos);
        StorageService.add(iceCream);
        StorageService.listOfProduct();
        ProductBasket.add(pamelo.getId());
        ProductBasket.add(nachos.getId());
        ProductBasket.add(iceCream.getId());
    }
}
