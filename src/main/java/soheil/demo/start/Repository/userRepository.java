package soheil.demo.start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Long> {
}
