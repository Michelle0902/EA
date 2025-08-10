package prompt.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@RestController
@RequestMapping("/convert")
public class CurrencyController {

    // USD base → others
    private static final Map<String, BigDecimal> RATES = Map.of(
            "EUR", new BigDecimal("0.91"),
            "JPY", new BigDecimal("157.50"),
            "GBP", new BigDecimal("0.77")
    );

    // GET /convert?amount=100&to=EUR → { "amount": 91.00, "currency": "EUR" }
    @GetMapping
    public Map<String, Object> convert(@RequestParam BigDecimal amount, @RequestParam String to) {
        BigDecimal rate = RATES.getOrDefault(to.toUpperCase(), BigDecimal.ONE);
        BigDecimal converted = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
        return Map.of("amount", converted, "currency", to.toUpperCase());
    }
}

