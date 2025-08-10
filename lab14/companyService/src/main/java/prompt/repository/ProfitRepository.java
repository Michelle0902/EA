package prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prompt.domain.Profit;
import java.util.Optional;

public interface ProfitRepository extends JpaRepository<Profit, Long> {
    Optional<Profit> findByMonth(String month);
}

