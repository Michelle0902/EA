package bank.repository;

import bank.domain.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntryRepository extends JpaRepository<AccountEntry, Long> {
}
