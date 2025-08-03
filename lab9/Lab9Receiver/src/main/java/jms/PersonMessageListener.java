package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PersonMessageListener {

    private int result = 0;

    @JmsListener(destination = "testTopic")
    public void receiveCommand(String commandJson) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Command command = objectMapper.readValue(commandJson, Command.class);
            switch (command.getOperator()) {
                case "+" -> result += command.getValue();
                case "-" -> result -= command.getValue();
                case "*" -> result *= command.getValue();
                default -> System.out.println("Unknown operator: " + command.getOperator());
            }
            System.out.println("Current result: " + result);
        } catch (IOException e) {
            System.out.println("Could not parse command: " + commandJson);
        }
    }
}