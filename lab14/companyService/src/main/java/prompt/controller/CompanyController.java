package prompt.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import prompt.tools.CompanyTools;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final ChatClient chat;
    private final CompanyTools companyTools;

    public CompanyController(ChatClient chat, CompanyTools companyTools) {
        this.chat = chat;
        this.companyTools=companyTools;
    }

    // Example:
    // GET /company/ask?q=What was profit in 2025-01 in EUR?
    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return chat.prompt()
                .system("""
        You are CompanyQ&A. If the user asks for profit by month, call the getProfit tool.
        If they want a different currency, also call convertCurrency with the USD amount.
        Reply with a short, clear answer.
      """)
                .tools(companyTools)
                .user(q)
                .call()
                .content();
    }
}