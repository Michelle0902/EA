package mcpserver.profit;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitService {
    @Autowired
    ProfitRepository profitRepository;

    @Tool(name = "getProfitByMonth", description = "Get profit in USD for a given month from database")
    public ProfitResponse getProfit(ProfitRequest profitRequest) {
        Profit profit = profitRepository.findByMonth(profitRequest.month());
        System.out.println("ProfitTool is called with " + profitRequest.month());
        if (profit != null) {
            return new ProfitResponse(profit.getMonth(), profit.getAmount());
        } else {
            return new ProfitResponse(profitRequest.month(), 0.0);
        }
    }
}
