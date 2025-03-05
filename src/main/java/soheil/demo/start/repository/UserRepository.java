package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
