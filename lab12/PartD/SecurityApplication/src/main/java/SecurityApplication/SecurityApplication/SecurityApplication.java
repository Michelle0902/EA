package SecurityApplication.SecurityApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	private final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Public
		makeGetRestCall("http://localhost:8080/shop", null, null);

		// /orders – sales only
		makeGetRestCall("http://localhost:8080/orders", "bob", "bob123");   // expect 200
		makeGetRestCall("http://localhost:8080/orders", "mary", "mary123"); // expect 403

		// /payments – finance only
		makeGetRestCall("http://localhost:8080/payments", "bob", "bob123");   // expect 403
		makeGetRestCall("http://localhost:8080/payments", "mary", "mary123"); // expect 200
	}

	private void makeGetRestCall(String url, String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		// Either omit Accept or use text/plain since your controller returns String
		headers.set(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE);

		if (username != null && password != null) {
			headers.setBasicAuth(username, password);
		}

		HttpEntity<Void> request = new HttpEntity<>(headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			System.out.printf("GET %s as %s -> %d%n", url, userLabel(username), response.getStatusCode().value());
			if (response.hasBody() && response.getBody() != null && !response.getBody().isBlank()) {
				System.out.println("Body: " + response.getBody());
			}
		} catch (Exception e) {
			System.out.printf("GET %s as %s -> ERROR: %s%n", url, userLabel(username), e.getMessage());
		}
		System.out.println("----");
	}

	private String userLabel(String u) {
		return u == null ? "anonymous" : u;
	}
}