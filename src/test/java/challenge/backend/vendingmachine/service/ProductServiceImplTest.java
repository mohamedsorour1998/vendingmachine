package challenge.backend.vendingmachine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import challenge.backend.vendingmachine.model.Product;
import challenge.backend.vendingmachine.repository.ProductRepository;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("SpeedCola");
        product.setAmountAvailable(1);
        product.setCost(10);
        product.setSellerId("seller1");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("SpeedCola", createdProduct.getProductName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "productId";
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductName("SpeedCola");
        Product product2 = new Product();
        product2.setProductName("FastDrink");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProductsBySeller() {
        String sellerId = "seller1";
        Product product1 = new Product();
        product1.setProductName("SpeedCola");
        product1.setSellerId(sellerId);
        Product product2 = new Product();
        product2.setProductName("FastDrink");
        product2.setSellerId(sellerId);

        when(productRepository.findBySellerId(sellerId)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProductsBySeller(sellerId);

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findBySellerId(sellerId);
    }

    @Test
    void testGetProductById() {
        String productId = "productId";
        Product product = new Product();
        product.setProductName("SpeedCola");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals("SpeedCola", foundProduct.get().getProductName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testUpdateProduct() {
        String productId = "productId";
        Product product = new Product();
        product.setId(productId);
        product.setProductName("SpeedCola");
        product.setAmountAvailable(1);
        product.setCost(10);
        product.setSellerId("seller1");

        // Mock behavior
        when(productRepository.existsById(productId)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the method to be tested
        Product updatedProduct = productService.updateProduct(product);

        // Verify results
        assertNotNull(updatedProduct);
        assertEquals("SpeedCola", updatedProduct.getProductName());

        // Verify interactions with the mock
        verify(productRepository, times(1)).save(product);
    }

}
