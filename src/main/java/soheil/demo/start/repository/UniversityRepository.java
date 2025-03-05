package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
