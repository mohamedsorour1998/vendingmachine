package challenge.backend.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.backend.vendingmachine.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
