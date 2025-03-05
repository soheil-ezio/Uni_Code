package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
