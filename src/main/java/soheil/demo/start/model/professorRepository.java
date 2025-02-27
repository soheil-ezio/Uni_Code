package soheil.demo.start.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface professorRepository extends JpaRepository<professor, Long> {
}
