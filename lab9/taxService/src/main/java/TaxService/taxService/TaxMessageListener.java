package TaxService.taxService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TaxMessageListener {

    @JmsListener(destination = "taxQueue")
    public void receive(String jsonMessage) {
        System.out.println("Received JSON string of Account in which it is deposited for over 10000 Euros: " + jsonMessage);
    }
}