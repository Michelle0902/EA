package app.repositories;

import app.domain.Customer;
import app.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("select o.ordernr from Order o where o.status = 'closed'")
    List<String> findClosedOrderNumbers();
    @Query("select o.ordernr from Order o where o.customer.address.city = :city")
    List<String> findOrderNumbersByCustomerCity(@Param("city") String city);
}
