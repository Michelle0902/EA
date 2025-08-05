package bank.repository;

import bank.domain.TraceRec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceRecordRepository extends JpaRepository<TraceRec, Long> {
}
