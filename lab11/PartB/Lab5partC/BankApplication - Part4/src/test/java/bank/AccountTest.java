package bank;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {


    @Test
    public void testIncrement(){
        Account account = new Account();
        account.deposit(100.0);
        assertThat( account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.deposit(200.0);
        account.withdraw(50.0);
        assertThat(account.getBalance(), closeTo(150.0, 0.01));
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        Account account = new Account();
        account.deposit(100.0);
        account.withdraw(120.0);
        assertThat(account.getBalance(), closeTo(-20.0, 0.01));
//        assertThrows(IllegalArgumentException.class, () -> {
//            account.withdraw(120.0);
//        });
    }

    @Test
    public void testTransferFunds() {
        Account from = new Account(1001L);
        from.setCustomer(new Customer("Alice"));
        from.deposit(300.0);

        Account to = new Account(1002L);
        to.setCustomer(new Customer("Bob"));

        from.transferFunds(to, 100.0, "Payment");

        assertThat(from.getBalance(), closeTo(200.0, 0.01));
        assertThat(to.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testNegativeDepositThrowsException() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        Account account = new Account();
        account.deposit(100.0);
        account.deposit(150.0);
        account.withdraw(75.0);
        assertThat(account.getBalance(), closeTo(175.0, 0.01));
    }
}