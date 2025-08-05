package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountAdapter;
import bank.dto.AccountDTO;
import bank.jms.JMSSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JMSSender jmsSender;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public AccountDTO createAccount(long num, String name) {
        Customer customer = new Customer(name);
        Account account = new Account(num);
        account.setCustomer(customer);
        accountRepository.save(account);
        logger.info("Created new account {} for customer {}", num, name);
        return AccountAdapter.getAccountDTOFromAccount(account);
    }

    @Override
    public AccountDTO getAccount(long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountnumber(accountNumber);
        if (account.isPresent()) {
            logger.debug("Retrieved account {}", accountNumber);
        } else {
            logger.warn("access non-existing account {}", accountNumber);
        }
        return account.map(AccountAdapter::getAccountDTOFromAccount).orElse(null);
    }

    @Override
    public Collection<AccountDTO> getAllAccounts() {
        logger.debug("Fetching all accounts");
        return AccountAdapter.getAccountDTOsFromAccounts(accountRepository.findAll());
    }

    @Override
    public void deposit(long accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = accountRepository.findByAccountnumber(accountNumber).orElseThrow();
        account.deposit(amount);
        accountRepository.save(account);
        logger.info("Deposited {} to account {}", amount, accountNumber);
        publisher.publishEvent(new SendMessageToAccHolder(accountNumber,"deposit", amount, "The " + accountNumber + "is deposited with amount" + amount));
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountnumber(accountNumber).orElseThrow();
        account.withdraw(amount);
        accountRepository.save(account);
        logger.info("Withdrew {} from account {}", amount, accountNumber);
        publisher.publishEvent(new SendMessageToAccHolder(accountNumber, "withdraw", amount, "Account " + accountNumber + " withdrawn " + amount));
    }

    @Override
    public void depositEuros(long accountNumber, double amount) {
        if (amount >= 10000) {
            Optional<Account> acc = accountRepository.findByAccountnumber(accountNumber);
            if(acc.isPresent()){
                logger.warn("euro deposit with large amount warning: {} to account {}", amount, accountNumber);
                Account account = acc.get();
                jmsSender.sendTaxMessage(account);
            }
        }
        deposit(accountNumber, amount * 1.1);
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) {
        logger.debug("Withdrawing euros {} from account {}", amount, accountNumber);
        withdraw(accountNumber, amount * 1.1);
    }

    @Override
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account from = accountRepository.findByAccountnumber(fromAccountNumber).orElseThrow();
        Account to = accountRepository.findByAccountnumber(toAccountNumber).orElseThrow();
        from.transferFunds(to, amount, description);
        accountRepository.save(from);
        accountRepository.save(to);
        logger.info("Transferred {} from account {} to account {} - {}", amount, fromAccountNumber, toAccountNumber, description);
        publisher.publishEvent(new SendMessageToAccHolder(fromAccountNumber, "transfer fund", amount, "Account " + fromAccountNumber + " transfer " + amount));
        publisher.publishEvent(new SendMessageToAccHolder(toAccountNumber, "receive fund", amount, "Account " + toAccountNumber + " receive " + amount));
    }
}
