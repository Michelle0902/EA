package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TraceRec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Long AccountNumber;
    private String operation;
    private double amount;

    public TraceRec() {}

    public TraceRec(double amount, String operation, Long accountNumber, LocalDateTime date) {
        this.amount = amount;
        this.operation = operation;
        this.AccountNumber = accountNumber;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TraceRec{" +
                "date=" + date +
                ", AccountNumber=" + AccountNumber +
                ", operation='" + operation + '\'' +
                ", amount=" + amount +
                '}';
    }
}
