package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {
}
