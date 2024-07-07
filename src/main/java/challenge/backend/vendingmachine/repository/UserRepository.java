package challenge.backend.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.backend.vendingmachine.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
