package functionalCalling.company;

import functionalCalling.currencyconverter.CurrencyConverterTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final ChatClient chatClient;
    @Autowired
    private CurrencyConverterTool currencyConverterTool;
//    @Autowired
//    private ProfitTool profitTool;

    public CompanyController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools){
        this.chatClient = chatClientBuilder
                .defaultSystem("Please prioritise context information for answering questions")
                .defaultToolCallbacks(tools)
                .build();

    }

    @GetMapping("/profit")
    public String askCompanyProfit(@RequestParam(defaultValue = "January") String month){
        return chatClient.prompt()
                .user(u -> u.text("What was my company's profit in {month}?").param("month", month))
                .call()
                .content();
    }

    @GetMapping("/profit-in-currency")
    public String askProfitInCurrency(@RequestParam(defaultValue = "January") String month,
                                      @RequestParam(defaultValue = "EUR") String currency){
        return chatClient.prompt()
                .tools(currencyConverterTool)
                .user(u -> u.text("What was my company's profit in {month} in {currency}?")
                        .param("month", month).param("currency", currency))
                .call()
                .content();
    }
}
