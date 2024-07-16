package challenge.backend.vendingmachine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import challenge.backend.vendingmachine.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
