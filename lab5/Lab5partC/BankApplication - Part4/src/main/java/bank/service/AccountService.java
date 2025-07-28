package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountAdapter;
import bank.dto.AccountDTO;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AccountDTO createAccount(long num, String name) {
        Customer customer = new Customer(name);
        Account account = new Account(num);
        account.setCustomer(customer);
        accountRepository.save(account);
        return AccountAdapter.getAccountDTOFromAccount(account);
    }

    @Override
    public AccountDTO getAccount(long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountnumber(accountNumber);
        return account.map(AccountAdapter::getAccountDTOFromAccount).orElse(null);
    }

    @Override
    public Collection<AccountDTO> getAllAccounts() {
        return AccountAdapter.getAccountDTOsFromAccounts(accountRepository.findAll());
    }

    @Override
    public void deposit(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountnumber(accountNumber).orElseThrow();
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountnumber(accountNumber).orElseThrow();
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public void depositEuros(long accountNumber, double amount) {
        deposit(accountNumber, amount * 1.1); // Example conversion
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) {
        withdraw(accountNumber, amount * 1.1);
    }

    @Override
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account from = accountRepository.findByAccountnumber(fromAccountNumber).orElseThrow();
        Account to = accountRepository.findByAccountnumber(toAccountNumber).orElseThrow();
        from.transferFunds(to, amount, description);
        accountRepository.save(from);
        accountRepository.save(to);
    }
}
