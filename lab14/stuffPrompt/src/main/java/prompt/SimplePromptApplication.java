package prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import prompt.service.MyLoggingAdvisor;

import org.springframework.boot.CommandLineRunner;
import prompt.domain.Product;
import prompt.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class SimplePromptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplePromptApplication.class, args);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
		ChatClient.Builder builder = ChatClient.builder(chatModel);
		builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
		builder.defaultAdvisors(new MyLoggingAdvisor());
		return builder.build();
	}

	@Bean
	CommandLineRunner seedProducts(ProductRepository repo) {
		return args -> {
			if (repo.count() > 0) return; // avoid duplicates on restarts

			List<Product> seed = List.of(
					new Product("FurCare Shampoo", "Grooming", 14.99, "Hypoallergenic shampoo for dogs and cats."),
					new Product("JointEase Chews", "Supplements", 29.99, "Glucosamine & chondroitin for mobility."),
					new Product("HealthyBites Dental", "Treats", 9.49, "Dental treats to reduce tartar."),
					new Product("OmegaSkin Oil", "Supplements", 19.99, "Omega-3 fish oil for coat and skin."),
					new Product("CozyPaws Bed", "Bedding", 45.00, "Plush orthopedic bed for support."),
					new Product("TravelSafe Carrier", "Accessories", 59.00, "Airline-approved hard shell carrier."),
					new Product("FreshStep Litter", "Litter", 13.75, "Low-dust, odor-control clumping litter."),
					new Product("AllergyRelief Tabs", "Medicine", 16.90, "Seasonal allergy support for pets."),
					new Product("PawGuard Booties", "Accessories", 12.99, "Protects paws from heat and salt."),
					new Product("SmartFeeder Mini", "Feeding", 79.00, "Programmable auto-feeder with app.")
			);
			repo.saveAll(seed);
			System.out.println("Seeded " + seed.size() + " products.");
		};
	}
}