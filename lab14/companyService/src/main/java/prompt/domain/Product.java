package prompt.domain;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String name;

    @Column(nullable=false, length=80)
    private String category;

    @Column(nullable=false)
    private Double price;

    @Column(nullable=false, length=255)
    private String description;

    // getters/setters/constructors
    public Product() {}
    public Product(String name, String category, Double price, String description) {
        this.name = name; this.category = category; this.price = price; this.description = description;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public String getDescription() { return description; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(Double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
}