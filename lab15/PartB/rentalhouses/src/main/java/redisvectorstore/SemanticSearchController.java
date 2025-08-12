package redisvectorstore;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SemanticSearchController {
    @Autowired
    private SemanticSearchService semanticSearchService;

    @PostMapping("/SemanticSearch")
    public ResponseEntity<?> search(@RequestBody String text, @RequestParam int count) {
        List<String> response = semanticSearchService.findClosestMatches(text,count);
        return ResponseEntity.ok(response);
    }
}
