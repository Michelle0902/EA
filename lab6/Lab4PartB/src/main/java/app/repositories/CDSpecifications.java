package app.repositories;

import app.domain.CD;
import org.springframework.data.jpa.domain.Specification;

public class CDSpecifications {
    public static Specification<CD> byArtistAndPriceGreaterThan(String artist, double price) {
        return (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("artist"), artist),
                        cb.greaterThan(root.get("price"), price)
                );
    }
}
