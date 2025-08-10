package prompt.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "profit")
public class Profit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String month;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amountUsd;

    public Profit() {}
    public Profit(String month, BigDecimal amountUsd) {
        this.month = month; this.amountUsd = amountUsd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(BigDecimal amountUsd) {
        this.amountUsd = amountUsd;
    }
}

