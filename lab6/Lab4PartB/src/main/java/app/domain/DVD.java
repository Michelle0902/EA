package app.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DVD")
public class DVD extends Product {
    private String genre;

    public DVD() {
        super();
    }

    public DVD(String name, String description, double price, String genre) {
        super(name, description, price);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
