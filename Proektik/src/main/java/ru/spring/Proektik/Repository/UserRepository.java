package ru.spring.Proektik.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Proektik.Models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
