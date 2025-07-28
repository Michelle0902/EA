package bank.dto;

import bank.domain.Account;
import bank.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO){
        Customer cus = new Customer(accountDTO.getCustomerName());
        return new Account(accountDTO.getAccountnumber());
    }

    public static AccountDTO getAccountDTOFromAccount(Account account) {
        AccountDTO accadt = new AccountDTO(account.getAccountnumber());
        accadt.setCustomerName(account.getCustomer().getName());
        accadt.setEntryList(account.getEntryList());
        accadt.setBalance(account.getBalance());
        return accadt;
    }

    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts){
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
        for (Account account: accounts){
            accountDTOs.add(getAccountDTOFromAccount(account));
        }
        return accountDTOs;
    }
}
