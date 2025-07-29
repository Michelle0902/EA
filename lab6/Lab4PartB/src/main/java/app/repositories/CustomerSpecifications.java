package app.repositories;

import app.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications {
    public static Specification<Customer> livesInAmsterdam() {
        return (root, query, cb) -> cb.equal(root.get("address").get("city"), "Amsterdam");
    }
}
