package challenge.backend.vendingmachine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import challenge.backend.vendingmachine.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
