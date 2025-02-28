package soheil.demo.start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.professor;

@Repository
public interface professorRepository extends JpaRepository<professor, Long> {
}
