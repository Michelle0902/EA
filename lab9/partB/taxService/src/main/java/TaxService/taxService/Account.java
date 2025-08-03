//package bank.domain;
//
//import jakarta.persistence.*;
//
//import java.util.*;
//
//@Entity
//public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true)
//    private Long accountnumber;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Collection<AccountEntry> entryList = new ArrayList<>();
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cus_id")
//    private Customer customer;
//
//    public Account() {}
//
//    public Account(long accountnr){
//        this.accountnumber = accountnr;
//    }
//
//    public long getAccountnumber() {
//        return accountnumber;
//    }
//
//    public void setAccountnumber(long accountnumber) {
//        this.accountnumber = accountnumber;
//    }
//
//    public double getBalance() {
//        return entryList.stream().mapToDouble(AccountEntry::getAmount).sum();
//    }
//
//    public void deposit(double amount){
//        AccountEntry entry = new AccountEntry(new Date(), amount, "deposit",
//                String.valueOf(this.accountnumber),
//                customer != null ? customer.getName() : "N/A");
//        entryList.add(entry);
//    }
//
//    public void withdraw(double amount){
//        AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw",
//                String.valueOf(this.accountnumber),
//                customer != null ? customer.getName() : "N/A");
//        entryList.add(entry);
//    }
//
//    public void addEntry(AccountEntry entry){
//        entryList.add(entry);
//    }
//
//    public void transferFunds(Account toAccount, double amount, String description){
//        AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description,
//                String.valueOf(toAccount.getAccountnumber()),
//                toAccount.getCustomer() != null ? toAccount.getCustomer().getName() : "N/A");
//
//        AccountEntry toEntry = new AccountEntry(new Date(), amount, description,
//                String.valueOf(this.accountnumber),
//                this.customer != null ? this.customer.getName() : "N/A");
//
//        this.addEntry(fromEntry);
//        toAccount.addEntry(toEntry);
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Collection<AccountEntry> getEntryList() {
//        return entryList;
//    }
//}