package bank;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collection;


@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application implements CommandLineRunner {
    @Autowired
    private IAccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // create 2 accounts
        accountService.createAccount(1263862, "Frank Brown");
        accountService.createAccount(4253892, "John Doe");

        // use account 1
        accountService.deposit(1263862, 240);
        accountService.deposit(1263862, 529);
        accountService.withdrawEuros(1263862, 230);

        // use account 2
        accountService.deposit(4253892, 12450);
        accountService.depositEuros(4253892, 100000);
        accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");

        // show balances
        Collection<AccountDTO> accountlist = accountService.getAllAccounts();
        for (AccountDTO accountDTO : accountlist) {
            String customerName = accountDTO.getCustomerName();
            System.out.println("Statement for Account: " + accountDTO.getAccountnumber());
            System.out.println("Account Holder: " + customerName);
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : accountDTO.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", accountDTO.getBalance());
        }
    }
}