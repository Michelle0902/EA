package functionalCalling;

import functionalCalling.Logging.MyLoggingAdvisor;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("GEMINI_API_KEY", dotenv.get("GEMINI_API_KEY"));

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory){
        ChatClient.Builder builder = ChatClient.builder(chatModel);

        builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        builder.defaultAdvisors(new MyLoggingAdvisor());
        return builder.build();
    }
}
