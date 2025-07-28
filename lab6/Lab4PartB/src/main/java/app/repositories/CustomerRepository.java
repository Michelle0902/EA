package app.repositories;

import app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Queue;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findByAddressZip(String zip);
    @Query("select distinct c from Customer join c.theOrders o join o.orderLines ol join ol.product p where type(p) = DVD and p.name = :name")
    List<Customer> findByOrderedDvdWithName(@Param("name") String name);
}