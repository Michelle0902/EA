package prompt.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String message) {
        return chatClient.prompt()
                .system("""
                        Your function is to give information about pet healthcare.
                        If someone asks you about another topic, tell them you only
                        provide information about pet healthcare.
                        """)
                .user(message)
                .call()
                .chatResponse();
    }
}