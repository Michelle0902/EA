package mcpserver.profit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profit")
public class ProfitController {
    @Autowired
    ProfitRepository profitRepository;

    @PostMapping("/init")
    public String initProfitData() {
        profitRepository.save(new Profit("January", 45000.0));
        profitRepository.save(new Profit("February", 35000.0));
        profitRepository.save(new Profit("March", 52000.0));
        profitRepository.save(new Profit("April", 47000.0));
        profitRepository.save(new Profit("May", 61000.0));
        profitRepository.save(new Profit("June", 39000.0));
        return "Profit data initialized!";
    }

    @GetMapping
    public ResponseEntity<?> getProductList(){
        return new ResponseEntity<>(profitRepository.findAll(), HttpStatus.OK);
    }
}
