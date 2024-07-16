package challenge.backend.vendingmachine.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.backend.vendingmachine.model.Product;
import challenge.backend.vendingmachine.model.User;
import challenge.backend.vendingmachine.service.ProductService;
import challenge.backend.vendingmachine.service.UserService;

@RestController
@RequestMapping("/buy")
public class BuyController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public Map<String, Object> buy(@RequestParam String productId, @RequestParam int amount, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.findByUsername(principal.getName());

        if (user != null && "buyer".equals(user.getRole())) {
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                int totalCost = product.getCost() * amount;
                if (user.getDeposit() >= totalCost && product.getAmountAvailable() >= amount) {
                    product.setAmountAvailable(product.getAmountAvailable() - amount);
                    user.setDeposit(user.getDeposit() - totalCost);
                    productService.updateProduct(product);
                    userService.updateUser(user);
                    response.put("totalSpent", totalCost);
                    response.put("productsPurchased", amount);
                    response.put("change", user.getDeposit());
                }
            }
        }
        return response;
    }
}
