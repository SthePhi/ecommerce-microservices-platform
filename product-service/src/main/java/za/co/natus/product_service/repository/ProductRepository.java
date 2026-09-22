package za.co.natus.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.natus.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
