package client;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@SpringBootApplication
public class KafkaSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSenderApplication.class, args);
    }

    @Bean
    public ProducerFactory<String, BankOperationMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        ));
    }

    @Bean
    public KafkaTemplate<String, BankOperationMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public CommandLineRunner sendMessages(KafkaTemplate<String, BankOperationMessage> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("bank-topic", new BankOperationMessage("CREATE", 123456, "Alice", 0));
            kafkaTemplate.send("bank-topic", new BankOperationMessage("DEPOSIT", 123456, "Alice", 500));
            kafkaTemplate.send("bank-topic", new BankOperationMessage("WITHDRAW", 123456, "Alice", 200));
            System.out.println("Messages sent to Kafka.");
        };
    }
}
