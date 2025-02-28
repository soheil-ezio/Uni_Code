package soheil.demo.start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.student;

@Repository
public interface studentRepository extends JpaRepository<student, Long> {
}
