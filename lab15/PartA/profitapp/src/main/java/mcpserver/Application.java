package mcpserver;

import mcpserver.Logging.MyLoggingAdvisor;
import io.github.cdimascio.dotenv.Dotenv;
import mcpserver.profit.ProfitService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
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
    public ToolCallbackProvider tools(ProfitService profitService){
        return MethodToolCallbackProvider.builder().toolObjects(profitService).build();
    }
}
