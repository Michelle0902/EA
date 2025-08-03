package bank.jms;

import bank.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendTaxMessage(Account account) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(account);
			jmsTemplate.convertAndSend("taxQueue", json);
			System.out.println("Sent account to TaxService: " + json);
		} catch (Exception e) {
			System.out.println("Failed to send account message: " + e.getMessage());
		}
	}
}