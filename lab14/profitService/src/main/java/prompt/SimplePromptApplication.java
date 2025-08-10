package prompt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import prompt.domain.Profit;
import prompt.repository.ProfitRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class SimplePromptApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimplePromptApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(ProfitRepository repo) {
		return args -> {
			if (repo.count() > 0) return;
			repo.saveAll(List.of(
					new Profit("2025-01", new BigDecimal("12500.00")),
					new Profit("2025-02", new BigDecimal("13150.50")),
					new Profit("2025-03", new BigDecimal("11890.75"))
			));
		};
	}
}
