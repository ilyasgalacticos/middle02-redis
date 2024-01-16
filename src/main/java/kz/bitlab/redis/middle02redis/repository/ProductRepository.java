package kz.bitlab.redis.middle02redis.repository;

import kz.bitlab.redis.middle02redis.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
