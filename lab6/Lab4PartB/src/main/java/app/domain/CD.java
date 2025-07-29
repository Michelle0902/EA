package app.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@DiscriminatorValue("CD")
@NamedQuery(
        name = "CD.findByArtist",
        query = "SELECT c FROM CD c WHERE c.artist = :artist"
)
public class CD extends Product {
    private String artist;

    public CD() {
        super();
    }

    public CD(String name, String description, double price, String artist) {
        super(name, description, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

//    @Override
//    public String toString() {
//        return "CD{" +
//                "artist='" + artist + '\'' +
//                '}';
//    }
}
