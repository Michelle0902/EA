package app.repositories;

import app.domain.CD;
import app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Long>, JpaSpecificationExecutor<CD> {
    List<CD> findByArtistAndPriceLessThan(String artist, double price);
    List<CD> findByArtist(@Param("artist") String artist);
    @Query("select c from CD c where c.artist = :artist and c.price > :price")
    List<CD> findCDsByArtistAndPriceGreaterThan(@Param("artist") String artist, @Param("price") double price);
    @Query(value = "SELECT * FROM product WHERE product_type = 'CD' AND artist = 'U2'", nativeQuery = true)
    List<CD> findCDsByU2();
}