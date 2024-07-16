package challenge.backend.vendingmachine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.backend.vendingmachine.model.Product;
import challenge.backend.vendingmachine.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProductsBySeller(String sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);

    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        return;

    }

}
