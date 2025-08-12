package mcpserver.profit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitRepository extends JpaRepository<Profit, Long> {
    public Profit findByMonth(String month);
}
