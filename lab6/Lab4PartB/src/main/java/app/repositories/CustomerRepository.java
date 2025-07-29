package app.repositories;

import app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Queue;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAll();
    List<Customer> findByAddressZip(String zip);
    @Query("select distinct c from Customer c join c.theOrders o join o.orderlines ol join ol.product p where type(p) = DVD and p.name = :name ")
    List<Customer> findByOrderedDvdWithName(@Param("name") String name);
    List<Customer> findAllFromUSA();
    @Query("select c.firstname, c.lastname from Customer c where c.address.city = 'Amsterdam'")
    List<Object[]> findCustomerNamesFromAmsterdam();
//    @Query("select o.ordernr from Order o where o.customer.address.city = :city")
//    List<String> findOrderNumbersByCustomerCity(@Param("city") String city);
}