package prompt.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import prompt.domain.Product;
import prompt.repository.ProductRepository;

import java.util.stream.Collectors;

@RestController
public class SimpleController {

    private final ProductRepository productRepository;
    private final ChatClient chatClient;

    private String catalog;

    public SimpleController(ProductRepository productRepository, ChatClient chatClient) {
        this.productRepository = productRepository;
        this.chatClient = chatClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    void loadCatalog() {
        this.catalog = productRepository.findAll().stream()
                .map(SimpleController::toCatalogLine)
                .collect(Collectors.joining("\n"));
        if (this.catalog.isBlank()) {
            this.catalog = "(catalog is empty)";
        }
    }

    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String message) {
        return chatClient.prompt()
                .system("""
                        You are a product expert. Answer ONLY using the product catalog:
                        """ + catalog)
                .user(message)
                .call()
                .chatResponse();
    }

    private static String toCatalogLine(Product p) {
        return "%s | %s | $%.2f | %s".formatted(
                nullSafe(p.getName()),
                nullSafe(p.getCategory()),
                p.getPrice() == null ? 0.0 : p.getPrice(),
                nullSafe(p.getDescription())
        );
    }

    private static String nullSafe(String s) {
        return s == null ? "" : s.replaceAll("[\\r\\n]+", " ").trim();
    }
}