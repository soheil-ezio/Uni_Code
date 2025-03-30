package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
