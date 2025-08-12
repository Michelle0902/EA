package mcpserver.profit;

import jakarta.persistence.*;

@Entity
public class Profit {
    @Id
    @GeneratedValue
    private Long id;

    private String month;
    private double amount;

    public Profit() {}

    public Profit(String month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public String getMonth() { return month; }
    public double getAmount() { return amount; }

    public void setMonth(String month) { this.month = month; }
    public void setAmount(double amount) { this.amount = amount; }
}
