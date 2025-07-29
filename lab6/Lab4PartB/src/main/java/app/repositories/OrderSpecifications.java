package app.repositories;

import app.domain.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {
    public static Specification<Order> statusIsClosed() {
        return (root, query, cb) -> cb.equal(root.get("status"), "closed");
    }
}
