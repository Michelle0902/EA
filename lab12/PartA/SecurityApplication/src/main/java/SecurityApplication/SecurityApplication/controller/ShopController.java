package SecurityApplication.SecurityApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @GetMapping("/shop")
    public String shop() {
        return "Everyone can access /shop.";
    }

    @GetMapping("/orders")
    public String orders() {
        return "Only employees can access /orders.";
    }

    @GetMapping("/payments")
    public String payments() {
        return "Only finance employees can access /payments.";
    }
}