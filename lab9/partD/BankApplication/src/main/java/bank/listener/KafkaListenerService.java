
package bank.listener;

import bank.domain.BankOperationMessage;
import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    private IAccountService accountService;

    @KafkaListener(topics = "bank-topic", groupId = "bank-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(BankOperationMessage message) {
        switch (message.getOperationType().toUpperCase()) {
            case "CREATE":
                accountService.createAccount(message.getAccountNumber(), message.getCustomerName());
                break;
            case "DEPOSIT":
                accountService.deposit(message.getAccountNumber(), message.getAmount());
                break;
            case "WITHDRAW":
                accountService.withdraw(message.getAccountNumber(), message.getAmount());
                break;
            default:
                System.err.println("Unknown operation: " + message.getOperationType());
        }
    }
}
