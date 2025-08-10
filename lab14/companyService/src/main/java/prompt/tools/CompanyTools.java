package prompt.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CompanyTools {

    private final RestClient profitClient;
    private final RestClient fxClient;

    // Explicit constructor
    public CompanyTools(@Qualifier("profitRestClient") RestClient profitClient,
                        @Qualifier("currencyRestClient") RestClient fxClient) {
        this.profitClient = profitClient;
        this.fxClient = fxClient;
    }

    @Tool(name = "getProfit", description = "Get company profit in USD by month (YYYY-MM)")
    public Map<String, Object> getProfit(String month) {
        Map<?, ?> json = profitClient.get()
                .uri(uri -> uri.path("/profit").queryParam("month", month).build())
                .retrieve()
                .body(Map.class);
        if (json == null || !json.containsKey("amountUsd")) {
            throw new RuntimeException("Profit not found for: " + month);
        }
        return Map.of("month", json.get("month"), "amountUsd", json.get("amountUsd"));
    }

    @Tool(name = "convertCurrency", description = "Convert a USD amount to another currency (e.g., EUR)")
    public Map<String, Object> convertCurrency(BigDecimal amount, String to) {
        Map<?, ?> json = fxClient.get()
                .uri(uri -> uri.path("/convert").queryParam("amount", amount).queryParam("to", to).build())
                .retrieve()
                .body(Map.class);
        if (json == null || !json.containsKey("amount")) {
            throw new RuntimeException("Conversion failed");
        }
        return Map.of("amount", json.get("amount"), "currency", json.get("currency"));
    }
}