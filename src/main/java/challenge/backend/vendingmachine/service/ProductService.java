package challenge.backend.vendingmachine.service;

import java.util.List;
import java.util.Optional;

import challenge.backend.vendingmachine.model.Product;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}