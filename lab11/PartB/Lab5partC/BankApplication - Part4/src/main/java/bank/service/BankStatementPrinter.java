package bank.service;

import bank.domain.Account;
import bank.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class BankStatementPrinter {

    @Autowired
    private IAccountService accountService;
    @Scheduled(fixedRate = 10000)
    public void printAcc() {
        Collection<AccountDTO> accounts = accountService.getAllAccounts();
        accounts.forEach(System.out::println);
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currenttime = timeFormatter.format(date);
        System.out.println("This task runs at " + currenttime);
    }
}
