package prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prompt.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}