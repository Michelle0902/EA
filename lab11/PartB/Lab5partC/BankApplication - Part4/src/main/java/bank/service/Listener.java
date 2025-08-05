package bank.service;

import bank.domain.TraceRec;
import bank.repository.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@EnableAsync
public class Listener {
    @Autowired
    TraceRecordRepository traceRecordRepository;
    @Async
    @EventListener
    public void onEvent(SendMessageToAccHolder event) {
        LocalDateTime now = LocalDateTime.now();
        TraceRec traceRec = new TraceRec(event.getAmount(), event.getOperation(), event.getAccountNumber(), now);
        traceRecordRepository.save(traceRec);
        System.out.println("received event :" + event.getMessage());
    }
}
