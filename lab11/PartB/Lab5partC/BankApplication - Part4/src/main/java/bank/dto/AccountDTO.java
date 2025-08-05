package bank.dto;

import bank.domain.AccountEntry;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDTO {
    private long accountnumber;
    private String customerName;
    private Collection<AccountEntry> entryList = new ArrayList<>();
    private double balance;

    public AccountDTO(long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public long getAccountnumber() {
        return accountnumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Collection<AccountEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(Collection<AccountEntry> entryList) {
        this.entryList = entryList;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountnumber=" + accountnumber +
                ", customerName='" + customerName + '\'' +
                ", entryList=" + entryList +
                ", balance=" + balance +
                '}';
    }
}