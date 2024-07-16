package challenge.backend.vendingmachine.service;

import java.util.List;
import java.util.Optional;

import challenge.backend.vendingmachine.model.User;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String id);

    User findByUsername(String username);
}
