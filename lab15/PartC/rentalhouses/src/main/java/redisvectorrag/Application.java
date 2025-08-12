package redisvectorrag;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));

        SpringApplication.run(Application.class, args);
    }
}
