package accounts.repositories;

import accounts.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByAccountHolder() {
        Account account = new Account();
        account.setAccountNumber("123456");
        account.setAccountHolder("John Doe");
        account.setBalance(500.0);
        accountRepository.save(account);

        Account result = accountRepository.findByAccountHolder("John Doe");

        assertThat(result).isNotNull();
        assertThat(result.getAccountHolder()).isEqualTo("John Doe");
        assertThat(result.getAccountNumber()).isEqualTo("123456");
        assertThat(result.getBalance()).isEqualTo(500.0);
    }
}