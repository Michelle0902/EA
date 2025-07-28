package app.repositories;

import app.domain.CD;
import app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Long> {
    List<CD> findByArtistAndPriceLessThan(String artist, double price);
}