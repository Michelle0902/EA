package prompt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prompt.repository.ProfitRepository;

import java.util.Map;

@RestController
@RequestMapping("/profit")
public class ProfitController {

    private final ProfitRepository repo;

    public ProfitController(ProfitRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<?> getProfit(@RequestParam String month) {
        return repo.findByMonth(month)
                .<ResponseEntity<?>>map(p -> ResponseEntity.ok(
                        Map.of("month", p.getMonth(), "amountUsd", p.getAmountUsd())))
                .orElse(ResponseEntity.notFound().build());
    }
}