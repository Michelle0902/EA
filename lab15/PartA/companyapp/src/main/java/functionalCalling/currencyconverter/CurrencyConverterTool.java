package functionalCalling.currencyconverter;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurrencyConverterTool {
    private static final Map<String, Double> exchangeRates = Map.of(
            "VND", 24500.0,
            "EUR", 0.91,
            "JPY", 142.0
    );

    @Tool(name = "convertCurrency", description = "Convert USD profit to another currency")
    public CurrencyResponse convert(CurrencyRequest currencyRequest) {
        double rate = exchangeRates.getOrDefault(currencyRequest.currencyCode(), 1.0);
        double converted = currencyRequest.amount() * rate;
        return new CurrencyResponse(currencyRequest.currencyCode(), converted);
    }
}
