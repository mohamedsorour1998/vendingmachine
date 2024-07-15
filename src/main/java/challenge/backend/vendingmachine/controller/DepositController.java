package challenge.backend.vendingmachine.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.backend.vendingmachine.model.User;
import challenge.backend.vendingmachine.service.UserService;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User deposit(@RequestParam int amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user != null && "buyer".equals(user.getRole())) {
            user.setDeposit(user.getDeposit() + amount);
            userService.updateUser(user);
        }
        return user;
    }
}
